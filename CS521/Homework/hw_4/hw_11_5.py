#!/usr/bin/env python

a, b , c = [], [] ,[]

def addMatrix(a, b):
    # Check matrices have correct dimensions
    if len(a) != len(b):
        print("Matrices do not have the sam dimensions!") and exit()

    # Let's add those matrices
    for i in range(0, 3):
        row = []
        for j in range(0, 3):
            a_val = float(a[i][j])
            b_val = float(b[i][j])
            row.append(a_val + b_val)
        c.append(row)
    print_matrices(a, b, c)

def print_matrices(a, b, c):
    print("The matrices are as follows:")
    for i in range(0, 3):
        if i == 1:
            print(" ".join(str(float(f)) for f in a[i]), "\t+\t", " ".join(str(float(f)) for f in b[i]), "\t=\t", " ".join(str(float(f)) for f in c[i]))
        else:
            print(" ".join(str(float(f)) for f in a[i]), "\t\t", " ".join(str(float(f)) for f in b[i]), "\t\t", " ".join(str(float(f)) for f in c[i]))


def main():
    # Get first matrix
    response = input("Enter 3-by-3 matrix 1: ")
    response = response.split()
    a = [ response[i:i+3] for i in range(0, len(response), 3) ]

    # Get second matrix
    response = input("Enter 3-by-3 matrix 2: ")
    response = response.split()
    b = [ response[i:i+3] for i in range(0, len(response), 3) ]

    addMatrix(a, b)

main()
