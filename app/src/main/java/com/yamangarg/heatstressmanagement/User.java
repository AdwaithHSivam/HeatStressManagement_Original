package com.yamangarg.heatstressmanagement;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class User implements Serializable {
    String  FirstName,
            LastName,
            Gender,
            Age,
            Email;
    Map<String,String> Responses;
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
    public void AddResponse(String Q,String A){
        Responses.put(Q, A);

    }
}
