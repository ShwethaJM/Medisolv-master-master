package com.android.medisolv;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DependentRegisterActivity extends ActionBarActivity implements AdapterView.OnItemSelectedListener{

    static EditText dob;
    static SimpleDateFormat dateFormatter,changeDateFormatter;

    EditText firstName;
    EditText lastName;
    EditText mobile;
    EditText email;
    Button submit;

    Spinner spinner;
    Spinner genderSpinner;

    String gender;
    String item;

    private final String serverUrl = "http://www.algosolv.com/medicosolv/login/signup.php";
    private static int DELAY_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dependent_register);

         /*code to set date picker*/
        dateFormatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.UK);
        addListenerOnButton();

          /*set spinner values*/
        spinner= (Spinner)findViewById(R.id.dependentSpinner1);
        ArrayAdapter arrayAdapter = ArrayAdapter.createFromResource(this,R.array.spinner1_array,R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        /*set gender spinner values*/
        genderSpinner = (Spinner)findViewById(R.id.dependentGenderSpinner);
        ArrayAdapter genderArrayAdapter = ArrayAdapter.createFromResource(this,R.array.gender_array,R.layout.support_simple_spinner_dropdown_item);
        genderSpinner.setAdapter(genderArrayAdapter);

        /*setting the listener when item selected in all spinners of the page*/
        spinner.setOnItemSelectedListener(this);
        genderSpinner.setOnItemSelectedListener(this);

        submit = (Button)findViewById(R.id.dependentReg_submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String enteredFirstName = firstName.getText().toString();

                String enteredLastName = lastName.getText().toString();

                String name = enteredFirstName +" "+ enteredLastName;

                String enteredEmail = email.getText().toString();

                String enteredMobile = mobile.getText().toString();

                String selectedGender = genderSpinner.getSelectedItem().toString();

                String enteredDob = dob.getText().toString();

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

                if(enteredFirstName.equals("") || enteredLastName.equals("") ||enteredEmail.equals("") || enteredMobile.equals("") || selectedGender.equals("") || changedFormatDob.equals("")|| salute.equals("")){

                    Toast.makeText(DependentRegisterActivity.this, "All fields are mandatory", Toast.LENGTH_LONG).show();

                    return;

                }
                else if(enteredMobile.length()<10)
                {
                    Toast.makeText(DependentRegisterActivity.this, "Mobile Number should be 10 digits", Toast.LENGTH_LONG).show();
                    return;
                }

                else if(enteredEmail.matches(emailPattern)== false){
                    Toast.makeText(DependentRegisterActivity.this, "Please enter the valid Email Address", Toast.LENGTH_LONG).show();
                    return;
                }
                // request authentication with remote server4
                else{
                    DependentRegisterActivityAsyncTask asyncRequestObject = new DependentRegisterActivityAsyncTask();
                    asyncRequestObject.execute(serverUrl, salute, name, enteredMobile, enteredEmail,selectedGender,enteredDob);
                }
            }
        });
    }


    public void addListenerOnButton() {

        dob = (EditText) findViewById(R.id.dependentReg_dob);

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
    /*Method to get the value of the spinners*/
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if(adapterView.getId() == R.id.dependentSpinner1){
            item = adapterView.getItemAtPosition(i).toString();
        }else if (adapterView.getId() == R.id.dependentGenderSpinner) {
            gender = adapterView.getItemAtPosition(i).toString();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

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
        }


    }

    private class DependentRegisterActivityAsyncTask extends AsyncTask<String,Void,String>{


        @Override
        protected String doInBackground(String... params) {

            HttpParams httpParameters = new BasicHttpParams();

            HttpConnectionParams.setConnectionTimeout(httpParameters, 5000);

            HttpConnectionParams.setSoTimeout(httpParameters, 5000);

            HttpClient httpClient = new DefaultHttpClient(httpParameters);

            HttpPost httpPost = new HttpPost(params[0]);
            System.out.println("params[0]: " + params[0]);
            String jsonResult = "";

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

                    Toast.makeText(getApplicationContext(), "Server connection failed", Toast.LENGTH_LONG).show();

                    return;

                }
                System.out.println("Result: " + result);
                int jsonResult = returnParsedJsonObject(result);


                System.out.println("Resulted Value: " + jsonResult);
                if (jsonResult == 0) {

                    Toast.makeText(getApplicationContext(), "Insertion failed, Try again", Toast.LENGTH_LONG).show();

                    return;

                } else {

                    Toast.makeText(getApplicationContext(), "Registration successful. Your Patient ID is sent to your registered mobile number. ", Toast.LENGTH_LONG).show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            // This method will be executed once the timer is over
                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);

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
