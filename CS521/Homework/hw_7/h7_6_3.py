# Return the reversal of an integer, e.g. reverse(456) returns # 654
def reverse(number):
    return number[::-1]

# Return true if number is a palindrome
def isPalindrome(number):
    if reverse(number) == number:
        return True

# Ask user
integer = input("Please enter integer to check if palindrome: ")
if isPalindrome(integer):
    print("Number is a palindrome")
else:
    print("Number is NOT a palindrome")
