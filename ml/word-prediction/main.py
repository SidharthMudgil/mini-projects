from keras.models import load_model
import numpy as np
import random
import sys


raw_text = open('the_jungle_book.txt', 'r', encoding='utf-8').read()
raw_text = raw_text.lower()
raw_text = ''.join(c for c in raw_text if not c.isdigit())
chars = sorted(list(set(raw_text)))
char_to_int = dict((c, i) for i, c in enumerate(chars))
int_to_char = dict((i, c) for i, c in enumerate(chars))
n_chars = len(raw_text)
n_vocab = len(chars)
seq_length = 60


# take all predictions and return prediction with highest probability 
def sample(preds):
    preds = np.asarray(preds).astype('float64')
    preds = np.log(preds)
    exp_preds = np.exp(preds) #exp of log (x), isn't this same as x??
    preds = exp_preds / np.sum(exp_preds)
    probas = np.random.multinomial(1, preds, 1) 
    return np.argmax(probas)



model = load_model("next_word_model.h5")
model.load_weights(r"C:\Users\Sidharth Mudgil\Desktop\Deep Learning\word prediction\saved_weights\saved_weights-10-1.1814.hdf5")
start_index = random.randint(0, n_chars - seq_length - 1)

#Initiate generated text and keep adding new predictions and print them out
generated = ''
sentence = raw_text[start_index: start_index + seq_length]
# sentence = 'my name is'
generated += sentence

print('Seed for our text prediction: "' + sentence + '"\n\n')
print(sentence, end='')


for i in range(50):
    x_pred = np.zeros((1, seq_length, n_vocab))
    for t, char in enumerate(sentence):
        x_pred[0, t, char_to_int[char]] = 1.

    preds = model.predict(x_pred, verbose=0)[0]
    next_index = sample(preds)
    next_char = int_to_char[next_index]

    generated += next_char
    sentence = sentence[1:] + next_char

    sys.stdout.write(next_char)
    sys.stdout.flush()