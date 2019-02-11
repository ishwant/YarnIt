package com.ahujafabrics.yarnit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ahujafabrics.yarnit.Activity.CartItemView;
import com.ahujafabrics.yarnit.Repository.CartItem;

public class CartSummary extends AppCompatActivity {

    private Context mContext;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private CartItemView cartItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView( R.layout.activity_cartsummary );
        mContext = getApplicationContext();

        // Get gridview object from xml file
        mRecyclerView = (RecyclerView) findViewById(R.id.cartItems);

        mLayoutManager = new GridLayoutManager(mContext,1);
        mRecyclerView.setLayoutManager(mLayoutManager);

        Intent intent = getIntent();
        CartItem cartItem = intent.getParcelableExtra("Selected");

        // Initialize a new instance of RecyclerView Adapter instance
        cartItemAdapter = new CartItemView(mContext,cartItem.getCartLineItems());

        // Set the adapter for RecyclerView
        mRecyclerView.setAdapter(cartItemAdapter);

    }
}