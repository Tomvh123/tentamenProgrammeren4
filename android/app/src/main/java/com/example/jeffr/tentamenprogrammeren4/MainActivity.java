package com.example.jeffr.tentamenprogrammeren4;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, AdapterView.OnItemClickListener {

    public final String TAG = this.getClass().getSimpleName();

    public final static String MOVIE_DATA = "MOVIE";

    public static final int MY_REQUEST_CODE = 1234;

    private ListView listView;
    private BaseAdapter movieAdapter;
    private ArrayList<Movie> movies = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(tokenAvailable()){
            setContentView(R.layout.activity_main);

            listView = (ListView) findViewById(R.id.listview);
            listView.setOnItemClickListener(this);
            
        }



    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
