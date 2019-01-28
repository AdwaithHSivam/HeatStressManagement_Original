package com.yamangarg.heatstressmanagement;

import android.app.Application;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;

public class Registration extends AppCompatActivity {


    EditText firstName;
    EditText lastName;
    RadioGroup gender;
    RadioGroup age;
    EditText email;
    Button register;
    TextView genderTextView;
    TextView ageTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        Log.d("abcde","Message1");

        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        gender = findViewById(R.id.gender);
        age = findViewById(R.id.age);
        email = findViewById(R.id.email);
        register = findViewById(R.id.register);
        genderTextView = findViewById(R.id.genderTextView);
        ageTextView = findViewById(R.id.ageTextView);


        Log.d("abcde","Message2");
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("abcde","Message3"+ R.id.belowTwenty);
                Log.d("abcde","Message3"+age.getCheckedRadioButtonId());
                if(checkDataEntered()) {
                    Log.d("abcde","Message4");

                    ((MyApplication) Registration.this.getApplication()).user = new User(firstName.getText().toString(),lastName.getText().toString(),getGender(),getAgeR(),email.getText().toString());
                    Intent i =new Intent(Registration.this, Location.class);
                    startActivity(i);
                    Log.d("abcde", "Message5");
                    Registration.this.finish();
                    Log.d("abcde", "Message5");

                }

            }
        });
    }

    String getGender() {
        switch (gender.getCheckedRadioButtonId()) {
            case 0:
                return "Male";
            case 1:
                return "Female";
            case 2:
                return "Other";
        }
        return "";
    }
    String getAgeR(){
        String[] AgeR ={"below 20", "21 to 30","31 to 40","41 to 50","51 to 60","above 60"};
        return AgeR[age.getCheckedRadioButtonId()-R.id.belowTwenty];
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
        /*
        if(!isEmail(email))  {
        email.setError("Invalid Email");
        }
        */
        return flag;
    }
}
