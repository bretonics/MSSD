#!/usr/bin/env python

answer = input("Enter SSN: ")
ssn = answer.split("-")

def is_number(n):
    try:
        int(n)
        return n
    except ValueError:
        print("Non-numeric data- '", n, "'", sep='')

# Test if input is comprised of digits
for n in ssn:
    if is_number(n): # continues if True
        continue
    else:
        print("Invalid SSN")
        exit()
# Test if input is proper lenght of 3-2-4 digits
if len(ssn) == 3:
    if len(ssn[0]) == 3:
        if len(ssn[1]) == 2:
            if len(ssn[2]) == 4:
                print("Valid SSN")
            else:
                print("Invalid SSN")
        else:
            print("Invalid SSN")
    else:
        print("Invalid SSN")
else:
    print("Invalid SSN")
