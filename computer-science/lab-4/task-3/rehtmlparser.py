import re
import requests
import itertools
import sys

def extract(html, tag = r"[\w\d]+", class_= None):

    BEGIN_TAG_PATTERN = r"<%s[^>]*>" % tag# r"<%s(?:\s*[\w\d\-:_]+(?:=([\"'])[^\"]*\1)?)*>" % tag
    
    END_TAG_PATTERN = r"</%s>" % tag

    SINGLE_TAG_PATTERN = r"<%s[^>]*\s*/>" % tag# r"<%s(?:\s*[\w\d\-:_]+(?:=([\"'])[^\"]*\1)?)*\s*/>" % tag
    

    begin_iter = re.finditer(BEGIN_TAG_PATTERN, html, re.IGNORECASE)
    end_iter = re.finditer(END_TAG_PATTERN, html, re.IGNORECASE)
    single_iter = re.finditer(SINGLE_TAG_PATTERN, html, re.IGNORECASE)
    
    # single check
    result = []
    #print(tag)
    #print(BEGIN_TAG_PATTERN)

    tags = [(0, i) for i in begin_iter] + [(1, i) for i in end_iter]

    tags.sort(key=lambda x: x[1].start())

    memory = []
    
    for i, v in tags:
        if i == 0:
            memory.append(v)
        if i == 1:
            open_tag = memory.pop(-1)
            end_tag = v
            length = open_tag.end() - open_tag.start()
            #print(length, open_tag)
            result.append((length, html[open_tag.start():end_tag.end()]))
    
    return [i[1] for i in result if class_ is None or "class=\"%s\"" % class_ in i[1][:i[0]].replace("'", "\"")]

def remove(html):
    UNIVERSAL_TAG_PATTERN = r"</?[\w\d-]+[^>]*\s*/?>"
    gg = re.compile(UNIVERSAL_TAG_PATTERN)
    
    html = gg.sub("", html)
    return html.strip()

if __name__ == "__main__":
    print(remove(requests.get("https://itmo.ru/ru/schedule/0/P3111/schedule.htm").text))