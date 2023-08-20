package com.example.horse_race;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Horse_RaceActivity extends AppCompatActivity {

    private static final int NUM_CABALLOS = 10;

    private Horse[] horses = new Horse[NUM_CABALLOS];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horse_race);
        LinearLayout layout = findViewById(R.id.horsesLayout);
        for (int i = 0; i < NUM_CABALLOS; i++) {
            View horseView = getLayoutInflater().inflate(R.layout.horse_layout, layout, false);
            ImageView imagenCaballo = horseView.findViewById(R.id.horseImage);
            TextView horseName = horseView.findViewById(R.id.horseName);
            String nombreImagen = "horse" + (i + 1);
            horseName.setText(nombreImagen);
            int idImagen = getResources().getIdentifier(nombreImagen, "drawable", getPackageName());
            imagenCaballo.setImageResource(idImagen);
            horses[i] = new Horse(horseView);
            layout.addView(horseView);
        }
    }
}