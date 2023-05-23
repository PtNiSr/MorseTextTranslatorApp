package com.ni___ckel.morseenglishtranslate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Translation extends AppCompatActivity {

    private TextView TextViewEnterText;
    private Button buttonDit;
    private Button buttonDash;
    private Button buttonSpace;
    private Button buttonDelete;

    private TextView textViewTest;

    private TranslationViewModel viewModel;
    private String stringBelow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translation);
        initViews();

        viewModel = new TranslationViewModel(getApplication());
        viewModel.getStr().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                TextViewEnterText.setText(s);
                stringBelow = viewModel.translateFromMorse();
                textViewTest.setText(stringBelow);

                boolean mistakeInMorse = viewModel.mistakeMorse(stringBelow);
                if (mistakeInMorse){
                    Toast.makeText(Translation.this, "# - wrong Morse code", Toast.LENGTH_SHORT).show();
                }


            }
        });

        buttonDit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.addDit();
            }
        });

        buttonDash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.addDash();
            }
        });

        buttonSpace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.addSpace();
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.strDelete();
            }
        });


    }

    private void initViews() {
        TextViewEnterText = findViewById(R.id.TextViewEnterText);
        buttonDit = findViewById(R.id.buttonDit);
        buttonDash = findViewById(R.id.buttonDash);
        buttonSpace = findViewById(R.id.buttonSpace);
        buttonDelete = findViewById(R.id.buttonDelete);
        textViewTest = findViewById(R.id.textViewTest);


    }

    public static Intent newIntent(Context context) {
        return new Intent(context, Translation.class);
    }


}