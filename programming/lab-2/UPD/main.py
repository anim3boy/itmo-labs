import requests
from bs4 import BeautifulSoup
from requests.models import Response
import shutil
import os

ID = 3

log = lambda x: print(decorator(x))
inp = lambda x: input("[?] %s:\n  > " % x)


def decorator(text):
    if type(text) is tuple:
        if text[0] == "ok":
            return "[+] %s" % text[1]
        if text[0] == "err":
            return "[-] %s" % text[1]
    else:
        return "[!] %s" % text


def get_pokemons():

    cookies = {
        "JSESSIONID": "dlwkAuzxuSpIJyF19c8Bi6IxHKoAmXl7BOV84f6r.lportal",
    }

    params = (
        ("p_p_id", "pbportletlab1_WAR_pbportlet"),
        ("p_p_lifecycle", "1"),
        ("p_p_state", "normal"),
        ("p_p_mode", "view"),
        ("_pbportletlab1_WAR_pbportlet_javax.portlet.action", "generate"),
        ("p_auth", "3px2rhP8"),
    )

    data = {"var": str(ID)}

    response = requests.post(
        "https://se.ifmo.ru/courses/programming",
        params=params,
        cookies=cookies,
        data=data,
    )
    if "Ваши покемоны" in response.text:
        soup = BeautifulSoup(response.content, "html.parser")
        pokemons = {}
        try:
            list(
                map(
                    lambda x: pokemons.update(
                        {
                            x.split("Атаки:")[0]: list(
                                map(
                                    lambda x: x.split("\xa0")[1:], x.split("Атаки:")[1:]
                                )
                            )[0]
                        }
                    ),
                    soup.getText().replace("Ваши покемоны:", "").split("\n")[:-2],
                )
            )
        except:
            return ("err", "Magic parser is broken :c")
        return ("ok", pokemons)
    else:
        return ("err", "Something went wrong")


def inject(file, marker, inj):
    try:
        with open(file) as f:
            text = f.read()
    except Exception as e:
        print(e)
        return ("err", "Can't open file!")

    if text.count(marker) != 2:
        return ("err", "Can't find inject marker!")

    try:
        with open(file, "w") as f:
            s = text.split(marker)
            f.write(s[0] + marker + "\n" + inj + "\n" + marker + s[2])
    except Exception as e:
        print(e)
        return ("err", "Can't write to file!")

    return ("ok", "Successful!")


def get_pokemon_info(name):
    name = name.split('-')[0] if '-' in name else name
    try:
        response = requests.get("https://veekun.com/dex/pokemon/%s" % name.lower())
        soup = BeautifulSoup(response.content, "html.parser")
        typ = soup.find(id="dex-page-types").find("img")["alt"]
        stats = []
        for i in soup.find(class_="dex-pokemon-stats").tbody.find_all("div", class_="dex-pokemon-stats-bar"):
            stats.append(i.get_text())
        return typ, stats
    except:
        response = requests.get("https://pokemondb.net/pokedex/%s" % name.lower())
        soup = BeautifulSoup(response.content, "html.parser")
        return soup.find("a", class_="type-icon type-fairy").get_text(), [i[1] for i in enumerate(soup.find_all(class_="vitals-table")[3].tbody.get_text().replace("Sp. Atk", "Sp.Atk").replace("Sp. Def", "Sp.Def").split()) if i[0] % 4 == 1]




REPORT_FILE_NAME = "README.md"
PROJECT_PATH = "/mnt/c/wsl/itmo-labs/programming/lab-2/Project/me/emokid/lab2/"
ROOT_PATH = "/mnt/c/wsl/itmo-labs/programming/lab-2/"

log("Trying to get pokemons list...")
status, pokemons = get_pokemons()
if status == "err":
    log(("err", pokemons))
    exit()
log(("ok", "Successful!"))

# log("Injecting pokemons list to report...")
# pokemons_marker = "<!-- pokemons_marker -->"
# inj = "\n".join(["* " + i + " (" + ", ".join(pokemons.get(i)) + ")" for i in pokemons])
# status, message = inject(ROOT_PATH + REPORT_FILE_NAME, pokemons_marker, inj)
# if status == "err":
#     log(("err", message))
#     exit()
# log(("ok", "Successful!"))

# log("Injecting pokemons creating code to main class...")
# marker = "//marker"
# names = ["Paul McCartney", "Kurt Cobain", "Tom DeLonge", "Lil Peep", "Thom Yorke", "Billy Joe Armstrong"]
# level = 2
# inj = "\n".join(["        %s p%s = new %s(\"%s\", %s);" % (i[1], i[0] + 1, i[1],  names[i[0]], level) for i in enumerate(pokemons)])
# status, message = inject(PROJECT_PATH + "Main.java", marker, inj)
# if status == "err":
#     log(("err", message))
#     exit()
# log(("ok", "Successful!"))

# log("Creating pokemons classes...")


# shutil.rmtree(PROJECT_PATH  + "pokemons/")
# os.system("mkdir %s" % PROJECT_PATH  + "pokemons/")

# template_header = """package me.emokid.lab2.pokemons;
# import me.emokid.lab2.moves.*;
# import ru.ifmo.se.pokemon.*;

# """

# template = """public class %s extends Pokemon {
#     public %s(String name, int level){
#         super(name,level);
#         setStats(%s);
#         setType(Type.%s);
#         setMove(%s);
#     }
# }"""

# for i in pokemons:
#     #print(i, get_pokemon_info(i))
#     info = get_pokemon_info(i)
#     cl = template_header + template % (i, i, str(info[1])[1:-1], info[0].upper(), 'new '+'(), new '.join([i.replace(" ", "_") for i in pokemons.get(i)])+"()")
#     with open(PROJECT_PATH + "pokemons/" + "%s.java" % i, "w") as f:
#         f.write(cl)
# log(("ok", "Successful"))

log("Creating attack classes...")

attacks = []
for i in [pokemons.get(i) for i in pokemons]:
    for j in i:
        attacks.append(j)
attacks = list(set(attacks))
print(attacks)