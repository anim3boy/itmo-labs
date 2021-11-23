# IT'S BAD BUT IT WORKS

out = ""
def echo(x, end = "\n"):
    x = str(x)
    global out
    out += x
    out += end

padding = ""
def iterlist(d, i_m_in_fucking_list=False):
    global padding
    padding_ = padding + ""
    if i_m_in_fucking_list:
        padding = padding[:-2] + "- "
    for i, v in enumerate(d):
        if v == None: 
            echo("".join((padding,  "null")))
        if isinstance(v, dict):
            padding += "  "
            iterdict(v, i_m_in_fucking_list = True)
        elif isinstance(v, list):
            padding += "  "
            iterlist(v, i_m_in_fucking_list = True)
        elif isinstance(v, str):            
            echo("".join((padding[:-2] + "- ",  "\"", v, "\"")))
        elif isinstance(v, int):
            echo("".join((padding[:-2] + "- ",  str(v))))
        elif isinstance(v, bool):
            if v: 
                echo("".join((padding[:-2] + "- ",  "true")))
            else:
                echo("".join((padding[:-2] + "- ",  "false")))
        else:
            echo("Unknown type!")
        
        padding = padding_ + ""
        
            
def iterdict(d, i_m_in_fucking_list=False):
    global padding
    padding_ = padding + ""
    if i_m_in_fucking_list:
        padding = padding[:-2] + "- "
    for k,v in d.items():
            
        if v == None: 
            echo("".join((padding,  "null")))
        if isinstance(v, dict):
            echo("".join((padding, k, ":")))
            padding += "  "
            iterdict(v)
        elif isinstance(v, list):
            
            echo("".join((padding, k, ":")))
            padding += "  "
            iterlist(v)
        elif isinstance(v, str):     
            echo("".join((padding, k, " : ", "\"", v, "\"")))  
        elif isinstance(v, int):
            echo("".join((padding, k, " : ", str(v)))) 
        elif isinstance(v, bool):
            if v: 
                echo("".join((padding, k, " : ",  "true")))
            else:
                echo("".join((padding, k, " : ",  "false")))
        else:
            echo("Unknown type!")
            
        padding = padding_ + ""
        

def generate(json):
    echo("---")
    iterdict(json)
    return out
