package com.yamangarg.heatstressmanagement;

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

        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        gender = findViewById(R.id.gender);
        age = findViewById(R.id.age);
        email = findViewById(R.id.email);
        register = findViewById(R.id.register);
        genderTextView = findViewById(R.id.genderTextView);
        ageTextView = findViewById(R.id.ageTextView);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkDataEntered();

                startActivity(new Intent(Registration.this, Location.class));

            }
        });
    }

    boolean isEmail(EditText text) {
        CharSequence email = text.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    void checkDataEntered() {
        if (isEmpty(firstName)) {
            firstName.setError("First name is required!");
        }

        if (isEmpty(lastName)) {
            lastName.setError("Last name is required!");
        }

        if(gender.getCheckedRadioButtonId() == -1) {
            genderTextView.setError("Select gender!");
        }

        if(age.getCheckedRadioButtonId() == -1) {
            ageTextView.setError("Select age!");
        }

        if (isEmpty(email) == false) {
            email.setError("Enter a valid email!");
        }
        if ()
    }
}
