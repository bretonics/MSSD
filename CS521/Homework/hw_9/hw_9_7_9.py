#!/usr/bin/env python

x1, y1, x2, y2 = eval(input("Enter the endpoints of the first line segment: "))
x3, y3, x4, y4 = eval(input("Enter the endpoints of the second line segment: "))

d = ( (x1 - x2) * (y3 -y4) - (y1 -y2) * (x3 - x4) )
x = ( (x1 * y2 - y1 * x2) * (x3 - x4) - (x1 - x2) * (x3 * y4 - y3 * x4) ) / d
y = ( (x1 * y2 - y1 * x2) * (y3 - y4) - (y1 - y2) * (x3 * y4 - y3 * x4) ) / d

print("The intersecting point is ({}, {})".format(x,y))
