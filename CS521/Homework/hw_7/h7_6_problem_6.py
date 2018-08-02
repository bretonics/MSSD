# NOTE: Description in the homework assignment has incorrect symatics -
# “TTAC” complementary sequence is “AATG”, NOT “GTAA” which is actually the reverse complement.
# Professor emailed stating he wanted reverse complement check


def reverseComplement(x, y):

    if len(x) != len(y):
        return False

    codons = { "A" : "T", "T" : "A", "C" : "G", "G" : "C"}
    seq1 = [a for a in x]
    seq2 = [a for a in y]

    i = -1
    for nuc1 in seq1:
        if codons[nuc1] == seq2[i]:
            pass
        else:
            return False
        i = i - 1
    return True



# True, this is the reverse complement
if reverseComplement("TTAC","GTAA"):
    print("The two sequences are their reverse complements")
else:
    print("NOT reverse complements")

# False, this is just the complement
if reverseComplement("TTAC","AATC"):
    print("The two sequences are their reverse complements")
else:
    print("NOT reverse complements")

# False, this is just the reverse
if reverseComplement("TTAC","CATT"):
    print("The two sequences are their reverse complements")
else:
    print("NOT reverse complements")
