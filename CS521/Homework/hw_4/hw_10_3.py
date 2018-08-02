#!/usr/bin/env python
from collections import defaultdict

integers = input("Enter integers between 1 and 100: ")
integers = [int(i) for i in integers.split()] # list comprehension to set as int values in list
counts = defaultdict(int)

for integer in integers:
    if integer < 1:
        print("This integer '", integer, "' is less than 1. Skipping count...")
        continue
    elif integer > 100:
        print("This integer '", integer, "' is greater than 100. Skipping count...")
        continue
    counts[integer] += 1

numbers = sorted(counts.keys())

for number in numbers:
    print(number, "occurs", counts[number], "time" if counts[number] == 1 else "times")
