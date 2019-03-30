package com.yamangarg.heatstressmanagement;

import android.support.annotation.Keep;

import java.util.HashMap;
import java.util.Map;

@Keep
public class ResponseClass {
    public Map<String, Object> Responses;
    public android.location.Location location;

    public ResponseClass(){
        Responses= new HashMap<>();
    }

    public void AddResponse(String Q,String A){
        Responses.put(Q, A);

    }
}
