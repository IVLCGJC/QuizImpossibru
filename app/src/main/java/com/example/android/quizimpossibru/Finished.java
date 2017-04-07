package com.example.android.quizimpossibru;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class Finished extends AppCompatActivity {
    int totalQuestions = 0;
    int correctAnswers = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finished);
        if (this.getIntent().getExtras() != null) {
            int tQ = getIntent().getExtras().getInt("total_questions");
            int cA = getIntent().getExtras().getInt("correct_answers");
            totalQuestions = tQ;
            correctAnswers = cA;
        }
        TextView scoreView = (TextView) findViewById(R.id.scoreView);
        scoreView.setText(String.valueOf(correctAnswers + " / " + totalQuestions));
    }

    public void startQuiz(View view) {
        Intent startIntent = new Intent(this, QuizImpossibru.class);
        startActivity(startIntent);
    }
}