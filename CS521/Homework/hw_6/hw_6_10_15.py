#!/usr/bin/env python

def isSorted(lst):
    length = len(lst)
    current = lst[0]
    for number in lst:
        if current <= number:
            current = number
            continue
        else:
            return False
    return True

# Let's do this...
numbers = input("Please enter a list of numbers: ")
numbers = [eval(i) for i in numbers.split()]
if isSorted(numbers):
    print("The list is already sorted")
else:
    print("The list is not sorted")
