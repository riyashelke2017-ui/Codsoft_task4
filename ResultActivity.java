package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    TextView txtScore, txtMessage;

    Button btnPlayAgain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_result);

        txtScore = findViewById(R.id.txtScore);
        txtMessage = findViewById(R.id.txtMessage);

        btnPlayAgain = findViewById(R.id.btnPlayAgain);

        int score = getIntent().getIntExtra(
                "score",
                0
        );

        int total = getIntent().getIntExtra(
                "total",
                0
        );

        txtScore.setText(
                score + " / " + total
        );

        txtMessage.setText(
                "Correct Answers: " + score
                        + "\nWrong Answers: "
                        + (total - score)
        );

        btnPlayAgain.setOnClickListener(v -> {

            Intent intent = new Intent(
                    ResultActivity.this,
                    MainActivity.class
            );

            startActivity(intent);

            finish();
        });
    }
}
