#!/usr/bin/env python

def sumSeries(i):
    m = i / ((2 * i) + 1)
    if i == 10: return m
    i += 1
    return m + sumSeries(i)

print(sumSeries(1))
