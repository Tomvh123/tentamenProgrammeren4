package com.example.jeffr.tentamenprogrammeren4.domain;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by tom on 16-6-2017.
 */

public class MovieMapper {

    // De JSON attributen die we uitlezen
    public static final String MOVIE_ID = "film_id";
    public static final String MOVIE_RESULT = "result";
    public static final String MOVIE_TITLE = "title";
    public static final String MOVIE_DESCRIPTION = "description";
    public static final String RELEASE_YEAR = "release_year";
    public static final String LANGUAGE_ID = "language_id";
    public static final String ORIGINALLANGUAGE = "original_language_id";
    public static final String RENTAL_DURATION = "rental_duration";
    public static final String RENTAL_RATE = "rental_rate";
    public static final String LENGTH = "length";
    public static final String REPLACEMENT_COST = "replacement_cost";
    public static final String RATING = "rating";
    public static final String SPECIAL_FEATURES = "special_features";
    public static final String LAST_UPDATE = "last_update";
    public static final String INVENTORY_ID = "inventory_id";



    /**
     * Map het JSON response op een arraylist en retourneer deze.
     */
    public static ArrayList<Film> mapMovieList(JSONObject response){

        ArrayList<Film> result = new ArrayList<>();

        try{
            JSONArray jsonArray = response.getJSONArray(MOVIE_RESULT);

            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                // Convert stringdate to Date
                //String timestamp = jsonObject.getString(TODO_UPDATED_AT);
                //DateTime todoDateTime = ISODateTimeFormat.dateTimeParser().parseDateTime(timestamp);

                Film film = new Film(
                        jsonObject.getInt(MOVIE_ID),
                        jsonObject.getString(MOVIE_TITLE),
                        jsonObject.getString(MOVIE_DESCRIPTION),
                        jsonObject.getInt(RELEASE_YEAR),
                        jsonObject.getInt(LANGUAGE_ID),

                        jsonObject.getInt(RENTAL_DURATION),
                        jsonObject.getInt(RENTAL_RATE),
                        jsonObject.getInt(LENGTH),
                        jsonObject.getInt(REPLACEMENT_COST),
                        jsonObject.getString(RATING),
                        jsonObject.getString(SPECIAL_FEATURES),
                        jsonObject.getString(LAST_UPDATE)


                 //       jsonObject.getString(TODO_STATUS),
                        //todoDateTime
                );
                result.add(film);
                Log.d("film", film.getTitle());
            }
        } catch( JSONException ex) {
            Log.e("MovieMapper", "onPostExecute JSONException " + ex.getLocalizedMessage());
        }
        return result;
    }
}
