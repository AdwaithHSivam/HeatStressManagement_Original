package com.yamangarg.heatstressmanagement;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class Location extends AppCompatActivity {

    FirebaseAuth mAuth;
    ProgressBar progressBar;
    String TAG ="abcde";


    @Override
    public void onBackPressed() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        mAuth = FirebaseAuth.getInstance();
        progressBar=findViewById(R.id.progressBar3);
        progressBar.setVisibility(View.VISIBLE);




    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser==null){
            //startActivity(new Intent(this, Login.class));
            mAuth.signInAnonymously().addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()) {
                        startActivity(new Intent(Location.this, Registration.class));
                    }
                }
            });
        }
        else {
            DocumentReference docRef = FirebaseFirestore.getInstance().collection("userdata").document(currentUser.getUid());
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            //Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                            MyApplication.user.userData= document.toObject(UserData.class);
                            startActivity(new Intent(Location.this, QuestionsActivity.class));
                            finish();
                        } else {
                            //Log.d(TAG, "No such document");
                            startActivity(new Intent(Location.this, Registration.class));
                        }

                    } else {
                        //Log.d(TAG, "get failed with ", task.getException());
                    }
                }
            });

        }

    }
}
