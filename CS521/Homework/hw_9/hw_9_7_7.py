#!/usr/bin/env python

class LinearEquation:
    def __init__(self, a, b, c, d, e, f):
        self.__a = a
        self.__b = b
        self.__c = c
        self.__d = d
        self.__e = e
        self.__f = f

    def getA(self):
        return self.__a
    def getB(self):
        return self.__b
    def getC(self):
        return self.__c
    def getD(self):
        return self.__d
    def getE(self):
        return self.__e
    def getF(self):
        return self.__f

    def isSolvable(self):
        if( self.getDenom() != 0 ):
            return True
    def getX(self):
        x = ( self.__e * self.__d - (self.__b * self.__f) ) / self.getDenom()
    def getY(self):
        y = ( self.__a * self.__f - (self.__e * self.__c) ) / self.getDenom()
    def getDenom(self):
        return (self.__a * self.__d) - (self.__b * self.__c)

# Ex.) 1.0, 2.0, 2.0, 4.0, 4.0, 5.0
a, b, c, d, e, f = eval(input("Please enter numbers for a, b, c, d, e, f: "))
t = LinearEquation(a, b, c, d, e, f)
if(t.getDenom() == 0):
    print("The equation has no solution.")
