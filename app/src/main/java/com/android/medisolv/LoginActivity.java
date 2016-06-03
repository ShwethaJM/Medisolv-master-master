package com.android.medisolv;

import android.content.Context;
import android.content.Intent;

import android.os.AsyncTask;

import android.os.Bundle;

import android.support.v7.app.ActionBarActivity;

import android.view.Menu;

import android.view.MenuItem;

import android.view.View;

import android.widget.Button;

import android.widget.EditText;

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

import java.util.ArrayList;

import java.util.List;

public class LoginActivity extends ActionBarActivity {

    protected EditText mobile;

    private EditText id;

    protected String name;

    protected String mobileNo;

    private final String serverUrl = "http://www.algosolv.com/medicosolv/login/index.php";

    private String patientID="";

    Context context;
    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        context = getApplicationContext();

        mobile = (EditText)findViewById(R.id.mobile_no);

        id = (EditText)findViewById(R.id.patientid);

        Button submit = (Button)findViewById(R.id.submit);

        //Button registerButton = (Button)findViewById(R.id.);
        Toast.makeText(LoginActivity.this, "Welcome, Enter Mobile number and Patient ID", Toast.LENGTH_LONG).show();
        submit.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                mobileNo = mobile.getText().toString();

                String enteredid = id.getText().toString();

                if(mobileNo.equals("") || enteredid.equals("")){

                    Toast.makeText(LoginActivity.this, "All the fields are mandatory", Toast.LENGTH_LONG).show();

                    return;

                }

                if(mobileNo.length() < 10){

                    Toast.makeText(LoginActivity.this, "Mobile number should have 10 digits", Toast.LENGTH_LONG).show();

                    return;

                }

// request authentication with remote server4

                AsyncDataClass asyncRequestObject = new AsyncDataClass();

                asyncRequestObject.execute(serverUrl, mobileNo, enteredid);

            }

        });


    }





    private class AsyncDataClass extends AsyncTask<String, Void, String> {

        @Override

        protected String doInBackground(String... params) {

            HttpParams httpParameters = new BasicHttpParams();

            HttpConnectionParams.setConnectionTimeout(httpParameters, 5000);

            HttpConnectionParams.setSoTimeout(httpParameters, 5000);

            HttpClient httpClient = new DefaultHttpClient(httpParameters);

            HttpPost httpPost = new HttpPost(params[0]);
            System.out.println("params[0]: " + params[0]);
            String jsonResult = null;

            try {

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);

                nameValuePairs.add(new BasicNameValuePair("mobile", params[1]));

                System.out.println("params[1]: " + params[1]);

                nameValuePairs.add(new BasicNameValuePair("id", params[2]));

                System.out.println("params[2]: " + params[2]);

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

        protected void onPreExecute() {

            super.onPreExecute();

        }

        @Override

        protected void onPostExecute(String result) {

            super.onPostExecute(result);


            try {
                if (result.equals("") || result == null) {

                    Toast.makeText(LoginActivity.this, "Server connection failed", Toast.LENGTH_LONG).show();

                    return;

                }
                System.out.println("Result: " + result);
                String jsonResult = returnParsedJsonObject(result);
                System.out.println("Resulted Value: " + jsonResult);
                if (jsonResult.equals("")||patientID.equals("")) {

                    Toast.makeText(LoginActivity.this, "Invalid mobile or id", Toast.LENGTH_LONG).show();

                    return;

                } else {

                    Intent intent = new Intent(LoginActivity.this, WelcomeActivity.class);

                    intent.putExtra("name", jsonResult);

                    intent.putExtra("id",patientID);

                    intent.putExtra("MESSAGE", "You have been successfully login");

                    startActivity(intent);

                }
            }catch(Exception e){

                e.printStackTrace();
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

    }


    private String returnParsedJsonObject(String result){

        JSONObject resultObject = null;

        String returnedResult = "";

        try {

            resultObject = new JSONObject(result);

            //returnedResult = resultObject.getInt("success");
            //returnedResult = resultObject.optString("name");
            returnedResult = resultObject.getString("name");
            patientID = resultObject.getString("id");
        } catch (JSONException e) {

            e.printStackTrace();

        }

        return returnedResult;

    }

}