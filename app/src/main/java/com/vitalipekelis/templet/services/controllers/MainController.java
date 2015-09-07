package com.vitalipekelis.templet.services.controllers;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;

/**
 * Created by vitali.pekelis on 06/09/2015.
 */
public class MainController extends AController {
    private static final int CONNECTION_TIME_OUT = 3000;

    //For Example
/*    public void getCurrentAppVersion(Response.Listener<ResponseGetCurrentAppVersion> iListener, Response.ErrorListener iErrorListener)
    {
        if (!isRequestQueueNull() && iListener != null && iErrorListener != null)
        {
            JacksonRequest<ResponseGetCurrentAppVersion> request = new JacksonRequest<>(Request.Method.POST, RequestStringBuilder.getRequestCurrentAppVersion(), new RequestGetCurrentAppVersion(), ResponseGetCurrentAppVersion.class, iListener, iErrorListener);
            request.setRetryPolicy(new DefaultRetryPolicy(CONNECTION_TIME_OUT, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            getRequestQueue().add(request);
        }
    }*/
}
