package com.yamangarg.heatstressmanagement;

import android.support.annotation.Keep;

import java.util.HashMap;
import java.util.Map;

@Keep
public class ResponseClass {
    public Map<String,String> Responses;
    public android.location.Location location;

    public ResponseClass(){
        Responses= new HashMap<String,String>();
    }

    public void AddResponse(String Q,String A){
        Responses.put(Q, A);

    }
}
