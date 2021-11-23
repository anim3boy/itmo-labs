def extract(html: str, tag: str, class_= None):
    html = html.replace("'", "\"") # EXPEREMENTAL

    is_tag_name_recording = False
    current_tag_inside = ""
    memory = []
    result = []

    end_tags_counter = 0 # BAD CODE
    for pos, i in enumerate(html.lower()):
        # print(pos, i, end = "   ")
        # print(current_tag_inside)
        if i == "<":
            is_tag_name_recording = True
            current_tag_inside = ""
            continue
        if i == ">":
            is_tag_name_recording = False
            current_tag_name = current_tag_inside.split()[0]
            if current_tag_name == tag:
                # finding begin tags
                # print(pos - len(current_tag_inside) - 1, len(current_tag_inside) + 2,  current_tag_inside)
                memory.append((pos - len(current_tag_inside) - 1, len(current_tag_inside) + 2))
            if current_tag_name == "/" + tag:
                # finding end tags
                end_tags_counter += 1 # BAD CODE
                
                # print(memory[-1])
                # print(pos - len(current_tag_inside) - 1, len(current_tag_inside) + 3 ,  current_tag_inside)
                begin_tag_pos, begin_tag_len = memory[-1]
                end_tag_post, end_tag_len = pos - len(current_tag_inside) - 1, len(current_tag_inside) + 2
                #print(html[begin_tag_pos:end_tag_post + end_tag_len]) # PRINT
                result.append((begin_tag_len, html[begin_tag_pos:end_tag_post + end_tag_len]))
                del memory[-1]
            
            continue
        if is_tag_name_recording:
            current_tag_inside += i

    # BAD CODE
    if end_tags_counter == 0:
        for pos, le in memory:
            # print(html[pos:pos+le]) # PRINT
            result.append(le, html[pos:pos+le])
    return [i[1] for i in result if class_ is None or "class=\"%s\"" % class_ in i[1][:i[0]]] # BAD CODE

def remove(html):
    
    is_recording = True
    result = ""
    for i in html:
        if i == "<":
            is_recording = False
            continue
        if i == ">":
            is_recording = True
            continue
        if is_recording:
            result += i
    
    return result.strip()
