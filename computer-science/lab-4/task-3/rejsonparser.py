import re

def type_detect(some_string):
    
    some_string = some_string.strip()
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

def parse_json(json):
    json = json.strip()
    JSON_REGEX = r"([\"'])((?:[\w\d]|[^\w\d\s])*)\1\s*:\s?({[^%]*}|\[[^\]]*\]|\1[^\"']*\1|\d+|(?:true|false|null))"
    gg = re.compile(JSON_REGEX)
    json = json[1:-1]
    result = {}

    for i in gg.findall(json):
        name = i[1]
        value = i[2]
        # print(value)
        # print("-------------")
        result.update({name : type_detect(value)})
    return result

def parse_array(json):
    json = json.strip()
    LIST_REGEX = r"({[^}]*}|\[[^\]]*\]|[\"'][^\"']*[\"']|\d+|(?:true|false|null))"
    gg = re.compile(LIST_REGEX)
    json = json[1:-1]
    result = []
    for i in gg.findall(json):
        result.append(type_detect(i))
    return result



if __name__ == "__main__":
    print(parse_json("""{
    "glossary": {
        "title": "example glossary",
		"GlossDiv": {
            "title": "S",
			"GlossList": {
                "GlossEntry": {
                    "ID": "SGML",
					"SortAs": "SGML",
					"GlossTerm": "Standard Generalized Markup Language",
					"Acronym": "SGML",
					"Abbrev": "ISO 8879:1986",
					"GlossDef": {
                        "para": "A meta-markup language, used to create markup languages such as DocBook.",
						"GlossSeeAlso": ["GML", "XML"]
                    },
					"GlossSee": "markup"
                }
            }
        }
    }
}
"""))