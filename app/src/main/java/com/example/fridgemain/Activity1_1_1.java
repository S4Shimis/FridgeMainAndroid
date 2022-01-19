package com.example.fridgemain;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ComponentActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;

public class Activity1_1_1 extends AppCompatActivity implements LocationListener {

    LocationManager locationManager;
    TextView tvLocality, const7, const8, const9, const10, const11, const17;
    private EditText eTCountry, eTState, eTCity, eTPostal, eTApartment, eTStreet;
    private String ID,user,password;
    Button manualChange, confirm;
    int REQUEST_CODE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity1_1_1);

        ID = getIntent().getExtras().getString("ID");
        user = getIntent().getExtras().getString("user");
        password = getIntent().getExtras().getString("password");

        /*if (ContextCompat.checkSelfPermission(Activity1_1_1.this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getApplicationContext(),
                android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(Activity1_1_1.this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_CODE);*/
            /*if (Build.VERSION.SDK_INT >= 11) {
                recreate();
            } else {
                Intent intent = getIntent();
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                finish();
                overridePendingTransition(0, 0);

                startActivity(intent);
                overridePendingTransition(0, 0);
            }*/
       // }

        checkPermission(Manifest.permission.ACCESS_FINE_LOCATION, REQUEST_CODE);

        eTCity = findViewById(R.id.etCity);
        eTState = findViewById(R.id.etState);
        eTCountry = findViewById(R.id.etCountry);
        eTPostal = findViewById(R.id.etPostal);
        eTApartment = findViewById(R.id.etApartemnt);
        eTStreet = findViewById(R.id.etStreet);

        tvLocality = findViewById(R.id.tvLocality);
        const7 = findViewById(R.id.textView7);
        const8 = findViewById(R.id.textView8);
        const9 = findViewById(R.id.textView9);
        const10 = findViewById(R.id.textView10);
        const11 = findViewById(R.id.textView11);
        const17 = findViewById(R.id.textView17);

        manualChange = findViewById(R.id.button6);
        confirm = findViewById(R.id.button7);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        /*eTCity.setClickable(false);
        eTState.setClickable(false);
        eTCountry.setClickable(false);
        eTPostal.setClickable(false);
        eTApartment.setClickable(false);
*/

        eTCity.setEnabled(false);
        eTState.setEnabled(false);
        eTCountry.setEnabled(false);
        eTPostal.setEnabled(false);
        eTApartment.setEnabled(false);
        eTStreet.setEnabled(false);





        manualChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!manualChange.getText().equals("Check address")) {
                    eTCity.setEnabled(true);
                    eTState.setEnabled(true);
                    eTCountry.setEnabled(true);
                    eTPostal.setEnabled(true);
                    eTApartment.setEnabled(true);
                    eTStreet.setEnabled(true);
                    tvLocality.setText("");
                    manualChange.setText("Check address");
                    Toast.makeText(getApplicationContext(), "Now you can change your address", Toast.LENGTH_SHORT).show();
                }
                else {
                    adressString();
                }
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO
                switchActivityTo1_1_1_1();
            }
        });

        /*eTCity.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                Location location;
                Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
                List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                if (eTApartment.getText().equals("")) {
                    tvLocality.setText(eTCountry.getText() + " ," + eTCity.getText() + " ," + eTPostal.getText() + "");
                }
                return false;
            }
        });*/


        /*public void onRequestPermissionsResult(
        int requestCode,
        String[] permissions,
        int[] grantResults
        )*/
        //locationEnabled();
        //getLocation();
    }
    /*@Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 101:
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        locationEnabled();
                        getLocation();
                    // Permission is granted. Continue the action or workflow
                    // in your app.
                } else {
                        Toast.makeText(getApplicationContext(), "You have denied permission", Toast.LENGTH_SHORT).show();
                    // Explain to the user that the feature is unavailable because
                    // the features requires a permission that the user has denied.
                    // At the same time, respect the user's decision. Don't link to
                    // system settings in an effort to convince the user to change
                    // their decision.
                }
                return;
        }
        // Other 'case' lines to check for other
        // permissions this app might request.
    }*/

    public void checkPermission(String permission, int requestCode)
    {
        if (ContextCompat.checkSelfPermission(Activity1_1_1.this, permission) == PackageManager.PERMISSION_DENIED) {

            // Requesting the permission
            ActivityCompat.requestPermissions(Activity1_1_1.this, new String[] { permission }, requestCode);
        }
        else {
            Toast.makeText(Activity1_1_1.this, "Permission already granted", Toast.LENGTH_SHORT).show();
            locationEnabled();
            getLocation();
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

        if (requestCode == REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(Activity1_1_1.this, "GPS Permission Granted", Toast.LENGTH_SHORT) .show();
                locationEnabled();
                getLocation();
            }
            else {
                Toast.makeText(Activity1_1_1.this, "GPS Permission Denied", Toast.LENGTH_SHORT) .show();
            }
        }
    }

    private void locationEnabled() {
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        boolean gps_enabled = false;
        boolean network_enabled = false;
        try {
            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!gps_enabled && !network_enabled) {
            new AlertDialog.Builder(Activity1_1_1.this)
                    .setTitle("Enable GPS Service")
                    .setMessage("We need your GPS location to show Near Places around you.")
                    .setCancelable(false)
                    .setPositiveButton("Enable", new
                            DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                                    startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                                }
                            })
                    .setNegativeButton("Cancel", null)
                    .show();
        }
    }

    void getLocation() {
        try {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 500, 5, (LocationListener) this);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        try {
            Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);

            eTCity.setText(addresses.get(0).getLocality());
            //eTState.setText(addresses.get(0).getAdminArea());
            eTState.setText(addresses.get(0).getSubLocality());
            eTCountry.setText(addresses.get(0).getCountryName());
            eTPostal.setText(addresses.get(0).getPostalCode());
            eTStreet.setText(addresses.get(0).getThoroughfare() + " " + addresses.get(0).getSubThoroughfare());
            tvLocality.setText(addresses.get(0).getAddressLine(0));

        } catch (Exception e) {
        }
    }

    //public void

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    private void switchActivityTo1_1_1_1() {
        Intent switchActivityIntent = new Intent(this, Activity1_1_1_1.class);
        String address = tvLocality.getText().toString();
        //Toast.makeText(Activity1_1_1.this, "Address 1_1_1: " + address, Toast.LENGTH_SHORT) .show();
        switchActivityIntent.putExtra("address", address);
        switchActivityIntent.putExtra("ID", ID.toString());
        switchActivityIntent.putExtra("user", user);
        switchActivityIntent.putExtra("password", password);
        startActivity(switchActivityIntent);
    }

    public void adressString() {
            boolean country = true;
            boolean state = true;
            boolean city = true;
            boolean street = true;
            boolean apartment = true;
            boolean postal = true;
            String finalstring = "";

            if (eTCountry.getText().toString().matches("")) country = false;
            if (eTState.getText().toString().matches("")) city = false;
            if (eTCity.getText().toString().matches("")) country = false;
            if (eTStreet.getText().toString().matches("")) city = false;
            if (eTApartment.getText().toString().matches("")) apartment = false;
            if (eTPostal.getText().toString().matches("")) postal = false;

            if (street) finalstring+=eTStreet.getText()+", ";
            if (apartment) finalstring+=eTApartment.getText()+", ";
            if (state) finalstring+=eTState.getText()+", ";
            if (city) finalstring+=eTCity.getText()+", ";
            if (postal) finalstring+="LV-"+eTPostal.getText()+", ";
            if (country) finalstring+=eTCountry.getText();
            //if (postal) finalstring+="LV-"+eTPostal.getText();

            tvLocality.setText(finalstring);
        //return false;
    }

}