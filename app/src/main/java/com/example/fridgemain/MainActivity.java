package com.example.fridgemain;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button button1_1, button1_2;
    //private ImageView qrcode;
    private static final int CAMERA_PERMISSION_CODE = 201;
    private String ID = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            ID = getIntent().getExtras().getString("ID");
        } catch (Exception exc) {
            ID = "";
        }


        button1_1 = (Button) findViewById(R.id.button);
        button1_2=(Button) findViewById(R.id.button9);
        //qrcode = (ImageView) findViewById(R.id.imageView);

        button1_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchActivities();
            }
        });

        button1_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchActivities1_2();
            }
        });

        /*qrcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchActivityQr();
            }
        });*/
    }

    private void switchActivities() {
        Intent switchActivityIntent = new Intent(this, Activity1_3.class);
        switchActivityIntent.putExtra("ID", ID);
        startActivity(switchActivityIntent);
    }

    private void switchActivities1_2() {
        Intent switchActivityIntent = new Intent(this, Activity1_2.class);
        startActivity(switchActivityIntent);
    }

    private void switchActivityQr() {
        checkPermission(Manifest.permission.CAMERA, CAMERA_PERMISSION_CODE);
    }

    public void checkPermission(String permission, int requestCode)
    {
        if (ContextCompat.checkSelfPermission(MainActivity.this, permission) == PackageManager.PERMISSION_DENIED) {

            // Requesting the permission
            ActivityCompat.requestPermissions(MainActivity.this, new String[] { permission }, requestCode);
        }
        else {
            Toast.makeText(MainActivity.this, "Permission already granted", Toast.LENGTH_SHORT).show();
            Intent switchActivityIntent = new Intent(this, ScanQRCodeActivity.class);
            startActivity(switchActivityIntent);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode,
                permissions,
                grantResults);

        if (requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(MainActivity.this, "Camera Permission Granted", Toast.LENGTH_SHORT) .show();
                Intent switchActivityIntent = new Intent(this, ScanQRCodeActivity.class);
                startActivity(switchActivityIntent);
            }
            else {
                Toast.makeText(MainActivity.this, "Camera Permission Denied", Toast.LENGTH_SHORT) .show();
            }
        }
    }

}