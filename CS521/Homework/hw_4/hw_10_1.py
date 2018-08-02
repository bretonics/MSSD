#!/usr/bin/env python

response = input("Enter scores: ")
scores = response.split()
nscores = len(scores)
best = eval(max(scores))

def grade(score):
    score = eval(score)
    if score >= (best - 10):
        return 'A'
    elif score >= (best - 20):
        return 'B'
    elif score >= (best - 30):
        return 'C'
    elif score >= (best - 40):
        return 'D'
    else:
        return 'F'

for i in range(0, nscores):
    print("Student", i, "score is", scores[i], "and grade is", grade(scores[i]))
