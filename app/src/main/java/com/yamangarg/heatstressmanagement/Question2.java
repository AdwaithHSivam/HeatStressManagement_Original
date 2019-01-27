package com.yamangarg.heatstressmanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Question2 extends AppCompatActivity {

    EditText editText;
    Button button1;
    Button button2;

    public  void next(View view){
        Intent intent=new Intent(getApplicationContext(),Question3.class);
        startActivity(intent);
    }

    public  void previous(View view){
        Intent intent=new Intent(getApplicationContext(),Question1.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question2);

        editText=(EditText) findViewById(R.id.editText);
        button1=(Button) findViewById(R.id.button1);
        button2=(Button) findViewById(R.id.button2);

    }
}
