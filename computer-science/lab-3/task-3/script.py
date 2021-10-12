from re import sub

REGEX = r"(-|)\d+"

mod = lambda x: str(3*int(x.group(0))**2 + 5)

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

def modificate(text):
    try:
        return ("ok", sub(REGEX, mod, text))
    except Exception as e:
        print(e)
        return ("err", "Something went wrong!")

def main():
    while True:
        text = inp("Please enter source text")
        fixed_text = modificate(text)
        if fixed_text[0] == "ok":
            log(("ok", "Fixed text: %s" % fixed_text[1])) 
        else:
            log(fixed_text)

if __name__ == "__main__":
    try:
        main()
    except KeyboardInterrupt:
        print("\nBye!")
