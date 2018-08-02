#!/usr/bin/env python

def reverseDisplay(value):
    length = len(value)
    letter = value[length-1]
    if length == 1: return str(letter)
    return(letter + reverseDisplay(value[:-1]))

print(reverseDisplay(input("Please enter a string: ")))
