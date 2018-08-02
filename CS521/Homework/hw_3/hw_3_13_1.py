#!/usr/bin/env python
import re

f_name = input("Please enter a file name: ")
f_in = open(f_name, "r")
string = input("Please enter string to be removed: ")

content = f_in.read()
# Read in all file as one string, replace word string with empty string, and print result
print("\n", content.replace(string, ''), sep='')
print("~~~~")
print("[ OH NO!! We missed capitalized matches for", string, "! Redoing... ]")
print("~~~~")
print("\n", re.sub(string, '', content, flags=re.IGNORECASE), sep='')
f_in.close()
print("NOW we are done.")

# --or-- go line by line....but nah
# for line in f_in:
#     line = line.replace(string, "")
#     print(line)
