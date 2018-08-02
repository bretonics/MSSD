#!/usr/bin/env python
import math

a, b, c, d, e, f = eval(input("Enter value for a, b, c, d, e, f: "))

s = a * d - b * c

if (s) == 0:
    print("The equation has no solution.")
else:
    x = (e * d - b * f) / (s)
    y = (a * f - e * c) / (s)
    print("x is", x, "and y is", y)
