import random

# Function to generate a strong 12-character password
def generate_password():
    """
    A strong password must be 12 characters long containing at least:
    - 1 lowercase letter [a-z]
    - 1 uppercase letter [A-Z]
    - 1 special character [@!?...]
    - 1 digit [0-9]
    """
    digits = list("0123456789")
    lowercase = list("abcdefghijklmnopqrstuvwxyz")
    uppercase = list("ABCDEFGHIJKLMNOPQRSTUVWXYZ")
    symbols = list("~`!@#$%^&*()_+-={}|[]\\;':\",./<>?")

    # Ensure at least one of each required character
    password_chars = [
        random.choice(digits),
        random.choice(lowercase),
        random.choice(uppercase),
        random.choice(symbols)
    ]

    # Fill the remaining 8 characters with a mix of all
    all_chars = digits + lowercase + uppercase + symbols
    password_chars += random.choices(all_chars, k=8)

    # Shuffle the final password list and join into a string
    random.shuffle(password_chars)
    return ''.join(password_chars)

if __name__ == "__main__":
    for i in range(5):
        print(f"Generated Password {i+1}: {generate_password()}")
