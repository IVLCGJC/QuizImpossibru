package com.example.android.quizimpossibru;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class QuizImpossibru extends AppCompatActivity {
    final Timer countDownTimer = new Timer();
    //Set the lists for question and answers
    ArrayList<String> questions = new ArrayList<>();
    ArrayList<String> answerA = new ArrayList<>();
    ArrayList<String> answerB = new ArrayList<>();
    ArrayList<String> answerC = new ArrayList<>();
    ArrayList<String> answerD = new ArrayList<>();
    ArrayList<String> correctAnswerList = new ArrayList<>();
    int questionLength = 16;//Number of questions
    int position = 0;//The current question and answers position in the array list
    int cc = 21; ////Set the time to count down from. I use this to have one place to set all.
    int c = cc; //Set the time to count down from
    int correctAnswers = 0;//Counting the correct answers
    String currentAnswer = "";//The value of current answer
    //check, which boxes are selected or not by the user
    boolean selectedA = false;
    boolean selectedB = false;
    boolean selectedC = false;
    boolean selectedD = false;
    //Protect from unintended back button press
    boolean isBackPressed = false;
    boolean isNoMoreQuestion = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_impossibru);
        addTexts();
        displayTexts();
        final TextView counterText = (TextView) findViewById(R.id.counter_text);
        //Set countDown timer
        countDownTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (c > 6) {
                            c--;
                            counterText.setText(String.valueOf(c));
                            counterText.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                        } else if (c > 0) {
                            c--;
                            counterText.setText(String.valueOf(c));
                            counterText.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.red));
                        } else {
                            checkAnswer();
                            displayTexts();
                            c = cc;
                        }
                    }
                });
            }
        }, 0, 1000);
    }

    private void displayTexts() {

        if (!isNoMoreQuestion) {
            int questionNumber = position + 1;
            //Display the current question and answers
            TextView questionTV = (TextView) findViewById(R.id.question_text);
            TextView answerATV = (TextView) findViewById(R.id.answer_a);
            TextView answerBTV = (TextView) findViewById(R.id.answer_b);
            TextView answerCTV = (TextView) findViewById(R.id.answer_c);
            TextView answerDTV = (TextView) findViewById(R.id.answer_d);
            questionTV.setText(String.valueOf(questionNumber + "/" + questionLength + ". " + questions.get(position)));
            answerATV.setText(String.valueOf(answerA.get(position)));
            answerBTV.setText(String.valueOf(answerB.get(position)));
            answerCTV.setText(String.valueOf(answerC.get(position)));
            answerDTV.setText(String.valueOf(answerD.get(position)));
//set current correct answer
            currentAnswer = correctAnswerList.get(position);
            //   Toast.makeText(this, currentAnswer, Toast.LENGTH_SHORT).show(); //Just to check if it is working or not
//Set all boxes unchecked
            CheckBox answerABox = (CheckBox) findViewById(R.id.answer_a);
            CheckBox answerBBox = (CheckBox) findViewById(R.id.answer_b);
            CheckBox answerCBox = (CheckBox) findViewById(R.id.answer_c);
            CheckBox answerDBox = (CheckBox) findViewById(R.id.answer_d);
            answerABox.setChecked(false);
            answerBBox.setChecked(false);
            answerCBox.setChecked(false);
            answerDBox.setChecked(false);
//Prepare the next position, which means the next answer in the array list
            if (position < questionLength - 1) {
                position++;
            } else if (position == questionLength - 1) {
                isNoMoreQuestion = true;
            }
        } else {
            countDownTimer.cancel();
            finishIntent();
        }
    }

    private void addTexts() {//Add the question and answers to the array list
        int numberToAdd;
        String numberToAddToString;
        String addCHarToQuestion;
        String addCHarToAnswerA;
        String addCHarToAnswerB;
        String addCHarToAnswerC;
        String addCHarToAnswerD;
        String addCHarToCorrectAList;
        String q = "q";
        String aa = "aa";
        String ab = "ab";
        String ac = "ac";
        String ad = "ad";
        String ca = "ca";

        for (numberToAdd = 1; numberToAdd <= questionLength; numberToAdd++) {
            numberToAddToString = String.valueOf(numberToAdd);
            addCHarToQuestion = q + numberToAddToString;
            addCHarToAnswerA = aa + numberToAddToString;
            addCHarToAnswerB = ab + numberToAddToString;
            addCHarToAnswerC = ac + numberToAddToString;
            addCHarToAnswerD = ad + numberToAddToString;
            addCHarToCorrectAList = ca + numberToAddToString;
            int idq = getResources().getIdentifier(addCHarToQuestion, "string", getPackageName());
            int idaa = getResources().getIdentifier(addCHarToAnswerA, "string", getPackageName());
            int idab = getResources().getIdentifier(addCHarToAnswerB, "string", getPackageName());
            int idac = getResources().getIdentifier(addCHarToAnswerC, "string", getPackageName());
            int idad = getResources().getIdentifier(addCHarToAnswerD, "string", getPackageName());
            int idca = getResources().getIdentifier(addCHarToCorrectAList, "string", getPackageName());
            questions.add(getString(idq));
            answerA.add(getString(idaa));
            answerB.add(getString(idab));
            answerC.add(getString(idac));
            answerD.add(getString(idad));
            correctAnswerList.add(getString(idca));
        }
    }

    public void nextQuestion(View view) {
        c = 0;
    }

    public void AAnswerSelected(View view) {
        CheckBox checkBoxA = (CheckBox) view;
        if (checkBoxA.isChecked()) {
            selectedA = true;
        } else {
            selectedA = false;
        }
    }

    public void BAnswerSelected(View view) {
        CheckBox checkBoxB = (CheckBox) view;
        if (checkBoxB.isChecked()) {
            selectedB = true;
        } else {
            selectedB = false;
        }
    }

    public void CAnswerSelected(View view) {
        CheckBox checkBoxC = (CheckBox) view;
        if (checkBoxC.isChecked()) {
            selectedC = true;
        } else {
            selectedC = false;
        }
    }

    public void DAnswerSelected(View view) {
        CheckBox checkBoxD = (CheckBox) view;
        if (checkBoxD.isChecked()) {
            selectedD = true;
        } else {
            selectedD = false;
        }
    }

    private void checkAnswer() {
        int userSelected = 0;
        if (selectedA) {
            userSelected += 1;
        }
        if (selectedB) {
            userSelected += 2;
        }
        if (selectedC) {
            userSelected += 3;
        }
        if (selectedD) {
            userSelected += 4;
        }
        String userSelectedString = String.valueOf(userSelected);
        if (userSelectedString.equals(currentAnswer)) {
            correctAnswers++;
            Toast.makeText(this, R.string.correct, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, R.string.wrong, Toast.LENGTH_SHORT).show();
        }
        //Resetting switches' value
        selectedA = selectedB = selectedC = selectedD = false;
    }

    public void finishIntent() {
        Intent finishIntent = new Intent(this, Finished.class);
        finishIntent.putExtra("total_questions", questionLength);
        finishIntent.putExtra("correct_answers", correctAnswers);
        startActivity(finishIntent);
    }

    @Override
    public void onBackPressed() { //Prevent the user from accidentally quit the app
        if (!isBackPressed) {
            isBackPressed = true;
            Toast.makeText(this, R.string.are_you_sure_you_want_to_quit, Toast.LENGTH_SHORT).show();
            final Timer tEndBackPressed = new Timer();
            tEndBackPressed.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            isBackPressed = false;
                            tEndBackPressed.cancel();
                        }
                    });
                }
            }, 2000, 1000);
        } else {
            countDownTimer.cancel();
            this.finish();
        }
    }

    //Prevent the app running when Home button is pressed
    @Override
    protected void onPause() {
        countDownTimer.cancel();
        this.finish();
        super.onPause();
    }
}

