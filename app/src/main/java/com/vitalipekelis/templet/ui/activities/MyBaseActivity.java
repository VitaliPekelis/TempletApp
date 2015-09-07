package com.vitalipekelis.templet.ui.activities;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.vitalipekelis.templet.services.MyService;
import com.vitalipekelis.templet.services.interfaces.IOnServiceConnectedListener;
import com.vitalipekelis.templet.utils.FragmentSwapper;
import com.vitalipekelis.templet.utils.MyLogger;

/**
 * Created by vitali.pekelis on 06/09/2015.
 */
public abstract class MyBaseActivity extends AppCompatActivity {

    protected MyService mService = null;
    protected ServiceConnection mServiceConnection = null;
    protected boolean mServiceConnected = false;
    protected FragmentSwapper mFragmentSwapper;
    private IOnServiceConnectedListener mIOnServiceConnectedListener = null;


    /**
     * Save bundle from onCreate
     */
    protected Bundle mSavedInstanceState = null;

    /**
     * calls in onCreate after execution of service binding
     */
    protected abstract void initScreen();

    /**
     * Notify an Activity when EWTService bounded and
     */
    protected abstract void onServiceConnected();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSavedInstanceState = savedInstanceState;
        initServiceConnection();
        initScreen();

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    @Override
    protected void onStart()
    {

        if (mServiceConnection != null)
        {
            Intent intent;
            intent = new Intent(this, MyService.class);

            mServiceConnected = bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
        }

        super.onStart();
    }

    @Override
    protected void onStop()
    {

        if (mServiceConnected && mServiceConnection != null)
        {

            unbindService(mServiceConnection);
            mServiceConnected = false;
            mServiceConnection = null;
        }

        super.onStop();

    }


    /**
     * Service connection initialization.
     */
    protected void initServiceConnection()
    {

        mServiceConnection = new ServiceConnection()
        {


            @Override
            public void onServiceDisconnected(ComponentName name)
            {

                mServiceConnected = false;
                mService = null;
            }

            @Override
            public void onServiceConnected(ComponentName cn, IBinder binder)
            {

                try
                {
                    mService = ((MyService.MyServiceBinder) binder).getService();

                    MyBaseActivity.this.onServiceConnected();

                    if (mIOnServiceConnectedListener != null) {
                        mIOnServiceConnectedListener
                                .onServiceConnected(mService);
                    }
                }
                catch (Exception e)
                {
                    MyLogger.printError("initServiceConnection", e.getMessage());
                }
            }
        };
    }

    /**
     * @return the mService
     */
    public MyService getService()
    {
        return mService;
    }

    /**
     * @return the mServiceConnected
     */
    public boolean isServiceConnected()
    {
        return mServiceConnected;
    }


    /**
     * @return the mISWOnServiceConnectedListener
     */
    public IOnServiceConnectedListener getIOnServiceConnectedListener() {
        return mIOnServiceConnectedListener;
    }

    /**
     * @param listener
     *            the ISWOnServiceConnectedListener to set
     */
    public void setISEOnServiceConnectedListener(
            IOnServiceConnectedListener listener) {

        if (mServiceConnected && mService != null && listener != null) {
            listener.onServiceConnected(mService);

        }

        this.mIOnServiceConnectedListener = listener;
    }
}

