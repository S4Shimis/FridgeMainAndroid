package com.example.fridgemain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Activity1_1_1_1 extends AppCompatActivity {

    private TextView const12, const13, const14, const15, const16;
    private EditText title, pinCode, confirmPinCode, fridgeNormalName;
    private Button done;
    private String address;
    private String ID;
    private String user, password;
    //Web web = new Web();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity1_1_1_1);

        address = getIntent().getExtras().getString("address");
        //Toast.makeText(Activity1_1_1_1.this, "Address 1_1_1_1: " + address, Toast.LENGTH_SHORT) .show();
        ID = getIntent().getExtras().getString("ID");
        user = getIntent().getExtras().getString("user");
        password = getIntent().getExtras().getString("password");

        const12 = findViewById(R.id.textView12);
        const13 = findViewById(R.id.textView13);
        const14 = findViewById(R.id.textView14);
        //const15 = findViewById(R.id.textView15);
        //const16 = findViewById(R.id.textView16);
        pinCode = findViewById(R.id.eTPin);
        confirmPinCode = findViewById(R.id.eTconfirmPin);
        fridgeNormalName = findViewById(R.id.eTName);

        title = findViewById(R.id.editTextTextPersonName3);
        //password = findViewById(R.id.eTpassword);
        //confirmPassword = findViewById(R.id.eTconfirmPassword);

        done = findViewById(R.id.button8);

        const12.setClickable(false);
        const13.setClickable(false);
        const14.setClickable(false);
        //const15.setClickable(false);
        //const16.setClickable(false);
        title.setClickable(false);



        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pinCode.getText().toString().matches(confirmPinCode.getText().toString()) && !fridgeNormalName.getText().equals(null)) {
                    //Toast.makeText(Activity1_1_1_1.this, address, Toast.LENGTH_SHORT) .show();
                    //web.volleyPutAdress(ID, address);
                    //web.volleyPutCodeWithoutOneTimeCode(ID, pinCode.toString(), password.toString());
                    //web.volleyPut(ID,address, pinCode.toString(), password.toString());
                    String pin, name;
                    pin = pinCode.getText().toString();
                    name = fridgeNormalName.getText().toString();
                    //pass = password.getText().toString();
                    //Toast.makeText(Activity1_1_1_1.this, "Address 1_1_1_1: " + address, Toast.LENGTH_SHORT) .show();
                    Put(ID,address, pin, name);

                    switchActivityTo1();
                } else {
                    Toast.makeText(Activity1_1_1_1.this, "Pin or password does not match", Toast.LENGTH_SHORT) .show();
                }
            }
        });

    }

    private void switchActivityTo1() {
        Intent switchActivityIntent = new Intent(this, MainActivity.class);
        switchActivityIntent.putExtra("ID", ID);
        startActivity(switchActivityIntent);
    }

    public void Put (String fridgeKeyGuid, String address, String pin, String fridgeName) {
        volleyPut(fridgeKeyGuid,address,pin,fridgeName);
        //volleyUserPut(user, password, fridgeName,fridgeKeyGuid);
    }


 /*   public void volleyUserPut(String user, String password, String fridgeName, String fridgeKeyGuid){
        String User = user;
        String Password = password;
        String FridgeName = fridgeName;
        String FridgeKeyGuid = fridgeKeyGuid;

        String putUrl = "https://fridgemain.azurewebsites.net/api/Fridges/User/" + user;
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JSONObject putData = new JSONObject();
        try {

            putData.put("userName", User);
            putData.put("password", Password);
            putData.put("fridgeNormalName", FridgeName);
            putData.put("fridgeID", FridgeKeyGuid);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, putUrl, putData, new Response.Listener<JSONObject>() {
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

    }*/

    public void volleyPut(String frigeKeyGuid, String address, String pin, String fridgeName){
        String FridgeKeyGuid = frigeKeyGuid;
        String Address = address;
        String Pin = pin;
        String FridgeName = fridgeName;

        String putUrl = "https://fridgemain.azurewebsites.net/api/Fridges/" + FridgeKeyGuid;
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JSONObject putData = new JSONObject();
        try {

            putData.put("fridgeKeyGuid", FridgeKeyGuid);
            putData.put("address", Address);
            putData.put("pin", Pin);
            putData.put("fridgeName", FridgeName);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, putUrl, putData, new Response.Listener<JSONObject>() {
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

}