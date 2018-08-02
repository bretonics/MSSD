import math

class QuadraticEquation:
    """Quadratic equation class."""
    def __init__(self, a, b, c):
        self.__a = a
        self.__b = b
        self.__c = c

    def getA(self):
        return self.__a
    def getB(self):
        return self.__b
    def getC(self):
        return self.__c

    def getDiscriminant(self):
        d = self.__b ** 2 - (4 * self.__a * self.__c)
        if d == 0:
            return 0
        else:
            return d

    def getRoot1(self):
        return (-self.__b + math.sqrt(self.getDiscriminant())) / (2 * self.__a)

    def getRoot2(self):
        return (-self.__b - math.sqrt(self.getDiscriminant())) / (2 * self.__a)


a, b, c = eval(input("Enter value for a, b, c: "))
o = QuadraticEquation(a, b, c)
d = o.getDiscriminant()
if d > 0:
    print(o.getRoot1())
    print(o.getRoot2())
elif d == 0:
    print(o.getRoot1())
else:
    print("The equation has no roots")
