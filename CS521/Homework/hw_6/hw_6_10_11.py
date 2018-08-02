#!/usr/bin/env python
from random import *

def shuffle(numbers):
    newList = []
    numL = len(numbers)
    # Loop as many times as there are numbers to shuffle
    for i in range(0, numL):
        idx = randint(0, numL - 1)
        element = numbers[idx]
        # Get another random index if element of list at idx already in new list
        while element in newList:
            element = numbers[randint(0, numL) - 1] # avoid duplicates, new idx
        newList.append(element)
    print("New random list of numbers:", newList)

# Let's do this...
numbers = input("Please enter a list of numbers: ")
numbers = [eval(i) for i in numbers.split()]
shuffle(numbers)
