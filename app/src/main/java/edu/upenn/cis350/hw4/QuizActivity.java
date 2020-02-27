package edu.upenn.cis350.hw4;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class QuizActivity extends AppCompatActivity {

    static String difficulty = "";
    Random r = new Random();

    int topNumber;
    char[] topCharArray;

    int bottomNumber;
    char[] bottomCharArray;

    int answerNumber;
    char[] answerCharArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        difficulty = intent.getStringExtra(MainActivity.difficulty);


    }

    private void generateNumbers(String difficulty) {
        if (difficulty.equals("Easy")) {
            // get the answer digits
            answerCharArray[1] = (char) r.nextInt(9);
            answerCharArray[0] = (char) r.nextInt(9);
            answerNumber = Integer.parseInt(String.valueOf(answerCharArray));

            // get the top number digits
            topCharArray[1] = (char) r.nextInt((int) answerCharArray[1]);
            topCharArray[0] = (char) r.nextInt((int) answerCharArray[0]);
            topNumber = Integer.parseInt(String.valueOf(topCharArray));

            // get the bottom number digits
            bottomNumber = answerNumber - topNumber;
            bottomCharArray = String.valueOf(bottomNumber).toCharArray();

        } else if (difficulty.equals("Medium")) {
            int overFlowDigit = r.nextInt(1);
            int sumOfOverFlow = r.nextInt(8) + 10;
            if (overFlowDigit == 1) {
                topCharArray[1] = (char) r.nextInt(sumOfOverFlow);
                bottomCharArray[1] = (char) (sumOfOverFlow - (int) topCharArray[1]);
                topCharArray[1] = (char) (sumOfOverFlow - (int) bottomCharArray[1]);

                topCharArray[0] = (char) r.nextInt(8);
                bottomCharArray[0] = (char) (8 - (int) topCharArray[0]);
                topCharArray[0] = (char) (topCharArray[0] - (int) bottomCharArray[0]);

                topNumber = Integer.parseInt(String.valueOf(topCharArray));
                bottomNumber =  Integer.parseInt(String.valueOf(bottomCharArray));

                answerNumber = topNumber + bottomNumber;
                answerCharArray = String.valueOf(answerNumber).toCharArray();

            } else {

                topCharArray[0] = (char) r.nextInt(sumOfOverFlow);
                bottomCharArray[0] = (char) (sumOfOverFlow - (int) topCharArray[0]);
                topCharArray[0] = (char) (sumOfOverFlow - (int) bottomCharArray[0]);

                topCharArray[1] = (char) r.nextInt(9);
                bottomCharArray[1] = (char) (8 - (int) topCharArray[1]);
                topCharArray[1] = (char) (topCharArray[1] - (int) bottomCharArray[1]);

                topNumber = Integer.parseInt(String.valueOf(topCharArray));
                bottomNumber =  Integer.parseInt(String.valueOf(bottomCharArray));

                answerNumber = topNumber + bottomNumber;
                answerCharArray = String.valueOf(answerNumber).toCharArray();
            }

        } else if (difficulty.equals("Hard")) {

            // the first digits can add up to 18
            int entionallyWaitingForTheLastDayToDoMyHomework = r.nextInt(8) + 10;

            topCharArray[1] = (char) r.nextInt(entionallyWaitingForTheLastDayToDoMyHomework);
            entionallyWaitingForTheLastDayToDoMyHomework -= (int) topCharArray[1];
            bottomCharArray[1] = (char) entionallyWaitingForTheLastDayToDoMyHomework;

            // the second digits can add up to 18, with the one maybe carried over from before
            entionallyWaitingForTheLastDayToDoMyHomework = r.nextInt(8) + 10;

            topCharArray[0] = (char) r.nextInt(entionallyWaitingForTheLastDayToDoMyHomework);
            entionallyWaitingForTheLastDayToDoMyHomework -= (int) topCharArray[0];
            bottomCharArray[0] = (char) entionallyWaitingForTheLastDayToDoMyHomework;

            // put together the numbers and the answer
            topNumber = Integer.parseInt(String.valueOf(topCharArray));
            bottomNumber = Integer.parseInt(String.valueOf(bottomCharArray));
            answerNumber = topNumber + bottomNumber;

        } else {
            Log.d("ERROR", "Couldn't parse difficulty");
        }
    }

    public void handleButtonClick(View view, String difficulty) {

    }

}
