from requests import get
from htmlparser import extract, remove_tags
import json
import yaml
import sys
import time
dict_to_json = lambda x: str(x).replace("'", "\"").replace("None", "null")

if len(sys.argv) > 1:
    with open(sys.argv[1]) as f:
        page_text = f.read()
else:
    GROUP_NAME = "P3111"
    URL = "https://itmo.ru/ru/schedule/0/%s/schedule.htm" % GROUP_NAME

    page_text = get(URL).text
def gg():
    s = extract(page_text, "article", class_="content_block")
    s = extract(s[0], "table", class_="rasp_tabl")[1:]

    shedule = {}

    for i in s:
        
        
        # ВЕРСТАЛЬЩИК ПЕТУХ
        # ВЕРСТАЛЬЩИК ПЕТУХ
        # ВЕРСТАЛЬЩИК ПЕТУХ
        # ВЕРСТАЛЬЩИК ПЕТУХ
        # ВЕРСТАЛЬЩИК ПЕТУХ
        if '<tbody><th class="day">' in i:
            #print(1)
            i = i.replace('<tbody><th class="day">', '<tbody><tr><th class="day">')
        #print(i.count("<tr"), i.count("</tr"))
        if i.count("<tr")!= i.count("</tr"): i = i[::-1].replace(">rt<", "", 1)[::-1]
        
        # ВЕРСТАЛЬЩИК ПЕТУХ
        # ВЕРСТАЛЬЩИК ПЕТУХ
        # ВЕРСТАЛЬЩИК ПЕТУХ
        # ВЕРСТАЛЬЩИК ПЕТУХ
        # ВЕРСТАЛЬЩИК ПЕТУХ
        lessons = []
        for j in extract(i, "tr"):
            lesson = {}
            # extract day
            day = extract(j, "th", class_="day")[0]
            # remove script tag
            script_tag = extract(day, "script")
            if script_tag:
                day = day.replace(script_tag[0], "", 1)
            if remove_tags(day):
                #print("day:", remove_tags(day))
                DAY = remove_tags(day)

            # extract time
            time_trash = extract(j, "td", class_="time")[0]
            #print("time:", remove_tags(extract(time_trash, "span")[0]))
            lesson.update({"time": remove_tags(extract(time_trash, "span")[0])}) 
    
            # extract weeks
            weeks = extract(time_trash, "div")
            if weeks:
                #print("weeks:", remove_tags(weeks[0]))
                lesson.update({"weeks": remove_tags(weeks[0])})
                pass

            
            # extract room
            room_trash = extract(j, "td", class_="room")[0]

            room = extract(room_trash, "dd")[0]
            if remove_tags(room):
                #print("room:", remove_tags(room))
                lesson.update({"room":remove_tags(room)})
            
            # extract address
            address = extract(room_trash, "span")[0]
            if remove_tags(address):
                #print("campus:", remove_tags(address))
                lesson.update({"campus":remove_tags(address)})
                pass

            # extract lessons
            lesson_trash = extract(j, "td", class_="lesson")[0]
            
            lessonn = extract(lesson_trash, "dd")[0]
            if remove_tags(lessonn):
                #print("lesson:", remove_tags(lesson))
                lesson.update({"lesson":remove_tags(lessonn)})
            
            # extract teacher name
            teacher = extract(lesson_trash, "b")[0]
            if remove_tags(teacher):
                #print("teacher:", remove_tags(teacher))
                lesson.update({"teacher":remove_tags(teacher)})
            
            # extract format
            format = extract(lesson_trash, "td", class_="lesson-format")[0]
            if remove_tags(format):
                #print("format:", remove_tags(format))
                lesson.update({"format":remove_tags(format)})
            
            lessons.append(lesson)
        shedule.update({DAY:lessons})
        
    JSON = dict_to_json(shedule)
    json_res = json.loads(JSON)
    yaml_res = yaml.dump(yaml.safe_load(json.dumps(json_res)), allow_unicode=True)
start_time = time.time()
[gg() for i in range(10)]
end_time = time.time()
print(end_time - start_time)
# print(yaml_res)