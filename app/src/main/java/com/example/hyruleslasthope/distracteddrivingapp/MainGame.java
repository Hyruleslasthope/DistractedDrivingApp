package com.example.hyruleslasthope.distracteddrivingapp;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Dialog;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.os.CountDownTimer;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import java.util.Random;

public class MainGame extends AppCompatActivity {

    //create the two fragments for swapping
    FragmentManager fm;
    FragmentTransaction ft;
    quizFrag qfrag = new quizFrag();
    carControlsFrag cfrag = new carControlsFrag();
    static boolean isRunning = false;


    @Override
    public void onStart(){
        super.onStart();
        isRunning = true;
    }
    @Override
    public void onStop(){
        super.onStop();
        isRunning = false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);

        //find imageviews and set up fragments
        final ImageView mainCar = (ImageView) findViewById(R.id.imageView);
        final ImageView pylon = (ImageView) findViewById(R.id.obstical);
        pylon.setVisibility(View.VISIBLE);

        fm = getFragmentManager();
        ft = fm.beginTransaction();
        ft.add(R.id.fragContainer,cfrag);
        ft.commit();

        final String[] stats = new String[]{"Nearly 3 out of 4 drivers in Canada have admitted to driving distracted",
                "You are 23 times more likely to crash if you text while drive",
                "If you take your eyes off the road for more than 2 seconds, your crash risk doubles",
                "The OPP recently reported that distracted driving is the #1 killer on our roads",
                "Deaths have doubled from collisions caused by distracted driving since 2000",
                "Every 30mins one person is injured from distracted driving",
                "94% of t  eenagers understand the consequences of DD, but 35% of them admitted they do it anyway"};

      final android.app.AlertDialog dialog = new android.app.AlertDialog.Builder(MainGame.this).create();
        dialog.setButton(Dialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });






        final ObjectAnimator botanimX = ObjectAnimator.ofFloat(pylon, "translationX", 1500, -1500);
        final ObjectAnimator botanimY = ObjectAnimator.ofFloat(pylon, "translationY", 110, 110);
        final AnimatorSet botanim = new AnimatorSet();
        botanim.setDuration(4000);
        botanim.playTogether(botanimX, botanimY);

        final ObjectAnimator topanimX = ObjectAnimator.ofFloat(pylon, "translationX", 1500, -1500);
        final ObjectAnimator topanimY = ObjectAnimator.ofFloat(pylon, "translationY", -320, -320);
        final AnimatorSet topanim = new AnimatorSet();
        topanim.setDuration(4000);
        topanim.playTogether(topanimX, topanimY);


       pylonSpawn(botanim,topanim);


        //animation listeners to start another animation when one ends
        botanim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);

                topanim.start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        //checks every frame of the animation to see if there is a collision
        botanimX.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                ImageView a = pylon;
                ImageView b = mainCar;
                Boolean collision = false;
                float bl = a.getY();
                float bt = a.getX();
                float br = a.getWidth() + bl;
                float bb = a.getHeight() + bt;
                float pl = b.getY();
                float pt = b.getX();
                float pr = b.getWidth() + pl;
                float pb = b.getHeight() + pt;
                if (bl <= pr && bl >= pl && bt >= pt && bt <= pb) {
                    collision = true;

                } else if (br >= pl && br <= pr && bb >= pt && bb <= pb) {
                    collision = true;
                } else if (bt <= pb && bt >= pt && br >= pl && br <= pr) {
                    collision =true;
                } else if (bb >= pt && bb <= pb && bl >= pl && bl <= pr) {
                    collision =true;
                }


                if(collision&& !isFinishing()){
                    dialog.setMessage("You Have Crashed, " + stats[random()]);
                    dialog.show();
                }

            }
        });


        topanim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);

                botanim.start();

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        topanimX.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                ImageView a = pylon;
                ImageView b = mainCar;
                Boolean collision = false;
                float bl = a.getY();
                float bt = a.getX();
                float br = a.getWidth() + bl;
                float bb = a.getHeight() + bt;
                float pl = b.getY();
                float pt = b.getX();
                float pr = b.getWidth() + pl;
                float pb = b.getHeight() + pt;
                if (bl <= pr && bl >= pl && bt >= pt && bt <= pb) {
                    collision = true;

                } else if (br >= pl && br <= pr && bb >= pt && bb <= pb) {
                    collision = true;
                } else if (bt <= pb && bt >= pt && br >= pl && br <= pr) {
                    collision =true;
                } else if (bb >= pt && bb <= pb && bl >= pl && bl <= pr) {
                    collision =true;
                }


                if(collision && !isFinishing()){
                    dialog.setMessage("You Have Crashed, " + stats[random()]);
                    dialog.show();
                }
            }
        });


    }

    //Switch to the quiz every 10 seconds
    public void timer (){
        new CountDownTimer(10000,1000){
            public void onTick(long millisUntilFinished){

            }
            public void onFinish(){
                swap();
            }
        }.start();
    }
    //swap the existing fragment with the other
    public void swap(){

        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if(cfrag.isAdded()){
            ft.remove(cfrag);
            ft.add(R.id.fragContainer,qfrag);
        }else{
            ft.remove(qfrag);
            ft.add(R.id.fragContainer,cfrag);
        }
            if(isRunning && !isFinishing()) {
                ft.commit();
            }
    }

    //randomly spawn a pylon(does not work constantly)
    public void pylonSpawn(AnimatorSet a, AnimatorSet b){
        Random random = new Random();
        int r = random.nextInt(10-1 + 1)+1;

        if(r%2 == 0){
            a.start();
        }else{
            b.start();
        }

    }
    public int random(){
        Random random = new Random();
        int r = random.nextInt(6-1 + 1)+1;

        return r;
    }
    }

