from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.metrics.pairwise import cosine_similarity


def calculate_similarity(text1, text2):
    vectorizer = TfidfVectorizer()
    vectors = vectorizer.fit_transform([text1, text2])

    similarity = cosine_similarity(vectors[0], vectors[1])[0][0]

    return similarity * 100


text1 = input("Enter first text: ")
text2 = input("Enter second text: ")

score = calculate_similarity(text1, text2)

print(f"\nSimilarity: {score:.2f}%")
