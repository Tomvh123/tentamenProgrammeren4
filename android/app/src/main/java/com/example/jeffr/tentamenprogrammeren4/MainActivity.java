package com.example.jeffr.tentamenprogrammeren4;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.jeffr.tentamenprogrammeren4.domain.Film;
import com.example.jeffr.tentamenprogrammeren4.domain.MovieAdapter;
import com.example.jeffr.tentamenprogrammeren4.service.MovieRequest;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, AdapterView.OnItemClickListener, MovieRequest.MovieListener, View.OnClickListener {

    public final String TAG = this.getClass().getSimpleName();

    public final static String MOVIE_DATA = "FILM";

    public static final int MY_REQUEST_CODE = 1234;

    private ListView listView;
    private BaseAdapter movieAdapter;
    private ArrayList<Film> films = new ArrayList<>();
    private Button logoutButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(tokenAvailable()){
            setContentView(R.layout.activity_main);

            logoutButton = (Button) findViewById(R.id.logouButton);
            logoutButton.setOnClickListener(this);

            listView = (ListView) findViewById(R.id.listview);
            listView.setOnItemClickListener(this);

            movieAdapter = new MovieAdapter(this, films);
            listView.setAdapter(movieAdapter);

            Log.d(TAG, "Token gevonden, movies ophalen");
            getMovies();

            
        }else{
            Log.d(TAG, "Geen token gevonden, er moet ingelogd worden");
            Intent login = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(login);
            finish();
        }



    }

    protected void onActivityResult(int requestCode, int resultCode, Intent pData)
    {
        if ( requestCode == MY_REQUEST_CODE )
        {
            Log.v( TAG, "onActivityResult OK" );
            if (resultCode == Activity.RESULT_OK )
            {
                final Film newFilm = (Film) pData.getSerializableExtra(MOVIE_DATA);
                Log.v( TAG, "Retrieved Value newToDo is " + newFilm);

                // We need to save our new ToDo
                postMovie(newFilm);
            }
        }

    }

    private boolean tokenAvailable() {
        boolean result = false;

        Context context = getApplicationContext();
        SharedPreferences sharedPref = context.getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        String token = sharedPref.getString(getString(R.string.saved_token), "dummy default token");
        if (token != null && !token.equals("dummy default token")) {
            result = true;
        }
        return result;
    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.i(TAG, "Position " + position + " is geselecteerd");

        Film film = films.get(position);
        Intent intent = new Intent(getApplicationContext(), MovieDetailActivity.class);
        intent.putExtra(MOVIE_DATA, film);
        startActivity(intent);
    }

    @Override
    public void onMoviesAvailable(ArrayList<Film> movieArrayList) {

        Log.i(TAG, "We hebben " + movieArrayList.size() + " items in de lijst");

        films.clear();
        for(int i = 0; i < movieArrayList.size(); i++) {
            films.add(movieArrayList.get(i));
        }
        movieAdapter.notifyDataSetChanged();
    }

    @Override
    public void onMovieAvailable(Film film) {
        films.add(film);
        movieAdapter.notifyDataSetChanged();
    }

    @Override
    public void onMoviesError(String message) {
        Log.e(TAG, message);
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    private void getMovies(){
        MovieRequest request = new MovieRequest(getApplicationContext(), this);
        request.handleGetAllMovies();
    }



    private void postMovie(Film film){
        MovieRequest request = new MovieRequest(getApplicationContext(), this);
        request.handlePostMovie(film);
    }

    @Override
    public void onClick(View v) {
        // Logout - remove token from local settings and navigate to login screen.
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.remove(getString(R.string.saved_token));
        editor.commit();

        // Empty the homescreen
        films.clear();
        movieAdapter.notifyDataSetChanged();

        // Navigate to login screen
        Intent login = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(login);
    }
}
