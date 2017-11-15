package com.example.hyruleslasthope.distracteddrivingapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Scanner;

public class mathGame extends AppCompatActivity {
    ArrayList<String> input;
    String[][] questions;
    TextView qText;
    Button b1,b2,b3,b4;
    Boolean correct;
    int questionNum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_math_game);

        correct  = true;

        qText = (TextView) findViewById(R.id.qText);
        b1 = (Button) findViewById(R.id.option1);
        b2 = (Button) findViewById(R.id.option2);
        b3 = (Button) findViewById(R.id.option3);


            LinearLayout layout = (LinearLayout) findViewById(R.id.layout);
            questions = new String[25][3];
        Scanner scan = new Scanner(getResources().openRawResource(R.raw.quizinput));
            input = new ArrayList<String>();

        while(scan.hasNextLine()){
            input.add(scan.nextLine());
        }
        scan.close();
            for(int i = 0; i < input.size(); i++){
                questions[i]= input.get(i).split("#|,");

            }



        questionNum = 0;


            if(questionNum < questions.length && correct) {
                qText.setText(questions[questionNum][0]);
                final int answerlocation = questions[questionNum].length;
                b1.setText(questions[questionNum][1]);
                b2.setText(questions[questionNum][2]);

//                    b1.setText(questions[questionNum][1]);
//                    b2.setText(questions[questionNum][2]);
//                    b3.setText(questions[questionNum][3]);
//                    b4.setText(questions[questionNum][4]);


                b1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if(b1.getText() != questions[questionNum][answerlocation]){
                            correct = false;

                        }
                    }
                });
                b2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                b3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                b4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        correct = false;
                    }
                });
            }




    }

}
