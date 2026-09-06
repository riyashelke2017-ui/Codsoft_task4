package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class QuizActivity extends AppCompatActivity {

    TextView txtQuestion, txtFeedback, txtQuestionNumber;

    RadioGroup radioGroup;

    RadioButton option1, option2, option3, option4;

    Button btnSubmit, btnNext;

    String[] questions = {
            "Which language is used for Android app development?",
            "Which file is used to design Android UI?",
            "What is the full form of APK?",
            "Which company developed Android?"
    };

    String[][] options = {
            {"Java", "HTML", "SQL", "PHP"},
            {"XML", "CSS", "TXT", "JSON"},
            {"Android Package Kit", "Android Program Kit", "Application Package Key", "Android Project Kit"},
            {"Google", "Microsoft", "Apple", "IBM"}
    };

    int[] correctAnswers = {0, 0, 0, 0};

    int currentQuestion = 0;

    int score = 0;

    boolean answered = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_quiz);

        txtQuestion = findViewById(R.id.txtQuestion);
        txtQuestionNumber = findViewById(R.id.txtQuestionNumber);
        txtFeedback = findViewById(R.id.txtFeedback);

        radioGroup = findViewById(R.id.radioGroup);

        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);

        btnSubmit = findViewById(R.id.btnSubmit);
        btnNext = findViewById(R.id.btnNext);

        showQuestion();

        btnSubmit.setOnClickListener(v -> checkAnswer());

        btnNext.setOnClickListener(v -> nextQuestion());
    }

    private void showQuestion() {

        answered = false;

        radioGroup.clearCheck();

        option1.setEnabled(true);
        option2.setEnabled(true);
        option3.setEnabled(true);
        option4.setEnabled(true);

        btnSubmit.setEnabled(true);
        btnNext.setEnabled(false);

        txtFeedback.setText("");

        txtQuestionNumber.setText(
                "Question " + (currentQuestion + 1)
                        + " of " + questions.length
        );

        txtQuestion.setText(
                questions[currentQuestion]
        );

        option1.setText(options[currentQuestion][0]);
        option2.setText(options[currentQuestion][1]);
        option3.setText(options[currentQuestion][2]);
        option4.setText(options[currentQuestion][3]);
    }

    private void checkAnswer() {

        if (radioGroup.getCheckedRadioButtonId() == -1) {

            Toast.makeText(
                    this,
                    "Please select an answer",
                    Toast.LENGTH_SHORT
            ).show();

            return;
        }

        int selectedAnswer;

        int selectedId = radioGroup.getCheckedRadioButtonId();

        if (selectedId == R.id.option1) {
            selectedAnswer = 0;
        } else if (selectedId == R.id.option2) {
            selectedAnswer = 1;
        } else if (selectedId == R.id.option3) {
            selectedAnswer = 2;
        } else {
            selectedAnswer = 3;
        }

        if (selectedAnswer == correctAnswers[currentQuestion]) {

            score++;

            txtFeedback.setText("Correct Answer! ✓");

        } else {

            txtFeedback.setText(
                    "Wrong Answer! Correct answer: "
                            + options[currentQuestion]
                            [correctAnswers[currentQuestion]]
            );
        }

        answered = true;

        option1.setEnabled(false);
        option2.setEnabled(false);
        option3.setEnabled(false);
        option4.setEnabled(false);

        btnSubmit.setEnabled(false);
        btnNext.setEnabled(true);
    }

    private void nextQuestion() {

        if (!answered) {
            return;
        }

        currentQuestion++;

        if (currentQuestion < questions.length) {

            showQuestion();

        } else {

            Intent intent = new Intent(
                    QuizActivity.this,
                    ResultActivity.class
            );

            intent.putExtra("score", score);

            intent.putExtra(
                    "total",
                    questions.length
            );

            startActivity(intent);

            finish();
        }
    }
}
