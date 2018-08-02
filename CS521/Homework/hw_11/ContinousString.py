class ContinousString:
    """Store text file as continous string (newline char as separator)"""
    def __init__(self):
        self.cursor = 0

    f_in = open("zen_of_python.txt", "r")
    file_text = f_in.read()

    def cmd_h(self):
        """move cursor one character to the left"""
        if self.cursor > 0:
            self.cursor = self.cursor - 1
        else:
            return self.cursor
        self.get_text(ContinousString.file_text)

    def cmd_I(self):
        """move cursor one character to the right"""
        if self.cursor < len(ContinousString.file_text):
            self.cursor = self.cursor + 1
        else:
            return self.cursor
        self.get_text(ContinousString.file_text)

    def cmd_j(self):
        """move cursor down vertically one line"""
        ls = [l + "\n" for l in ContinousString.file_text.split("\n")]
        numLines = len(ls)
        length = 0
        prev = 0
        for counter, line in enumerate(ls):
            length = len(line) + length
            if prev <= self.cursor <= length:
                if counter != numLines:
                    self.cursor = self.cursor + len(line)
                    self.get_text(ContinousString.file_text)
                    break
                prev = length

    def cmd_k(self):
        """move cursor up vertically one line"""
        ls = [l + "\n" for l in ContinousString.file_text.split("\n")]
        length = 0
        older = 0
        prev = 0
        for counter, line in enumerate(ls):
            length = len(line) + length
            if prev <= self.cursor <= length:
                if counter != 0:
                    self.cursor = self.cursor - older
                    self.get_text(ContinousString.file_text)
                    break
                prev = length
            older = len(line)

    def cmd_X(self):
        """delete the character to the left of the cursor"""
        if self.cursor > 0:
            text = ContinousString.file_text[0:self.cursor-1] + ContinousString.file_text[self.cursor:]
            self.cursor = self.cursor - 1
            self.get_text(text)
        else:
            self.get_text(ContinousString.file_text)

    def cmd_D(self):
        """remove on current line from cursor to the end"""
        ls = [l + "\n" for l in ContinousString.file_text.split("\n")]
        length = 0
        prev = 0
        result = ""
        for line in ls:
            length = len(line) + length
            if prev <= self.cursor <= length:
                prev = length
                continue
            result = result + line
        self.get_text(result)

    def cmd_dd(self):
        """delete current line and move cursor to the beginning of next line"""
        ls = [l + "\n" for l in ContinousString.file_text.split("\n")]
        length = 0
        prev = 0
        result = ""
        for line in ls:
            length = len(line) + length
            if prev <= self.cursor <= length:
                self.cursor = prev
                prev = length
                continue
            result = result + line
        self.get_text(result)

    def cmd_ddp(self, l1, l2):
        """transpose two adjacent lines"""
        ls = [l + "\n" for l in ContinousString.file_text.split("\n")]
        tmp1 = ls[l1]
        tmp2 = ls[l2]
        result = ""
        for counter, line in enumerate(ls):
            if counter == l1:
                result = result + tmp2
            elif counter == l2:
                result = result + tmp1
            else:
                result = result + line
        self.get_text(result)

    def cmd_n(self, s):
        """search for next occurrence of a string (assume that string to be searched is fully in one line."""
        self.cursor = ContinousString.file_text.find(s)
        self.get_text(ContinousString.file_text)

    def cmd_wq(self):
        """write your representation as text file and save it"""
        out_file = open("ContinousString.txt", "w")
        out_file.write(str(self.get_text(ContinousString.file_text)))
        out_file.close()
        print("File saved to ContinousString.txt")

    def setCursor(self, cursor):
        self.cursor = cursor
        self.get_text(ContinousString.file_text)

    def get_text(self, text):
        print(text[0:self.cursor] + "^" + text[self.cursor:])
        return(text[0:self.cursor] + "^" + text[self.cursor:])


def main():
    print("--- Create instance, print initial\n")
    fo = ContinousString()
    fo.get_text(ContinousString().file_text)
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
