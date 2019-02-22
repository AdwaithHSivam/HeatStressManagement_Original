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
    final String OptionsA[] = {"Comfortable", "Raining", "Hot", "Very Hot", "Sweltering"};
    final String OptionsB[]={"NoActivity","Light","Moderate","Heavy"};
    final String OptionsC[]={"DirectSun","Shading","Indoors"};
    final String OptionsD[]={"FullCovered","Normal","Minimal"};

    public void startProgressbar(){

    }

    public void next(View view) {
        if (optionsA.getCheckedRadioButtonId() != -1)
            user.AddResponse(Qid[0], OptionsA[optionsA.getCheckedRadioButtonId() - R.id.radioButton1A]);
        if (optionsB.getCheckedRadioButtonId() != -1)
            user.AddResponse(Qid[1], OptionsB[optionsB.getCheckedRadioButtonId() - R.id.radioButton1B]);
        if (optionsA.getCheckedRadioButtonId() != -1)
            user.AddResponse(Qid[2], OptionsC[optionsC.getCheckedRadioButtonId() - R.id.radioButton1C]);
        if (optionsA.getCheckedRadioButtonId() != -1)
            user.AddResponse(Qid[3], OptionsD[optionsD.getCheckedRadioButtonId() - R.id.radioButton1D]);
        Log.d("abcde", user.toString());
        Intent intent = new Intent(getApplicationContext(), Submit.class);
        startActivity(intent);
    }

    public void previous(View view) {
        onBackPressed();
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
