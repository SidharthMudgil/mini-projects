import re


# function to extract email from the text
def extract(text):
    # The pattern of email stored in a regex
    mail = r"[\w+_%]+@[\S]+\.+[\w+_%]+"

    # finding all matched patterns
    emails = re.findall(mail, text)

    # printing all matched patterns
    for email in emails:
        print(email)


if __name__ == "__main__":
    string = input("->")
    extract(string)
