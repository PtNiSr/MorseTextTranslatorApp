package com.ni___ckel.morseenglishtranslate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TranslationFromEngtoMrose extends AppCompatActivity {
    private EditText editTextTextMultiLineEngText;
    private Button buttonTranslate;

    private TextView textViewResultInMorse;
    private TranslationFromEngtoMorseViewModel viewModel;
    private String stringBelowInMorse;
    private Button buttonToSendViaLight;
    private Thread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translation_from_engto_mrose);
        initViews();

        boolean isFlashAvailable = getApplicationContext().getPackageManager()      //Checking if there is flash light (on this device).
                .hasSystemFeature(PackageManager.FEATURE_CAMERA_FRONT);

        if (!isFlashAvailable) {
            showNoFlashError();
            buttonToSendViaLight.setVisibility(View.INVISIBLE);
        }

        viewModel = new TranslationFromEngtoMorseViewModel(getApplication());
        viewModel.getStrMutEng().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                stringBelowInMorse = viewModel.TranslateFromEng();
                textViewResultInMorse.setText(stringBelowInMorse);
            }
        });

        buttonTranslate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                viewModel.setStrEngText(editTextTextMultiLineEngText.getText().toString());
            }
        });

        buttonToSendViaLight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String s = textViewResultInMorse.getText().toString();

                thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        viewModel.sendMessageViaLight(s, TranslationFromEngtoMrose.this);
                    }
                });
                thread.start();
            }
        });

    }

    public static Intent newIntent(Context context) {
        return new Intent(context, TranslationFromEngtoMrose.class);
    }

    private void initViews() {
        editTextTextMultiLineEngText = findViewById(R.id.editTextTextMultiLineEngText);
        buttonTranslate = findViewById(R.id.buttonTranslate);
        textViewResultInMorse = findViewById(R.id.textViewResultInMorse);
        buttonToSendViaLight = findViewById(R.id.buttonToSendViaLight);

    }

    private void showNoFlashError() {
        Toast.makeText(TranslationFromEngtoMrose.this, "There is no flash available", Toast.LENGTH_LONG).show();
    }
}