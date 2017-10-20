package com.example.hyruleslasthope.distracteddrivingapp;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

public class MainGame extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);


        final ImageView myImageView = (ImageView) findViewById(R.id.imageView);
        Button upButton = (Button) findViewById(R.id.upBtn);
        Button downButton= (Button) findViewById(R.id.dwnBtn);

        ImageView pylon = new ImageView(MainGame.this);
        pylon.setImageResource(R.drawable.Pylon);

        downButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ViewGroup.MarginLayoutParams)myImageView.getLayoutParams()).topMargin = 300;
                myImageView.requestLayout();
            }
        });

        upButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ViewGroup.MarginLayoutParams)myImageView.getLayoutParams()).topMargin = 0;
                myImageView.requestLayout();
            }
        });



    }
}
