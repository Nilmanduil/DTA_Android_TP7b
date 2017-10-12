package fr.codevallee.formation.android_tp7b;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    public Quiz quiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent infos = getIntent();
        Bundle extras = infos.getExtras();
        this.quiz = (Quiz) extras.get("quiz");

        TextView scoreFinal = (TextView) findViewById(R.id.scoreFinal);
        scoreFinal.setText(getString(R.string.score) + " " + quiz.getScore());

        this.quiz.reset();
        Button redoButton = (Button) findViewById(R.id.redoButton);
        redoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent redo = new Intent(ResultActivity.this, MainActivity.class);
                redo.putExtra("quiz", ResultActivity.this.quiz);
                startActivity(redo);
            }
        });
    }
}
