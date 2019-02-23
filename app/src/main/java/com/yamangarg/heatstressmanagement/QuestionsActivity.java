package com.yamangarg.heatstressmanagement;


import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormat;
import java.util.Date;

public class QuestionsActivity extends Activity {

    RadioGroup optionsA,optionsB,optionsC,optionsD;
    ProgressBar progressBar;
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

    public void submit(View view) {

        progressBar.setVisibility(View.VISIBLE);
        if (optionsA.getCheckedRadioButtonId() != -1)
            user.AddResponse(Qid[0], OptionsA[getOptionA()]);
        if (optionsB.getCheckedRadioButtonId() != -1)
            user.AddResponse(Qid[1], OptionsB[getOptionB()]);
        if (optionsA.getCheckedRadioButtonId() != -1)
            user.AddResponse(Qid[2], OptionsC[getOptionC()]);
        if (optionsA.getCheckedRadioButtonId() != -1)
            user.AddResponse(Qid[3], OptionsD[getOptionD()]);
        Log.d("abcde", user.toString());


        FirebaseFirestore.getInstance().collection("responses")
                .document(FirebaseAuth.getInstance().getCurrentUser().getUid()+" -" + DateFormat.getDateInstance().format(new Date()))
                .set(user.responseObject)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"Response Submission Successful",Toast.LENGTH_SHORT).show();

                            onBackPressed();

                        }
                        else {
                            Toast.makeText(getApplicationContext(),"Response Submission Unsuccessful",Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });



    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        optionsA = findViewById(R.id.OptionsA);
        optionsB = findViewById(R.id.OptionsB);
        optionsC = findViewById(R.id.OptionsC);
        optionsD = findViewById(R.id.OptionsD);
        progressBar = findViewById(R.id.progressBarQ);
        user = MyApplication.user;
        progressBar.setVisibility(View.GONE);
        TextView link = findViewById(R.id.hyperlink);
        /*String linkText = "<a href='https://drive.google.com/open?id=1GtH-ALYlWV_9ppiavzlBi-kY5DfNBgsx'>More Info</a>";

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Log.d("abcde", "onCreate: "+Html.fromHtml(linkText,Html.FROM_HTML_MODE_LEGACY));
            link.setText(Html.fromHtml(linkText,Html.FROM_HTML_MODE_LEGACY));
        }
        else {
            Log.d("abcde", "onCreate: "+Html.fromHtml(linkText));
            link.setText(Html.fromHtml(linkText));
        }
        Log.d("abcde", "onCreate: "+link.getText().toString());*/
        link.setMovementMethod(LinkMovementMethod.getInstance());
    }

}
