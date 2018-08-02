class ListOfStrings:
    """Store text file as list of strings where each string
    represents a line"""
    def __init__(self):
        self.cursor = 0

    f_in = open("zen_of_python.txt", "r")
    file_text = f_in.readlines() # read file into list of strings

    def cmd_h(self):
        """move cursor one character to the left"""
        if self.cursor > 0:
            self.cursor = self.cursor - 1
        else:
            return self.cursor
        self.get_text(ListOfStrings.file_text)

    def cmd_I(self):
        """move cursor one character to the right"""
        ls = ListOfStrings.file_text
        length = 0
        prev = 0
        for line in ls:
            length = len(line) + length
            if prev <= self.cursor < length:
                self.cursor = self.cursor + 1
                prev = length
                continue
        self.get_text(ListOfStrings.file_text)

    def cmd_j(self):
        """move cursor down vertically one line"""
        ls = ListOfStrings.file_text
        numLines = len(ls)
        length = 0
        prev = 0
        for counter, line in enumerate(ls):
            length = len(line) + length
            if prev <= self.cursor <= length:
                if counter != numLines:
                    self.cursor = self.cursor + len(line)
                    self.get_text(ListOfStrings.file_text)
                    break
                prev = length

    def cmd_k(self):
        """move cursor up vertically one line"""
        ls = ListOfStrings.file_text
        length = 0
        older = 0
        prev = 0
        for counter, line in enumerate(ls):
            length = len(line) + length
            if prev <= self.cursor <= length:
                if counter != 0:
                    self.cursor = self.cursor - older
                    self.get_text(ListOfStrings.file_text)
                    break
                prev = length
            older = len(line)

    def cmd_X(self):
        """delete the character to the left of the cursor"""
        ls = ListOfStrings.file_text
        length = 0
        prev = 0
        result = []
        if self.cursor > 0:
            for line in ls:
                length = len(line) + length
                if prev <= self.cursor < length:
                    result.append(line[0:self.cursor-1] + line[self.cursor:])
                    self.cursor = self.cursor - 1
                    prev = length
                    continue
                result.append(line)
            self.get_text(result)
        else:
            self.get_text(ListOfStrings.file_text)

    def cmd_D(self):
        """remove on current line from cursor to the end"""
        ls = ListOfStrings.file_text
        length = 0
        prev = 0
        result = []
        for line in ls:
            length = len(line) + length
            if prev <= self.cursor <= length:
                prev = length
                continue
            result.append(line)
        self.get_text(result)

    def cmd_dd(self):
        """delete current line and move cursor to the beginning of next line"""
        ls = ListOfStrings.file_text
        length = 0
        prev = 0
        result = []
        for line in ls:
            length = len(line) + length
            if prev <= self.cursor <= length:
                self.cursor = prev
                prev = length
                continue
            result.append(line)
        self.get_text(result)

    def cmd_ddp(self, l1, l2):
        """transpose two adjacent lines"""
        ls = ListOfStrings.file_text
        tmp1 = ls[l1]
        tmp2 = ls[l2]
        result = []
        for counter, line in enumerate(ls):
            if counter == l1:
                result.append(tmp2)
            elif counter == l2:
                result.append(tmp1)
            else:
                result.append(line)
        self.get_text(result)

    def cmd_n(self, s):
        """search for next occurrence of a string (assume that string to be searched is fully in one line."""
        ls = ListOfStrings.file_text
        length = 0
        prev = 0
        for line in ls:
            length = len(line) + length
            if line.find(s):
                self.cursor = prev + line.find(s)
                break
            prev = length
        self.get_text(ListOfStrings.file_text)

    def cmd_wq(self):
        """write your representation as text file and save it"""
        out_file = open("ListOfStrings.txt", "w")
        out_file.write(str(self.get_text(ListOfStrings.file_text)))
        out_file.close()
        print("File saved to ListOfStrings.txt")

    def setCursor(self, cursor):
        self.cursor = cursor
        self.get_text(ListOfStrings.file_text)

    def get_text(self, ls):
        result = ""
        length = 0
        prev = 0
        for line in ls:
            length = len(line) + length
            if prev <= self.cursor <= length:
                end = self.cursor - length
                result = result + line[0:end] + "^" + line[end:]
                prev = length
                continue
            result =  result + line
        print(result)
        return(result)

def main():
    print("--- Create instance, print initial\n")
    fo = ListOfStrings()
    fo.get_text(ListOfStrings().file_text)
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
    print("--- Remove current line from cursor\n")
    fo.cmd_D()
    print("--- Delete current line and move cursor to beginning of next line\n")
    fo.cmd_dd()
    print("--- Transpose lines 2 and 3\n")
    fo.cmd_ddp(2,3)
    print("--- Search next \"better\" string\n")
    fo.cmd_n("better")
    print("--- Write text and save as file\n")
    fo.cmd_wq()
main()
