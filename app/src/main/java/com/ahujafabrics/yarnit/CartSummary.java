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

import com.ahujafabrics.yarnit.Activity.CartItemView;
import com.ahujafabrics.yarnit.Controllers.OrderController;
import com.ahujafabrics.yarnit.Repository.CartItem;
import com.ahujafabrics.yarnit.Repository.CartLineItem;
import com.ahujafabrics.yarnit.Repository.Order;
import com.ahujafabrics.yarnit.Repository.OrderItem;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CartSummary extends AppCompatActivity {

    private Context mContext;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private CartItemView cartItemAdapter;

    private CartItem cartItem;
    private OrderController orderController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView( R.layout.activity_cartsummary );
        mContext = getApplicationContext();
        orderController = new OrderController();

        // Get gridview object from xml file
        mRecyclerView = (RecyclerView) findViewById(R.id.cartItems);

        mLayoutManager = new GridLayoutManager(mContext,1);
        mRecyclerView.setLayoutManager(mLayoutManager);

        Intent intent = getIntent();
        cartItem = intent.getParcelableExtra("SelectedShades");

        // Initialize a new instance of RecyclerView Adapter instance
        cartItemAdapter = new CartItemView(mContext,cartItem.getCartLineItems());

        // Set the adapter for RecyclerView
        mRecyclerView.setAdapter(cartItemAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.placeordermenu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        Intent i;
        switch (item.getItemId()){
            case R.id.placeOrder_menu:
                orderController.submitOrder(cartItem);
                Toast.makeText(this, "Order Submitted", Toast.LENGTH_LONG).show();
                i = new Intent(this, Dashboard.class);
                startActivity(i);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


}