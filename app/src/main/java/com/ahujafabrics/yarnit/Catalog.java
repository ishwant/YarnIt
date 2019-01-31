package com.ahujafabrics.yarnit;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.ahujafabrics.yarnit.Activity.catalogItemView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class Catalog extends Activity {

    GridView catalogGridView;

    private static List<String> catalogList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        catalogList = setShades();

        setContentView( R.layout.activity_catalog );

        // Get gridview object from xml file

        catalogGridView = (GridView) findViewById(R.id.catalogGrid);

        // Set custom adapter (GridAdapter) to gridview

        catalogGridView.setAdapter(  new catalogItemView( this, catalogList ) );

        catalogGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {


                Toast.makeText(
                        getApplicationContext(),
                        ((TextView) v.findViewById( R.id.shade ))
                                .getText(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private List<String> setShades(){
        List<String> shadesList = new LinkedList<>();

        for(int i=0; i<100; i++){
            String temp = "S/" + Integer.toString(i);
            shadesList.add(temp);
        }
        return shadesList;
    }
}