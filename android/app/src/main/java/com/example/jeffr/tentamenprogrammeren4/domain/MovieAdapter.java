package com.example.jeffr.tentamenprogrammeren4.domain;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.jeffr.tentamenprogrammeren4.R;

import java.util.ArrayList;

/**
 * Created by tom on 16-6-2017.
 */

public class MovieAdapter extends ArrayAdapter<Movie> {

    public static final String TAG = MovieAdapter.class.getSimpleName();
    private Context mContext;
    private ArrayList<Movie> items;

    private static class ViewHolder{
        TextView test;
    }

    public MovieAdapter(Context context, ArrayList<Movie> items){
        super(context, R.layout.activity_movierow, items);
        this.mContext = context;
        this.items = items;
        Log.d(TAG, "Consturctor called");
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d(TAG, "getView Called");
        Movie item = items.get(position);

        ViewHolder viewHolder;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(R.layout.activity_movierow, parent, false);

            viewHolder.test = (TextView) convertView.findViewById(R.id.test);


            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.test.setText(item.getTitle());

        return convertView;
    }
}
