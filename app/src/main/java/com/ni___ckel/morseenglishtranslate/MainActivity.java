package com.ni___ckel.morseenglishtranslate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Camera;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

import java.security.Policy;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private TextView textViewStart;
    private TextView textViewStartFromEnglishToMorse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

        textViewStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = Translation.newIntent(MainActivity.this);
                startActivity(intent);
            }
        });

        textViewStartFromEnglishToMorse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = TranslationFromEngtoMrose.newIntent(MainActivity.this);
                startActivity(intent);
            }
        });

    }

    private void initViews(){
        textViewStart = findViewById(R.id.textViewStartFromMorseToEnglish);
        textViewStartFromEnglishToMorse = findViewById(R.id.textViewStartFromEnglishToMorse);
    }


}