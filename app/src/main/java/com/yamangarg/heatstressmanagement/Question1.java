package com.yamangarg.heatstressmanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

public class Question1 extends AppCompatActivity {

    Button radioButton1;
    Button radioButton2;
    Button radioButton3;
    Button radioButton4;
    Button radioButton5;
    Button button1;
    Button button2;
    RadioGroup options;
    User user ;
    String Qid ="Question1";
    String Options[]={"Comfortable","Warm","Hot","Very Hot","Sweltering"};


    public void next(View view){
        user.AddResponse(Qid,Options[options.getCheckedRadioButtonId()- R.id.radioButton1]);
        Log.d("abcde",user.toString());
        Intent intent=new Intent(getApplicationContext(),Question2.class);
        startActivity(intent);
    }
    public void previous(View view){
        user.AddResponse(Qid,Options[options.getCheckedRadioButtonId()- R.id.radioButton1]);
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
        options=(RadioGroup) findViewById(R.id.Options);
        user = MyApplication.user;

    }
}
