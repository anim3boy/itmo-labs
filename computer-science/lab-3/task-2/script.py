from re import sub

REGEX = r"([0-1]?[0-9]|[2][0-3]):([0-5][0-9])(:[0-5][0-9])?"

PLUG = "(TBD)"

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

def fix(text):
    try:
        return ("ok", sub(REGEX, PLUG, text))
    except:
        return ("err", "Something went wrong!")

def main():
    while True:
        text = inp("Please enter source text")
        fixed_text = fix(text)
        if fixed_text[0] == "ok":
            log(("ok", "Fixed text: %s" % fixed_text[1])) 
        else:
            log(fixed_text)

if __name__ == "__main__":
    try:
        main()
    except KeyboardInterrupt:
        print("\nBye!")
