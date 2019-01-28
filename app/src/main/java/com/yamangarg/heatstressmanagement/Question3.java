package com.yamangarg.heatstressmanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

public class Question3 extends AppCompatActivity {

    RadioGroup options;
    User user;
    String Qid ="Question3";

    String Options[]={"NoActivity","Light","Moderate","Heavy"};

    public void next(View view){
        user.AddResponse(Qid,Options[options.getCheckedRadioButtonId()- R.id.radioButton1]);
        Intent intent=new Intent(getApplicationContext(),Question4.class);
        startActivity(intent);
    }
    public void previous(View view){
        user.AddResponse(Qid,Options[options.getCheckedRadioButtonId()- R.id.radioButton1]);
        Intent intent=new Intent(getApplicationContext(),Question2.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question3);

        options=findViewById(R.id.Options);
        user = MyApplication.user;
    }
}
