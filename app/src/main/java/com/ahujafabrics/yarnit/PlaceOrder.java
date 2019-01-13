package com.ahujafabrics.yarnit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ahujafabrics.yarnit.Repository.Order;
import com.ahujafabrics.yarnit.Repository.OrderItem;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class PlaceOrder extends AppCompatActivity{
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private EditText qty;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference().child("OrdersNew");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.checkoutmenu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.checkout_menu:
                submitOrder();
                Toast.makeText(this, "Order Submitted", Toast.LENGTH_LONG).show();
                clean();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void submitOrder(){
        qty = findViewById(R.id.qty1);
        String quantity = qty.getText().toString();
        OrderItem orderitem = new OrderItem(
                "1",
                "Thread",
                "S1",
                Integer.parseInt(quantity)
        );
        Order order = new Order(
                "1",
                "ishwant",
                new Date().getTime(),
                Arrays.asList(orderitem),
                Order.OrderStatus.Submitted
        );
        mDatabaseReference.push().setValue(order);
    }

    public void clean(){
        qty.setText("");
    }
}
