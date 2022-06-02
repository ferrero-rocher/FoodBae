package com.example.foodbae;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NandosActivity extends AppCompatActivity {
    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nandos);
        b=findViewById(R.id.videos1);


    }
    public void play(View v) {
        Intent i = new Intent(NandosActivity.this, video.class);
        startActivity(i);
    }

}
