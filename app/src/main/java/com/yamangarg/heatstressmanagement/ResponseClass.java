package com.yamangarg.heatstressmanagement;

import android.support.annotation.Keep;

import com.google.firebase.firestore.GeoPoint;

import java.util.HashMap;
import java.util.Map;

@Keep
public class ResponseClass {
    public Map<String, Object> Responses;
    public GeoPoint geoPoint;
    public String place;

    public ResponseClass(){
        Responses= new HashMap<>();
        place="";
    }

    public void AddResponse(String Q,String A){
        Responses.put(Q, A);

    }
}
