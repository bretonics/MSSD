#!/usr/bin/env python

import math

a, b, c = eval(input("Enter value for a, b, c: "))

# Discriminant value
d = b**2 - (4 * a * c)

def r1():
    answer = ( -b + math.sqrt(d) ) / (2 * a)
    return answer

def r2():
    answer = ( -b - math.sqrt(d) ) / (2 * a)
    return answer

if d > 0:
    print("The equation has 2 real roots: ", round(r1(), 6), " and", round(r2(), 6))
elif d < 0:
    print("The equation has no real roots.")
elif d == 0:
    print("The equation has one real roots: ", round(r1(), 6))
else:
    pass
