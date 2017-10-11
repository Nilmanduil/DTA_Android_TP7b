package fr.codevallee.formation.android_tp7b;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final int totalNumber = 10;

    private Quiz quiz;
    private final char[] answers = {'b', 'a', 'd', 'a', 'd', 'c', 'b', 'b', 'c', 'c'};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ArrayList<Question> questions = new ArrayList<Question>(totalNumber);
        Question question;
        for (int i = 1; i <= totalNumber; i++) {
            question = new Question();
            question.label = getString(R.string.question1);
        }

        this.quiz = new Quiz(questions);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void nextQuestion() {

    }


}
