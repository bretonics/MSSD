#!/usr/bin/env python
import math

class RegularPolygon:
    def __init__(self, n = 3, side = 1.0, x = 0.0, y = 0.0):
        self.__n = n
        self.__side = side
        self.__x = x
        self.__y = y

    def getN(self):
        return self.__n
    def getSide(self):
        return self.__side
    def getX(self):
        return self.__x
    def getY(self):
        return self.__y

    def setN(self, n):
        self.__n = n
    def setSide(self, side):
        self.__side = side
    def setX(self, x):
        self.__x = x
    def setY(self, y):
        self.__y = y

    def getPerimeter(self):
        return self.__side * self.__n

    def getArea(self):
        return round( ( self.__n * self.__side ** 2 ) / ( 4 * math.tan(math.pi / self.__n) ), 2)


RP1 = RegularPolygon(6, 4)
RP2 = RegularPolygon(10, 4, 5.6, 7.8)

print("RP1 has an area of {} and a perimeter of {}.".format(RP1.getArea(), RP1.getPerimeter()))
print("RP2 has an area of {} and a perimeter of {}.".format(RP2.getArea(), RP2.getPerimeter()))
