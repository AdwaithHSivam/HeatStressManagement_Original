package com.yamangarg.heatstressmanagement;

import android.support.annotation.Keep;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
@Keep
public class User {
     public String  FirstName,
            LastName,
            Gender,
            Age,
            Email;
    public Map<String,String> Responses;
    public android.location.Location location;
    public User (){
        FirstName="";
        LastName="";
        Gender="";
        Age="";
        Email="";
        Responses= new HashMap<String,String>();

    }

    public User(String firstName, String lastName, String gender, String age, String email) {
        FirstName = firstName;
        LastName = lastName;
        Gender = gender;
        Age = age;
        Email = email;
        Responses= new HashMap<String,String>();
    }
    public void setValues(String firstName, String lastName, String gender, String age, String email){
        FirstName = firstName;
        LastName = lastName;
        Gender = gender;
        Age = age;
        Email = email;
    }
    public void AddResponse(String Q,String A){
        Responses.put(Q, A);

    }

}
