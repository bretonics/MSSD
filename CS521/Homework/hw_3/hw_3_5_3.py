#!/usr/bin/env python

def k2p(k):
    p = round(float(k) * 2.2, 1)
    return(p)

print("Kilograms\tPounds")
for k in range(1,200):
    print(k, end='\t')
    print(k2p(k))
