#!/usr/bin/env python
import os.path
import sys
from collections import defaultdict

def main():
    keyWords = {"and", "as", "assert", "break", "class",
                "continue", "def", "del", "elif", "else",
                "except", "False", "finally", "for", "from",
                "global", "if", "import", "in", "is", "lambda",
                "None", "nonlocal", "not", "or", "pass", "raise",
                "return", "True", "try", "while", "with", "yield"}

    filename = input("Enter a Python source code filename: ").strip()

    if not os.path.isfile(filename):  # Check if target file exists
        print("File", filename, "does not exist")
        sys.exit()

    infile = open(filename, "r") # Open files for input

    text = infile.read().split() # Read and split words from the file

    count = defaultdict(int)
    for word in text:
        if word in keyWords:
            count[word] += 1

    print("\nThere are a total of", sum(count.values()), "keywords in the file", filename)
    for keyword in count.keys():
        print(count[keyword], "{}'s".format(keyword) if count[keyword] > 1 else keyword)

main()
