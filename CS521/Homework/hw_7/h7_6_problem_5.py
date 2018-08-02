def bubbleSort(list):
    size = len(list)

    # Traverse as many times as there are elements
    for x in range(size):
        # Last element already sorted, traverse as many as times as there are elements - 1
        current = size-x-1
        for i in range(0, current):
            # If next element is bigger, switch
            if list[i] > list[i+1]:
                tmp = list[i+1]
                list[i+1] = list[i]
                list[i] = tmp

    # Everything sorted at this point, put all negative numbers at end of list
    for x in range(size):
        if list[x] < 0:
            list.append(list.pop(0))

    return list

x = [2, 6, -6, -3, 1, 19]
print(bubbleSort(x))
