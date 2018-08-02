#!/usr/bin/env python

def gcd(m, n):
    if m % n == 0: return n
    return gcd(n, m % n)

m, n = eval(input("Please enter two integers: "))
print(gcd(m, n))
