package com.example.horse_race;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Horse implements Runnable {
    private final ImageView horseImage;
    public LinearLayout horsesLayout;
    private static final int MAX_DISTANCE = 1000;
    private boolean start=true;
    private boolean finaly=false;
    private TextView name;
    private ProgressBar progressBar;
    public Horse(View view) {
        horseImage = view.findViewById(R.id.horseImage);
        horsesLayout = view.findViewById(R.id.racetrackLayout);
        name= view.findViewById(R.id.horseName);
        progressBar= view.findViewById(R.id.progressBar);
    }

    @Override
    public void run() {
        int distance = 0;
        while (distance < MAX_DISTANCE && start) {
            try {
                Thread.sleep((long) (Math.random() * 200)); // Simula el tiempo que tarda el caballo en avanzar
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            distance += (int) (Math.random() * 10); // Simula la distancia que avanza el caballo
            int finalDistance = Math.min(distance, MAX_DISTANCE);
            float progress = (float) finalDistance / MAX_DISTANCE;
            horseImage.post(() -> {
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) horseImage.getLayoutParams();
                params.leftMargin = (int) (progress * (horsesLayout.getWidth() - horseImage.getWidth()));
                horseImage.setLayoutParams(params);
            }); // Actualiza la posición de la imagen
            progressBar.setProgress(finalDistance/10);
        }
        finaly=distance>=MAX_DISTANCE;
    }

    public boolean isStart() {
        return start;
    }

    public void setStart(boolean start) {
        this.start = start;
    }

    public boolean isFinaly() {
        return finaly;
    }

    public TextView getName() {
        return name;
    }
}