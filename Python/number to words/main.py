def three_digit(num):
    ones = ['one', 'two', 'three', 'four', 'five', 'six', 'seven', 'eight', 'nine']
    tens = ['ten', 'twenty', 'thirty', 'forty', 'fifty', 'sixty', 'seventy', 'eighty', 'ninety']
    name = ['eleven', 'twelve', 'thirteen', 'fourteen', 'fifteen', 'sixteen', 'seventeen', 'eighteen', 'nineteen']

    rem = quo = 0

    # Will find length of number using len function by temporary converting into string
    digit = len(str(num))

    # Splitting hundredth place and up to tens place
    if digit == 3:
        x = num // 100          # hundredth place number
        num = num - (x * 100)   # tens and ones place number
        print(ones[x - 1], "hundred ", end='')

    # For 100, 200, ...
    if num == 0:
        return ""

    quo = num // 10  # Will find quotient of number
    rem = num % 10   # Will fund remainder of number

    # One digit Number
    if quo == 0:
        return ones[rem - 1]

    # Two digit number ending with zero
    elif rem == 0:
        return tens[quo - 1]

    # Two digit starting from 1 except 10
    elif quo == 1 and rem != 0:
        return name[rem - 1]

    # Others two digit numbers
    else:
        return f"{tens[quo - 1]}-{ones[rem - 1]}"


def split_up(num):
    units = ['thousand', 'million', 'billion', 'trillion', 'quadrillion', 'quintillion', 'sextillion', 'septillion']

    # If number is zero it will return zero and if number is negative it will convert into positive
    if num == 0:
        return "zero"
    elif num < 0:
        num = -num
        print("Negative of", end=" ")

    # Count the digits of number
    digit = len(str(num))

    # Will work if number is greater than 999
    while digit > 3:

        # Total number of 3-3 digits pair in the number ex: 12345 -> term = 1
        term = 0

        # Find total number of terms multiplied by 3
        if digit > 3 and digit % 3 != 0:
            term = (digit // 3) * 3
        else:
            term = ((digit // 3) - 1) * 3

        # Split number into two for ex: 1234 -> (1, 234); 123456 -> (123,456)
        front_3 = num // (10 ** term)       # front three digit
        num = num - front_3 * (10 ** term)  # left digit

        # storing text for number of front part with its place 1234 -> 1 -> one thousand
        word = three_digit(front_3) + " " + units[term // 3 - 1]
        print(word, end=' ')
        digit = len(str(num))

    # For numbers less than 1000
    return three_digit(num)


if __name__ == "__main__":
    try:
        number = int(input("Enter Number : "))
        print(split_up(number))
    except ValueError:
        print("Only Numbers Allowed")