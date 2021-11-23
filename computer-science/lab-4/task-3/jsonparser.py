import string 

def a(some_string):
    if not some_string:
        return  
    if some_string[0] == "\"" and some_string[-1] == "\"":
        # is string
        return some_string[1:-1]
    if some_string[0] == "[" and some_string[-1] == "]":
        # is list
        return parse_array(some_string)
    if some_string[0] == "{" and some_string[-1] == "}":
        # is json
        return parse_json(some_string)
    # is boolean
    if some_string == "true":
        return True
    if some_string == "false":
        return False
    # is number
    if some_string.isnumeric():
        return int(some_string)
    

def parse_array(array):
            
    array = array.strip()[1:-1].strip()
    array += ","
    result = []
    value = ""
    is_escaping = ""
    depth = 0
    for pos, sym in enumerate(array):
        if sym in string.whitespace and is_escaping == "":
            continue
        # print()
        # print(sym, end = "      ")
        if sym == "," and is_escaping == "":
            #print(2, value, end= " ")
            result.append(a(value))
            value = ""
            continue

        # escaping
        if sym == is_escaping and is_escaping in ("{", "["):
            depth += 1
            #print("!!!")
        if sym in ("{", "[", "\"") and is_escaping == "":
            is_escaping = sym
            #print("!")
        elif sym in ("}", "]", "\"") and is_escaping:
            if (is_escaping, sym) == ("{", "}") or (is_escaping, sym) == ("[", "]") or (is_escaping, sym) == ("\"", "\""):
                #print("?")
                if depth != 0:
                    depth -= 1
                else:
                    is_escaping = ""

        # recording

        value += sym
    return result

def parse_json(json):            

    json = json.strip()[1:-1].strip()
    json += ","
    result = {}
    id = ""
    value = ""
    recording_mode = 0
    is_escaping = ""
    depth = 0
    for pos, sym in enumerate(json):
        if sym in string.whitespace and is_escaping == "":
            continue
        # print()
        # print(sym, end = "      ")
        if sym == ":" and is_escaping == "":
            #print(1, id, end = " ")
            recording_mode = 1
            value = ""
            continue
        if sym == "," and is_escaping == "":
            #print(2, value, end= " ")
            result.update({a(id):a(value)})
            id = ""
            recording_mode = 0
            continue

        # escaping
        if sym == is_escaping and is_escaping in ("{", "["):
            depth += 1
            #print("!!!")
        if sym in ("{", "[", "\"") and is_escaping == "":
            is_escaping = sym
            #print("!")
        elif sym in ("}", "]", "\"") and is_escaping:
            if (is_escaping, sym) == ("{", "}") or (is_escaping, sym) == ("[", "]") or (is_escaping, sym) == ("\"", "\""):
                #print("?")
                if depth != 0:
                    depth -= 1
                else:
                    is_escaping = ""
                    
        # recording
        if recording_mode == 0:
            id += sym
            continue
        if recording_mode == 1:
            value += sym
            continue
            
    return result
