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
        if(options.getCheckedRadioButtonId()!=-1)
        user.AddResponse(Qid,Options[options.getCheckedRadioButtonId()- R.id.radioButton1]);
        Intent intent=new Intent(getApplicationContext(),Submit.class);
        startActivity(intent);
    }
    public void previous(View view){
        onBackPressed();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question5);

        options=findViewById(R.id.Options);
        user =MyApplication.user;
    }
}
