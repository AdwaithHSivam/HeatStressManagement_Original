package com.yamangarg.heatstressmanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class Submit extends AppCompatActivity {

    //FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);
        Log.d("abcde",((MyApplication) Submit.this.getApplication()).user.FirstName);
    }
    public void previous(View view){
        Intent intent=new Intent(getApplicationContext(),Question5.class);
        startActivity(intent);
    }
    public void submit(View view){

    }

}
