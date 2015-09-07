package com.vitalipekelis.templet.interfaces;

/**
 * Created by vitali.pekelis on 06/09/2015.
 */


/**
 * Must be implement by object that can access to Application Context and can provide
 * network state to any objects through INetworkProvider interface
 */
public interface INetworkProvider {

    /**
     *
     * @return true if device connect to any network
     */
    public boolean isNetworkAvailable();

    /**
     *
     * @return true if device connect to mobile network
     */
    public boolean isMobileNetworkAvailable();

    /**
     *
     * @return true if device connect to wifi network
     */
    public boolean isWifiNetworkAvailable();
}
