package com.ahujafabrics.yarnit.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.ahujafabrics.yarnit.Adapter.ViewOrderDetailsAdapter;
import com.ahujafabrics.yarnit.R;
import com.ahujafabrics.yarnit.Repository.Order;

public class ViewOrderDetails extends AppCompatActivity {

    private static final String TAG = "ViewOrderDetails";
    private Order order;
    TextView userId, orderDate;
    private RecyclerView mRecyclerView;
    private Context mContext;

    private ViewOrderDetailsAdapter viewOrderDetailsAdapterAdapter;

    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        order = intent.getParcelableExtra("SelectedOrder");

        setContentView( R.layout.activity_orderdetails );
        mContext = getApplicationContext();

        userId = findViewById(R.id.userID);
        orderDate = findViewById(R.id.orderDate);

        userId.setText(order.getUserID());
        orderDate.setText(order.getCreationDate());

        mRecyclerView = (RecyclerView) findViewById(R.id.orderItems);

        mLayoutManager = new GridLayoutManager(mContext,1);
        mRecyclerView.setLayoutManager(mLayoutManager);

        viewOrderDetailsAdapterAdapter = new ViewOrderDetailsAdapter(mContext, order.getOrderLineItems());

        mRecyclerView.setAdapter(viewOrderDetailsAdapterAdapter);

    }
}
