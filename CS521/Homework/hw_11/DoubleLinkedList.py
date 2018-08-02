class DoubleLinkedList:
    class Node:
        """Store text file where each node stores one line as a double DoubleLinkedList"""
        def __init__(self, element):
            self.element = element
            self.prev = None
            self.next = None

    # Create DoubleLinkedList representation of file
    file_text = open("zen_of_python.txt", "r").readlines() # read file into list of strings
    nodes = []
    for counter, line in enumerate(file_text):
        node = Node(line)
        if counter == 0:
            nodes.append(node)
        else:
            prev = nodes[counter-1]
            prev.next = node
            node.prev = prev
            nodes.append(node)

    def __init__(self):
        self.start = DoubleLinkedList.nodes[0]
        self.prev = None
        self.next = None

        self.cursor = DoubleLinkedList.nodes[0]
        self.delta = 0


    def cmd_h(self):
        """move cursor one character to the left"""
        self.delta = self.delta - 1
        self.get_text()

    def cmd_I(self):
        """move cursor one character to the right"""
        self.delta = self.delta + 1
        self.get_text()


    def cmd_j(self):
        """move cursor down vertically one line"""
        node = self.start
        while node is not None:
            if node == self.cursor:
                if node.next is not None:
                    self.cursor = node.next
                break
            node = node.next
        self.get_text()

    def cmd_k(self):
        """move cursor up vertically one line"""
        node = self.start
        while node is not None:
            if node == self.cursor:
                if node.prev is not None:
                    self.cursor = node.prev
                break
            node = node.next
        self.get_text()

    def cmd_X(self):
        """delete the character to the left of the cursor"""
        node = self.start
        while node is not None:
            if node == self.cursor:
                line = node.element
                node.element = line[0:self.delta-1] + line[self.delta:]
                self.delta = self.delta - 1
                break
            node = node.next
        self.get_text()

    def cmd_D(self):
        """remove on current line from cursor to the end"""
        node = self.start
        while node is not None:
            if node == self.cursor:
                line = node.element
                node.element = line[0:self.delta] + "\n"
                break
            node = node.next
        self.get_text()

    def cmd_dd(self):
        """delete current line and move cursor to the beginning of next line"""
        node = self.start
        while node is not None:
            if node == self.cursor:
                previous = node.prev
                next = node.next

                if previous is None:
                    self.start = next
                    next.prev = None
                    self.cursor = next
                elif next is None:
                    previous.next = None
                    self.cursor = previous
                else:
                    previous.next = next
                    next.prev = previous
                    self.cursor = next

                self.delta = 0
                break
            node = node.next
        self.get_text()

    def cmd_ddp(self):
        """transpose two adjacent lines"""
        start = self.start
        previous = start.prev
        next = start.next

        start.next = next.next
        start.prev = next

        self.start = next
        next.prev = previous
        next.next = start

        self.get_text()

    def cmd_n(self,s):
        """search for next occurrence of a string (assume that string to be searched is fully in one line."""
        length = 0
        node = self.start
        while node is not None:
            line = node.element
            length += len(line)
            if line.find(s):
                self.cursor = node
                self.delta = line.find(s)
                break
            node = node.next
        self.get_text()

    def cmd_wq(self):
        """write your representation as text file and save it"""
        out_file = open("DoubleLinkedList.txt", "w")
        out_file.write(str(self.get_text()))
        out_file.close()
        print("File saved to DoubleLinkedList.txt")

    def setCursor(self, position):
        prev = 0
        length = 0
        node = self.start
        while node is not None:
            length += len(node.element)
            if prev <= position <= length:
                self.cursor = node
                self.delta = position - prev
                prev = length
            node = node.next
        self.get_text()

    def get_text(self):
        result = ""
        node = self.start
        while node is not None:
            line = node.element
            if self.cursor == node:
                result = result + line[0:self.delta] + "^" + line[self.delta:]
            else:
                result = result + line
            node = node.next
        print(result)
        return(result)


def main():
    print("--- Create instance, print initial\n")
    fo = DoubleLinkedList()
    fo.get_text()
    print("--- Setting cursor randomly at 25\n")
    fo.setCursor(25)
    print("--- Move cursor 1 to the right\n")
    fo.cmd_I()
    print("--- Move cursor 1 to the left\n")
    fo.cmd_h()
    print("--- Move cursor down vertically one line\n")
    fo.cmd_j()
    print("--- Move cursor up vertically one line\n")
    fo.cmd_k()
    print("--- Delete the character to the left of cursor\n")
    fo.cmd_X()
    print("--- Remove text from cursor to end of current line\n")
    fo.cmd_D()
    print("--- Delete current line and move cursor to beginning of next line\n")
    fo.cmd_dd()
    print("--- Transpose lines 1 and 2\n")
    fo.cmd_ddp()
    print("--- Search next \"better\" string\n")
    fo.cmd_n("better")
    print("--- Write text and save as file\n")
    fo.cmd_wq()
main()
