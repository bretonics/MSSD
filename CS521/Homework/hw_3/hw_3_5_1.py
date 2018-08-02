#!/usr/bin/env python

integers = {"pos": [], "neg": []}
while True:
    i = int(input("Enter an integer, the input ends if 0 is entered: "))
    if i == 0:
        break
    elif i > 0: # positive number
        # add number to dictionary list of positive numbers
        integers["pos"].append(i)
    else: # negative number
        # add number to dictionary list of negative numbers
        integers["neg"].append(i)

# Amount of numbers that are positive/negative
npos = len(integers["pos"])
nneg = len(integers["neg"])
# Sum of each positive/negative to calculate average
# Omit mean() function because of structure implemented here
psum = sum(integers["pos"])
nsum = sum(integers["neg"])

print("The number of positives is", npos)
print("The number of negatives is", nneg)
print("The total is", psum + nsum)
print("The mean is", (psum + nsum) / (npos + nneg))
