#!/usr/bin/env python

COUNT = 0
def main():
    index = eval(input("Enter an index for a Fibonacci number: "))
    # Find and display the Fibonacci number
    print("The Fibonacci number at index", index, "is", fib(index))
    print(COUNT, "total recursions were made.")

# The function for finding the Fibonacci number
def fib(index):
    global COUNT
    COUNT += 1
    if index == 0: # Base case
        return 0
    elif index == 1: # Base case
        return 1
    else:  # Reduction and recursive calls
        return fib(index - 1) + fib(index - 2)

main() # Call the main function
