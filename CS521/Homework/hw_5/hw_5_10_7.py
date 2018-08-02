#!/usr/bin/env python
from random import randint
from collections import Counter

randNums = []
for _ in range(1000):
    randNums.append(randint(0,9))

counts = Counter(randNums)

for i in range(0,9):
    print("The number of occurences for", str(i), " is:" , str(counts.get(i)))
