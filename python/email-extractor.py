import re

def extract_emails(text):
    # Improved regular expression pattern for email extraction
    email_pattern = r"\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Z|a-z]{2,7}\b"

    # Finding all matched email patterns
    emails = re.findall(email_pattern, text)

    # Checking if any emails were found
    if emails:
        for email in emails:
            print(email)
    else:
        print("No valid email addresses found in the provided text.")

if __name__ == "__main__":
    string = input("Enter a text to extract email addresses from: ")
    extract_emails(string)
