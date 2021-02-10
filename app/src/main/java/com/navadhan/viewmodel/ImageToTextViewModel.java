package com.navadhan.viewmodel;

import android.app.Application;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;
import com.navadhan.DocumentOCR;
import com.navadhan.common.Constant;
import com.navadhan.model.HouseholdData;
import com.navadhan.model.ResponseModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ImageToTextViewModel extends AndroidViewModel {

    public String extractedText="";
    public Application application;
    private RequestQueue mRequestQueue;
    public MutableLiveData<ResponseModel> responseUpdateDetails;
    public ImageToTextViewModel(@NonNull Application application) {
        super(application);
        this.application=application;

    }
    public MutableLiveData<ResponseModel> getResponseupdateDetails() {
        if (responseUpdateDetails == null)
            responseUpdateDetails = new MutableLiveData<>();
        return responseUpdateDetails;
    }

    public void shiftToNextKyc(HouseholdData householdData){
        Intent i = new Intent(application.getApplicationContext(), DocumentOCR.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.putExtra("household_data",householdData);
        application.getApplicationContext().startActivity(i);
    }

    public String extractVoterId(Bitmap bitmap) {
        StringBuilder stringBuilder = new StringBuilder();
        TextRecognizer textRecognizer = new TextRecognizer.Builder(application.getApplicationContext()).build();
        if (!textRecognizer.isOperational()) {
            Toast.makeText(application.getApplicationContext(), "Detector is not available yet", Toast.LENGTH_LONG);
        }
        Frame frame = new Frame.Builder().setBitmap(bitmap).build();
        final SparseArray<TextBlock> items = textRecognizer.detect(frame);
        if (items.size() != 0) {


            boolean flag_elect_name = false;
            boolean is_voter = false;
            for (int i = 0; i < items.size(); i++) {
                TextBlock item = items.get(i);
                if (String.valueOf(item.getValue()).toLowerCase().contains("Election commission Of India".toLowerCase())) {
                    is_voter = true;
                }
                if (is_voter) {
                    if (flag_elect_name) {
                        flag_elect_name = false;
                        stringBuilder.append(item.getValue());
                        stringBuilder.append("\n");
                    }
                    Pattern textPattern = Pattern.compile("^(?=.*[A-Z])(?=.*\\d).+$");
                    if (textPattern.matcher(String.valueOf(item.getValue())).matches()) {
                        if (String.valueOf(item.getValue()).length() == 10) {
                            extractedText = item.getValue();
                            stringBuilder.append("Voter Id: " + String.valueOf(item.getValue()));
                            stringBuilder.append("\n");
                        }
                    }
                    if (String.valueOf(item.getValue()).toLowerCase().contains("Elector's".toLowerCase())) {
                        if (item.getValue().length() == 14) {
                            int index = String.valueOf(item.getValue()).indexOf("Elector's");
                            int lastIndex = String.valueOf(item.getValue()).length();
                            String str1 = String.valueOf(item.getValue()).substring(index);
                            stringBuilder.append(str1 + " : ");
                            flag_elect_name = true;

                        } else {
                            int index = String.valueOf(item.getValue()).indexOf("Elector's");
                            int lastIndex = String.valueOf(item.getValue()).length();
                            String str1 = String.valueOf(item.getValue()).substring(index);
                            stringBuilder.append(str1);
                            stringBuilder.append("\n");
                        }


                    }
                    if (String.valueOf(item.getValue()).toLowerCase().contains("Date of Birth".toLowerCase())) {
                        int index = String.valueOf(item.getValue()).indexOf("Date of Birth");
                        int lastIndex = String.valueOf(item.getValue()).length();
                        String str1 = String.valueOf(item.getValue()).substring(index);
                        stringBuilder.append(str1);
                        stringBuilder.append("\n");

                    } else if (String.valueOf(item.getValue()).toLowerCase().contains("Age".toLowerCase())) {
                        int index = String.valueOf(item.getValue()).indexOf("Age");
                        int lastIndex = String.valueOf(item.getValue()).length();
                        String str1 = String.valueOf(item.getValue()).substring(index);
                        stringBuilder.append(str1);
                        stringBuilder.append("\n");

                    }
                        /*stringBuilder.append(item.getValue());
                        stringBuilder.append("\n");*/

                }
                //extractedText = stringBuilder.toString();
            }
            if(!is_voter){
                extractedText = "It is not a Voter Id";

            }

        }
        return extractedText;
    }



    public String extractAdharCardBack(Bitmap bitmap){
        TextRecognizer textRecognizer = new TextRecognizer.Builder(application.getApplicationContext()).build();
        if (!textRecognizer.isOperational()) {
            Toast.makeText(application.getApplicationContext(), "Detector is not available yet", Toast.LENGTH_LONG);
        }
        Frame frame = new Frame.Builder().setBitmap(bitmap).build();
        final SparseArray<TextBlock> items = textRecognizer.detect(frame);
        if (items.size() != 0) {
                    StringBuilder stringBuilder = new StringBuilder();
                    int count=0;
                    boolean flag_address=false;
                    for (int i = 0; i < items.size(); i++) {
                        TextBlock item = items.valueAt(i);


                        if(count==2){
                            flag_address=false;
                        }

                        if(onlyDigits(String.valueOf(item.getValue()))&&String.valueOf(item.getValue()).length()==14){
                            flag_address=false;
                        }
                        if(flag_address){
                            stringBuilder.append(item.getValue());
                            stringBuilder.append("\n");
                        }
                        String str = extractInt(String.valueOf(item.getValue()));
                        if(str.length()==6){
                            count++;
                        }
                        if(String.valueOf(item.getValue()).toLowerCase().contains("Address".toLowerCase())){
                            int index = String.valueOf(item.getValue()).indexOf("Address");
                            int lastIndex= String.valueOf(item.getValue()).length();
                            String str1 = String.valueOf(item.getValue()).substring(index);
                            stringBuilder.append(str1);
                            stringBuilder.append("\n");
                            flag_address=true;
                        }






                    }
                    extractedText = stringBuilder.toString();


        }
        return extractedText;
    }

    public String extractAdharCardFront(Bitmap bitmap) {
        TextRecognizer textRecognizer = new TextRecognizer.Builder(application.getApplicationContext()).build();
        if (!textRecognizer.isOperational()) {
            Toast.makeText(application.getApplicationContext(), "Detector is not available yet", Toast.LENGTH_LONG);
        }
        Frame frame = new Frame.Builder().setBitmap(bitmap).build();
        final SparseArray<TextBlock> items = textRecognizer.detect(frame);
        if (items.size() != 0) {
                    StringBuilder stringBuilder = new StringBuilder();
                    StringBuilder stringBuilder1 = new StringBuilder();
                    boolean flag_name = false;
                    boolean is_aadhar = false;
                    for (int i = 0; i < items.size(); i++) {
                        TextBlock item = items.valueAt(i);
                        if (String.valueOf(item.getValue()).toLowerCase().contains("Government of India".toLowerCase())) {
                            is_aadhar = true;
                        }
                        if (is_aadhar) {
                            if (flag_name == true) {
                                stringBuilder1.append("NAME: " + item.getValue());
                                stringBuilder1.append("\n");
                            }
                            if (String.valueOf(item.getValue()).toLowerCase().contains("INDIA".toLowerCase())) {
                                flag_name = true;
                            } else {
                                flag_name = false;
                            }
                            if (String.valueOf(item.getValue()).contains("Birth")) {
                                Pattern p = Pattern.compile("[0-9]+");
                                Matcher m = p.matcher(String.valueOf(item.getValue()));
                                while (m.find()) {
                                    if (m.group().length() == 4) {
                                        stringBuilder1.append("Date Of Birth: " + m.group());
                                        stringBuilder1.append("\n");
                                    }
                                }


                            } else if (String.valueOf(item.getValue()).toLowerCase().contains("DOB".toLowerCase())) {
                                String[] dob = getDate(item.getValue());
                                if (dob.length > 0) {
                                    stringBuilder1.append("Date Of Birth: " + dob[0]);
                                    stringBuilder1.append("\n");
                                }
                            }
                            if (onlyDigits(String.valueOf(item.getValue()))) {
                                extractedText = item.getValue();
                                stringBuilder1.append("Aadhar No: " + item.getValue());
                                stringBuilder1.append("\n");
                            }
                            stringBuilder.append(item.getValue());
                            stringBuilder.append("\n");

                        }
                    }
            if(!is_aadhar){
                extractedText = "It is not a AAdhar Id";

            }


        }
        return extractedText.replaceAll("\\s", "");
    }

    public String extractPanCrad(Bitmap bitmap) {

        TextRecognizer textRecognizer = new TextRecognizer.Builder(application.getApplicationContext()).build();
        if (!textRecognizer.isOperational()) {
            Toast.makeText(application.getApplicationContext(), "Detector is not available yet", Toast.LENGTH_LONG);
        }
        Frame frame = new Frame.Builder().setBitmap(bitmap).build();
        final SparseArray<TextBlock> items = textRecognizer.detect(frame);
        if (items.size() != 0) {

                    StringBuilder stringBuilder = new StringBuilder();
                    StringBuilder stringBuilder1 = new StringBuilder();
                    boolean flag_name = false;
                    boolean flag_pan_number = false;
                    boolean is_pan = false;
            for (int i = 0; i < items.size(); i++) {
                TextBlock item = items.valueAt(i);
                if (String.valueOf(item.getValue()).toLowerCase().contains("Income Tax Department".toLowerCase())) {
                    is_pan = true;
                }
                if (is_pan) {
                    if (flag_name == true) {
                        stringBuilder1.append("NAME: " + item.getValue());
                        stringBuilder1.append("\n");
                    }
                    if (flag_pan_number == true) {
                        stringBuilder1.append("PAN NUMBER: " + item.getValue());
                        extractedText = item.getValue();
                        stringBuilder1.append("\n");
                    }

                    if (String.valueOf(item.getValue()).toLowerCase().contains("INDIA".toLowerCase())) {
                        flag_name = true;
                    } else {
                        flag_name = false;
                    }
                    if (String.valueOf(item.getValue()).toLowerCase().contains("Account".toLowerCase())) {
                        flag_pan_number = true;
                    } else {
                        flag_pan_number = false;
                    }
                    stringBuilder.append(item.getValue());

                    stringBuilder.append("\n");
                }
                String[] dob = getDate(stringBuilder.toString());
                stringBuilder1.append("DOB: " + dob[0]);
            }
            if(!is_pan){
                extractedText = "It is not a PAN card";

            }

        }
        return extractedText;
    }

    private static String[] getDate(String desc) {
        String allMatches[] = new String[1];
        int count=0;
        Matcher m = Pattern.compile("(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\d\\d").matcher(desc);
        while (m.find()) {
            allMatches[count] = m.group();
            count ++;
        }

        return allMatches;
    }

    public static boolean onlyDigits(String content){
        String regex = "[0-9 ]+";
        Pattern p = Pattern.compile(regex);
        if (content == null) {
            return false;
        }
        Matcher m = p.matcher(content);
        return m.matches();
    }

    static String extractInt(String str)
    {
        // Replacing every non-digit number
        // with a space(" ")
        str = str.replaceAll("[^\\d]", " ");

        // Remove extra spaces from the beginning
        // and the ending of the string
        str = str.trim();

        // Replace all the consecutive white
        // spaces with a single space
        str = str.replaceAll(" +", " ");

        if (str.equals(""))
            return "-1";

        return str;
    }

    public void updatePanIntoDatabase(String family_member_name, String mobile_no, String pan){
        mRequestQueue = Volley.newRequestQueue(getApplication().getApplicationContext());
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("family_member_name",family_member_name);
            jsonObject.put("mobile_no",mobile_no);
            jsonObject.put("pan",pan);
            ResponseModel responseModel = new ResponseModel();
            JsonObjectRequest jsonObjectRequest =new JsonObjectRequest(Request.Method.POST, Constant.update_pan,
                    jsonObject,response -> {
                JSONObject jsonObject1= null;
                try {
                    responseModel.setJsonObject(response);
                    responseModel.setVolleyError(null);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }, error -> {
                responseModel.setJsonObject(null);
                responseModel.setVolleyError(error);
                Toast.makeText(application, error.getMessage(), Toast.LENGTH_SHORT).show();
            });
            mRequestQueue.add(jsonObjectRequest);
            //AppClass.getInstance().addRequestQueue(jsonObjectRequest, TAG);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
    public void updateAdharIntoDatabase(String family_member_name, String mobile_no, String aadhaar_no){
        mRequestQueue = Volley.newRequestQueue(getApplication().getApplicationContext());
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("family_member_name",family_member_name);
            jsonObject.put("mobile_no",mobile_no);
            jsonObject.put("aadhaar_no",aadhaar_no.trim());
            ResponseModel responseModel = new ResponseModel();
            JsonObjectRequest jsonObjectRequest =new JsonObjectRequest(Request.Method.POST, Constant.update_adhar,
                    jsonObject,response -> {
                JSONObject jsonObject1= null;
                try {
                    responseModel.setJsonObject(response);
                    responseModel.setVolleyError(null);
                    responseUpdateDetails.setValue(responseModel);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }, error -> {
                responseModel.setJsonObject(null);
                responseModel.setVolleyError(error);
            });
            mRequestQueue.add(jsonObjectRequest);
            //AppClass.getInstance().addRequestQueue(jsonObjectRequest, TAG);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    public void updateVoterIntoDatabase(String family_member_name, String mobile_no, String voter_id){
        mRequestQueue = Volley.newRequestQueue(getApplication().getApplicationContext());
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("family_member_name",family_member_name);
            jsonObject.put("mobile_no",mobile_no);
            jsonObject.put("voter_id",voter_id);
            ResponseModel responseModel = new ResponseModel();
            JsonObjectRequest jsonObjectRequest =new JsonObjectRequest(Request.Method.POST, Constant.update_voter,
                    jsonObject,response -> {
                JSONObject jsonObject1= null;
                try {
                    responseModel.setJsonObject(response);
                    responseModel.setVolleyError(null);
                    Toast.makeText(application, response.toString(), Toast.LENGTH_SHORT).show();
                    /*jsonObject1 = new JSONObject(response.toString());
                    JSONArray arr = jsonObject1.getJSONArray("household_data");
                    householdDataList = new ArrayList<>();
                    for(int i=0;i<arr.length();i++){
                        JSONObject jsonObject2 = arr.getJSONObject(i);
                        HouseholdData householdData = new HouseholdData(jsonObject2.getString("household_name"),
                                jsonObject2.getString("family_member_name"), jsonObject2.getString("mobile_no"),
                                jsonObject2.getString("household_number"));
                        householdDataList.add(householdData);
                    }
                    Log.i("list_length",String.valueOf(householdDataList.size()));
*/
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }, error -> {
                responseModel.setJsonObject(null);
                responseModel.setVolleyError(error);
            });
            mRequestQueue.add(jsonObjectRequest);
            //AppClass.getInstance().addRequestQueue(jsonObjectRequest, TAG);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }



}
