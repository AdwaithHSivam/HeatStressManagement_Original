package com.yamangarg.heatstressmanagement;

import android.support.annotation.Keep;

@Keep
public class UserData {

    public String  Uid,
            FirstName,
            LastName,
            Gender,
            Age,
            Email;

    public UserData(){
        Uid ="";
        FirstName="";
        LastName="";
        Gender="";
        Age="";
        Email="";
    }

    public void setValues(String firstName, String lastName, String gender, String age, String email){
        FirstName = firstName;
        LastName = lastName;
        Gender = gender;
        Age = age;
        Email = email;
    }
}
