package com.example.jeffr.tentamenprogrammeren4;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Package: Android
 * Created by Jeffry on 14-6-2017.
 */

public class Movie implements Serializable {

    private int filmId;
    private String title;
    private String description;
    private String releaseYear;
    private int languageId;
    private int origionalLanguageId;
    private int rentalDuration;
    private Double rentalRate;
    private int length;
    private Double replacementCost;
    private String rating;
    private String specialFeatures;
    private String lastUpdate;

    public Movie(int filmId, String title, String description, String releaseYear, int languageId, int origionalLanguageId, int rentalDuration, Double rentalRate, int length, Double replacementCost, String rating, String specialFeatures, String lastUpdate) {
        this.filmId = filmId;
        this.title = title;
        this.description = description;
        this.releaseYear = releaseYear;
        this.languageId = languageId;
        this.origionalLanguageId = origionalLanguageId;
        this.rentalDuration = rentalDuration;
        this.rentalRate = rentalRate;
        this.length = length;
        this.replacementCost = replacementCost;
        this.rating = rating;
        this.specialFeatures = specialFeatures;
        this.lastUpdate = lastUpdate;
    }

    public Movie() {
        this.filmId = 0;
        this.title = "";
        this.description = "";
        this.releaseYear = "";
        this.languageId = 0;
        this.origionalLanguageId = 0;
        this.rentalDuration = 0;
        this.rentalRate = 0.00;
        this.length = 0;
        this.replacementCost = 0.00;
        this.rating = "";
        this.specialFeatures = "";
        this.lastUpdate = "";
    }

    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    public int getLanguageId() {
        return languageId;
    }

    public void setLanguageId(int languageId) {
        this.languageId = languageId;
    }

    public int getOrigionalLanguageId() {
        return origionalLanguageId;
    }

    public void setOrigionalLanguageId(int origionalLanguageId) {
        this.origionalLanguageId = origionalLanguageId;
    }

    public int getRentalDuration() {
        return rentalDuration;
    }

    public void setRentalDuration(int rentalDuration) {
        this.rentalDuration = rentalDuration;
    }

    public Double getRentalRate() {
        return rentalRate;
    }

    public void setRentalRate(Double rentalRate) {
        this.rentalRate = rentalRate;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Double getReplacementCost() {
        return replacementCost;
    }

    public void setReplacementCost(Double replacementCost) {
        this.replacementCost = replacementCost;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getSpecialFeatures() {
        return specialFeatures;
    }

    public void setSpecialFeatures(String specialFeatures) {
        this.specialFeatures = specialFeatures;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public static JSONObject toJSONObject(Movie movie){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("film_id", movie.getFilmId());
            jsonObject.put("title", movie.getTitle());
            jsonObject.put("description", movie.getDescription());
            jsonObject.put("release_year", movie.getReleaseYear());
            jsonObject.put("language_id", movie.getLanguageId());
            jsonObject.put("original_language_id", (movie.getOrigionalLanguageId() == 0) ? null : movie.getOrigionalLanguageId());
            jsonObject.put("rental_duration", movie.getRentalDuration());
            jsonObject.put("rental_rate", movie.getRentalRate());
            jsonObject.put("length", movie.getLength());
            jsonObject.put("replacement_cost", movie.getReplacementCost());
            jsonObject.put("rating", movie.getRating());
            jsonObject.put("special_features", movie.getSpecialFeatures());
            jsonObject.put("last_update", movie.getLastUpdate());

            return jsonObject;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Movie fromJSONObject(JSONObject jsonObject){
        try {
            Movie movie = new Movie();
            movie.setFilmId(jsonObject.getInt("film_id"));
            movie.setTitle(jsonObject.getString("title"));
            movie.setDescription(jsonObject.getString("description"));
            movie.setReleaseYear(jsonObject.getString("release_year"));
            movie.setLanguageId(jsonObject.getInt("language_id"));
            movie.setOrigionalLanguageId((jsonObject.get("original_language_id").toString().equals("null") ? 0 : jsonObject.getInt("original_language_id")));
            movie.setRentalDuration(jsonObject.getInt("rental_duration"));
            movie.setRentalRate(jsonObject.getDouble("rental_rate"));
            movie.setLength(jsonObject.getInt("length"));
            movie.setReplacementCost(jsonObject.getDouble("replacement_cost"));
            movie.setRating(jsonObject.getString("rating"));
            movie.setSpecialFeatures(jsonObject.getString("special_features"));
            movie.setLastUpdate(jsonObject.getString("last_update"));
            return movie;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static ArrayList<Movie> fromJSONArray(JSONArray jsonArray){
        ArrayList<Movie> movies = new ArrayList<>();

        for(int i = 0; i<jsonArray.length(); i++){
            try {
                movies.add(Movie.fromJSONObject(jsonArray.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return movies;
    }
}
