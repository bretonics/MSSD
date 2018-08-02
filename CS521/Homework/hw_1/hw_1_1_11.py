#!/usr/bin/env python
population = 312032486

# People per 60 seconds (1 minute)
births = 60 // 7
deaths = 60 // 13
immigrant = 60 // 45

# Project of people per year
# 60 minutes in 1 hour, 24 hours in 1 day, 365 days a year
projection = 60 * 24 * 365 * (births - deaths + immigrant)

print("Current population is:" , population)
print("Population projections for next 5 years:")
for i in range(1,5):
    population = population + projection
    print(population)
