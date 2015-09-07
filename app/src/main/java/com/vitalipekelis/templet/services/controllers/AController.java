package com.vitalipekelis.templet.services.controllers;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.request.StringRequest;

/**
 * Created by vitali.pekelis on 06/09/2015.
 */
public class AController {
        private static final int CONNECTION_TIME_OUT = 1500;
        private static final String GOOGLE_URL = "http://www.google.com";

        private RequestQueue mRequestQueue;

        /**
         * Sets the requestQueue for the controller provided from the service.
         * @param iRequestQueue
         */
    public void setVolleyRequestQueue(RequestQueue iRequestQueue)
    {
        mRequestQueue = iRequestQueue;
    }

    public boolean isRequestQueueNull()
    {
        return mRequestQueue == null;
    }

    public RequestQueue getRequestQueue()
    {
        return mRequestQueue;
    }

    public void performInternetConnectivityCheck(Response.Listener<String> iListener, Response.ErrorListener iErrorListener)
    {
        if (!isRequestQueueNull())
        {
            StringRequest request = new StringRequest(Request.Method.GET, GOOGLE_URL, iListener, iErrorListener);
            request.setRetryPolicy(new DefaultRetryPolicy(CONNECTION_TIME_OUT, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            getRequestQueue().add(request);
        }
    }
}
