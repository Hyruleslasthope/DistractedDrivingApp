package com.example.hyruleslasthope.distracteddrivingapp;

import android.animation.ObjectAnimator;
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
import android.widget.ImageView;

public class carControlsFrag extends android.app.Fragment {


    public carControlsFrag() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((MainGame)getActivity()).timer();
        View v = inflater.inflate(R.layout.fragment_car_controls, container, false);

        Button upButton = (Button) v.findViewById(R.id.upBtn);
        Button downButton = (Button) v.findViewById(R.id.dwnBtn);


        ImageView mainCar = (ImageView) getActivity().findViewById(R.id.imageView);

        final ObjectAnimator carMoveDwn = ObjectAnimator.ofFloat(mainCar,"translationY",0,430);
        carMoveDwn.setDuration(0);

        final ObjectAnimator carMoveUp = ObjectAnimator.ofFloat(mainCar,"translationY",430,0);
        carMoveUp.setDuration(0);

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


        // Inflate the layout for this fragment
        return v;
    }

}