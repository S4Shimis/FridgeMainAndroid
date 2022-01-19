package com.example.fridgemain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Activity1_3_1_1 extends AppCompatActivity {

    private Button done;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity1_3_1_1);

        done = findViewById(R.id.button5);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchActivityTo1();
            }
        });

    }

    private void switchActivityTo1() {
        Intent switchActivityIntent = new Intent(this, MainActivity.class);
        startActivity(switchActivityIntent);
    }

}