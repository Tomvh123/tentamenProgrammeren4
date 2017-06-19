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
import com.example.jeffr.tentamenprogrammeren4.domain.MovieIdMapper;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tom on 19-6-2017.
 */

public class MovieIdRequest {

    private Context context;
    public final String TAG = this.getClass().getSimpleName();

    private MovieIdRequest.MovieIdlistener listener;

    public MovieIdRequest(Context context, MovieIdRequest.MovieIdlistener listener){
        this.context = context;
        this.listener = listener;
    }

        public void handleGetAllMoviesId(int film_id) {

            Log.i(TAG, "handleGetMovies");

            // Haal het token uit de prefs
            SharedPreferences sharedPref = context.getSharedPreferences(
                    context.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
            final String token = sharedPref.getString(context.getString(R.string.saved_token), "dummy default token");
            if(token != null && !token.equals("dummy default token")) {

                Log.i(TAG, "Token gevonden, we gaan het request uitvoeren");
                JsonObjectRequest jsObjRequest = new JsonObjectRequest(
                        Request.Method.GET,
                        Config.URL_MoviesId + film_id,
                        null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                // Succesvol response
                                Log.i(TAG, response.toString());
                                ArrayList<Film> result = MovieIdMapper.mapMovieIDList(response);
                                listener.onMoviesIdAvailable(result);
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
                        headers.put("X-Access-Token", token);
                        return headers;
                    }
                };

                // Access the RequestQueue through your singleton class.
                VolleyRequestQueue.getInstance(context).addToRequestQueue(jsObjRequest);
            }
        }

    public interface MovieIdlistener{
        void onMoviesIdAvailable(ArrayList<Film> films);

    }

    }


