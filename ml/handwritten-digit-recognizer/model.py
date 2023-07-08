# import os
# from turtle import mode
# import cv2
import numpy as np
import matplotlib.pyplot as plt
import tensorflow as tf

# keras labelled image dataset
mnist = tf.keras.datasets.mnist
# divided dataset into training and testing data
(x_train, y_train), (x_test, y_test) = mnist.load_data()

# normalized values to 0-1
x_train = tf.keras.utils.normalize(x_train, axis=1)
x_test = tf.keras.utils.normalize(x_test, axis=1)

# defined sequential neural network model
model = tf.keras.models.Sequential()
# adding layers to the model
model.add(tf.keras.layers.Flatten(input_shape=(28, 28)))
model.add(tf.keras.layers.Dense(128, activation='relu'))
model.add(tf.keras.layers.Dense(128, activation='relu'))
model.add(tf.keras.layers.Dense(10, activation='softmax'))

# compiling the model
model.compile(optimizer='adam',
              loss='sparse_categorical_crossentropy', metrics=['accuracy'])

# training the data
model.fit(x_train, y_train, epochs=3)

# saving the model
model.save('handwritten.model')

# evaluating accuracy and loss
loss, accuracy = model.evaluate(x_test, y_test)

print('loss:', loss)
print('accuracy:', accuracy)
