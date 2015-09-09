package com.vitalipekelis.templet.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.vitalipekelis.templet.services.MyService;
import com.vitalipekelis.templet.services.interfaces.IOnServiceConnectedListener;
import com.vitalipekelis.templet.ui.activities.MyBaseActivity;

/**
 * Created by vitali.pekelis on 06/09/2015.
 */
public class MyBaseFragment extends Fragment implements
        IOnServiceConnectedListener {

    private MyService mService = null;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getActivity() != null
                && getActivity() instanceof MyBaseActivity) {
            ((MyBaseActivity) getActivity())
                    .setISEOnServiceConnectedListener(this);
        }

    }

    @Override
    public void onServiceConnected(MyService service) {
        this.mService = service;
    }

    /**
     * @return the SWService
     */
    public MyService getService() {
        return mService;
    }



    protected boolean isAtachedToAcivity(){
        if(getActivity() != null){
            return true;
        }else{
            return false;
        }
    }
}
