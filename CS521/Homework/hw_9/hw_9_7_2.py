#!/usr/bin/env python

class Stock():
    # Constructor
    def __init__(self, symbol, name, previousClosingPrice, currentPrice):
        self.__symbol = symbol
        self.__name = name
        self.__previousClosingPrice = previousClosingPrice
        self.__currentPrice = currentPrice

    def getName(self):
        return self.__name
    def getSymbol(self):
        return self.__symbol
    def getPreviousClosingPrice(self):
        return self.__previousClosingPrice
    def getCurrentPrice(self):
        return self.__currentPrice

    def getChangePercent(self):
        return round( ( (self.__currentPrice - self.__previousClosingPrice) / self.__previousClosingPrice) * 100, 2)

    def setPreviousClosingPrice(self,previousClosingPrice):
        self.__previousClosingPrice = previousClosingPrice
    def setCurrentPrice(self, currentPrice):
        self.__currentPrice = currentPrice


stock = Stock("INTC", "Intel Corporation", 20.5, 20.35)

print("{}, with ticker symbol \"{}\" previously closed at {}. Currently at {}, there is a {}% change.".format(stock.getName(), stock.getSymbol(), stock.getPreviousClosingPrice(), stock.getCurrentPrice(), stock.getChangePercent()))
