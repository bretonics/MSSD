#!/usr/bin/env python

class Rectangle():
    # Constructor
    def __init__(self, width=1, height=2):
        self.width =  width
        self.height = height

    def getArea(self):
        return round(self.width * self.height, 2)

    def getPerimeter(self):
        return round((2 * self.width) + (2 * self.height), 2)


r0 = Rectangle()
r1 = Rectangle(4,40)
r2 = Rectangle(3.5,35.7)

print("Default rectangle has a width of {}, a height of {}, and an area of {}, with a perimeter of {}".format(r0.width, r0.height, r0.getArea(), r0.getPerimeter()))
print("Rectangle 1 has a width of {}, a height of {}, and an area of {}, with a perimeter of {}".format(r1.width, r1.height, r1.getArea(), r1.getPerimeter()))
print("Rectangle 2 has a width of {}, a height of {}, and an area of {}, with a perimeter of {}".format(r2.width, r2.height, r2.getArea(), r2.getPerimeter()))
