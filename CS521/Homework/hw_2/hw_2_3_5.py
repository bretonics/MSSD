#!/usr/bin/env python
import math
n = eval(input("Enter the number of sides: "))
l = eval(input("Enter the length of a side: "))
a = (n * l**2) / (4 * ( math.tan(math.pi / n) ))
print("The area of the polygon is" , a)
