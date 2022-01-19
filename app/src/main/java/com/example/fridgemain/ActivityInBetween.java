package com.example.fridgemain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class ActivityInBetween extends AppCompatActivity {

    private String ID = "";
    private static final String TAG = MainActivity.class.getName();
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private static String url = "https://fridgemain.azurewebsites.net/api/Fridges/";
    private String address = "";
    private String user, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_between);


        ID = getIntent().getStringExtra("ID");
        user = getIntent().getStringExtra("user");
        password = getIntent().getStringExtra("password");


        mRequestQueue = Volley.newRequestQueue(this);
        if (url.equals("https://fridgemain.azurewebsites.net/api/Fridges/")) url += ID;
        else url = "https://fridgemain.azurewebsites.net/api/Fridges/" + ID;
        //String Request initialized
        mStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject json = new JSONObject(response);
                    address = json.getString("address");
                    //ID = json.getString("FridgeKeyGuid");
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),"JSONException", Toast.LENGTH_LONG).show();//display the response on screen
                }



                if (address.equals("null")) {

                    //answerTV.setText(response.toString());
                    Toast.makeText(getApplicationContext(), "Response: " + address, Toast.LENGTH_LONG).show();//display the response on screen
                /*try {
                    wait(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/

                    switchActivityTo1_1_1();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.i(TAG,"Error :" + error.toString());
                Toast.makeText(getApplicationContext(),"Did not work", Toast.LENGTH_LONG).show();//display the response on screen
            }
        });

        mRequestQueue.add(mStringRequest);

    }

    private void switchActivityTo1_1_1() {
        Intent switchActivityIntent = new Intent(this, Activity1_1_1.class);
        switchActivityIntent.putExtra("ID", ID.toString());
        switchActivityIntent.putExtra("user", user);
        switchActivityIntent.putExtra("password", password);
        startActivity(switchActivityIntent);
    }
}