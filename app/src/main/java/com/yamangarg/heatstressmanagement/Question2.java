package com.yamangarg.heatstressmanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Question2 extends AppCompatActivity {

    EditText editText;
    Button button1;
    Button button2;
    String Qid ="Question2";
    User user;

    public  void next(View view){
        user.AddResponse(Qid,editText.getText().toString());
        Log.d("abcde",user.toString());
        Intent intent=new Intent(getApplicationContext(),Question3.class);
        startActivity(intent);
    }

    public  void previous(View view){
        user.AddResponse(Qid,editText.getText().toString());
        Intent intent=new Intent(getApplicationContext(),Question1.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question2);
        editText=findViewById(R.id.editText);
        user = MyApplication.user;
    }
}
