#!/usr/bin/env python

def reverseDisplayHelper(s, high):
    return reverseDisplay(s[:high])

def reverseDisplay(value):
    length = len(value)
    letter = value[length-1]
    if length == 1: return str(letter)
    return(letter + reverseDisplay(value[:-1]))

s = input("Please enter a string: ")
high = eval(input("Please enter upper substring index number: "))

print(reverseDisplayHelper(s, high))
