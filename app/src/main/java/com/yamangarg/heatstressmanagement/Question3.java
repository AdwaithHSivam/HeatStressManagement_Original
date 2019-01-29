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
        if(options.getCheckedRadioButtonId()!=-1)
        user.AddResponse(Qid,Options[options.getCheckedRadioButtonId()- R.id.radioButton1]);
        Intent intent=new Intent(getApplicationContext(),Question4.class);
        startActivity(intent);
    }
    public void previous(View view){
        onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question3);

        options=findViewById(R.id.Options);
        user = MyApplication.user;
    }
}
