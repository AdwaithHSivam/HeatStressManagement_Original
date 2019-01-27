package com.yamangarg.heatstressmanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Question1 extends AppCompatActivity {

    Button radioButton1;
    Button radioButton2;
    Button radioButton3;
    Button radioButton4;
    Button radioButton5;
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
    public void option5(View view){

    }
    public void next(View view){
        Intent intent=new Intent(getApplicationContext(),Question2.class);
        startActivity(intent);
    }
    public void previous(View view){
        Intent intent=new Intent(getApplicationContext(),Location.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question1);

        radioButton1=(Button) findViewById(R.id.radioButton1);
        radioButton2=(Button) findViewById(R.id.radioButton2);
        radioButton3=(Button) findViewById(R.id.radioButton3);
        radioButton4=(Button) findViewById(R.id.radioButton4);
        radioButton5=(Button) findViewById(R.id.radioButton5);
        button1=(Button) findViewById(R.id.button1);
        button2=(Button) findViewById(R.id.button2);


    }
}
