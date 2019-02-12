package com.yamangarg.heatstressmanagement;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

public class Question2 extends AppCompatActivity {

    EditText editTime;
    String Qid ="Question2";
    User user;

    public String toStringTime(int h,int m){
        return (h<10?"0":"")+h +":"+(m<10?"0":"")+m;
    }

    public void timePicker(View view){
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(Question2.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                editTime.setText(toStringTime(hourOfDay, minute));
            }
        },hour,0,true);
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();

    }
    public  void next(View view){
        user.AddResponse(Qid,editTime.getText().toString());
        Log.d("abcde",user.toString());
        Intent intent=new Intent(getApplicationContext(),Question3.class);
        startActivity(intent);
    }

    public  void previous(View view){
        onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question2);
        editTime=findViewById(R.id.editTime);
        user = MyApplication.user;
    }
}
