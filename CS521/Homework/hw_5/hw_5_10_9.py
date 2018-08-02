#!/usr/bin/env python
import math

def main():
    numbers = input("Enter numbers: ")
    numbers = [eval(i) for i in numbers.split()]
    print("The mean is", round(mean(numbers),2))
    print("The standard deviation is", round(deviation(numbers),5))

def deviation(x):
    n = len(x)
    summation = [(i - mean(x))**2 for i in x]
    sd = math.sqrt( sum(summation) / (n - 1) )
    return sd

def mean(x):
    n = len(x)
    return(sum(x)/n)

main()
