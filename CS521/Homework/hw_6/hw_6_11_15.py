#!/usr/bin/env python
import sys
def main():
    points = input("Enter five points: ")
    points = [eval(num) for num in points.split()] # split numbers by space delim
    nums = len(points) # number of numbers passed
    points = [list(points[i:i+2]) for i in range(0,nums,2)] # seperate numbers into sets of points (pairs)

    # Index for points in list
    i = 0
    j = 1
    h = 2
    numPoints = len(points)

    # Iterate through every set of points, (x,y) coordinates
    while h < numPoints:
        # Get point's X and Y coordinates
        x0 = points[i][0]
        y0 = points[i][1]
        x1 = points[j][0]
        y1 = points[j][1]
        x2 = points[h][0]
        y2 = points[h][1]
        # Send of...
        onTheSameLine(x0, y0, x1, y1, x2, y2)
        # Move on to next set of (x, y) coordinates
        i = i + 1
        j = j + 1
        h = h + 1

    # If all points checked are on same line, print
    print("The five points are on the same line")

# Return true if point (x2, y2) is on the same line from (x0, y0) to (x1, y1)
def onTheSameLine(x0, y0, x1, y1, x2, y2):
    status = (x1 - x0) * (y2 - y0) - (x2 - x0) * (y1 - y0)
    # When == 0, then on same line
    if status == 0:
        return True # return True to keep iterating through each set of points in list
    else:
        # If set of points passed are not on the same, execution can terminate
        print("The five points are not on the same line")
        sys.exit()

main()
