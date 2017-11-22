package com.example.hyruleslasthope.distracteddrivingapp;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Application;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.graphics.Rect;
import android.os.CountDownTimer;
import android.support.v4.app.FragmentActivity;
import android.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import java.util.Random;

import javax.xml.datatype.Duration;

public class MainGame extends AppCompatActivity {

    FragmentManager fm;
    FragmentTransaction ft;
    quizFrag qfrag = new quizFrag();
    carControlsFrag cfrag = new carControlsFrag();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);

        View v = findViewById(R.id.fragContainer);

        fm = getFragmentManager();

        ft = fm.beginTransaction();


        ft.add(R.id.fragContainer,cfrag);

        ft.commit();

      final android.app.AlertDialog dialog = new android.app.AlertDialog.Builder(MainGame.this).setMessage("Crashed").create();



        final ImageView mainCar = (ImageView) findViewById(R.id.imageView);


        final ImageView pylon = (ImageView) findViewById(R.id.obstical);
        pylon.setVisibility(View.VISIBLE);

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




        final Toast toast = Toast.makeText(getApplicationContext(), "Hit", Toast.LENGTH_SHORT);





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


                if(collision){
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


                if(collision){
                    dialog.show();
                }
            }
        });
        botanim.start();









    }


    public void timer (){
        new CountDownTimer(10000,1000){
            public void onTick(long millisUntilFinished){

            }
            public void onFinish(){
                swap();
            }
        }.start();
    }
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
        ft.commit();
    }
    }

