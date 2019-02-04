package com.ahujafabrics.yarnit;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ahujafabrics.yarnit.Activity.CatalogItemView;
import com.ahujafabrics.yarnit.Repository.ShadeCard;

import java.util.LinkedList;
import java.util.List;

public class Catalog extends AppCompatActivity {

    private Context mContext;

    RelativeLayout mRelativeLayout;
    private RecyclerView mRecyclerView;

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static List<ShadeCard> catalogList;
    private CatalogItemView catalogItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView( R.layout.activity_catalog );
        mContext = getApplicationContext();

        catalogList = setShades();

        // Get gridview object from xml file
        mRecyclerView = (RecyclerView) findViewById(R.id.catalogGrid);

        mLayoutManager = new GridLayoutManager(mContext,3);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // Initialize a new instance of RecyclerView Adapter instance
        mAdapter = new CatalogItemView(mContext,catalogList);

        // Set the adapter for RecyclerView
        mRecyclerView.setAdapter(mAdapter);

    }

    private List<ShadeCard> setShades(){
        List<ShadeCard> shadesList = new LinkedList<>();

        for(int i=1; i<=400; i++){
            String temp = "S/" + Integer.toString(i);
            shadesList.add(new ShadeCard(temp, i));
        }
        return shadesList;
    }
}