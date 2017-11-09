package com.example.hyruleslasthope.distracteddrivingapp;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

import javax.xml.datatype.Duration;

public class MainGame extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);




        final ImageView mainCar = (ImageView) findViewById(R.id.imageView);
        Button upButton = (Button) findViewById(R.id.upBtn);
        Button downButton = (Button) findViewById(R.id.dwnBtn);

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

        final ObjectAnimator carMoveDwn = ObjectAnimator.ofFloat(mainCar,"translationY",0,430);
        carMoveDwn.setDuration(0);

        final ObjectAnimator carMoveUp = ObjectAnimator.ofFloat(mainCar,"translationY",430,0);
        carMoveUp.setDuration(0);


        final Toast toast = Toast.makeText(getApplicationContext(), "Hit", Toast.LENGTH_SHORT);


        downButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                carMoveDwn.start();

            }
        });

        upButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                carMoveUp.start();
    }
});

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

                int x1=(int)pylon.getX();
                int y1=(int)pylon.getY();
                int width1=pylon.getWidth();
                int height1=pylon.getHeight();
                int x2=(int)mainCar.getX();
                int y2=(int)mainCar.getY();
                int width2=mainCar.getWidth();
                int height2=mainCar.getHeight();
                int right1 = x1 + width1 / 2;
                int right2 = x2 + width2 / 2;
                int bottom1 = y1 + height1 / 2;
                int bottom2 = y2 + height2 /2 ;

                if (y1 == y2)
                {
                   toast.setText("" + y1 + " " + y2);
                   toast.show();
                }



                if (right1 == right2 )
                {



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
                int x1=(int)pylon.getX();
                int y1=(int)pylon.getY();
                int width1=pylon.getWidth();
                int height1=pylon.getHeight();
                int x2=(int)mainCar.getX();
                int y2=(int)mainCar.getY();
                int width2=mainCar.getWidth();
                int height2=mainCar.getHeight();
                int right1 = x1 + width1 / 2;
                int right2 = x2 + width2 / 2;
                int bottom1 = y1 + height1 / 2;
                int bottom2 = y2 + height2 / 2;
                if (x2 >= x1 && x2 <= right1 && y2 >= y2 && y2 <= bottom1)
                {
                   //toast.setText("top hit");
                    //toast.show();
                }

                if (
                        right2 >= x1 && right2 <= right1 && bottom2 >= y2 && bottom2 <= bottom1)
                {
                   // toast.setText("top hit");
                 //  toast.show();
                }
            }
        });
        botanim.start();








    }
    private int RanNum() {
        Random ran = new Random();
        int r = ran.nextInt(11 - 1) + 1;
        return r;
    }

    }

