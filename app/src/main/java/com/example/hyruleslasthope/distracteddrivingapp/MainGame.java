package com.example.hyruleslasthope.distracteddrivingapp;

import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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


        final ImageView myImageView = (ImageView) findViewById(R.id.imageView);
        Button upButton = (Button) findViewById(R.id.upBtn);
        Button downButton= (Button) findViewById(R.id.dwnBtn);

        final ImageView pylon = (ImageView) findViewById(R.id.obstical);

        pylon.setVisibility(View.GONE);

        final Animation obUp = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.obsticalmove_uppos);

        final Animation obDwn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.obsticalmove_dwnpos);

        pylon.setX(1500);
        obUp.setFillAfter(true);
        obDwn.setFillAfter(true);
        pylon.startAnimation(obDwn);

        final Animation moveDown = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.carmove_down);


        obUp.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                pylon.setX(1500);
                pylon.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                if(RanNum() % 2 == 0) {
                    pylon.startAnimation(obDwn);
                }else{
                    pylon.startAnimation(obUp);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        obDwn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                pylon.setX(1500);
                pylon.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                if(RanNum() % 2 == 1) {
                    pylon.startAnimation(obUp);
                }else{
                    pylon.startAnimation(obDwn);
                }
                }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        final Animation moveUp = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.carmove_up);

        downButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  ((ViewGroup.MarginLayoutParams)myImageView.getLayoutParams()).topMargin = 300;
                //myImageView.requestLayout();
                myImageView.startAnimation(moveDown);
            }
        });

        upButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   ((ViewGroup.MarginLayoutParams)myImageView.getLayoutParams()).topMargin = 0;
               // myImageView.requestLayout();
                myImageView.startAnimation(moveUp);
            }
        });

        Toast toast = Toast.makeText(getApplicationContext(), "Hit", Toast.LENGTH_SHORT);

        Rect r1 = new Rect();
        Rect r2 = new Rect();

        myImageView.getHitRect(r1);
        pylon.getHitRect(r2);

        if(Rect.intersects(r2,r1)){
            toast.show();
        }
    }

    private int RanNum(){
        Random ran = new Random();
        int r = ran.nextInt(11-1)+ 1;
        return r;
    }

}
