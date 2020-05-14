package com.example.anew.voicetotext;

import android.app.Activity;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    Button speak;
    TextView msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        speak = findViewById(R.id.speak);
        msg = findViewById(R.id.msg);

        speak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent voice = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                // to contain all languages
                voice.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                voice.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
                //to start to speak
                voice.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak Now...");
                // to pass the voice msg to os
                startActivityForResult(voice, 1);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // to check the process is yours
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            //because value is not fix thats why we use array list
            ArrayList<String> arrayList = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            msg.setText(arrayList.get(0).toString());
        }
    }

}
