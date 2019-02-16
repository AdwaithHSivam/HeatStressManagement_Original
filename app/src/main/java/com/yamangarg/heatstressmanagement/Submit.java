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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.iid.FirebaseInstanceId;

import java.text.DateFormat;
import java.util.Date;

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
        Log.d("abcde", "Progressbar Visibility0: " + progressBar.getVisibility());

        if(flag) {
            progressBar.setVisibility(View.VISIBLE);
            db.collection("responses")
                    .document(FirebaseAuth.getInstance().getCurrentUser().getUid()+" -" +DateFormat.getDateTimeInstance().format(new Date()))
                    .set(user.responseObject)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(getApplicationContext(),"Response Submission Successful",Toast.LENGTH_SHORT).show();
                                Intent i =new Intent(Submit.this, Location.class);
                                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(i);

                            }
                            else {
                                Toast.makeText(getApplicationContext(),"Response Submission Unsuccessful",Toast.LENGTH_SHORT).show();

                            }
                        }
                    });

            progressBar.setVisibility(View.GONE);
        }


    }

}
