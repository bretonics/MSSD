#!/usr/bin/env python

integers = input("Enter ten numbers: ")
# set == no duplicates
integers = {int(i) for i in integers.split()} # list comprehension to set as int values in set
print("The distinc numbers are: ", end="")
print(" ".join(str(i) for i in integers))
