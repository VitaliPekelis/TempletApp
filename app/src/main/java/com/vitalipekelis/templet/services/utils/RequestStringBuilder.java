package com.vitalipekelis.templet.services.utils;

/**
 * Created by vitali.pekelis on 07/09/2015.
 */
public class RequestStringBuilder {

    private static final String SERVICE_PATH = "http://YOU_URL";
    private static final String MOBILE_HANDLER = "mobilehandler.ashx";

    public static String getTestUrl() {
        return "";
    }

    /*Which WS To Run*/
    public enum ActionType{
        DomesticFlight ("SearchDomesticFlight"),
        InternationalFlight("SearchInternationalFlight");


        private final String action;

        ActionType(String action) {
            this.action = action;
        }

        public boolean equalsActionType(String otherAction){
            return (otherAction != null) && action.equals(otherAction);
        }

        public String getActionType(){
            return action;
        }
    }


    public static String getData() {
        return SERVICE_PATH + MOBILE_HANDLER;
    }
}
