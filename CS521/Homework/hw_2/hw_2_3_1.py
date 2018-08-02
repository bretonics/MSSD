#!/usr/bin/env python
import math
r = eval(input("Enter the length from the center to a vertex: "))
s = 2 * r * math.sin(math.pi / 5)
area = ((3 * math.sqrt(3)) /  2) * s**2
print("The area of the pentagon is", round(area,2))
