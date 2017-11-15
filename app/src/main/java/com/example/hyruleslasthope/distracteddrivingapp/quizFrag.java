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
    Button b1,b2,b3,b4;
    Boolean correct;
    int questionNum;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_math_game, container, false);



        correct  = true;

        qText = (TextView) v.findViewById(R.id.qText);
        b1 = (Button) v.findViewById(R.id.option1);
        b2 = (Button) v.findViewById(R.id.option2);
        b3 = (Button)v.findViewById(R.id.option3);


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

       final Toast toast = Toast.makeText(v.getContext(), "Hit", Toast.LENGTH_SHORT);
        Random ran = new Random();
        int r = ran.nextInt(12 - 1) + 1;
        questionNum = r;
        if(questionNum < questions.length) {
            qText.setText(questions[questionNum][0]);

            final int answerlocation = questions[questionNum].length - 1;

            b1.setText(questions[questionNum][1]);
            b2.setText(questions[questionNum][2]);
            if(questions[questionNum][1].equals("True")){

            }else {
                b3.setText(questions[questionNum][3]);
            }


            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(b1.getText() == questions[questionNum][answerlocation]){

                        toast.show();
                        ((MainGame) getActivity()).swap();

                    }else{
                        b1.setEnabled(false);

                    }
                }
            });
            b2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(b2.getText().equals(questions[questionNum][answerlocation])){
                        correct = true;
                        toast.show();

                        ((MainGame) getActivity()).swap();
                    }else{
                        b2.setEnabled(false);
                    }

                }
            });
            b3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(b3.getText().equals(questions[questionNum][answerlocation])){
                        correct = true;
                        toast.show();

                        ((MainGame) getActivity()).swap();
                    }else{
                        b3.setEnabled(false);
                    }

                }
            });
        }




        // Inflate the layout for this fragment


        return v;
    }

    @Override
    public void onPause() {
        super.onPause();

    }
}
