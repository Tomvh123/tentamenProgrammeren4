package com.example.jeffr.tentamenprogrammeren4;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.jeffr.tentamenprogrammeren4.domain.Film;

import static com.example.jeffr.tentamenprogrammeren4.MainActivity.MOVIE_DATA;

//import nl.avans.movierent.data.RESTApiV1;
//import nl.avans.movierent.interfaces.RequestInterface;
//import nl.avans.movierent.objects.Movie;

/**
 * Package: Android
 * Created by Jeffry on 16-6-2017.
 */

public class MovieDetailActivity extends AppCompatActivity {

    private TextView title, desription, release;

    public final String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        title = (TextView) findViewById(R.id.dtitle);
        desription = (TextView) findViewById(R.id.ddescription);
        release = (TextView) findViewById(R.id.drelease);

        Bundle extras = getIntent().getExtras();

        Film film = (Film) extras.getSerializable(MOVIE_DATA);
        Log.d(TAG, film.toString());

        title.setText(film.getTitle());
        desription.setText(film.getDescription());
        release.setText(String.valueOf(film.getRelease_year()));

}

}
