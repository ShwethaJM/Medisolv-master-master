package com.android.medisolv;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;

import android.os.AsyncTask;

import android.os.Bundle;

import android.os.Handler;
import android.support.v7.app.ActionBarActivity;

import android.support.v7.widget.Toolbar;

import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;

import android.widget.DatePicker;
import android.widget.EditText;

import android.widget.Spinner;
import android.widget.Toast;

import org.apache.http.HttpResponse;

import org.apache.http.NameValuePair;

import org.apache.http.client.ClientProtocolException;

import org.apache.http.client.HttpClient;

import org.apache.http.client.entity.UrlEncodedFormEntity;

import org.apache.http.client.methods.HttpPost;

import org.apache.http.impl.client.DefaultHttpClient;

import org.apache.http.message.BasicNameValuePair;

import org.apache.http.params.BasicHttpParams;

import org.apache.http.params.HttpConnectionParams;

import org.apache.http.params.HttpParams;

import org.json.JSONException;

import org.json.JSONObject;

import java.io.BufferedReader;

import java.io.IOException;

import java.io.InputStream;

import java.io.InputStreamReader;

import java.text.ParseException;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class RegistrationActivity extends ActionBarActivity implements AdapterView.OnItemSelectedListener {

    static EditText dob;

    static SimpleDateFormat dateFormatter,changeDateFormatter;

    EditText firstName;
    EditText lastName;
    EditText mobile;
    EditText specialisation;
    EditText license;
    EditText email;

    Spinner spinner;
    Spinner genderSpinner;
    Spinner regTypeSpinner;

    Button submit;

    String registrationType;
    String gender;
    String item;
    String name;
    String enteredLicense = "";

    String enteredSpecialization = "";
    Boolean doctorFlag;
    private final String serverUrl = "http://www.algosolv.com/medicosolv/login/signup.php";
    private static int DELAY_TIME_OUT = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mobile = (EditText)findViewById(R.id.mobile_no);

        firstName = (EditText)findViewById(R.id.reg_fn);
        lastName = (EditText)findViewById(R.id.reg_ln);
        email = (EditText)findViewById(R.id.reg_email);
        mobile = (EditText)findViewById(R.id.reg_mobile);


        /*code to set date picker*/
        dateFormatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.UK);
        dob = (EditText) findViewById(R.id.reg_dob);
        addListenerOnButton();

        /*code to set custom title bar*/
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        /*set spinner values*/
        spinner= (Spinner)findViewById(R.id.spinner1);
        ArrayAdapter arrayAdapter = ArrayAdapter.createFromResource(this,R.array.spinner1_array,R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        /*set gender spinner values*/
        genderSpinner = (Spinner)findViewById(R.id.genderSpinner);
        ArrayAdapter genderArrayAdapter = ArrayAdapter.createFromResource(this,R.array.gender_array,R.layout.support_simple_spinner_dropdown_item);
        genderSpinner.setAdapter(genderArrayAdapter);

        /* set Registration Type spinner values*/
        regTypeSpinner = (Spinner)findViewById(R.id.regTypeSpinner);
        ArrayAdapter regTypeArrayAdapter = ArrayAdapter.createFromResource(this,R.array.regType_array,R.layout.support_simple_spinner_dropdown_item);
        regTypeSpinner.setAdapter(regTypeArrayAdapter);

        spinner.setOnItemSelectedListener(this);
        genderSpinner.setOnItemSelectedListener(this);
        regTypeSpinner.setOnItemSelectedListener(this);

        submit=(Button)findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                String enteredFirstName = firstName.getText().toString();

                String enteredLastName = lastName.getText().toString();

                String name = enteredFirstName + enteredLastName;

                String enteredEmail = email.getText().toString().trim();

                String enteredMobile = mobile.getText().toString();

                String selectedGender = genderSpinner.getSelectedItem().toString();

                String enteredDob = dob.getText().toString();

                String selectedRegType = regTypeSpinner.getSelectedItem().toString();

                String enteredLicense = license.getText().toString();

                String enteredSpecialization = specialisation.getText().toString();

                String salute = spinner.getSelectedItem().toString();

                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

                String changedFormatDob="";

                try {
                    Date dobDate = dateFormatter.parse(enteredDob);
                    changeDateFormatter = new SimpleDateFormat("yyyy/mm/dd",Locale.UK);
                    changedFormatDob = changeDateFormatter.format(dobDate);
                    System.out.println("Changed date: "+changedFormatDob);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                if(enteredFirstName.equals("") || enteredLastName.equals("") ||enteredEmail.equals("") || enteredMobile.equals("") || selectedGender.equals("") || selectedRegType.equals("") || changedFormatDob.equals("")|| salute.equals("") ||enteredEmail.equals("")){

                    Toast.makeText(RegistrationActivity.this, "All fields are mandatory", Toast.LENGTH_LONG).show();

                    return;

                }else if(selectedRegType.equals("Doctor") && (enteredLicense.equals("") || enteredSpecialization.equals(""))){

                    Toast.makeText(RegistrationActivity.this, "All fields are mandatory", Toast.LENGTH_LONG).show();

                    return;

                }else if(enteredMobile.length()<10)
                {
                    Toast.makeText(RegistrationActivity.this, "Mobile Number should be 10 digits", Toast.LENGTH_LONG).show();
                    return;
                }

                else if(enteredEmail.matches(emailPattern)== false){
                    System.out.println("Invalid Email");
                    Toast.makeText(RegistrationActivity.this, "Please enter the valid Email Address", Toast.LENGTH_LONG).show();
                    return;
                }
// request authentication with remote server4
                else {
                    RegistrationActivityAsyncTask asyncRequestObject = new RegistrationActivityAsyncTask();

                    asyncRequestObject.execute(serverUrl, salute, name, enteredMobile, enteredEmail, selectedGender, changedFormatDob, selectedRegType, enteredLicense, enteredSpecialization);
                }
            }

        });



    }

    public void addListenerOnButton() {

        dob = (EditText) findViewById(R.id.reg_dob);

        dob.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // Initialize a new date picker dialog fragment
                DialogFragment dFragment = new DatePickerFragment();

                // Show the date picker dialog fragment
                dFragment.show(getFragmentManager(), "Date Picker");

            }

        });

    }
    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            // DatePickerDialog THEME_TRADITIONAL
            DatePickerDialog dpd = new DatePickerDialog(getActivity(),
                    AlertDialog.THEME_TRADITIONAL, this, year, month, day);

            // Return the DatePickerDialog
            return dpd;
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Display the chosen date
            Calendar newCalender = Calendar.getInstance();
            newCalender.set(year,month,day);
            dob.setText(dateFormatter.format(newCalender.getTime()));
            System.out.println("Previous Date: "+dateFormatter.format(newCalender.getTime()));

        }


    }
    /*method to un hide the specialisation and license edit text and to popup message
    to select the registration type when it is not selected*/
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        if(adapterView.getId() == R.id.spinner1){
            item = adapterView.getItemAtPosition(i).toString();
        }else if (adapterView.getId() == R.id.genderSpinner){
            gender = adapterView.getItemAtPosition(i).toString();
        }if (adapterView.getId() == R.id.regTypeSpinner) {
            if (i > 0) {
            /*code to un hide the specialisation and license edit text*/
                registrationType = adapterView.getItemAtPosition(i).toString();
                specialisation = (EditText) findViewById(R.id.reg_spl);
                license = (EditText) findViewById(R.id.reg_license);
                if (registrationType.equals(new String("Doctor"))) {
                    doctorFlag=true;
                    specialisation.setVisibility(View.VISIBLE);
                    license.setVisibility(View.VISIBLE);
                } else {
                    doctorFlag=false;
                    specialisation.setVisibility(View.INVISIBLE);
                    license.setVisibility(View.INVISIBLE);
                }

            } else {
            /*code to popup message to select the registration type when it is not selected*/
                // Toast.makeText(getApplicationContext(), "Please select the Registration type", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        Toast.makeText(this, "Please select the Registration type", Toast.LENGTH_LONG).show();
    }


    /*code to get the values form the view and check all mandatory fields are containing values or not*/

    private class RegistrationActivityAsyncTask extends AsyncTask<String,Void,String>
    {
        @Override
        protected String doInBackground(String... params) {

            HttpParams httpParameters = new BasicHttpParams();

            HttpConnectionParams.setConnectionTimeout(httpParameters, 5000);

            HttpConnectionParams.setSoTimeout(httpParameters, 5000);

            HttpClient httpClient = new DefaultHttpClient(httpParameters);

            HttpPost httpPost = new HttpPost(params[0]);
            System.out.println("params[0]: " + params[0]);
            String jsonResult = "";
            System.out.println("params[9] Specialisation: "+params[9]);

            try {

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);

                nameValuePairs.add(new BasicNameValuePair("salute", params[1]));

                System.out.println("params[1]: " + params[1]);

                nameValuePairs.add(new BasicNameValuePair("name", params[2]));

                System.out.println("params[2]: " + params[2]);

                nameValuePairs.add(new BasicNameValuePair("mobile", params[3]));

                nameValuePairs.add(new BasicNameValuePair("email", params[4]));

                nameValuePairs.add(new BasicNameValuePair("gender", params[5]));

                nameValuePairs.add(new BasicNameValuePair("dob", params[6]));

                nameValuePairs.add(new BasicNameValuePair("regType", params[7]));

                nameValuePairs.add(new BasicNameValuePair("license", params[8]));

                nameValuePairs.add(new BasicNameValuePair("spcialization", params[9]));

                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                HttpResponse response = httpClient.execute(httpPost);


                jsonResult = inputStreamToString(response.getEntity().getContent()).toString();


                System.out.println("jsonResult Value: " + jsonResult);
                //              Toast.makeText(LoginActivity.this, jsonResult, Toast.LENGTH_LONG).show();

            } catch (ClientProtocolException e) {

                e.printStackTrace();

            } catch (IOException e) {

                e.printStackTrace();

            }




            return jsonResult;
        }

        @Override
        protected void onPostExecute(String result) {
            //database retrivel code comes here
            super.onPostExecute(result);


            try {
                if (result.equals("") || result == null) {

                    Toast.makeText(RegistrationActivity.this, "Server connection failed", Toast.LENGTH_LONG).show();

                    return;

                }
                System.out.println("Result: " + result);
                int jsonResult = returnParsedJsonObject(result);


                System.out.println("Resulted Value: " + jsonResult);
                if (jsonResult == 0) {

                    Toast.makeText(RegistrationActivity.this, "Insertion failed, Try again", Toast.LENGTH_LONG).show();

                    return;

                } else {

                    Toast.makeText(RegistrationActivity.this, "Registration successful. Your Patient ID is sent to your registered mobile number. ", Toast.LENGTH_LONG).show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            // This method will be executed once the timer is over
                            Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);

                            //intent.putExtra("name", "Sneha");

                            intent.putExtra("MESSAGE", "You have been successfully registered, Kindly Login");

                            startActivity(intent);
                        }
                    },DELAY_TIME_OUT);



                }
            }catch(Exception e){

                e.printStackTrace();
            }

        }



        }
    private StringBuilder inputStreamToString(InputStream is) {

        String rLine = "";

        StringBuilder answer = new StringBuilder();

        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        try {

            while ((rLine = br.readLine()) != null) {

                answer.append(rLine);

            }

        } catch (IOException e) {

// TODO Auto-generated catch block

            e.printStackTrace();

        }

        return answer;

    }



    private int returnParsedJsonObject(String result){

        JSONObject resultObject = null;

        int returnedResult = 0;

        try {

            resultObject = new JSONObject(result);

            //returnedResult = resultObject.getInt("success");
            //returnedResult = resultObject.optString("name");
            returnedResult = resultObject.getInt("success");
        } catch (JSONException e) {

            e.printStackTrace();

        }

        return returnedResult;

    }
    }

