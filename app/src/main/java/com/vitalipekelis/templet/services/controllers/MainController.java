package com.vitalipekelis.templet.services.controllers;


import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.vitalipekelis.templet.services.responses.QueryResult;
import com.vitalipekelis.templet.services.utils.JacksonRequest;

/**
 * Created by vitali.pekelis on 06/09/2015.
 */
public class MainController extends AController {
    private static final int CONNECTION_TIME_OUT = 3000;


    public void getRates(String uri, Response.Listener<QueryResult> listener,
                         Response.ErrorListener errorListener) { // for diplomacy and experience worlds
        JacksonRequest<QueryResult> request = new JacksonRequest<>(
                Request.Method.GET, uri, null, QueryResult.class, listener,
                errorListener);
        request.setRetryPolicy(new DefaultRetryPolicy(CONNECTION_TIME_OUT, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        getRequestQueue().add(request);
    }


}
