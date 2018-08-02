#!/usr/bin/env python
import math

# Decimal coordinates (latitude, longitude):
atlanta = [33.7489954, -84.3879824]
savannah = [32.0835407, -81.09983419999998]
orlando = [28.5383355, -81.37923649999999]
charlotte = [35.2270869, -80.84312669999997]

radius = 6371.01

# Distance between Atlanta and Savannah
as_distance = radius * math.acos( math.sin(math.radians(atlanta[0])) * math.sin(math.radians(savannah[0])) + math.cos(math.radians(atlanta[0])) * math.cos(math.radians(savannah[0])) * math.cos( math.radians(atlanta[1]) - math.radians(savannah[1]) ) )
print("Distance between Atlanta & Savannah:", round(as_distance,2), "km")

# Distance between Atlanta and Orlando
ao_distance = radius * math.acos( math.sin(math.radians(atlanta[0])) * math.sin(math.radians(orlando[0])) + math.cos(math.radians(atlanta[0])) * math.cos(math.radians(orlando[0])) * math.cos( math.radians(atlanta[1]) - math.radians(orlando[1]) ) )
print("Distance between Atlanta & Orlando:", round(ao_distance,2), "km")

# Distance between Orlando and Savannah
os_distance = radius * math.acos( math.sin(math.radians(orlando[0])) * math.sin(math.radians(savannah[0])) + math.cos(math.radians(orlando[0])) * math.cos(math.radians(savannah[0])) * math.cos( math.radians(orlando[1]) - math.radians(savannah[1]) ) )
print("Distance between Orlando & Savannah:", round(os_distance,2), "km")

# Distance between Atlanta and Charlotte
ac_distance = radius * math.acos( math.sin(math.radians(atlanta[0])) * math.sin(math.radians(charlotte[0])) + math.cos(math.radians(atlanta[0])) * math.cos(math.radians(charlotte[0])) * math.cos( math.radians(atlanta[1]) - math.radians(charlotte[1]) ) )
print("Distance between Atlanta & Charlotte:", round(ac_distance,2), "km")

# Distance between Charlotte and Savannah
cs_distance = radius * math.acos( math.sin(math.radians(charlotte[0])) * math.sin(math.radians(savannah[0])) + math.cos(math.radians(charlotte[0])) * math.cos(math.radians(savannah[0])) * math.cos( math.radians(charlotte[1]) - math.radians(savannah[1]) ) )
print("Distance between Charlotte & Savannah:", round(cs_distance,2), "km")

# Area of first triangle
s1 = (as_distance + os_distance + ao_distance) / 2
area1 = math.sqrt( s1 * (s1 - as_distance) * (s1 - os_distance) * (s1 - ao_distance) )
# Area of second triangle
s2 = (as_distance + cs_distance + ac_distance) / 2
area2 = math.sqrt( s2 * (s2 - as_distance) * (s2 - cs_distance) * (s2 - ac_distance) )
print("The total area between the four cities is:", round((area1 + area2),2), "sq km")
