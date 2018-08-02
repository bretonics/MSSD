#!/usr/bin/env python

def sumDigits(n):
    number = eval(n[0])
    if len(n) == 1: return number
    return(number + sumDigits(n[1:]))

print(sumDigits(input("Please enter an integer: ")))
