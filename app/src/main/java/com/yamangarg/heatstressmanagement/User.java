package com.yamangarg.heatstressmanagement;

import android.support.annotation.Keep;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
@Keep
public class User {

    public UserData userData;
    public ResponseClass responseObject;
    public boolean isOk;
    public User (){
        userData= new UserData();
        responseObject= new ResponseClass();
        isOk=false;

    }

    /* User(String firstName, String lastName, String gender, String age, String email) {
        FirstName = firstName;
        LastName = lastName;
        Gender = gender;
        Age = age;
        Email = email;
        Responses= new HashMap<String,String>();
    }*/
    public void setOk(boolean flag){
        isOk=flag;
    }
    public void setValues(String firstName, String lastName, String gender, String age, String email){
        userData.setValues(firstName, lastName, gender, age, email);
    }

    public void AddResponse(String Q,String A){
        responseObject.AddResponse(Q, A);

    }

}
