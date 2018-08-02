#!/usr/bin/env python
from collections import Counter

vowels={'a', 'e', 'i', 'o', 'u'}
consonants={'b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'p', 'q', 'r', 's', 't', 'v', 'w', 'x', 'z'}

f_name = input("Please enter a file name: ")
f_in = open(f_name, "r")
content = f_in.read() # Read in all file as one string

numV = sum(list(map(content.lower().count, vowels)))
numC = sum(list(map(content.lower().count, consonants)))

print("There are", numV, "vowels in the file '{}'".format(f_name))
print("There are", numC, "consonants in the file '{}'".format(f_name))
