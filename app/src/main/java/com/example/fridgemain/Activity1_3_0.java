package com.example.fridgemain;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Activity1_3_0 extends AppCompatActivity {

    private Button qrcode,next;
    private String fridgeName = "";
    private String ID = "";
    private String user, password;
    private static final int CAMERA_PERMISSION_CODE = 201;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity1_3_0);

        qrcode = (Button) findViewById(R.id.button10);
        next = (Button) findViewById(R.id.button11);

        user = getIntent().getExtras().getString("user");
        password = getIntent().getExtras().getString("password");
        fridgeName = getIntent().getExtras().getString("fridgeName");
        ID = getIntent().getExtras().getString("ID");
        //Toast.makeText(getApplicationContext(), ID, Toast.LENGTH_LONG).show();//display the response on screen


        Spinner fridgeSpinner = (Spinner) findViewById(R.id.spinner);
        fridgeSpinner.getBackground().setColorFilter(ContextCompat.getColor(this, R.color.black), PorterDuff.Mode.SRC_ATOP);

        ArrayList<String> fridges = new ArrayList();

        fridges.add("Select your fridge...");
        //fridges.add("Azenes iela 12");
        //fridges.add("Daugavgrivas iela 2");

        if (!fridgeName.equals("") && !fridgeName.equals("null")) fridges.add(fridgeName);

        final List<String> fridgeList = new ArrayList<String>(fridges);

        fridgeSpinner.setSelection(0);

        // Initializing an ArrayAdapter
        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this,R.layout.spinner_item,fridgeList){
            @Override
            public boolean isEnabled(int position){
                if(position == 0)
                {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                }
                else
                {
                    return true;
                }
            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        fridgeSpinner.setAdapter(spinnerArrayAdapter);

        fridgeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItemText = (String) parent.getItemAtPosition(position);
                // If user change the default selection
                // First item is disable and it is used for hint
                if(position > 0){
                    // Notify the selected item text
                    Toast.makeText
                            (getApplicationContext(), "Selected : " + selectedItemText, Toast.LENGTH_SHORT)
                            .show();
                    fridgeName = selectedItemText;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                    fridgeSpinner.setSelection(0);
            }
        });

        qrcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchActivityQr();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchActivities();
            }
        });

    }

    private void switchActivities() {
        Intent switchActivityIntent = new Intent(this, Activity1_3_1.class);
        switchActivityIntent.putExtra("ID", ID);
        switchActivityIntent.putExtra("fridgeName", fridgeName);
        //Toast.makeText(getApplicationContext(), ID, Toast.LENGTH_LONG).show();//display the response on screen
        startActivity(switchActivityIntent);
    }

    private void switchActivityQr() {
        checkPermission(Manifest.permission.CAMERA, CAMERA_PERMISSION_CODE);

    }

    public void checkPermission(String permission, int requestCode)
    {
        if (ContextCompat.checkSelfPermission(Activity1_3_0.this, permission) == PackageManager.PERMISSION_DENIED) {

            // Requesting the permission
            ActivityCompat.requestPermissions(Activity1_3_0.this, new String[] { permission }, requestCode);
        }
        else {
            Toast.makeText(Activity1_3_0.this, "Permission already granted", Toast.LENGTH_SHORT).show();
            Intent switchActivityIntent = new Intent(this, ScanQRCodeActivity.class);
            switchActivityIntent.putExtra("user", user);
            switchActivityIntent.putExtra("password", password);
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
                Toast.makeText(Activity1_3_0.this, "Camera Permission Granted", Toast.LENGTH_SHORT) .show();
                Intent switchActivityIntent = new Intent(this, ScanQRCodeActivity.class);
                switchActivityIntent.putExtra("user", user);
                switchActivityIntent.putExtra("password", password);
                startActivity(switchActivityIntent);
            }
            else {
                Toast.makeText(Activity1_3_0.this, "Camera Permission Denied", Toast.LENGTH_SHORT) .show();
            }
        }
    }



}