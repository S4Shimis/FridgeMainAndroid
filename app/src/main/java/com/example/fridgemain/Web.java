package com.example.fridgemain;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Web extends AppCompatActivity{
    //private static String url;
    private String jstring = "";
    //private static JSONObject js = null;
    private static final String TAG = MainActivity.class.getName();
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private static String url = "https://fridgemain.azurewebsites.net/api/Fridges/aaf3af5a-57e9-4c1b-a646-620e86410a11";

    public String sendAndRequestResponse(String frigeKeyGuid) {

        //JSONObject jsonObje = new JSONObject();
        //RequestQueue requestQueue = Volley.newRequestQueue(this);
        //String FridgeKeyGuid = frigeKeyGuid;
        //url+="/"+FridgeKeyGuid;
        //RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(this);

        //String Request initialized
        mStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
        //JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
        //        (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            //public void onResponse(JSONObject jsonObj) {
            public void onResponse(String response) {

                //return (jsonObj);
                //js = jsonObj;

                jstring = response;
                //answerTV.setText(response.toString());
                Toast.makeText(getApplicationContext(),"Response :" + response.toString(), Toast.LENGTH_LONG).show();//display the response on screen

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.i(TAG,"Error :" + error.toString());
            }
        });

        mRequestQueue.add(mStringRequest);
        return jstring;
    }

    public void sendAndRequestResponseCode(String frigeCodeKeyGuid) {

        String FridgeCodeKeyGuid = frigeCodeKeyGuid;
        url+="/Code"+FridgeCodeKeyGuid;

        //RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(this);

        //String Request initialized
        mStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {



                //answerTV.setText(response.toString());
                //Toast.makeText(getApplicationContext(),"Response :" + response.toString(), Toast.LENGTH_LONG).show();//display the response on screen

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.i(TAG,"Error :" + error.toString());
            }
        });

        mRequestQueue.add(mStringRequest);
    }

    public void volleyPost(String frigeKeyGuid, String adress, boolean isOnline, boolean isFull){

        String FridgeKeyGuid = frigeKeyGuid;
        String Adress = adress;
        boolean IsOnline = isOnline;
        boolean IsFull = isFull;

        String postUrl = "https://fridgemain.azurewebsites.net/api/Fridges";
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JSONObject postData = new JSONObject();
        try {

            postData.put("FridgeKeyGuid", FridgeKeyGuid);
            postData.put("Adress ", Adress );
            postData.put("IsOnline", IsOnline);
            postData.put("IsFull", IsFull);
            postData.put("fridgeCodes", null);

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

    public void volleyPostCode(String frigeCodeKeyGuid, String oneTimeCode, String pin, String password){
        String postUrl = "https://fridgemain.azurewebsites.net/api/Fridges/Code";
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        String FridgeCodeKeyGuid = frigeCodeKeyGuid;
        String OneTimeCode = oneTimeCode;
        String Pin = pin;
        String Password = password;

        JSONObject postData = new JSONObject();
        try {

            postData.put("FridgeCodeKeyGuid", FridgeCodeKeyGuid);
            postData.put("OneTimeCode", OneTimeCode);
            postData.put("Pin", Pin);
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

    //public void volleyPut(String frigeKeyGuid, String adress, boolean isOnline, boolean isFull, String oneTimeCode, String pin, String password){
    public void volleyPut(String frigeKeyGuid, String adress, String pin, String password){
        String FridgeKeyGuid = frigeKeyGuid;
        String Adress = adress;
        //boolean IsOnline = isOnline;
        //boolean IsFull = isFull;
        //String OneTimeCode = oneTimeCode;
        String Pin = pin;
        String Password = password;

        String putUrl = "https://fridgemain.azurewebsites.net/api/Fridges/" + FridgeKeyGuid;
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JSONObject putData = new JSONObject();
        try {

            putData.put("FridgeKeyGuid", FridgeKeyGuid);
            putData.put("Adress ", Adress );
            //putData.put("IsOnline", IsOnline);
            //putData.put("IsFull", IsFull);
            //putData.put("OneTimeCode", OneTimeCode);
            putData.put("Pin", Pin);
            putData.put("Password", Password);

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

    public void volleyPutOneTimeCode(String frigeKeyGuid, String oneTimeCode){
        String FridgeKeyGuid = frigeKeyGuid;
        //String Adress = adress;
        //boolean IsOnline = isOnline;
        //boolean IsFull = isFull;
        String OneTimeCode = oneTimeCode;
        //String Pin = pin;
        //String Password = password;

        String putUrl = "https://fridgemain.azurewebsites.net/api/Fridges/" + FridgeKeyGuid;
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JSONObject putData = new JSONObject();
        try {

            putData.put("FridgeKeyGuid", FridgeKeyGuid);
            //putData.put("Adress ", Adress );
            //putData.put("IsOnline", IsOnline);
            //putData.put("IsFull", IsFull);
            putData.put("OneTimeCode", OneTimeCode);
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

    public void volleyPutAdress(String frigeKeyGuid, String adress){

        String FridgeKeyGuid = frigeKeyGuid;
        String Adress = adress;


        String putUrl = "https://fridgemain.azurewebsites.net/api/Fridges/" + FridgeKeyGuid;
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JSONObject putData = new JSONObject();
        try {

            putData.put("FridgeKeyGuid", FridgeKeyGuid);
            putData.put("Adress ", Adress );

            //"FridgeKeyGuid": "4c50b63d-7bc5-4012-9baf-0224d416b1d5",
            //       "FridgeIdString": "fridgeidstring10",
            //       "fridgeName":"Genetersafdasdfasdfasdfasdf",
            //       "IsFull": false,
            //      "IsOnline": false,
            //       "fridgeProperties":null



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

    public void volleyPutCode(String frigeCodeKeyGuid, String oneTimeCode, String pin, String password){

        String FridgeCodeKeyGuid = frigeCodeKeyGuid;
        String OneTimeCode = oneTimeCode;
        String Pin = pin;
        String Password = password;

        String putUrl = "https://fridgemain.azurewebsites.net/api/Fridges/Code/" + FridgeCodeKeyGuid;
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JSONObject putData = new JSONObject();
        try {

            putData.put("FridgeCodeKeyGuid", FridgeCodeKeyGuid);
            putData.put("OneTimeCode", OneTimeCode);
            putData.put("Pin", Pin);
            putData.put("Password", Password);

            //"FridgeKeyGuid": "4c50b63d-7bc5-4012-9baf-0224d416b1d5",
            //       "FridgeIdString": "fridgeidstring10",
            //       "fridgeName":"Genetersafdasdfasdfasdfasdf",
            //       "IsFull": false,
            //      "IsOnline": false,
            //       "fridgeProperties":null



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

    public void volleyPutCodeWithoutOneTimeCode(String frigeCodeKeyGuid, String pin, String password){

        String FridgeCodeKeyGuid = frigeCodeKeyGuid;
        String Pin = pin;
        String Password = password;

        String putUrl = "https://fridgemain.azurewebsites.net/api/Fridges/Code/" + FridgeCodeKeyGuid;
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JSONObject putData = new JSONObject();
        try {

            putData.put("FridgeCodeKeyGuid", FridgeCodeKeyGuid);
            putData.put("Pin", Pin);
            putData.put("Password", Password);

            //"FridgeKeyGuid": "4c50b63d-7bc5-4012-9baf-0224d416b1d5",
            //       "FridgeIdString": "fridgeidstring10",
            //       "fridgeName":"Genetersafdasdfasdfasdfasdf",
            //       "IsFull": false,
            //      "IsOnline": false,
            //       "fridgeProperties":null



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

    public void volleyPutCodeOneTimeCode(String frigeCodeKeyGuid, String oneTimeCode){

        String FridgeCodeKeyGuid = frigeCodeKeyGuid;
        String OneTimeCode = oneTimeCode;

        String putUrl = "https://fridgemain.azurewebsites.net/api/Fridges/Code/" + FridgeCodeKeyGuid;
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JSONObject putData = new JSONObject();
        try {

            putData.put("FridgeCodeKeyGuid", FridgeCodeKeyGuid);
            putData.put("OneTimeCode", OneTimeCode);

            //"FridgeKeyGuid": "4c50b63d-7bc5-4012-9baf-0224d416b1d5",
            //       "FridgeIdString": "fridgeidstring10",
            //       "fridgeName":"Genetersafdasdfasdfasdfasdf",
            //       "IsFull": false,
            //      "IsOnline": false,
            //       "fridgeProperties":null



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
