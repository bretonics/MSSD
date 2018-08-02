def displaySortedNumbers(num1, num2, num3):
    numbers = []
    numbers.append(num1)
    numbers.append(num2)
    numbers.append(num3)
    numSorted = sorted(numbers)
    print("The sorted numbers are", str(numSorted).strip('[]'))

num1, num2, num3 = eval(input("Enter three numbers: "))
displaySortedNumbers(num1, num2, num3)
