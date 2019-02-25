package com.ahujafabrics.yarnit.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.ahujafabrics.yarnit.R;

import java.util.ArrayList;
import java.util.List;

public class ShadeCatalog  extends AppCompatActivity implements View.OnClickListener {
    AutoCompleteTextView searchShade;
    Button addShade;
    private List<String> shadesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shadecatalog);

        searchShade = findViewById(R.id.searchShade);
        addShade = findViewById(R.id.addshade);

        shadesList = setShades();

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, shadesList);
        searchShade.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()) {
            case R.id.addshade: {

            }
            break;

            default:
                break;
        }
    }

    private List<String> setShades(){
        List<String> shadesList = new ArrayList<>();

        for(int i=1; i<=424; i++){
            String temp = Integer.toString(i);
            shadesList.add(temp);
        }
        return shadesList;
    }


}