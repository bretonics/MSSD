from Account import Account

# Create 10 accounts
accounts = []
for a in range(0,10):
    accounts.append(Account(a))

while True:
    id = eval(input("\nEnter account id: "))
    while 0 < id > 9:
        print("Incorrect account id provided.")
        id = eval(input("Please enter correct acocunt id: "))
    account = accounts[id]
    response = 0
    while response != 4:
        print("\nMain menu\n \
1: check balance\n \
2: withdraw\n \
3: deposit\n \
4: exit")
        response = eval(input("Enter a choice: "))
        if response == 1:
            print("The balance is", account.getBalance())
        elif response == 2:
            w = eval(input("Enter amount to withdraw: "))
            account.withdraw(w)
        elif response == 3:
            d = eval(input("Enter amount to deposit: "))
            account.deposit(d)
