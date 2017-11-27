package com.example.hyruleslasthope.distracteddrivingapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class quizFrag extends android.app.Fragment {


    public quizFrag() {
        // Required empty public constructor
    }


    ArrayList<String> input;

    String[][] questions;
    TextView qText;
    Button b1,b2,b3;
    Boolean correct;
    int questionNum;
    String ans;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_math_game, container, false);



        correct  = true;

        //create buttons and questions
        qText = (TextView) v.findViewById(R.id.qText);
        b1 = (Button) v.findViewById(R.id.option1);
        b2 = (Button) v.findViewById(R.id.option2);
        b3 = (Button)v.findViewById(R.id.option3);



        //hard coded questions :(
        questions = new String[25][5];
        questions[0][0]= "True or False: one person is injured in a distracted-driving collision every hour";
        questions[0][1]= "True";
        questions[0][2] = "False";
        questions[0][4] = "False";

        questions[1][0]= "How many demerit points is distracted driving worth?";
        questions[1][1]= "3";
        questions[1][2] = "2";
        questions[1][3] = "5";
        questions[1][4] = "3";

        questions[2][0] = "True or False: a mounted device(secure and not moving) allowed to be used while driving";
        questions[2][1]= "True";
        questions[2][2] = "False";
        questions[2][4] = "True";

        questions[3][0]= "Without a full G license first conviction is what?";
        questions[3][1]= "30-day licence suspension";
        questions[3][2] = "60-day licence suspension";
        questions[3][3] = "Loss of Licence";
        questions[3][4] = "30-day licence suspension";

        questions[4][0]= "True or False: you can use your phone at a stop light";
        questions[4][1]= "True";
        questions[4][2] = "False";
        questions[4][4] = "False";

        questions[5][0]= "How much is the fine for distracted driving?";
        questions[5][1]= "$350";
        questions[5][2] = "$180";
        questions[5][3] = "$490";
        questions[5][4] = "$490";

        questions[6][0]= "True or False: you can be charged with careless driving while using your phone";
        questions[6][1]= "True";
        questions[6][2] = "False";
        questions[6][4] = "True";

        questions[7][0]= "True or False: you can use a hand held device for music while driving";
        questions[7][1]= "True, only if the playlist was started before driving";
        questions[7][2] = "False, anytime";
        questions[7][4] = "True, only if the playlist was started before driving";

        questions[8][0]= "How many instances of distracted driving lead to cancellation of a license?";
        questions[8][1]= "5";
        questions[8][2] = "1";
        questions[8][3] = "3+";
        questions[8][4] = "3+";

        questions[9][0]= "What other charges could you face for using your phone while driving?";
        questions[9][1]= "Careless Driving";
        questions[9][2] = "Dangerous Driving";
        questions[9][3] = "Both";
        questions[9][4] = "Both";

        questions[9][0]= "What other charges could you face for using your phone while driving?";
        questions[9][1]= "Careless Driving";
        questions[9][2] = "Dangerous Driving";
        questions[9][3] = "Both";
        questions[9][4] = "Both";

        questions[10][0]= "How many demerit points is careless driving worth?";
        questions[10][1]= "3";
        questions[10][2] = "6";
        questions[10][3] = "5";
        questions[10][4] = "6";

        questions[11][0]= "How much is the fine for careless driving?";
        questions[11][1]= "$1000";
        questions[11][2] = "$1500";
        questions[11][3] = "$2000";
        questions[11][4] = "$2000";


            //create the question and answers
            ans = setQuestions(b1,b2,b3,questions);


            //listeners to see if the right option was selected
            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(b1.getText() == ans){


                        ((MainGame) getActivity()).swap();

                    }else{
                        setQuestions(b1,b2,b3,questions);

                    }
                }
            });
            b2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(b2.getText() == ans){
                        correct = true;


                        ((MainGame) getActivity()).swap();
                    }else{
                        setQuestions(b1,b2,b3,questions);
                    }

                }
            });
            b3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(b3.getText() == ans){
                        correct = true;


                        ((MainGame) getActivity()).swap();
                    }else{
                        setQuestions(b1,b2,b3,questions);
                    }

                }
            });





        // Inflate the layout for this fragment


        return v;
    }

    @Override
    public void onPause() {
        super.onPause();

    }
    //create a random number and find that element in the questiuon array, make the buttons the possible answers and set the text to the appropriate question
    private String setQuestions(Button b1, Button b2,Button b3, String[][] questions ){

        Random ran = new Random();

        int r = ran.nextInt(11 - 1) + 1;
        questionNum = r;

        qText.setText(questions[questionNum][0]);
        final int answerlocation = questions[questionNum].length-1;
        ans = questions[questionNum][answerlocation];

        b1.setText(questions[questionNum][1]);
        b2.setText(questions[questionNum][2]);

        if(questions[questionNum][1].equals("True")){
            b3.setEnabled(false);
        }else {
            b3.setEnabled(true);
            b3.setText(questions[questionNum][3]);
        }

        return ans;
    }
}
