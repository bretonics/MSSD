class Rectangle2D:
    def __init__(self, x, y, width, height):
        self.x = x
        self.y = y
        self.width = width
        self.height = height

    def getX(self):
        return self.x
    def getY(self):
        return self.y
    def getHeight(self):
        return self.height
    def getWidth(self):
        return self.width

def getRectangle(points):

    def getMax(points,n):
        x = points[n]
        length = len(points)
        for i in range(n, length, 2):
            if x < points[i]:
                x = points[i]
        return x

    def getMin(points, n):
        x = points[n]
        length = len(points)
        for i in range(n, length , 2):
            if x > points[i]:
                x = points[i]
        return x

    centerX = (getMax(points, 0) + getMin(points, 0)) /2
    width = (getMax(points, 0) - getMin(points, 0))

    centerY = (getMax(points, 1) + getMin(points, 1)) /2
    height = (getMax(points, 1) - getMin(points, 1))

    return Rectangle2D(centerX, centerY, width, height) # return rectangle object

points = input("Enter points: ")
points = [eval(x) for x in points.split()]
rectangle = getRectangle(points) # rectangle object returned form function
print("The bounding rectangle is centered at (", rectangle.getX(), ",", rectangle.getY(), ") with width", rectangle.getWidth(), "and height", rectangle.getHeight())
