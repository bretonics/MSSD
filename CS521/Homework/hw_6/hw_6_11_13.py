#!/usr/bin/env python

def locateLargest(a):
    nRows = len(a)
    highest, x, y = 0, 0, 0
    for i in range(0, nRows):
        nVals = len(row)
        for j in range(0, nVals):
            if a[i][j] > highest:
                highest = a[i][j]
                x = i
                y = j
    print("The location of the largest element is at ({}, {})".format(x, y))

matrix = []
rows = eval(input("Enter the number of rows in the list: "))
for i in range(0, rows):
    row = input("Enter a row of numbers: ")
    row = [eval(i) for i in row.split()]
    matrix.append(row)
locateLargest(matrix)
