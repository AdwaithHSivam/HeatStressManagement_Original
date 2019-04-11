package com.yamangarg.heatstressmanagement;


import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.os.ResultReceiver;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.GeoPoint;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class QuestionsActivity extends Activity {

    LocationManager locationManager;
    LocationListener locationListener;

    RadioGroup whichDay, optionsAa,optionsAb,optionsAc,optionsB,optionsC,optionsD;
    ProgressBar progressBar,progressBarL;
    User user;
    EditText loc;


    HashMap<String,BiMap> Qid = new HashMap<>();
    HashMap<String,RadioGroup> Qradio = new HashMap<>();

    BiMap<Integer,String> options_Aa=new BiMap<>();
    BiMap<Integer,String> options_Ab=new BiMap<>();
    BiMap<Integer,String> options_Ac=new BiMap<>();
    BiMap<Integer,String> options_B=new BiMap<>();
    BiMap<Integer,String> options_C=new BiMap<>();
    BiMap<Integer,String> options_D=new BiMap<>();




    private void initializeMap(){
        options_Aa.put(R.id.radioButton1Aa,"DryHot");
        options_Aa.put(R.id.radioButton2Aa,"Windy");
        options_Aa.put(R.id.radioButton3Aa,"Humid");
        options_Aa.put(R.id.radioButton4Aa,"Rainy");

        options_Ab.put(R.id.radioButton1Ab,"1100-1330");
        options_Ab.put(R.id.radioButton2Ab,"1330-1600");

        options_Ac.put(R.id.radioButton1Ac,"Comfortable");
        options_Ac.put(R.id.radioButton2Ac,"Warm");
        options_Ac.put(R.id.radioButton3Ac,"VeryHot");
        options_Ac.put(R.id.radioButton4Ac,"Sweltering");

        options_B.put(R.id.radioButton1B,"NoActivity");
        options_B.put(R.id.radioButton2B,"Light");
        options_B.put(R.id.radioButton3B,"Moderate");
        options_B.put(R.id.radioButton4B,"Heavy");

        options_C.put(R.id.radioButton1C,"DirectSun");
        options_C.put(R.id.radioButton2C,"Shading");
        options_C.put(R.id.radioButton3C,"Indoor");

        options_D.put(R.id.radioButton1D,"FullCovered");
        options_D.put(R.id.radioButton2D,"Normal");
        options_D.put(R.id.radioButton3D,"Minimal");

        Qid.put("QuestionAa",options_Aa);
        Qid.put("QuestionAb",options_Ab);
        Qid.put("QuestionAc",options_Ac);
        Qid.put("QuestionB",options_B);
        Qid.put("QuestionC",options_C);
        Qid.put("QuestionD",options_D);

        Qradio.put("QuestionAa",optionsAa);
        Qradio.put("QuestionAb",optionsAb);
        Qradio.put("QuestionAc",optionsAc);
        Qradio.put("QuestionB",optionsB);
        Qradio.put("QuestionC",optionsC);
        Qradio.put("QuestionD",optionsD);


    }



    public void submit(View view) {

        Calendar cal = Calendar.getInstance();


        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            showGPSDisabledAlertToUser();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);

        for (Map.Entry<String, BiMap> pair : Qid.entrySet()) {
            user.AddResponse(pair.getKey(), (String) pair.getValue().map.get(Qradio.get(pair.getKey()).getCheckedRadioButtonId()));

        }



        //Log.d("abcde", user.toString());

        if(whichDay.getCheckedRadioButtonId()==R.id.yesterday){
            cal.add(Calendar.DATE, -1);
        }

        Map a= new HashMap<>();
        a.put("Uid",FirebaseAuth.getInstance().getCurrentUser().getUid());

        FirebaseFirestore.getInstance().collection("responses_3").document(FirebaseAuth.getInstance().getCurrentUser().getUid()).set(a);

        FirebaseFirestore.getInstance().collection("responses_3")
                .document(FirebaseAuth.getInstance().getCurrentUser().getUid()).collection("responses_by_date").document( DateFormat.getDateInstance().format(cal.getTime()))
                .set(user.responseObject)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            FirebaseFirestore.getInstance().collection("responses_3")
                                    .document(FirebaseAuth.getInstance().getCurrentUser().getUid()).collection("last_response").document( "last")
                                    .set(user.responseObject.Responses)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()){
                                                Toast.makeText(getApplicationContext(),"Response Submission Successful",Toast.LENGTH_SHORT).show();

                                                onBackPressed();

                                            }
                                            else {
                                                Toast.makeText(getApplicationContext(),"Response Submission Unsuccessful",Toast.LENGTH_SHORT).show();
                                                progressBar.setVisibility(View.GONE);
                                            }
                                        }
                                    });

                        }
                        else {
                            Toast.makeText(getApplicationContext(),"Response Submission Unsuccessful",Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });




    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {

            @Override
            public void onLocationChanged(android.location.Location location) {

                //Log.i("Location", location.toString());
                if (location!=null) {
                    progressBarL.setVisibility(View.VISIBLE);

                    AddressResultReceiver resultReceiver=new AddressResultReceiver(new Handler());

                    MyApplication.user.responseObject.geoPoint = new GeoPoint(location.getLatitude(), location.getLongitude());

                    Intent intent = new Intent(QuestionsActivity.this, FetchAddressIntentService.class);
                    intent.putExtra(FetchAddressIntentService.RECEIVER, resultReceiver);
                    intent.putExtra(FetchAddressIntentService.LOCATION_DATA_EXTRA, location);
                    startService(intent);

                }
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }

        };


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
        else {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        }

        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            showGPSDisabledAlertToUser();
        }

        whichDay = findViewById(R.id.whichDay);
        optionsAa = findViewById(R.id.OptionsAa);
        optionsAb = findViewById(R.id.OptionsAb);
        optionsAc = findViewById(R.id.OptionsAc);
        optionsB = findViewById(R.id.OptionsB);
        optionsC = findViewById(R.id.OptionsC);
        optionsD = findViewById(R.id.OptionsD);
        progressBar = findViewById(R.id.progressBarQ);
        progressBarL= findViewById(R.id.progressbarL);
        loc=findViewById(R.id.loc);
        user = MyApplication.user;
        initializeMap();
        progressBar.setVisibility(View.GONE);
        progressBarL.setVisibility(View.GONE);
        TextView link = findViewById(R.id.hyperlink);
        link.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Override
    protected void onStart() {
        super.onStart();
        DocumentReference docRef = FirebaseFirestore.getInstance().collection("responses_3")
                .document(FirebaseAuth.getInstance().getCurrentUser().getUid()).collection("last_response").document( "last");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        //Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                        MyApplication.user.responseObject.Responses= document.getData();
                        for (Map.Entry<String, Object> pair : MyApplication.user.responseObject.Responses.entrySet()) {
                            Object a = Qid.get(pair.getKey()).inversedMap.get(pair.getValue());
                            if(a!=null) {
                                Qradio.get(pair.getKey()).check(( int)a);
                            }
                        }

                    }

                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

            }

        } else if (Build.VERSION.SDK_INT >= 23 && !shouldShowRequestPermissionRationale(permissions[0])) {
            // User selected the Never Ask Again Option Change settings in app settings manually
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setTitle("Error");
            alertDialogBuilder
                    .setMessage("This app requires access to location")
                    .setCancelable(false)
                    .setPositiveButton("Go to Settings", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                            Uri uri = Uri.fromParts("package", getPackageName(), null);
                            intent.setData(uri);
                            startActivityForResult(intent, 1000);
                        }
                    });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();

        } else {
            // User selected Deny Dialog to EXIT App ==> OR <== RETRY to have a second chance to Allow Permissions
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                alertDialogBuilder.setTitle("Error");
                alertDialogBuilder
                        .setMessage("This app requires access to location." )
                        .setCancelable(false)
                        .setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, Integer.parseInt(WRITE_EXTERNAL_STORAGE));
                                Intent i = new Intent(QuestionsActivity.this, Location.class);
                                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(i);
                            }
                        })
                        .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                onBackPressed();
                                dialog.cancel();
                            }
                        });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        }

    }


    private void showGPSDisabledAlertToUser(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Location is disabled.")
                .setCancelable(false)
                .setPositiveButton("Location Settings",
                        new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int id){
                                Intent callGPSSettingIntent = new Intent(
                                        android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                startActivity(callGPSSettingIntent);
                            }
                        });
        alertDialogBuilder.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id){
                        dialog.cancel();
                    }
                });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }


    class AddressResultReceiver extends ResultReceiver {
        public AddressResultReceiver(Handler handler) {
            super(handler);
        }

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {

            if (resultData == null) {
                return;
            }

            // Display the address string
            // or an error message sent from the intent service.
            String addressOutput = resultData.getString(FetchAddressIntentService.RESULT_DATA_KEY);
            if (addressOutput == null) {
                addressOutput = "";
            }


            // Show a toast message if an address was found.
            if (resultCode == FetchAddressIntentService.SUCCESS_RESULT) {
                loc.setText(addressOutput);
                MyApplication.user.responseObject.place=addressOutput;
                progressBarL.setVisibility(View.GONE);
                Toast.makeText(QuestionsActivity.this,getString(R.string.address_found),Toast.LENGTH_SHORT).show();
            }

        }
    }

}
