package com.example.jeffr.tentamenprogrammeren4.service;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.jeffr.tentamenprogrammeren4.R;
import com.example.jeffr.tentamenprogrammeren4.domain.Film;
import com.example.jeffr.tentamenprogrammeren4.domain.MovieMapper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tom on 16-6-2017.
 */

public class MovieRequest {

    private Context context;
    public final String TAG = this.getClass().getSimpleName();

    private MovieRequest.MovieListener listener;

    public MovieRequest(Context context, MovieRequest.MovieListener listener){
        this.context = context;
        this.listener = listener;
    }

    public void handleGetAllMovies() {

        Log.i(TAG, "handleGetMovies");

        // Haal het token uit de prefs
        SharedPreferences sharedPref = context.getSharedPreferences(
                context.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        final String token = sharedPref.getString(context.getString(R.string.saved_token), "dummy default token");
        if(token != null && !token.equals("dummy default token")) {

            Log.i(TAG, "Token gevonden, we gaan het request uitvoeren");
            JsonObjectRequest jsObjRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    Config.URL_Movies,
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            // Succesvol response
                            Log.i(TAG, response.toString());
                            ArrayList<Film> result = MovieMapper.mapMovieList(response);
                            listener.onMoviesAvailable(result);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // handleErrorResponse(error);
                            Log.e(TAG, error.toString());
                        }
                    }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> headers = new HashMap<>();
                    headers.put("Content-Type", "application/json");
                    headers.put("Authorization", "Bearer " + token);
                    return headers;
                }
            };

            // Access the RequestQueue through your singleton class.
            VolleyRequestQueue.getInstance(context).addToRequestQueue(jsObjRequest);
        }
    }

    public void handlePostMovie(final Film newFilm) {

        Log.i(TAG, "handlePostMovie");

        // Haal het token uit de prefs
        // TODO Verplaats het ophalen van het token naar een centraal beschikbare 'utility funtion'
        SharedPreferences sharedPref = context.getSharedPreferences(
                context.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        final String token = sharedPref.getString(context.getString(R.string.saved_token), "dummy default token");
        if(token != null && !token.equals("dummy default token")) {

            //
            // Maak een JSON object met username en password. Dit object sturen we mee
            // als request body (zoals je ook met Postman hebt gedaan)
            //
            String body = "{\"Titel\":\"" + newFilm.getTitle() + "\",\"Beschrijving\":\"" + newFilm.getDescription() + "\"}";

            try {
                JSONObject jsonBody = new JSONObject(body);
                Log.i(TAG, "handlePostToDo - body = " + jsonBody);
                JsonObjectRequest jsObjRequest = new JsonObjectRequest(
                        Request.Method.POST,
                        Config.URL_Movies,
                        jsonBody,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Log.i(TAG, response.toString());
                                // Het toevoegen is gelukt
                                // Hier kun je kiezen: of een refresh door de hele lijst op te halen
                                // en de ListView bij te werken ... Of alleen de ene update toevoegen
                                // aan de ArrayList. Wij doen dat laatste.
                                listener.onMovieAvailable(newFilm);
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // Error - send back to caller
                                listener.onMoviesError(error.toString());
                            }
                        }){
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String, String> headers = new HashMap<>();
                        headers.put("Content-Type", "application/json");
                        headers.put("Authorization", "Bearer " + token);
                        return headers;
                    }
                };

                // Access the RequestQueue through your singleton class.
                VolleyRequestQueue.getInstance(context).addToRequestQueue(jsObjRequest);
            } catch (JSONException e) {
                Log.e(TAG, e.getMessage());
                listener.onMoviesError(e.getMessage());
            }
        }
    }





    public interface MovieListener {
        void onMoviesAvailable(ArrayList<Film> films);
        void onMovieAvailable(Film film);
        void onMoviesError(String message);
    }
}