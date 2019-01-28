package com.yamangarg.heatstressmanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

public class Question5 extends AppCompatActivity {


    RadioGroup options;
    User user;
    String Qid ="Question5";
    String Options[]={"FullCovered","Normal","Minimal"};

    public void next(View view){
        user.AddResponse(Qid,Options[options.getCheckedRadioButtonId()- R.id.radioButton1]);
        Intent intent=new Intent(getApplicationContext(),Submit.class);
        startActivity(intent);
    }
    public void previous(View view){
        user.AddResponse(Qid,Options[options.getCheckedRadioButtonId()- R.id.radioButton1]);
        Intent intent=new Intent(getApplicationContext(),Question4.class);
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question5);

        options=findViewById(R.id.Options);
        user =MyApplication.user;
    }
}
