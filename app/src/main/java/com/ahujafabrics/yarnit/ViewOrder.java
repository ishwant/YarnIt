package com.ahujafabrics.yarnit;

import android.content.Context;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.ahujafabrics.yarnit.Activity.ViewOrderItem;
import com.ahujafabrics.yarnit.Repository.Order;
import com.ahujafabrics.yarnit.Repository.OrderItem;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewOrder extends AppCompatActivity {

    private static final String TAG = "ViewOrders";

    private Context mContext;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private ViewOrderItem viewOrderItemAdapter;

    private static ArrayList<Order> ordersList;
    private Order order;

    private FirebaseDatabase mFirebaseDatabase;
    private static DatabaseReference ordersDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView( R.layout.activity_vieworder );
        mContext = getApplicationContext();

        // Get gridview object from xml file
        mRecyclerView = findViewById(R.id.ordersList);

        mLayoutManager = new GridLayoutManager(mContext,1);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        ordersDb = mFirebaseDatabase.getReference().child("OrdersNew");

        ordersDb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                getOrdersList(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getMessage());
            }
        });
    }

    public void getOrdersList(DataSnapshot dataSnapshot){

        ordersList = new ArrayList<>();

        for(DataSnapshot ds : dataSnapshot.getChildren()){
            Order order = new Order();
                    /*ds.child("orderID").getValue(String.class),
                    ds.child("userID").getValue(String.class),
                    ds.child("creationDate").getValue(long.class),
                    ds.child("orderLineItems").getValue(List.class),
                    ds.child("orderStatus").getValue(Order.OrderStatus.class)); */

            order.setOrderID(ds.child("orderID").getValue(String.class));
            order.setCreationDate(ds.child("creationDate").getValue(String.class));
            order.setUserID(ds.child("userID").getValue(String.class));
            order.setOrderStatus(ds.child("orderStatus").getValue(Order.OrderStatus.class));

            List<OrderItem> orderItemList = new ArrayList<>();

            for(DataSnapshot os : ds.child("orderLineItems").getChildren()){
                /*OrderItem oi = new OrderItem(
                        ds.child("orderLineItemID").getValue(Integer.class),
                        ds.child("productType").getValue(String.class),
                        ds.child("shadeId").getValue(String.class),
                        ds.child("quantity").getValue(Integer.class)
                ); */
                OrderItem oi = new OrderItem();
                oi.setOrderLineItemID(os.child("orderLineItemID").getValue(Integer.class));
                oi.setProductType(os.child("productType").getValue(String.class));
                oi.setQuantity(os.child("quantity").getValue(Integer.class));
                oi.setShadeId(os.child("shadeId").getValue(String.class));

                orderItemList.add(oi);
            }
            GenericTypeIndicator<List<OrderItem>> genericOrderItemList = new GenericTypeIndicator<List<OrderItem>>() {};

            order.setOrderLineItems(ds.child("orderLineItems").getValue(genericOrderItemList));
                    //ds.getValue(Order.class);
            ordersList.add(order);
        }
        // Initialize a new instance of RecyclerView Adapter instance
        viewOrderItemAdapter = new ViewOrderItem(mContext, ordersList);

        // Set the adapter for RecyclerView
        mRecyclerView.setAdapter(viewOrderItemAdapter);
    /*    OrderItem oi = new OrderItem(1, "Shantoon", "S/1", 4);
        List<OrderItem> oiList = new ArrayList<>();
        oiList.add(oi);
        Order temp = new Order("1", "Ishwant", 2142019,
                oiList, Order.OrderStatus.Submitted);
        ordersList.add(temp); */

    }

}
