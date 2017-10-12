package fr.codevallee.formation.android_tp7b;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AnswerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        Intent infos = getIntent();
        boolean result = infos.getBooleanExtra("result", false);
        String answer = infos.getStringExtra("answer");
        final Bundle extras = infos.getExtras();
        final Quiz quiz = (Quiz) extras.get("quiz");

        TextView questionResult = (TextView) findViewById(R.id.questionResult);
        if(result) {
            questionResult.setText(R.string.right);
        }
        else {
            questionResult.setText(R.string.wrong);
        }

        TextView goodAnswer = (TextView) findViewById(R.id.goodAnswer);
        goodAnswer.setText(answer);

        Button nextButton = (Button) findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextIntent = new Intent(AnswerActivity.this, MainActivity.class);
                nextIntent.putExtra("quiz", quiz);
                startActivity(nextIntent);
            }
        });
    }
}
