package com.yamangarg.heatstressmanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Question5 extends AppCompatActivity {

    Button radioButton1;
    Button radioButton2;
    Button radioButton3;
    Button button1;
    Button button2;

    public void option1(View view){

    }
    public void option2(View view){

    }
    public void option3(View view){

    }
    public void previous(View view){
        Intent intent=new Intent(getApplicationContext(),Question4.class);
        startActivity(intent);
    }
    public void submit(View view){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question5);

        radioButton1=(Button) findViewById(R.id.radioButton1);
        radioButton2=(Button) findViewById(R.id.radioButton2);
        radioButton3=(Button) findViewById(R.id.radioButton3);
        button1=(Button) findViewById(R.id.button1);
        button2=(Button) findViewById(R.id.button2);
    }
}
