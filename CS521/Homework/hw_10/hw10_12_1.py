from GeometricObject import GeometricObject
import math

class Triangle(GeometricObject):
    """Extends GeometricObject class"""
    def __init__(self, color, filled, side1 = 1.0, side2 = 1.0, side3 = 1.0):
        super().__init__(color, filled) # superclass initializer
        self.side1 = side1
        self.side2 = side2
        self.side3 = side3

        self.color = color
        self.filled = filled
    def __str__(self):
        return "Triangle: side1 = " + str(side1) + " side2 = " + str(side2) + " side3 = " + str(side3)

    def getSide1(self):
        return self.side1
    def getSide2(self):
        return self.side2
    def getSide3(self):
        return self.side3

    def getArea(self):
        s = self.getPerimeter() / 2
        return math.sqrt( s * (s - self.side1) * (s - self.side2) * (s - self.side3) )
    def getPerimeter(self):
        return self.side1 + self.side2 + self.side3

    def getFill (self):
        return bool(self.filled)

side1, side2, side3, color, filled = input("Please enter values for side1, side2, side3, color, filled (1 or 0): ").split(",")
t = Triangle(color, float(filled), float(side1), float(side2), float(side3))
print("Triangle has an area =", str(t.getArea()), ", a perimeter =", str(t.getPerimeter()), ", color =", str(t.getColor()), ", with fill =", str(t.getFill()))
