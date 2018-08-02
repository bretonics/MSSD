#!/usr/bin/env python

number = eval(input("Enter a number between 0 and 511: "))

binary = '{0:09b}'.format(number)
matrix = [list(binary[i:i+3]) for i in range(0,9,3)]
result = []
i = 0
for row in matrix:
    j = 0
    new_row = []
    for flip in row:
        if flip == '0':
            print('H', end='')
        else:
            print('T', end='')
        print(' ', end='')
        j = j+1
    print('\n', end='')
    i = i+1
