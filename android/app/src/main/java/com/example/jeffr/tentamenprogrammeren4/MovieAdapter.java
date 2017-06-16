package com.example.jeffr.tentamenprogrammeren4;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Package: Android
 * Created by Jeffry on 14-6-2017.
 */

public class MovieAdapter extends ArrayAdapter<Movie> {

    public static final String TAG = MovieAdapter.class.getSimpleName();
    private Context mContext;
    private ArrayList<Movie> items;

    private static class ViewHolder {
        TextView cost;
        TextView movieName;
        ImageView moviePhoto;
    }

    public MovieAdapter(Context context, ArrayList<Movie> items) {
        super(context, R.layout.view_movie, items);
        this.mContext = context;
        this.items = items;
        Log.d(TAG, "Constructor called");
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Movie item = items.get(position);

        ViewHolder viewHolder;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(R.layout.view_movie, parent, false);

            viewHolder.cost = (TextView) convertView.findViewById(R.id.Cost);
            viewHolder.movieName = (TextView) convertView.findViewById(R.id.MovieName);
            viewHolder.moviePhoto = (ImageView) convertView.findViewById(R.id.MoviePhoto);


            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.movieName.setText(item.getTitle());

        return convertView;
    }
}