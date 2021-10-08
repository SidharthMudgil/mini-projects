import random


# Function to convert string into list
def tolist(string):
    li = []
    li[:] = string
    return li


# Function to generate random password
def generate():
    """
    A strong password must be 12-digit long containing at least
    1 lower-case alphabet [a-z]
    1 upper-case alphabet [A-Z]
    1 special symbol like [@!?]
    1 numeric digit [0-9]
    """
    # num contains list of numbers
    num = tolist("1234567890")

    # lower contains list of lower-case alphabets
    lower = tolist("abcdefghijklmnopqrstuvwxyz")

    # lower contains list of upper-case alphabets
    upper = tolist("ABCDEFGHIJKLMNOPQRSTUVWXYZ")

    # symbol contains all special symbols
    symbol = tolist(" ~`!@#$%^&*()_+-={}|[]\\;':\",./<>?")

    # mixed contains all numbers, alphabets and special symbols
    mixed = num + lower + upper + symbol

    # will generate 1-1 number, alphabet both lower & upper and symbols
    password = random.choice(num) + random.choice(lower) + random.choice(upper) + random.choice(symbol)

    # will generate other 8 digit 4 digits already made
    for i in range(9):
        password = tolist(password)
        random.shuffle(password)
        password = ''.join(password)
        password += random.choice(mixed)

    return password


if __name__ == "__main__":
    for i in range (5):
        print("Generated Password: ", generate())