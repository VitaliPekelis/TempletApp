package com.vitalipekelis.templet.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;


import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.vitalipekelis.templet.interfaces.INetworkProvider;
import com.vitalipekelis.templet.services.controllers.AController;
import com.vitalipekelis.templet.utils.MyLogger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by vitali.pekelis on 06/09/2015.
 */
public class MyService extends Service implements INetworkProvider {

    private static final int sf_MAX_ACTIVE_THREADS_IN_THREAD_POOL = 3;
    protected static final String TAG = MyService.class.getSimpleName();

    private RequestQueue mRequestQueue;
    private IBinder mBinder;
    private ArrayMap<String, AController> mControllers;
    private ExecutorService mThreadExecutor;

    @Override
    public void onCreate()
    {
        super.onCreate();
        mBinder = new MyServiceBinder();
        mRequestQueue = Volley.newRequestQueue(MyService.this);
        mControllers = new ArrayMap<>();
        mThreadExecutor = Executors.newFixedThreadPool(sf_MAX_ACTIVE_THREADS_IN_THREAD_POOL);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {

        if (intent == null)
        {
            MyLogger.print(TAG, "system  re-created the service!!!!");
        }
        else
        {
            MyLogger.print(TAG, "Received start id " + startId + ": " + intent.getAction());
        }

        // if the process is killed with no remaining start commands to deliver,
        // then the service will be  restarted with a null intent object.
        return START_STICKY;
    }


    /**
     * Retrieves the wanted controller by the controller type from the controllers hashMap.<BR>
     * If this is the first time retrieving the controller it will be created and added to the controllers hashMap.<BR>
     * @param iControllerClass - The class of the controller to retrive. Must implement IController.
     * @return The instance of the wanted controller.
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public <T extends AController> T getController(Class<T> iControllerClass) throws IllegalAccessException, InstantiationException
    {
        T result = (T) mControllers.get(iControllerClass.getSimpleName());

        if (result == null)
        {
            result = iControllerClass.newInstance();
            result.setVolleyRequestQueue(mRequestQueue);
            mControllers.put(iControllerClass.getSimpleName(), result);
        }

        return result;
    }

    public void addRunnableToThreadExecuter(Runnable iRunnable)
    {
        try
        {
            mThreadExecutor.execute(iRunnable);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    /**
     * Provides Binder to current service.
     */
    public class MyServiceBinder extends Binder
    {

        /**
         * @return binded service.
         */
        public MyService getService()
        {
            return MyService.this;
        }
    }


    //----------------------------------------------------------------
    //  INetworkProvider - implementations
    //----------------------------------------------------------------
    @Override
    public boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(
                Context.CONNECTIVITY_SERVICE);

        if(isWifiNetworkAvailable() || isMobileNetworkAvailable()){
            return true;
        }


        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnected()) {
            return true;
        }

        return false;
    }

    @Override
    public boolean isMobileNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(
                Context.CONNECTIVITY_SERVICE);

        NetworkInfo mobileNetwork = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (mobileNetwork != null && mobileNetwork.isConnected()) {
            return true;
        }

        return false;
    }

    @Override
    public boolean isWifiNetworkAvailable()
    {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo wifiNetwork = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (wifiNetwork != null && wifiNetwork.isConnected())
        {
            return true;
        }

        return false;
    }


}
