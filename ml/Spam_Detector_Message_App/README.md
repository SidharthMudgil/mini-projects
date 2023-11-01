# Spam Detector Project

## Description
--> The ML Spam Detection is a Flask web application which predicts whether the message is a spam or not. SMS Spam Collection dataset from Kaggle was used to classify the messages into 2 classes- Ham(1) and Spam(0) using stemming, Bag of Words model and Naive Bayes Classifiers.

--> My objective was to reduce the FP(False Positive) value as much as possible for this case and in order to overcome this issue, Naive Bayes classifiers namely, MultinomiallNB and BernoulliNB were implemented to get best accuracy_score and precision_score from the dataset.

## Requirements
Python
Flask
Machine learning libraries

## Installation
Run all commands

* Assuming that model and .pkl files are in same folder
* After that app.py file will be created with essential backend code.
* Create new virtual python environment python3 -m venv venv
* Activate virtual python environment source venv/bin/activate
* Install all the libraries mentioned in Requirements using pip install library_name
* Run FlaskApp file python app.py
* Go to your browser and type http://127.0.0.1:5000/ in the address bar.
* Hurray! That's it.

## Result
| ![Input](https://github.com/SidharthMudgil/mini-projects/blob/main/ml/Spam_Detector_Message_App/images/Screenshot%20(408).png) | ![Output](https://github.com/SidharthMudgil/mini-projects/blob/main/ml/Spam_Detector_Message_App/images/Screenshot%20(409).png) |
| ----- | ------ |


