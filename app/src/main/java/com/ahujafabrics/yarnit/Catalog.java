package com.ahujafabrics.yarnit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.ahujafabrics.yarnit.Activity.CatalogItemView;
import com.ahujafabrics.yarnit.Repository.CartItem;
import com.ahujafabrics.yarnit.Repository.CartLineItem;
import com.ahujafabrics.yarnit.Repository.ShadeCard;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Catalog extends AppCompatActivity {

    private Context mContext;

    private RecyclerView mRecyclerView;

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
        catalogItemAdapter = new CatalogItemView(mContext,catalogList);

        // Set the adapter for RecyclerView
        mRecyclerView.setAdapter(catalogItemAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.checkoutmenu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        Intent i;
        switch (item.getItemId()){
            case R.id.checkout_menu:
                i = new Intent(this, CartSummary.class);
                CartLineItem cli = new CartLineItem("S/1", 30);
                ArrayList<CartLineItem> cliList = new ArrayList<>();
                cliList.add(cli);
                i.putExtra("Selected", new CartItem("Thread", cliList));
                startActivity(i);
                Toast.makeText(this, "Order Submitted", Toast.LENGTH_LONG).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
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