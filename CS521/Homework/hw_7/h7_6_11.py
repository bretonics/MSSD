def computeCommission(salesAmount):
    commission = 0
    if salesAmount > 10000:
        # Portion to be commissioned
        portion = salesAmount - 10000
        commission = commission + (portion * 0.12)
        # Remaining sales amount to be commissioned
        salesAmount = salesAmount - portion
    if 10000 <= salesAmount > 5000:
        # Portion to be commissioned
        portion = salesAmount - 5000
        commission = commission + (portion * 0.10)
        # Remaining sales amount to be commissioned
        salesAmount = salesAmount - portion
    if salesAmount <= 5000:
        commission = commission + ( salesAmount * 0.08)

    return commission

print("Sales Amount\tCommision")
for salesAmount in range(10000,105000,5000):
    print(salesAmount, end='\t\t')
    print(computeCommission(salesAmount))
