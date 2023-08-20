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

    private List<String> positionList;
    private Horse[] horses = new Horse[NUM_CABALLOS];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horse_race);
        positionList=new ArrayList<>();
        TextView numero=findViewById(R.id.editTextNumber);
        LinearLayout layout = findViewById(R.id.horsesLayout);
        for (int i = 0; i < NUM_CABALLOS; i++) {
            View horseView = getLayoutInflater().inflate(R.layout.horse_layout, layout, false);
            ImageView imagenCaballo = horseView.findViewById(R.id.horseImage);
            TextView horseName=horseView.findViewById(R.id.horseName);
            String nombreImagen = "horse" + (i + 1);
            horseName.setText(nombreImagen);
            int idImagen = getResources().getIdentifier(nombreImagen, "drawable", getPackageName());
            imagenCaballo.setImageResource(idImagen);
            horses[i] = new Horse(horseView);
            layout.addView(horseView);
        }

        Button startRaceButton = findViewById(R.id.startRaceButton);
        startRaceButton.setOnClickListener(v -> startRace());
        Button stopRaceButton = findViewById(R.id.stopRaceButton);
        stopRaceButton.setOnClickListener(view -> {
            String caballo=numero.getText().toString();
            if(caballo.isEmpty()){
                stopRace();
            }else {
                int ncaballo=Integer.parseInt(caballo);
                horses[ncaballo-1].setStart(false);
            }
        });
    }

    private void startRace() {
        positionList.clear();
        for (Horse horse : horses) {
            horse.setStart(true);
            if(horse.isStart()){
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        horse.run();
                        if(horse.isFinaly()){
                            positionList.add(horse.getName().getText().toString());
                        }
                    }
                }).start();
            }
        }
    }
    private void stopRace(){
        try {
            for (Horse horse : horses) {
                horse.setStart(false);
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public void viewPositions(View v) {
        // Crear una cadena de texto con las posiciones de los caballos
        StringBuilder positionsText = new StringBuilder();
        for (int i = 0; i < positionList.size(); i++) {
            positionsText.append("Posición "+(i + 1)+": "+positionList.get(i)+"\n");
        }
        // Crear un diálogo para mostrar las posiciones
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("Posiciones de la carrera")
                .setMessage(positionsText.toString())
                .setPositiveButton("Cerrar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }
}