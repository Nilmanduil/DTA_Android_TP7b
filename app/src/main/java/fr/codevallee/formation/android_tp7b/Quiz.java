package fr.codevallee.formation.android_tp7b;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by tgoudouneix on 11/10/2017.
 */

public class Quiz implements Serializable {
    private ArrayList<Question> questions;
    private Integer score = 0;
    private Integer current = -1;

    public Quiz(ArrayList<Question> questions) {
        this.questions = questions;
        reset();
    }

    public Integer getCurrent() {
        return current;
    }

    public Integer getScore() {
        return score;
    }

    public Integer getTotalQuestions() {
        return questions.size();
    }

    public void reset() {
        score = 0;
        current = -1;
    }

    public boolean answer(String answer) {
        Log.d("Info", "Answer : " + answer + " | Good answer : " + questions.get(current).goodAnswer);
        if (answer.equals(questions.get(current).goodAnswer)) {
            addScore();
            return true;
        }
        else
            return false;
    }

    public void addScore() {
        score++;
    }

    public Question getNextQuestion() {
        current++;
        return questions.get(current);
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "questions=" + questions.toString() +
                ", score=" + score +
                ", current=" + current +
                '}';
    }
}
