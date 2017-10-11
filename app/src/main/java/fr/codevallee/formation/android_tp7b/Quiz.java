package fr.codevallee.formation.android_tp7b;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by tgoudouneix on 11/10/2017.
 */

public class Quiz {
    private ArrayList<Question> questions;
    private Iterator<Question> iterator;
    private int score = 0;
    private int current = 0;

    public Quiz(ArrayList<Question> questions) {
        this.questions = questions;
        reset();
    }

    public int getCurrent() {
        return current;
    }

    public int getScore() {
        return score;
    }

    public int getTotalQuestions() {
        return questions.size();
    }

    public void reset() {
        score = 0;
        current = 0;
        iterator = questions.iterator();
    }

    public boolean answer(char answer) {
        if (answer == questions.get(current).goodAnswer) {
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
        return questions.iterator().next();
    }
}
