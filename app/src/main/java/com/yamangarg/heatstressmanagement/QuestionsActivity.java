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
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormat;
import java.util.Date;

public class QuestionsActivity extends Activity {

    LocationManager locationManager;
    LocationListener locationListener;

    RadioGroup optionsAa,optionsAb,optionsAc,optionsB,optionsC,optionsD;
    ProgressBar progressBar;
    User user;
    final String Qid[] = {"QuestionAa","QuestionAb","QuestionAc","QuestionB","QuestionC","QuestionD"};
    final String OptionsAa[] = {"DryHot", "Windy", "Humid", "Rainy"};
    final String OptionsAb[] = {"1100-1330", "1330-1600"};
    final String OptionsAc[] = {"Comfortable", "Warm", "Very Hot", "Sweltering"};
    final String OptionsB[]={"NoActivity","Light","Moderate","Heavy"};
    final String OptionsC[]={"DirectSun","Shading","Indoor"};
    final String OptionsD[]={"FullCovered","Normal","Minimal"};

    int getOptionAa(){
        switch (optionsAa.getCheckedRadioButtonId()){
            case R.id.radioButton1Aa: return 0;
            case R.id.radioButton2Aa: return 1;
            case R.id.radioButton3Aa: return 2;
            case R.id.radioButton4Aa: return 3;
        }
        return 0;
    }
    int getOptionAb(){
        switch (optionsAb.getCheckedRadioButtonId()){
            case R.id.radioButton1Ab: return 0;
            case R.id.radioButton2Ab: return 1;
        }
        return 0;
    }
    int getOptionAc(){
        switch (optionsAc.getCheckedRadioButtonId()){
            case R.id.radioButton1Ac: return 0;
            case R.id.radioButton2Ac: return 1;
            case R.id.radioButton3Ac: return 2;
            case R.id.radioButton4Ac: return 3;
        }
        return 0;
    }
    int getOptionB(){
        switch (optionsB.getCheckedRadioButtonId()){
            case R.id.radioButton1B: return 0;
            case R.id.radioButton2B: return 1;
            case R.id.radioButton3B: return 2;
            case R.id.radioButton4B: return 3;
        }
        return 0;
    }
    int getOptionC(){
        switch (optionsC.getCheckedRadioButtonId()){
            case R.id.radioButton1C: return 0;
            case R.id.radioButton2C: return 1;
            case R.id.radioButton3C: return 2;
        }
        return 0;
    }
    int getOptionD(){
        switch (optionsD.getCheckedRadioButtonId()){
            case R.id.radioButton1D: return 0;
            case R.id.radioButton2D: return 1;
            case R.id.radioButton3D: return 2;
        }
        return 0;
    }

    public void submit(View view) {

        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            showGPSDisabledAlertToUser();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        if (optionsAa.getCheckedRadioButtonId() != -1)
            user.AddResponse(Qid[0], OptionsAa[getOptionAa()]);
        if (optionsAb.getCheckedRadioButtonId() != -1)
            user.AddResponse(Qid[1], OptionsAb[getOptionAb()]);
        if (optionsAc.getCheckedRadioButtonId() != -1)
            user.AddResponse(Qid[2], OptionsAc[getOptionAc()]);
        if (optionsB.getCheckedRadioButtonId() != -1)
            user.AddResponse(Qid[3], OptionsB[getOptionB()]);
        if (optionsC.getCheckedRadioButtonId() != -1)
            user.AddResponse(Qid[4], OptionsC[getOptionC()]);
        if (optionsD.getCheckedRadioButtonId() != -1)
            user.AddResponse(Qid[5], OptionsD[getOptionD()]);
        Log.d("abcde", user.toString());


        FirebaseFirestore.getInstance().collection("responses")
                .document(FirebaseAuth.getInstance().getCurrentUser().getUid()+" -" + DateFormat.getDateInstance().format(new Date()))
                .set(user.responseObject)
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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {

            @Override
            public void onLocationChanged(android.location.Location location) {

                //Log.i("Location", location.toString());
                MyApplication.user.responseObject.location = location;

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


        optionsAa = findViewById(R.id.OptionsAa);
        optionsAb = findViewById(R.id.OptionsAb);
        optionsAc = findViewById(R.id.OptionsAc);
        optionsB = findViewById(R.id.OptionsB);
        optionsC = findViewById(R.id.OptionsC);
        optionsD = findViewById(R.id.OptionsD);
        progressBar = findViewById(R.id.progressBarQ);
        user = MyApplication.user;
        progressBar.setVisibility(View.GONE);
        TextView link = findViewById(R.id.hyperlink);
        link.setMovementMethod(LinkMovementMethod.getInstance());
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
                            startActivityForResult(intent, 1000);     // Comment 3.
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

}
