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
import android.view.inputmethod.EditorInfo;
import android.support.v7.widget.SearchView;

import com.ahujafabrics.yarnit.Activity.CatalogItemView;
import com.ahujafabrics.yarnit.Repository.CartItem;
import com.ahujafabrics.yarnit.Repository.CartLineItem;
import com.ahujafabrics.yarnit.Repository.OnCatalogItemClick;
import com.ahujafabrics.yarnit.Repository.ShadeCard;

import java.util.ArrayList;
import java.util.List;

public class Catalog extends AppCompatActivity implements OnCatalogItemClick {

    private Context mContext;

    private RecyclerView mRecyclerView;

    private RecyclerView.LayoutManager mLayoutManager;
    private static List<ShadeCard> catalogList;
    private CatalogItemView catalogItemAdapter;
    private List<ShadeCard> shadeGridValues;

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
        catalogItemAdapter = new CatalogItemView(mContext,catalogList, this);

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
                i.putExtra("SelectedShades", new CartItem("Thread",
                        convertShadeCardstoCartItems(shadeGridValues)));
                startActivity(i);
                return true;

            case R.id.action_search:
                SearchView searchView = (SearchView) item.getActionView();

                searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        catalogItemAdapter.getFilter().filter(newText);
                        return false;
                    }
                });
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private List<ShadeCard> setShades(){
        List<ShadeCard> shadesList = new ArrayList<>();

        for(int i=1; i<=424; i++){
            String temp = "S/" + Integer.toString(i);
            shadesList.add(new ShadeCard(temp));
        }
        return shadesList;
    }

    @Override
    public void onCatalogClick(List<ShadeCard> shadeGridValues) {
        this.shadeGridValues = shadeGridValues;
    }

    public ArrayList<CartLineItem> convertShadeCardstoCartItems(List<ShadeCard> shadeGridValues){
        ArrayList<CartLineItem> cartLineItems = new ArrayList<>();
        for (ShadeCard sc:shadeGridValues
             ) {
            if(!sc.getQty().equals("")) {
                cartLineItems.add(new CartLineItem(sc.getShade(),Integer.parseInt(sc.getQty())));
            }
        }
        return cartLineItems;
    }
}