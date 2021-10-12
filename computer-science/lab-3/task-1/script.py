from re import findall, escape

EYES = [":", ";", "X", "8", "="]
NOSES = ["-", "<", "-{", "<{"]
MOUTHS = ["(", ")", "O", "|", "\\", "/", "P"]

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


def generate_pattern(isu=335050):
    ISU = isu

    eye = EYES[ISU % 5]
    nose = NOSES[ISU % 4]
    mouth = MOUTHS[ISU % 7]

    pattern = eye + nose + mouth

    return ("ok", pattern)


def blackbox(pattern=generate_pattern()[1], string=""):
    try:
        return ("ok", len(findall(escape(pattern), string)))
    except:
        return ("err", "Something went wrong!")


def main():
    isu = inp("Enter your ISU number")
    
    if isu.isdecimal() and len(isu) == 6:
        isu = int(isu)
    else:
        log("err", "Wrong ISU number!")
    pattern = generate_pattern(isu)
    if pattern[0] == "ok":
        log(("ok", "Your emoji is %s" % pattern[1]))
    else:
        log(pattern)
        exit()
    pattern = pattern[1]

    try: 
        from pyfiglet import figlet_format as cool_font_print
        print(cool_font_print(' '.join([i for i in pattern])))
    except ModuleNotFoundError:
        log("For best experience please install pyfiglet module and run script again! (pip3 install pyfiglet)")

    while True:
        string = inp("Enter your string")
        res = blackbox(pattern, string)
        if res[0] == "ok":
            log(("ok", "Number of matches is %s" % res[1]))
        else:
            log(res)
            exit()


if __name__ == "__main__":
    try:
        main()
    except KeyboardInterrupt:
        print("\nBye!")
