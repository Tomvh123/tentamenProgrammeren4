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
 * Created by jeffr on 16-6-2017.
 */

public class RentalAdapter extends ArrayAdapter<Film> {

    public static final String TAG = RentalAdapter.class.getSimpleName();
    private Context mContext;
    private ArrayList<Film> items;


    private static class ViewHolder {
        TextView title, return_date, available;


    }

    public RentalAdapter(Context context, ArrayList<Film> items){
        super(context, R.layout.activity_rentalrow, items);
        this.mContext = context;
        this.items = items;
        Log.d(TAG, "Consturctor called");
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d(TAG, "getView Called");
        Film item = items.get(position);

        ViewHolder viewHolder;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(R.layout.activity_rentalrow, parent, false);

            viewHolder.title = (TextView) convertView.findViewById(R.id.rentaltitle);
            viewHolder.return_date = (TextView) convertView.findViewById(R.id.returndate) ;
            viewHolder.available = (TextView) convertView.findViewById(R.id.available);


            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.title.setText(item.getTitle());
        Log.d(TAG, item.getReturn_date());


        if (item.getReturn_date() == "null"){
            viewHolder.return_date.setText("Rental duration: " + item.getRental_rate() + " Rental rate: "+item.getRental_rate());
        }else{
            viewHolder.return_date.setText("Return date: " + item.getReturn_date());
        }


        Integer rental_id = item.getRental_id();
        if (rental_id != 0){
            viewHolder.available.setText("Unavailable");
        }else{
            viewHolder.available.setText("Available");
        }


        return convertView;
    }
}
