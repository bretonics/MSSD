class Location():
    """Locates maximal value and its location in a two-dimensional list"""
    def __init__(self, row, column, maxValue):
        self.row = row
        self.column = column
        self.maxValue = maxValue

    def getRow(self):
        return self.row
    def getColumn(self):
        return self.column
    def getMaxValue(self):
        return self.maxValue

def locateLargest(a):
    size = len(a)
    maxValue = a[0][0] # default max value to compare
    row = 0
    column = 0

    for i in range(size): # loop rows
        for j in range(len(a[i])): # loop columns
            if maxValue < a[i][j]: # if current max value is less than interogated value
                maxValue = a[i][j]
                row = i
                column = j

    return Location(row, column, maxValue)


rows, columns = eval(input("Please enter number of rows and columns in list: "))
matrix = []
for row in range(rows):
    response = input("Enter row {}: ".format(row))
    response = [eval(x) for x in response.split(",")] # convert to numbers
    matrix.append(response)

location = locateLargest(matrix)
print("The location of the largest element", location.getMaxValue(), "is at (", location.getRow(), ", ", location.getColumn(), ")")
