package fr.codevallee.formation.android_tp7b;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Quiz quiz;
    private boolean quizInitialized = false;

    private void initializeQuiz() {
        if(!quizInitialized) {
            Resources res = getResources();
            String[] questionsArray = res.getStringArray(R.array.questions);
            int totalNumber = questionsArray.length;

            ArrayList<Question> questions = new ArrayList<Question>();
            Question question;
            JSONObject questionInfos;

            for (Integer i = 0; i < totalNumber; i++) {
                question = new Question();
                try {
                    questionInfos = new JSONObject((String) questionsArray[i]);
                    question.label = questionInfos.getString("question");
                    question.theme = questionInfos.getString("theme");
                    question.A = questionInfos.getString("A");
                    question.B = questionInfos.getString("B");
                    question.C = questionInfos.getString("C");
                    question.D = questionInfos.getString("D");
                    question.goodAnswer = questionInfos.getString("answer");

                    questions.add(question);
                }
                catch(JSONException e) {
                    e.printStackTrace();
                }
            }

            this.quiz = new Quiz(questions);
            quizInitialized = true;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get info from previous activity
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if(intent.toString() == "" || extras == null) {
            initializeQuiz();
        }
        else {
            this.quiz = (Quiz) extras.get("quiz");
        }


        // Get current question & number
        final Question currentQuestion = nextQuestion();
        Integer currentNumber = quiz.getCurrent();
        Integer totalNumber = quiz.getTotalQuestions();

        // Set question text + theme
        TextView questionLabel = (TextView) findViewById(R.id.question);
        questionLabel.setText("Question " + currentNumber + "/" + totalNumber + " : " + currentQuestion.label);
        // questionLabel.setTextColor(R.color.theme1);
        TextView questionTheme = (TextView) findViewById(R.id.theme);
        questionTheme.setText(currentQuestion.theme);
        // questionTheme.setBackgroundColor(R.color.theme1);

        // Set buttons text
        Button buttonA = (Button) findViewById(R.id.buttonA);
        buttonA.setText(currentQuestion.A);
        Button buttonB = (Button) findViewById(R.id.buttonB);
        buttonB.setText(currentQuestion.B);
        Button buttonC = (Button) findViewById(R.id.buttonC);
        buttonC.setText(currentQuestion.C);
        Button buttonD = (Button) findViewById(R.id.buttonD);
        buttonD.setText(currentQuestion.D);

        // Set buttons actions
        buttonA.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                boolean result = answer("A");
                Log.d("Info", "Score : " + quiz.getScore());
                generateIntent(result, currentQuestion);
            }
        });
        buttonB.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                boolean result = answer("B");
                Log.d("Info", "Score : " + quiz.getScore());
                generateIntent(result, currentQuestion);
            }
        });
        buttonC.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                boolean result = answer("C");
                Log.d("Info", "Score : " + quiz.getScore());
                generateIntent(result, currentQuestion);
            }
        });
        buttonD.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                boolean result = answer("D");
                Log.d("Info", "Score : " + quiz.getScore());
                generateIntent(result, currentQuestion);
            }
        });
    }

    private void generateIntent(boolean result, Question currentQuestion) {
        Intent answerIntent = new Intent(MainActivity.this, AnswerActivity.class);
        answerIntent.putExtra("result", result);
        switch (currentQuestion.goodAnswer) {
            case "A":
                answerIntent.putExtra("answer", currentQuestion.A);
                break;
            case "B":
                answerIntent.putExtra("answer", currentQuestion.B);
                break;
            case "C":
                answerIntent.putExtra("answer", currentQuestion.C);
                break;
            case "D":
                answerIntent.putExtra("answer", currentQuestion.D);
                break;
        }
        answerIntent.putExtra("quiz", quiz);
        startActivity(answerIntent);
    }

    public Question nextQuestion() {
        return quiz.getNextQuestion();
    }

    private boolean answer(String answer) {
        if(quiz.answer(answer)) {
            return true;
        }
        else {
            return false;
        }
    }
}
