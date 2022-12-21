import os
from turtle import mode
import cv2
import numpy as np
import matplotlib.pyplot as plt
import tensorflow as tf

imgs = os.listdir('img')
model = tf.keras.models.load_model('handwritten.model')

for _ in imgs:
    try:    
        img = cv2.imread(f'img/{_}')[:,:,0]
        img = cv2.resize(img, (28, 28))
        img = np.invert(np.array([img]))
        prediction = model.predict(img)
        print(f'Prediction \'{_}\': {np.argmax(prediction)}')
        plt.imshow(img[0], cmap=plt.cm.binary)
        plt.show()
    except:
        print("Error")