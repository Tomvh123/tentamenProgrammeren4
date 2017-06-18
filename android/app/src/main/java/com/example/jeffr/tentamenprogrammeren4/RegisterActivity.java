package com.example.jeffr.tentamenprogrammeren4;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.jeffr.tentamenprogrammeren4.service.Config;
import com.example.jeffr.tentamenprogrammeren4.service.VolleyRequestQueue;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by tom on 17-6-2017.
 */

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    public final String TAG = this.getClass().getSimpleName();

    private EditText usernameET, passwordET, firstnameET, lastnameET, emailET;
    private String username, password, firstname, lastname, email, createdate;
    private int storeid, adress, active;
    private Button registerButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        usernameET = (EditText) findViewById(R.id.usernameEditText);
        passwordET = (EditText) findViewById(R.id.passwordEditText);
        firstnameET = (EditText) findViewById(R.id.firstnameEditText);
        lastnameET = (EditText) findViewById(R.id.lastnameEditText);
        emailET = (EditText) findViewById(R.id.emailEditText);
        registerButton = (Button) findViewById(R.id.registerButton);
        registerButton.setOnClickListener(this);




}

    private void handleRegister(String username, String password, int storeid, String firstname, String lastname, int address, String email, int active, String createdate ) {
        //
        // Maak een JSON object met username en password. Dit object sturen we mee
        // als request body (zoals je ook met Postman hebt gedaan)
        //
        String body = "{\"username\":\"" + username + "\",\"password\":\"" + password + "\",\"storeid\":\"" + storeid + "\",\"firstname\":\"" + firstname + "\",\"lastname\":\"" + lastname + "\",\"address\":\"" + address + "\",\"email\":\"" + email + "\",\"active\":\"" + active + "\",\"createdate\":\"" + createdate + "\"}";
        Log.i(TAG, "handleRegister - body = " + body);

        try {
            JSONObject jsonBody = new JSONObject(body);
            JsonObjectRequest jsObjRequest = new JsonObjectRequest
                    (Request.Method.POST, Config.URL_REGISTER, jsonBody, new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            displayMessage("Succesvol geregistreerd!");
                                Intent main = new Intent(getApplicationContext(), LoginActivity.class);
                                startActivity(main);
                                finish();

                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            handleErrorResponse(error);
                        }
                    });

            jsObjRequest.setRetryPolicy(new DefaultRetryPolicy(
                    1500, // SOCKET_TIMEOUT_MS,
                    2, // DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

            // Access the RequestQueue through your singleton class.
            VolleyRequestQueue.getInstance(this).addToRequestQueue(jsObjRequest);
        } catch (JSONException e) {

            // e.printStackTrace();
        }
        return;


    }

    public void handleErrorResponse(VolleyError error) {
        Log.e(TAG, "handleErrorResponse");

        if(error instanceof com.android.volley.AuthFailureError) {
            String json = null;
            NetworkResponse response = error.networkResponse;
            if (response != null && response.data != null) {
                json = new String(response.data);
                json = trimMessage(json, "error");
                if (json != null) {
                    json = "Error " + response.statusCode + ": " + json;
                    displayMessage(json);
                }
            } else {
                Log.e(TAG, "handleErrorResponse: kon geen networkResponse vinden.");
            }
        } else if(error instanceof com.android.volley.NoConnectionError) {
            Log.e(TAG, "handleErrorResponse: server was niet bereikbaar");

        } else {
            Log.e(TAG, "handleErrorResponse: error = " + error);
        }
    }

    public String trimMessage(String json, String key){
        Log.i(TAG, "trimMessage: json = " + json);
        String trimmedString = null;

        try{
            JSONObject obj = new JSONObject(json);
            trimmedString = obj.getString(key);
        } catch(JSONException e){
            e.printStackTrace();
            return null;
        }
        return trimmedString;
    }

    public void displayMessage(String toastString){
        Toast.makeText(getApplicationContext(), toastString, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View v) {
        username = usernameET.getText().toString();
        password = passwordET.getText().toString();
        storeid = 1;
        firstname = firstnameET.getText().toString();
        lastname = lastnameET.getText().toString();
        adress = 1;
        email = emailET.getText().toString();
        active = 1;
        createdate = "2017-05-05 10:05:40";

        handleRegister(username, password, storeid, firstname, lastname, adress, email, active, createdate);



    }
}

