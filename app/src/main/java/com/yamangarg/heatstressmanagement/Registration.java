package com.yamangarg.heatstressmanagement;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.firestore.FirebaseFirestore;


public class Registration extends AppCompatActivity {


    private FirebaseAuth mAuth;

    EditText firstName;
    EditText lastName;
    RadioGroup gender;
    RadioGroup age;
    EditText email;
    Button register;
    TextView genderTextView;
    TextView ageTextView;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        Log.d("abcde", "Message1");

        mAuth=FirebaseAuth.getInstance();
        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        gender = findViewById(R.id.gender);
        age = findViewById(R.id.age);
        email = findViewById(R.id.email);
        register = findViewById(R.id.register);
        genderTextView = findViewById(R.id.genderTextView);
        ageTextView = findViewById(R.id.ageTextView);
        progressBar = findViewById(R.id.progressBar);

        Log.d("abcde", "Message2");
    }
    public void register(View view) {

                Log.d("abcde","Message3"+ R.id.belowTwenty);
                Log.d("abcde","Message3"+age.getCheckedRadioButtonId());

                if(checkDataEntered()) {
                    Log.d("abcde","Message4");
                    progressBar.setVisibility(View.VISIBLE);
                    String Email =email.getText().toString();
                    MyApplication.user
                            .setValues(firstName.getText().toString(),lastName.getText().toString(),getGender(),getAgeR(),Email);
                    mAuth.createUserWithEmailAndPassword(Email,"password").addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressBar.setVisibility(View.GONE);
                            if(task.isSuccessful()){
                                FirebaseFirestore.getInstance().collection("userdata").document(FirebaseAuth.getInstance().getCurrentUser().getUid()).set(MyApplication.user)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if(task.isSuccessful()){
                                                    Toast.makeText(getApplicationContext(),"User Registration Successful",Toast.LENGTH_SHORT).show();
                                                    onBackPressed();

                                                }
                                                else {
                                                    Toast.makeText(getApplicationContext(),"User Registration Unsuccessful",Toast.LENGTH_SHORT).show();

                                                }
                                            }
                                        });

                            }
                            else if(task.getException() instanceof FirebaseAuthUserCollisionException){
                                Toast.makeText(getApplicationContext(),"You are already registered",Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    Log.d("abcde",MyApplication.user.toString());
                    Log.d("abcde", "Message5");

                }


    }


    String getGender() {
        switch (gender.getCheckedRadioButtonId()) {
            case R.id.male:
                return "Male";
            case R.id.female:
                return "Female";
            case R.id.other:
                return "Other";
            default:
                return "";
        }

    }
    String getAgeR(){
        String[] AgeR ={"below 20", "21 to 30","31 to 40","41 to 50","51 to 60","above 60"};
        switch (age.getCheckedRadioButtonId()){
            case R.id.belowTwenty:
                return AgeR[0];
            case R.id.twentyToThirty:
                return AgeR[1];
            case R.id.thirtyToForty:
                return AgeR[2];
            case R.id.fortyToFifty:
                return AgeR[3];
            case R.id.fiftyToSixty:
                return AgeR[4];
            case R.id.aboveSixty:
                return AgeR[5];
            default:
                return "";
        }
    }

    boolean isEmail(EditText text) {
        CharSequence email = text.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    boolean checkDataEntered() {
        boolean flag =true;
        if (isEmpty(firstName)) {
            firstName.setError("First name is required!");
            flag=false;
        }

        if (isEmpty(lastName)) {
            lastName.setError("Last name is required!");
            flag=false;
        }

        if(gender.getCheckedRadioButtonId() == -1) {
            genderTextView.setError("Select gender!");
            flag=false;
        }

        if(age.getCheckedRadioButtonId() == -1) {
            ageTextView.setError("Select age!");
            flag=false;
        }

        if (isEmpty(email)) {
            email.setError("Enter a valid email!");
            flag=false;
        }

        if(!isEmail(email))  {
        email.setError("Invalid Email");
        }

        return flag;
    }
}
