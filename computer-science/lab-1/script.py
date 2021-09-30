from string import digits as numbers, ascii_uppercase as letters
from math import log
from itertools import product 

def base_translate(source, a=10, b=10):
    ALPHABET = numbers + letters

    int_part_of_decimal, fract_part_of_decimal = 0,0
    int_part_of_source, fract_part_of_source = "", ""

    if a != 10:
        # a -> 10   int(source, 10)
        if ',' in source:
            int_part_of_source, fract_part_of_source = source.split(',')
        else: 
            int_part_of_source = source
        
        for i in enumerate(reversed(int_part_of_source)):
            index, number = i
            int_part_of_decimal += int(ALPHABET.index(number)) * (a ** index)
        
        for i in enumerate(fract_part_of_source):
            index, number = i
            index = -(index + 1)
            fract_part_of_decimal += int(ALPHABET.index(number)) * (a ** index)
        decimal = int_part_of_decimal + fract_part_of_decimal
        
    else:
        decimal = float(source.replace(',', '.'))

    # 10 -> b 
    if b == 10: 
        return str(int(decimal * 10 ** 5) / 10**5).replace('.', ',')\
            if int(decimal) != decimal else decimal

    fract_part_of_decimal = decimal - int(decimal)
    int_part_of_decimal = int(decimal)
    
    int_part_of_result, fract_part_of_result = "", ""

    for i in range(5):
        fract_part_of_result += str(ALPHABET[int(fract_part_of_decimal*b)])
        fract_part_of_decimal = fract_part_of_decimal*b - int(fract_part_of_decimal*b)

    while int_part_of_decimal > 0:
        int_part_of_decimal, m = divmod(int_part_of_decimal, b)
        int_part_of_result += ALPHABET[m]

    int_part_of_result = "0" if not int_part_of_result else int_part_of_result
    while fract_part_of_result and fract_part_of_result[-1] == "0": 
        fract_part_of_result = fract_part_of_result[::-1].replace('0', '', 1)[::-1]
    return '%s%s%s' % (int_part_of_result[::-1], ',' if fract_part_of_result else '', fract_part_of_result)

def translate(source, a=10, b=10):
    if a == b: return source
    if b == 'Fib': 
        return fib_encoding(source, int(a))
    if a == 'Fib': 
        return fib_decoding(source, int(b))    
    a, b = int(a), int(b)
    if a > 0 and b > 0 and (int(log(b) / log(a)) == log(b) / log(a) \
        or int(log(a) / log(b)) == log(a) / log(b)) and ',' not in source:  #!!
        # "easy way"
        block_length = int(log(b, a))
        padding = len(source) % block_length
        if padding:
            source = "0" * (block_length - padding) + source
        blocks = [source[i : i + block_length] for i in range(0, len(source), block_length)]
        return ''.join([base_translate(i, a, b) for i in blocks])
    else:   
        return base_translate(source, a, b)

def fib_encoding(n, a):
    if a != 10: n = int(translate(n, a, 10))
    n = int(n)
    ALPHABET = []
    a, b = 0, 1
    while a <= n:
        ALPHABET.append(a)
        a, b = b, a + b
    ALPHABET = sorted(list(set(ALPHABET[1:])))

    for i in product([0,1], repeat=len(ALPHABET)):
        if sum(i[j] * ALPHABET[j] for j in range(len(ALPHABET))) == n \
            and '11' not in ''.join(list(map(str, i))): 
            return ''.join(list(map(str, i)))[::-1]

def fib_decoding(n, b):
    ALPHABET = []
    a, b = 0, 1
    for i in range(len(n) + 2):
        ALPHABET.append(a)
        a, b = b, a + b
    ALPHABET = sorted(list(set(ALPHABET[1:])))

    return sum(int(n[j]) * ALPHABET[::-1][j] for j in range(len(ALPHABET)))

with open('s.txt') as f:
    s = list(map(lambda x: x[:-1].split(), f.readlines()))
    print('{:<10}  {:<3}    {:<3}    {}'.format("A", "B", "C", "Ans"))
    for i in s: 
        print('{:<10} [{:<3} -> {:<3}]   {}'.format(i[0], i[1], i[2], translate(i[0], i[1], i[2])))