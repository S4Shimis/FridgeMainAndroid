package com.example.fridgemain;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Activity1_3 extends Activity implements OnClickListener {

    private String username, password;
    private Button ok;
    private EditText editTextUsername, editTextPassword;
    private CheckBox saveLoginCheckBox;
    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;
    private Boolean saveLogin;
    private static final String TAG = MainActivity.class.getName();
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private static String url = "https://fridgemain.azurewebsites.net/api/Fridges/User";
    //private String address = "";
    private String ID = "";
    private String serverPassword = "";
    private String fridgeName = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity1_3);

        ok = (Button) findViewById(R.id.buttonLogin);
        ok.setOnClickListener(this);
        editTextUsername = (EditText) findViewById(R.id.editTextUsername);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        saveLoginCheckBox = (CheckBox) findViewById(R.id.saveLoginCheckBox);
        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();
        mRequestQueue = Volley.newRequestQueue(this);



        saveLogin = loginPreferences.getBoolean("saveLogin", false);
        if (saveLogin == true) {
            editTextUsername.setText(loginPreferences.getString("username", ""));
            editTextPassword.setText(loginPreferences.getString("password", ""));
            saveLoginCheckBox.setChecked(true);
        }
    }

    public void onClick(View view) {
       // if (view == ok) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(editTextUsername.getWindowToken(), 0);

            username = editTextUsername.getText().toString();
            password = editTextPassword.getText().toString();

            if (saveLoginCheckBox.isChecked()) {
                loginPrefsEditor.putBoolean("saveLogin", true);
                loginPrefsEditor.putString("username", username);
                loginPrefsEditor.putString("password", password);
                loginPrefsEditor.commit();
            } else {
                loginPrefsEditor.clear();
                loginPrefsEditor.commit();
            }
            volleyGetAndCheck(username,password);
            //changeActivity();
        //}
    }

    private void switchActivityTo1_3_0() {
        Intent switchActivityIntent = new Intent(this, Activity1_3_0.class);
        switchActivityIntent.putExtra("user", username);
        switchActivityIntent.putExtra("password", password);
        switchActivityIntent.putExtra("fridgeName", fridgeName);
        switchActivityIntent.putExtra("ID", ID);
        startActivity(switchActivityIntent);
    }

    public void volleyGetAndCheck(String id, String password) {
        if (url.equals("https://fridgemain.azurewebsites.net/api/Fridges/User/")) url += id;
        else url = "https://fridgemain.azurewebsites.net/api/Fridges/User/" + id;
        mStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject json = new JSONObject(response);
                    serverPassword = json.getString("password");
                    ID = json.getString("fridgeID");
                    //Toast.makeText(getApplicationContext(), ID, Toast.LENGTH_LONG).show();//display the response on screen
                    volleyGetFridgeName(ID);
                    //ID = json.getString("FridgeKeyGuid");
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Did not work", Toast.LENGTH_LONG).show();//display the response on screen
                }


                if (password.equals(serverPassword)) switchActivityTo1_3_0();

                else
                    Toast.makeText(getApplicationContext(), "Did not work", Toast.LENGTH_LONG).show();//display the response on screen

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.i(TAG, "Error :" + error.toString());
            }
        });

        mRequestQueue.add(mStringRequest);
    }

    public void volleyGetFridgeName(String id) {
        if (url.equals("https://fridgemain.azurewebsites.net/api/Fridges/")) url += id;
        else url = "https://fridgemain.azurewebsites.net/api/Fridges/" + id;

        mRequestQueue = Volley.newRequestQueue(this);
        //String serverPassword = new String();
        mStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject json = new JSONObject(response);
                    fridgeName = json.getString("fridgeName");
                    Toast.makeText(getApplicationContext(), fridgeName, Toast.LENGTH_LONG).show();//display the response on screen

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