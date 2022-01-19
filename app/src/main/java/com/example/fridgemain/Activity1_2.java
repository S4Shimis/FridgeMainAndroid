package com.example.fridgemain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.UUID;

public class Activity1_2 extends AppCompatActivity {

    private EditText email, confirmEmail, password, confirmPassword;
    private Button done;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity1_2);

        email = findViewById(R.id.eTPin);
        confirmEmail = findViewById(R.id.eTconfirmPin);
        password = findViewById(R.id.eTpassword);
        confirmPassword = findViewById(R.id.eTconfirmPassword);

        done = findViewById(R.id.button8);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO
                if (email.getText().toString().equals(confirmEmail.getText().toString()) && password.getText().toString().equals(confirmPassword.getText().toString())) {
                    String uniqueID = UUID.randomUUID().toString();
                    volleyPostUser(uniqueID, email.getText().toString(), password.getText().toString());
                    switchActivityTo1();
                }
                else Toast.makeText(getApplicationContext(), "Email or password are incorrect",Toast.LENGTH_LONG).show();//display the response on screen
            }
        });
    }


    private void switchActivityTo1() {
        Intent switchActivityIntent = new Intent(this, MainActivity.class);
        startActivity(switchActivityIntent);
    }

    public void volleyPostUser(String frigeUserKeyGuid, String username, String password){
        String postUrl = "https://fridgemain.azurewebsites.net/api/Fridges/User";
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        String FridgeUserKeyGuid = frigeUserKeyGuid;
        String Username = username;
        String Password = password;

        JSONObject postData = new JSONObject();
        try {

            postData.put("Username", Username);
            postData.put("Password", Password);

            //"FridgeKeyGuid": "4c50b63d-7bc5-4012-9baf-0224d416b1d5",
            //       "FridgeIdString": "fridgeidstring10",
            //       "fridgeName":"Genetersafdasdfasdfasdfasdf",
            //       "IsFull": false,
            //      "IsOnline": false,
            //       "fridgeProperties":null



        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, postUrl, postData, new Response.Listener<JSONObject>() {
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