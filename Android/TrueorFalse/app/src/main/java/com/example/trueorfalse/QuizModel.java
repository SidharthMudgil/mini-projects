package com.example.trueorfalse;

public class QuizModel {
    private int mQuestion;
    private boolean mAnswer;

    public QuizModel(int question, boolean answer) {
        mQuestion = question;
        mAnswer = answer;
    }

    public int getQuestion() {
        return mQuestion;
    }

    public void setQuestion(int mQuestion) {
        this.mQuestion = mQuestion;
    }

    public boolean getAnswer() {
        return mAnswer;
    }

    public void setAnswer(boolean mAnswer) {
        this.mAnswer = mAnswer;
    }
}
