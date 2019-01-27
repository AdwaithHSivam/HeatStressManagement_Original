package com.yamangarg.heatstressmanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Question3 extends AppCompatActivity {

    Button radioButton1;
    Button radioButton2;
    Button radioButton3;
    Button radioButton4;
    Button button1;
    Button button2;

    public void option1(View view){

    }
    public void option2(View view){

    }
    public void option3(View view){

    }
    public void option4(View view){

    }
    public void next(View view){
        Intent intent=new Intent(getApplicationContext(),Question4.class);
        startActivity(intent);
    }
    public void previous(View view){
        Intent intent=new Intent(getApplicationContext(),Question2.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question3);
    }
}
