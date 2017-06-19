package com.example.jeffr.tentamenprogrammeren4;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.jeffr.tentamenprogrammeren4.domain.Film;
import com.example.jeffr.tentamenprogrammeren4.domain.RentalAdapter;
import com.example.jeffr.tentamenprogrammeren4.service.MovieIdRequest;

import java.util.ArrayList;

//import nl.avans.movierent.data.RESTApiV1;
//import nl.avans.movierent.interfaces.RequestInterface;
//import nl.avans.movierent.objects.Movie;

/**
 * Package: Android
 * Created by Jeffry on 28-5-2017.
 */

public class MovieDetailActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, MovieIdRequest.MovieIdlistener{
    public final String TAG = this.getClass().getSimpleName();


    public final static String MOVIE_DATA = "FILM";

    public static final int MY_REQUEST_CODE = 1234;

    private ListView listView;
    private BaseAdapter movieAdapter;
    private ArrayList<Film> films = new ArrayList<>();
    private int film_id;
    private TextView status;

    private TextView title, desription, release, rating;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        title = (TextView) findViewById(R.id.dtitle);
        desription = (TextView) findViewById(R.id.ddescription);
        release = (TextView) findViewById(R.id.drelease);
        rating = (TextView) findViewById(R.id.drating);
        status = (TextView) findViewById(R.id.available);

        listView = (ListView) findViewById(R.id.listViewDetail);
        listView.setOnItemClickListener(this);



        movieAdapter = new RentalAdapter(this, films);
        listView.setAdapter(movieAdapter);

        Bundle extras = getIntent().getExtras();

        Film film = (Film) extras.getSerializable(MOVIE_DATA);
        Log.d(TAG, film.toString());

        film_id = film.getFilm_id();

        title.setText(film.getTitle());
        desription.setText(film.getDescription());
        release.setText(String.valueOf(film.getRelease_year()));
        rating.setText(film.getRating());

        getMovies();



}

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Log.i(TAG, "Position " + position + " is geselecteerd");
        Film film = films.get(position);
        Log.d(TAG, "" + film.getRental_id());
        if (film.getRental_id() != 0) {
            Log.d(TAG, "movie not saved");
        }else{

            MovieIdRequest request = new MovieIdRequest(getApplicationContext(), this);
            request.handlePostMovie(film);
            movieAdapter.notifyDataSetChanged();
        }




    }

    @Override
    public void onMoviesIdAvailable(ArrayList<Film> movieArrayList) {

        Log.i(TAG, "We hebben " + movieArrayList.size() + " items in de lijst");

        films.clear();
        for(int i = 0; i < movieArrayList.size(); i++) {
            films.add(movieArrayList.get(i));
        }
        movieAdapter.notifyDataSetChanged();


    }

    @Override
    public void onMovieAvailable(Film film) {

    }

    @Override
    public void onMoviesError(String message) {

    }

    private void getMovies(){
        MovieIdRequest request = new MovieIdRequest(getApplicationContext(), this);
        request.handleGetAllMoviesId(film_id);
    }

}
