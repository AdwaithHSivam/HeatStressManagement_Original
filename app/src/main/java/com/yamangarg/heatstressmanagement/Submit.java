package com.yamangarg.heatstressmanagement;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.iid.FirebaseInstanceId;

public class Submit extends AppCompatActivity {

    User user;
    boolean flag;
    ProgressBar progressBar;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);
        progressBar=findViewById(R.id.progressBar2);
        user = MyApplication.user;
        flag=true;
    }
    public void previous(View view){
        onBackPressed();
    }
    public void submit(View view){

        Log.d("abcde",user.toString());
        if(flag) {
            progressBar.setVisibility(View.VISIBLE);
            db.collection("users")
                    .add(user)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Log.d("abcde", "DocumentSnapshot added with ID: " + documentReference.getId());
                            flag=false;
                            Toast.makeText(Submit.this, "Data Successfully Uploaded",Toast.LENGTH_LONG).show();
                            Intent i =new Intent(Submit.this, Location.class);
                            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(i);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w("abcde", "Error adding document", e);
                            Toast.makeText(Submit.this, "Try Again",Toast.LENGTH_LONG).show();

                        }
                    });
            progressBar.setVisibility(View.GONE);
        }


    }

}
