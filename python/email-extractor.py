import re

def extract_emails(text):
    """
    Extracts all valid email addresses from the input text using regex.

    Parameters:
    - text (str): The input string containing potential email addresses.

    Returns:
    - List[str]: A list of extracted email addresses.
    """
    # Regex pattern to match valid email addresses
    email_pattern = r"\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}\b"

    # Find all matches
    emails = re.findall(email_pattern, text)

    if emails:
        print("Extracted Email(s):")
        for email in emails:
            print("-", email)
    else:
        print("No valid email addresses found in the provided text.")

if __name__ == "__main__":
    user_input = input("Enter a text to extract email addresses from: ")
    extract_emails(user_input)
