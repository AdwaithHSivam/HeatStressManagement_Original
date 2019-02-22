package com.yamangarg.heatstressmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

public class QuestionsActivity extends Activity {

    RadioGroup optionsA,optionsB,optionsC,optionsD;
    User user;
    final String Qid[] = {"QuestionA","QuestionB","QuestionC","QuestionD"};
    final String OptionsA[] = {"Comfortable", "Hot", "Very Hot", "Sweltering","Raining"};
    final String OptionsB[]={"NoActivity","Light","Moderate","Heavy"};
    final String OptionsC[]={"DirectSun","Shading","Indoors"};
    final String OptionsD[]={"FullCovered","Normal","Minimal"};

    int getOptionA(){
        switch (optionsA.getCheckedRadioButtonId()){
            case R.id.radioButton1A: return 0;
            case R.id.radioButton2A: return 1;
            case R.id.radioButton3A: return 2;
            case R.id.radioButton4A: return 3;
            case R.id.radioButton5A: return 4;
        }
        return 0;
    }
    int getOptionB(){
        switch (optionsB.getCheckedRadioButtonId()){
            case R.id.radioButton1B: return 0;
            case R.id.radioButton2B: return 1;
            case R.id.radioButton3B: return 2;
            case R.id.radioButton4B: return 3;
        }
        return 0;
    }
    int getOptionC(){
        switch (optionsC.getCheckedRadioButtonId()){
            case R.id.radioButton1C: return 0;
            case R.id.radioButton2C: return 1;
            case R.id.radioButton3C: return 2;
        }
        return 0;
    }
    int getOptionD(){
        switch (optionsD.getCheckedRadioButtonId()){
            case R.id.radioButton1D: return 0;
            case R.id.radioButton2D: return 1;
            case R.id.radioButton3D: return 2;
        }
        return 0;
    }

    public void next(View view) {
        if (optionsA.getCheckedRadioButtonId() != -1)
            user.AddResponse(Qid[0], OptionsA[getOptionA()]);
        if (optionsB.getCheckedRadioButtonId() != -1)
            user.AddResponse(Qid[1], OptionsB[getOptionB()]);
        if (optionsA.getCheckedRadioButtonId() != -1)
            user.AddResponse(Qid[2], OptionsC[getOptionC()]);
        if (optionsA.getCheckedRadioButtonId() != -1)
            user.AddResponse(Qid[3], OptionsD[getOptionD()]);
        Log.d("abcde", user.toString());
        Intent intent = new Intent(getApplicationContext(), Submit.class);
        startActivity(intent);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        optionsA = findViewById(R.id.OptionsA);
        optionsB = findViewById(R.id.OptionsB);
        optionsC = findViewById(R.id.OptionsC);
        optionsD = findViewById(R.id.OptionsD);

        user = MyApplication.user;

    }

}
