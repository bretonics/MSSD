#!/usr/bin/env python
import sys

# Calculate value
def calc(x0, y0, x1, y1, x2, y2):
    status = (x1 - x0) * (y2 - y0) - (x2 - x0) * (y1 - y0)
    return status

# Return true if point (x2, y2) is on the left side of the directed line from (x0, y0) to (x1, y1)
def leftOfTheLine(status):
    if status > 0:
        return True

# Return true if point (x2, y2) is on the line segment from (x0, y0) to (x1, y1)
def onTheLineSegment(x0, y0, x1, y1, x2, y2):
    if ( (x0 <= x2 and x2 <= x1) or (x0 >= x2 and x2 >= x1) ):
        return True

# Return true if point (x2, y2) is on the same line from (x0, y0) to (x1, y1)
def onTheSameLine(x0, y0, x1, y1, x2, y2):
    status = calc(x0, y0, x1, y1, x2, y2)
    if status == 0:
        if onTheLineSegment(x0, y0, x1, y1, x2, y2):
            print("(", str(x2), ",", str(y2), ") is on the line segment from", "(", str(x0), ",", str(y0), ") to" , "(", str(x1), ",", str(y1), ")")
            sys.exit()
        return True

# Return true if point (x2, y2) is on the right side of the directed line from (x0, y0) to (x1, y1)
def rightOfTheLine(status):
    if status < 0:
        return True


x0, y0, x1, y1, x2, y2 = eval(input("Enter three points for p0, p1, p2: "))
status = calc(x0, y0, x1, y1, x2, y2)

if leftOfTheLine(status):
    print("p2 is on the left side of the line from p0 and p1")
elif rightOfTheLine(status):
    print("p2 is on the right side of the line from p0 to p1")
elif onTheSameLine(x0, y0, x1, y1, x2, y2):
    print("p2 is on the same line from p0 to p1")
