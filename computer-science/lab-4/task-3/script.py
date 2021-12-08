from requests import get
from rehtmlparser import extract, remove
from rejsonparser import parse_json
from yamlgenerator import generate
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
    # print(s)
    shedule = {}

    # exit(0)
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
            if remove(day):
                #print("day:", remove(day))
                DAY = remove(day)

            # extract time
            time_trash = extract(j, "td", class_="time")[0]
            #print("time:", remove(extract(time_trash, "span")[0]))
            lesson.update({"time": remove(extract(time_trash, "span")[0])}) 
    
            # extract weeks
            weeks = extract(time_trash, "div")
            if weeks:
                #print("weeks:", remove(weeks[0]))
                lesson.update({"weeks": remove(weeks[0])})
                pass

            
            # extract room
            room_trash = extract(j, "td", class_="room")[0]

            room = extract(room_trash, "dd")[0]
            if remove(room):
                #print("room:", remove(room))
                lesson.update({"room":remove(room)})
            
            # extract address
            address = extract(room_trash, "span")[0]
            if remove(address):
                #print("campus:", remove(address))
                lesson.update({"campus":remove(address)})
                pass

            # extract lessons
            lesson_trash = extract(j, "td", class_="lesson")[0]
            
            lessonn = extract(lesson_trash, "dd")[0]
            if remove(lessonn):
                #print("lesson:", remove(lesson))
                lesson.update({"lesson":remove(lessonn)})
            
            # extract teacher name
            teacher = extract(lesson_trash, "b")[0]
            if remove(teacher):
                #print("teacher:", remove(teacher))
                lesson.update({"teacher":remove(teacher)})
            
            # extract format
            format = extract(lesson_trash, "td", class_="lesson-format")[0]
            if remove(format):
                #print("format:", remove(format))
                lesson.update({"format":remove(format)})
            # print(lesson)
            lessons.append(lesson)
        shedule.update({DAY:lessons})
        
    #print(shedule)
    # exit()
    JSON = dict_to_json(shedule)
    res = parse_json(JSON)
    #print(dict_to_json(res))

start_time = time.perf_counter()
[gg() for i in range(10)]
end_time = time.perf_counter()
print(end_time - start_time)