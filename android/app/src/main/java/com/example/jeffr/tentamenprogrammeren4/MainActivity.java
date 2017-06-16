package com.example.jeffr.tentamenprogrammeren4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private MovieAdapter movieAdapter;
    private ArrayList<Movie> movies;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        movies = new ArrayList<>();
        movieAdapter = new MovieAdapter(this, movies);

        ListView listView = (ListView) findViewById(R.id.listMovies);
        listView.setAdapter(movieAdapter);
        listView.setOnItemClickListener(this);


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        token = bundle.getString("token");

    }

    private ArrayList<Movie> getMovies(String string){
        ArrayList<Movie> movies = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(string);
            movies = Movie.fromJSONArray(jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return movies;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        Movie movie = movies.get(position);
        startMovieDetailActivity(movie);
    }

    /**
     * Overrides the pending Activity transition by performing the "Enter" animation.
     */

    private void startMovieDetailActivity(Movie movie) {
        Intent intent = new Intent(this, MovieDetailActivity.class);
        intent.putExtra("movie", movie);
        intent.putExtra("token", token);

    }
}
