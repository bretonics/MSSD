#!/usr/bin/env python

matrix = []

def sumColumn(m, columnIndex):
    nrows = len(m)
    total = 0
    for x in range(0, nrows):
        total += eval(m[x][columnIndex])
    return total

for row in range(0,3):
    response = input("Enter a 3-by-4 matrix row for row {}: ".format(row))
    matrix.append(response.split())

for column in range(0,4):
    print("Sum of the elements for column", column, "is", sumColumn(matrix, column))
