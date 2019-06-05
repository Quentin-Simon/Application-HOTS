package com.example.hots_application.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.hots_application.Injection;
import com.example.hots_application.R;
import com.example.hots_application.controller.MainController;
import com.example.hots_application.model.HotsPlayers;
import com.example.hots_application.view.MyAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainController controller = new MainController(this, Injection.getRestApiInstance());
        controller.start();
    }
    public void showList(List<HotsPlayers> hotsPlayersList){
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        // use this setting to
        // improve performance if you know that changes
        // in content do not change the layout size
        // of the RecyclerView
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);

        // define an adapter
        mAdapter = new MyAdapter(hotsPlayersList);
        recyclerView.setAdapter(mAdapter);
    }
}