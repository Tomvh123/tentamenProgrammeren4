package com.example.jeffr.tentamenprogrammeren4.service;

import android.content.Context;

import com.example.jeffr.tentamenprogrammeren4.domain.Movie;

import java.util.ArrayList;

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

    



    public interface MovieListener {
        // Callback function to return a fresh list of ToDos
        void onToDosAvailable(ArrayList<Movie> movies);

        // Callback function to handle a single added ToDo.
        void onToDoAvailable(Movie movie);

        // Callback to handle serverside API errors
        void onToDosError(String message);
    }
}
