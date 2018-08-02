def getPentagonalNumber(n):
    return int(n * (3 * n - 1) / 2)

for n in range(1, 101):
    getPentagonalNumber(n)
    print(getPentagonalNumber(n), "\t", end="")
    if n % 10 == 0:
        print("\n")
