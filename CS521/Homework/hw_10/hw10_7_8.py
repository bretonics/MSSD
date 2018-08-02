import time

class StopWatch:
    """Stopwatch class"""
    def __init__(self):
        self.__startTime = time.time()
        # self.__endTime = endTime

    def getStartTime(self):
        return self.__startTime
    def getEndTime(self):
        return self.__endTime

    def start(self):
        self.__startTime = time.time()
    def stop(self):
        self.__endTime = time.time()
    def getElapsedTime(self):
        return self.getEndTime() - self.getStartTime()

sum = 0
sw = StopWatch()
start = sw.getStartTime()
for x in range(1,1000001):
    sum += x
sw.stop()
print("Execution time elapsed", sw.getElapsedTime())
