from keras.models import Sequential
from keras.layers import Dense, LSTM
from keras.optimizers import RMSprop
from keras.callbacks import ModelCheckpoint
import numpy as np
import pickle


# loading
raw_text = open('the_jungle_book.txt', 'r', encoding='utf-8').read()
raw_text = raw_text.lower()

# removing numbers [data cleaning]
raw_text = ''.join(c for c in raw_text if not c.isdigit())
n_chars = len(raw_text)
print('n_chars:', n_chars)

# list of all characters
chars = sorted(list(set(raw_text)))
n_vocab = len(chars)
print('n_vocab:', n_vocab)

# encoding characters to integer using dictionary with characters as keys and indices as values
char_to_int = dict((c, i) for i, c in enumerate(chars))

# decoding integers to characters using dictionary with indices as keys and characters as values
int_to_char = dict((i, c) for i, c in enumerate(chars))

# length of each input sequence
seq_length = 60
# instead of moving 1 letter at a time, taking 10 steps
step = 10
# X values => input sequence
sentences = []
# Y values => output character
next_chars = []
for i in range(0, n_chars - seq_length, step):
    sentences.append(raw_text[i: i + seq_length])   # sequence in
    next_chars.append(raw_text[i + seq_length])     # sequence out
n_patterns = len(sentences)

x = np.zeros((len(sentences), seq_length, n_vocab), dtype=np.bool)
y = np.zeros((len(sentences), n_vocab), dtype=np.bool)
for i, sentence in enumerate(sentences):
    for t, char in enumerate(sentence):
        x[i, t, char_to_int[char]] = 1
    y[i, char_to_int[next_chars[i]]] = 1

print('X:', x.shape)
print('Y:', y.shape)

# building the model
model = Sequential()
model.add(LSTM(128, input_shape=(seq_length, n_vocab)))
model.add(Dense(n_vocab, activation='softmax'))

optimizer = RMSprop(lr=0.01)
model.compile(loss='categorical_crossentropy', optimizer=optimizer)
model.summary()

# saving the model
filepath = "saved_weights/saved_weights-{epoch:02d}-{loss:.4f}.hdf5"
checkpoint = ModelCheckpoint(
    filepath, monitor='loss', verbose=1, save_best_only=True, mode='min')
callbacks_list = [checkpoint]

history = model.fit(x, y, batch_size=128, epochs=10, callbacks=callbacks_list)
model.save('next_word_model.h5')
pickle.dump(history, open("history.p", "wb"))
