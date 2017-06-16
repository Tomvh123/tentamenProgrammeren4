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
    public static final String MOVIE_RESULT = "result";
    public static final String MOVIE_TITLE = "title";


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
                        jsonObject.getString(MOVIE_TITLE)
                 //       jsonObject.getString(TODO_DESCRIPTION),
                 //       jsonObject.getString(TODO_STATUS),
                        //todoDateTime
                );
                // Log.i("ToDoMapper", "ToDo: " + toDo);
                result.add(film);
                Log.d("film", film.getTitle());
            }
        } catch( JSONException ex) {
            Log.e("MovieMapper", "onPostExecute JSONException " + ex.getLocalizedMessage());
        }
        return result;
    }
}
