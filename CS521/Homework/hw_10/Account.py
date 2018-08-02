#!/usr/bin/env python

class Account:
    def __init__(self, id = 0, balance = 100.00, annualInterestRate = 0.0):
        self.__id = id
        self.__balance= balance
        self.__annualIntterestRate = annualInterestRate

    def getID(self):
        return self.__id
    def getBalance(self):
        return self.__balance
    def getAnnualInterestRate(self):
        return self.__annualIntterestRate
    def getMonthlyInterestRate(self):
        return (self.getAnnualInterestRate() / 100) / 12
    def getMonthlyInterest(self):
        return self.__balance * self.getMonthlyInterestRate()

    def withdraw(self, amount):
        self.__balance = self.__balance - amount
    def deposit(self, amount):
        self.__balance = self.__balance + amount

    def setID(self, id):
        self.__id = id
    def setBalance(self, balance):
        self.__balance = balance
    def setAnnualInterestRate(self, air):
        self.__annualIntterestRate = air
