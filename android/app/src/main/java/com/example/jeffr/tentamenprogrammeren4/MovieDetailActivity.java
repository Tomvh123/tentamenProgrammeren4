package com.example.jeffr.tentamenprogrammeren4;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import nl.avans.movierent.data.RESTApiV1;
import nl.avans.movierent.interfaces.RequestInterface;
import nl.avans.movierent.objects.Movie;

/**
 * Package: Android
 * Created by Jeffry on 28-5-2017.
 */

public class MovieDetailActivity extends AppCompatActivity implements View.OnClickListener{

    private Movie movie;
    private String token;
    private Boolean fabClicked = false;
    private TextView textViewMovieTitle, textViewMovieDescription, textViewMovieReleaseYear, textViewMovieLanguageId, textViewMovieOrigionalLanguageId, textViewMovieRentalDuration, textViewMovieRentalRate, textViewMovieLength, textViewMovieReplacementCost, textViewMovieRating, textViewMovieSpecialFeature, textViewMovieLastUpdate;
    private EditText editTextMovieTitle, editTextMovieDescription, editTextMovieReleaseYear, editTextMovieLanguageId, editTextMovieOrigionalLanguageId, editTextMovieRentalDuration, editTextMovieRentalRate, editTextMovieLength, editTextMovieReplacementCost, editTextMovieRating, editTextMovieSpecialFeature, editTextMovieLastUpdate;
    private ViewSwitcher viewSwitcherMovieTitle, viewSwitcherMovieDescription, viewSwitcherMovieReleaseYear, viewSwitcherMovieLanguageId, viewSwitcherMovieOrigionalLanguageId, viewSwitcherMovieRentalDuration, viewSwitcherMovieRentalRate, viewSwitcherMovieLength, viewSwitcherMovieReplacementCost, viewSwitcherMovieRating, viewSwitcherMovieSpecialFeature, viewSwitcherMovieLastUpdate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Bundle bundleExtra = getIntent().getExtras();
        movie = (Movie) bundleExtra.get("movie");
        token = bundleExtra.getString("token");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(movie.getTitle());
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        loadTextviews();
        loadEdittexts();
        loadViewSwitchers();
        loadMovieDataToTextView(movie);
        loadMovieDataToEditText(movie);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransitionExit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransitionExit();
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        if(movie.getFilmId() != 0) {
            getMenuInflater().inflate(R.menu.menu_detail, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {

        }
    }


    @NonNull
    private String getStringFromEditText(EditText editText){
        return editText.getText().toString();
    }

    @NonNull
    private Integer getIntegerFromEditText(EditText editText){
        return Integer.parseInt(getStringFromEditText(editText));
    }

    @NonNull
    private Double getDoubleFromEditText(EditText editText){
        return Double.parseDouble(getStringFromEditText(editText));
    }


    private void loadTextviews(){
        textViewMovieTitle = (TextView) findViewById(R.id.textViewMovieTitle);
        textViewMovieDescription = (TextView) findViewById(R.id.textViewMovieDescription);
        textViewMovieReleaseYear = (TextView) findViewById(R.id.textViewMovieReleaseYear);
        textViewMovieLanguageId = (TextView) findViewById(R.id.textViewMovieLanguageId);
        textViewMovieOrigionalLanguageId = (TextView) findViewById(R.id.textViewMovieOrigionalLanguageId);
        textViewMovieRentalDuration = (TextView) findViewById(R.id.textViewMovieRentalDuration);
        textViewMovieRentalRate = (TextView) findViewById(R.id.textViewMovieRentalRate);
        textViewMovieLength = (TextView) findViewById(R.id.textViewMovieLength);
        textViewMovieReplacementCost = (TextView) findViewById(R.id.textViewMovieReplacementCost);
        textViewMovieRating = (TextView) findViewById(R.id.textViewMovieRating);
        textViewMovieSpecialFeature = (TextView) findViewById(R.id.textViewMovieSpecialFeature);
        textViewMovieLastUpdate = (TextView) findViewById(R.id.textViewMovieLastUpdate);
    }

    private void loadEdittexts(){
        editTextMovieTitle = (EditText) findViewById(R.id.editTextMovieTitle);
        editTextMovieDescription = (EditText) findViewById(R.id.editTextMovieDescription);
        editTextMovieReleaseYear = (EditText) findViewById(R.id.editTextMovieReleaseYear);
        editTextMovieLanguageId = (EditText) findViewById(R.id.editTextMovieLanguageId);
        editTextMovieOrigionalLanguageId = (EditText) findViewById(R.id.editTextMovieOrigionalLanguageId);
        editTextMovieRentalDuration = (EditText) findViewById(R.id.editTextMovieRentalDuration);
        editTextMovieRentalRate = (EditText) findViewById(R.id.editTextMovieRentalRate);
        editTextMovieLength = (EditText) findViewById(R.id.editTextMovieLength);
        editTextMovieReplacementCost = (EditText) findViewById(R.id.editTextMovieReplacementCost);
        editTextMovieRating = (EditText) findViewById(R.id.editTextMovieRating);
        editTextMovieSpecialFeature = (EditText) findViewById(R.id.editTextMovieSpecialFeature);
        editTextMovieLastUpdate = (EditText) findViewById(R.id.editTextMovieLastUpdate);
    }

    private void loadViewSwitchers() {
        viewSwitcherMovieTitle = (ViewSwitcher) findViewById(R.id.viewSwitcherMovieTitle);
        viewSwitcherMovieDescription = (ViewSwitcher) findViewById(R.id.viewSwitcherMovieDescription);
        viewSwitcherMovieReleaseYear = (ViewSwitcher) findViewById(R.id.viewSwitcherMovieReleaseYear);
        viewSwitcherMovieLanguageId = (ViewSwitcher) findViewById(R.id.viewSwitcherMovieLanguageId);
        viewSwitcherMovieOrigionalLanguageId = (ViewSwitcher) findViewById(R.id.viewSwitcherMovieOrigionalLanguageId);
        viewSwitcherMovieRentalDuration = (ViewSwitcher) findViewById(R.id.viewSwitcherMovieRentalDuration);
        viewSwitcherMovieRentalRate = (ViewSwitcher) findViewById(R.id.viewSwitcherMovieRentalRate);
        viewSwitcherMovieLength = (ViewSwitcher) findViewById(R.id.viewSwitcherMovieLength);
        viewSwitcherMovieReplacementCost = (ViewSwitcher) findViewById(R.id.viewSwitcherMovieReplacementCost);
        viewSwitcherMovieRating = (ViewSwitcher) findViewById(R.id.viewSwitcherMovieRating);
        viewSwitcherMovieSpecialFeature = (ViewSwitcher) findViewById(R.id.viewSwitcherMovieSpecialFeature);
        viewSwitcherMovieLastUpdate = (ViewSwitcher) findViewById(R.id.viewSwitcherMovieLastUpdate);
    }

    private void loadMovieDataToTextView(Movie movie){
        textViewMovieTitle.setText(movie.getTitle());
        textViewMovieDescription.setText(movie.getDescription());
        textViewMovieReleaseYear.setText(movie.getReleaseYear());
        textViewMovieLanguageId.setText(String.valueOf(movie.getLanguageId()));
        textViewMovieOrigionalLanguageId.setText(String.valueOf(movie.getOrigionalLanguageId()));
        textViewMovieRentalDuration.setText(String.valueOf(movie.getRentalDuration()));
        textViewMovieRentalRate.setText(String.valueOf(movie.getRentalRate()));
        textViewMovieLength.setText(String.valueOf(movie.getLength()));
        textViewMovieReplacementCost.setText(String.valueOf(movie.getReplacementCost()));
        textViewMovieRating.setText(movie.getRating());
        textViewMovieSpecialFeature.setText(movie.getSpecialFeatures());
        textViewMovieLastUpdate.setText(movie.getLastUpdate());
    }

    private void loadMovieDataToEditText(Movie movie){
        editTextMovieTitle.setText(movie.getTitle());
        editTextMovieDescription.setText(movie.getDescription());
        editTextMovieReleaseYear.setText(movie.getReleaseYear());
        editTextMovieLanguageId.setText(String.valueOf(movie.getLanguageId()));
        editTextMovieOrigionalLanguageId.setText(String.valueOf(movie.getOrigionalLanguageId()));
        editTextMovieRentalDuration.setText(String.valueOf(movie.getRentalDuration()));
        editTextMovieRentalRate.setText(String.valueOf(movie.getRentalRate()));
        editTextMovieLength.setText(String.valueOf(movie.getLength()));
        editTextMovieReplacementCost.setText(String.valueOf(movie.getReplacementCost()));
        editTextMovieRating.setText(movie.getRating());
        editTextMovieSpecialFeature.setText(movie.getSpecialFeatures());
        editTextMovieLastUpdate.setText(movie.getLastUpdate());
    }

    private void changeMovie(){
        if(fabClicked) {
            movie.setTitle(getStringFromEditText(editTextMovieTitle));
            movie.setDescription(getStringFromEditText(editTextMovieDescription));
            movie.setReleaseYear(getStringFromEditText(editTextMovieReleaseYear));
            movie.setLanguageId(getIntegerFromEditText(editTextMovieLanguageId));
            movie.setOrigionalLanguageId(getIntegerFromEditText(editTextMovieOrigionalLanguageId));
            movie.setRentalDuration(getIntegerFromEditText(editTextMovieRentalDuration));
            movie.setRentalRate(getDoubleFromEditText(editTextMovieRentalRate));
            movie.setLength(getIntegerFromEditText(editTextMovieLength));
            movie.setReplacementCost(getDoubleFromEditText(editTextMovieReplacementCost));
            movie.setRating(getStringFromEditText(editTextMovieRating));
            movie.setSpecialFeatures(getStringFromEditText(editTextMovieSpecialFeature));
            movie.setLastUpdate(getStringFromEditText(editTextMovieLastUpdate));

        }
    }

    private void switchView(){
        viewSwitcherMovieTitle.showNext();
        viewSwitcherMovieDescription.showNext();
        viewSwitcherMovieReleaseYear.showNext();
        viewSwitcherMovieLanguageId.showNext();
        viewSwitcherMovieOrigionalLanguageId.showNext();
        viewSwitcherMovieRentalDuration.showNext();
        viewSwitcherMovieRentalRate.showNext();
        viewSwitcherMovieLength.showNext();
        viewSwitcherMovieReplacementCost.showNext();
        viewSwitcherMovieRating.showNext();
        viewSwitcherMovieSpecialFeature.showNext();
        viewSwitcherMovieLastUpdate.showNext();
    }
}
