import re


def validator(code):
    digit = r'[0-9]'
    upper = r'[A-Z]'
    lower = r'[a-z]'
    symbol = r'[$@#!]'

    length = len(code)
    points = 5

    # Check whether password length is more than 6 digit or not
    if length < 6:
        print("password length must be greater than 6")
        points = points - 1
        
    # Check whether password contains digit or not
    if not re.search(digit, code):
        print("password must contain a number 0-9")
        points = points - 1

    # Check whether password contains a upper-case letter or not
    if not re.search(upper, code):
        print("password must contain a upper-case letter A-Z")
        points = points - 1

    # Check whether password contains a lower-case letter or not
    if not re.search(lower, code):
        print("password must contain a lower-case letter a-z")
        points = points - 1

    # Check whether password contains a special symbol or not
    if not re.search(symbol, code):
        print("password must contain a special symbol ($ @ # !)")
        points = points - 1

    if points >= 5:
        print("Strong Password")


if __name__ == "__main__":
    password = input("Password : ")
    validator(password)