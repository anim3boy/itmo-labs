#!/usr/bin/env python3

import sys
from binascii import hexlify, unhexlify
from math import sqrt
args = sys.argv[1:]

f = lambda x: 3*x**2 + 5
f_rev = lambda y: int(sqrt((y + 5) / 3))


def encode(plaintext):
    return hexlify(''.join([chr(f(ord(i))) for i in plaintext]).encode()).decode()

def decode(ciphertext):
    return ''.join([chr(f_rev(ord(i))) for i in unhexlify(ciphertext).decode()])

if len(args) != 2: 
    exit("Wrong args, should be: ./crypt [encode|decode] \"text\"")

if args[0] not in ("encode", "decode"):
    exit("Wrong args, should be: ./crypt [encode|decode] \"text\"")

mode = args[0]
message = args[1]

if mode == "encode":
    print(encode(message))
if mode == "decode":
    print(decode(message))