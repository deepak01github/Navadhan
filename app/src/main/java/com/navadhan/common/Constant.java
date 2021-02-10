package com.navadhan.common;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.ParseError;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Constant {


    private static final String IP = "192.168.0.11";

    //private static final String IP = "103.219.41.46";
    private static final String HOST = "http://" + IP + "/API/android";
    public static final String SEARCH_STRING = HOST + "/library/php/search_household.php";
    public static final String HEATMAP_DETAILS = HOST + "/customer-app/credit_bureau/credit_bureau_heatmap.php";
    public static final String ALERT_DETAILS = HOST + "/customer-app/credit_bureau/credit_bureau_alert.php";
    public static final String OBLIGATION_DETAILS = HOST + "/customer-app/credit_bureau/credit_bureau_obligation.php";
    public static final String SUMMARY_DETAILS = HOST + "/customer-app/credit_bureau/credit_bureau_summary.php";
    public static final String update_pan = HOST + "/customer-app/lead/updatePanNo.php";
    public static final String update_adhar=HOST + "/customer-app/lead/updateAdharNo.php";
    public static final String update_voter=HOST + "/customer-app/lead/updateVoterId.php";
    public static final String FAMILY_RELATIONS_LIST=HOST + "/library/php/fetch_family_relation_master.php";
    public static final String FAMILY_MEMBERS_LIST=HOST + "/customer-app/lead/fetchFamilyDetailFirstSet.php";



    public static String getFormatedAmount(String inputString){

        double amount = Double.parseDouble(inputString);
        DecimalFormat formatter = new DecimalFormat("#,###");
        return formatter.format(amount);
    }

    public static String getFormatedDate(String inputDate){
        Date date1;
        SimpleDateFormat dt1;
        String outDate="";
        try {
            date1=new SimpleDateFormat("yyyy-mm-dd").parse(inputDate);
             dt1 = new SimpleDateFormat("dd MMM yyyy");
             outDate = dt1.format(date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return outDate;

    }





    public static String VOLLEY_ERROR(VolleyError volleyError) {
        String returnError = "";
        if (volleyError instanceof TimeoutError) {
            returnError = "Time out Error";
        } else if (volleyError instanceof AuthFailureError) {
            returnError = "Auth Failure Error";
        } else if (volleyError instanceof ServerError) {
            returnError = "Server Error";
        } else if (volleyError instanceof NetworkError) {
            returnError = "Network Error";
        } else if (volleyError instanceof ParseError) {
            returnError = "Parser Error";
        }
        return returnError;
    }
}
