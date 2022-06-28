package com.example.worldtrivia.model;

public class QuizQuestion {
    private String questionText;
    private boolean trueAnswer;

    public QuizQuestion(String questionText, boolean trueAnswer) {
        this.questionText = questionText;
        this.trueAnswer = trueAnswer;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public boolean isTrueAnswer() {
        return trueAnswer;
    }

    public void setTrueAnswer(boolean trueAnswer) {
        this.trueAnswer = trueAnswer;
    }
}
