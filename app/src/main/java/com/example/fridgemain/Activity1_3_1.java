package com.example.fridgemain;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

public class Activity1_3_1 extends AppCompatActivity {

    private Button NFC;
    private Button done;
    private Button generateCode;
    private TextView isOnline;
    private TextView isFull;
    private TextView code;
    private EditText fridgeID;
    private Random rand = new Random();
    private int randomInt;
    private String randString = "";
    private Web web = new Web();
    private String ID;
    private String fridgeName="";
    private String url = "https://fridgemain.azurewebsites.net/api/Fridges/";
    private static final String TAG = MainActivity.class.getName();
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private String address, pin, password, oneTimeCode, oneTimeCodeNew;
    private AlertDialog alert11;
    private boolean change = false;
//    AlertDialog.Builder builder = new AlertDialog.Builder(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity1_3_1);

        NFC = findViewById(R.id.button2);
        generateCode = findViewById(R.id.button3);
        done = findViewById(R.id.button4);
        code = findViewById(R.id.textView3);
        isOnline = findViewById(R.id.textView4);
        isFull = findViewById(R.id.textView5);
        fridgeID = findViewById(R.id.editTextTextPersonName2);

        ID = getIntent().getExtras().getString("ID");
        fridgeName = getIntent().getExtras().getString("fridgeName");

        fridgeID.setText(fridgeName);

        Toast.makeText(getApplicationContext(), ID, Toast.LENGTH_LONG).show();//display the response on screen
        //builder.setMessage(R.string.dialog_message)
        //        .setTitle(R.string.dialog_title);

        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("The One-Time-Code is already in use. Are you sure you want to change One-Time-Code?");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        change = true;
                        volleyPut(ID, address, pin, oneTimeCodeNew, fridgeName);
                        dialog.cancel();
                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        change = false;
                        dialog.cancel();
                    }
                });

        alert11 = builder1.create();
        //alert11.show();

        NFC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchActivityTo1_3_1_1();
            }
        });

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchActivityTo1();
            }
        });

        generateCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                code.setText("Code: ");
                for (int i=0; i < 4; i++) {
                    randomInt = rand.nextInt(10);
                    randString+=String.valueOf(randomInt);
                }
                //Toast.makeText(getApplicationContext(), randString, Toast.LENGTH_LONG).show();//display the response on screen
                volleyGet(ID, randString);
                //Toast.makeText(getApplicationContext(), address + "; " + pin + "; " + password, Toast.LENGTH_LONG).show();//display the response on screen

                randString = "";
            }
        });
    }

    private void switchActivityTo1_3_1_1() {
        Intent switchActivityIntent = new Intent(this, Activity1_3_1_1.class);
        startActivity(switchActivityIntent);
    }

    private void switchActivityTo1() {
        Intent switchActivityIntent = new Intent(this, MainActivity.class);
        startActivity(switchActivityIntent);
    }

    public void volleyPut(String fridgeKeyGuid, String address, String pin, String oneTimeCode, String fridgeName){
        code.setText("Code: " + oneTimeCode);
        String FridgeKeyGuid = fridgeKeyGuid;
        //String Address = address;
        //boolean IsOnline = isOnline;
        //boolean IsFull = isFull;
        String Address = address;
        String Pin = pin;
        String OneTimeCode = oneTimeCode;
        //Toast.makeText(getApplicationContext(), Address + "; " + Pin + "; " + Password, Toast.LENGTH_LONG).show();//display the response on screen
        //String Pin = pin;
        //String Password = password;
        change = false;
        //url += FridgeKeyGuid;
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JSONObject putData = new JSONObject();
        try {

            putData.put("FridgeKeyGuid", FridgeKeyGuid);
            //putData.put("Address ", Address );
            //putData.put("IsOnline", IsOnline);
            //putData.put("IsFull", IsFull);
            putData.put("address", Address);
            putData.put("pin", Pin);
            putData.put("oneTimeCode", OneTimeCode);
            //putData.put("Pin", Pin);
            //putData.put("Password", Password);

            //putData.put("fridgeCodes", null);

            //"FridgeKeyGuid": "4c50b63d-7bc5-4012-9baf-0224d416b1d5",
            //       "FridgeIdString": "fridgeidstring10",
            //       "fridgeName":"Genetersafdasdfasdfasdfasdf",
            //       "IsFull": false,
            //      "IsOnline": false,
            //       "fridgeProperties":null



        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, url, putData, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        requestQueue.add(jsonObjectRequest);

    }

    public void volleyGet(String id, String RandString) {
        if (url.equals("https://fridgemain.azurewebsites.net/api/Fridges/")) url += id;
        else url = "https://fridgemain.azurewebsites.net/api/Fridges/" + id;
        //Toast.makeText(getApplicationContext(), id, Toast.LENGTH_LONG).show();//display the response on screen
        oneTimeCodeNew = RandString;
        //Toast.makeText(getApplicationContext(), temp, Toast.LENGTH_LONG).show();//display the response on screen
        mRequestQueue = Volley.newRequestQueue(this);
        //String serverPassword = new String();
        mStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject json = new JSONObject(response);
                    address = json.getString("address");
                    pin = json.getString("pin");
                    try {
                        oneTimeCode = json.getString("oneTimeCode");
                    } catch (JSONException ex) {
                        oneTimeCode = null;
                    }

                    if (!oneTimeCode.equals(null)) {
                        alert11.show();
                    } else change = true;

                    //Toast.makeText(getApplicationContext(), temp, Toast.LENGTH_LONG).show();//display the response on screen

                    if (change) volleyPut(id, address, pin, oneTimeCode, fridgeName);
                    //randString = 0;
                    //ID = json.getString("FridgeKeyGuid");
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Did not work", Toast.LENGTH_LONG).show();//display the response on screen
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.i(TAG, "Error :" + error.toString());
            }
        });

        mRequestQueue.add(mStringRequest);

    }

}