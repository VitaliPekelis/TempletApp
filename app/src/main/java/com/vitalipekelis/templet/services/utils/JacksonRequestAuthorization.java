package com.vitalipekelis.templet.services.utils;

import android.util.Base64;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.error.AuthFailureError;
import com.android.volley.error.ParseError;
import com.android.volley.request.JsonRequest;
import com.android.volley.toolbox.HttpHeaderParser;

import java.util.HashMap;
import java.util.Map;

public class JacksonRequestAuthorization<T> extends JsonRequest<T> {
	private Class<T>    responseType;
	private String mUserName;
	private String mPassword;
	
	
	
	public void setmUserName(String mUserName) 
	{
		this.mUserName = mUserName;
	}



	public void setmPassword(String mPassword) 
	{
		this.mPassword = mPassword;
	}



	/**
     * Creates a new request.
     * 
     * @param method
     *            the HTTP method to use
     * @param url
     *            URL to fetch the JSON from
     * @param requestData
     *            A {@link Object} to post and convert into json as the request. Null is allowed and indicates no parameters will be posted along with request.
     * @param listener
     *            Listener to receive the JSON response
     * @param errorListener
     *            Error listener, or null to ignore errors.
     */
    public JacksonRequestAuthorization(int method, String url, Object requestData, Class<T> responseType, Listener<T> listener, ErrorListener errorListener)
    {
    	
        super(method, url, (requestData == null) ? null : Mapper.string(requestData), listener, errorListener);
        this.responseType = responseType;
    }



	@Override
	protected Response<T> parseNetworkResponse(NetworkResponse response) {
		 try
	        {
	            String jsonString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
	            T jsonObject = Mapper.objectOrThrow(jsonString, responseType);
	            return Response.success(jsonObject, HttpHeaderParser.parseCacheHeaders(response));
	        }
	        catch (Exception e)
	        {
	            return Response.error(new ParseError(e));
	        }
	}
	
	@Override
	public Map<String, String> getHeaders() throws AuthFailureError
	{
		Map<String, String> headers = new HashMap<String, String>();
		String auth = "Basic "
				+ Base64.encodeToString((mUserName 
				+ ":" + mPassword).getBytes(),
						Base64.NO_WRAP);
		headers.put("Authorization", auth);
		return headers;
	}

}
