package com.ahujafabrics.yarnit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class Dashboard extends AppCompatActivity implements View.OnClickListener {
    private CardView cartCard, profileCard, viewOrderCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        cartCard = (CardView) findViewById(R.id.orderId);
        profileCard = (CardView) findViewById(R.id.profilId);
        viewOrderCard = (CardView) findViewById(R.id.viewId);

        cartCard.setOnClickListener(this);
        profileCard.setOnClickListener(this);
        viewOrderCard.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        Intent i ;

        switch (v.getId()){
            case R.id.orderId:
                //i = new Intent(this, PlaceOrder.class);
                i = new Intent(this, Catalog.class);
                startActivity(i);
                break;
            case R.id.profilId :
                i = new Intent(this, Profile.class);
                startActivity(i);
                break;
            case R.id.viewId :
                i = new Intent(this, ViewOrder.class);
                startActivity(i);
                break;

            default:break;
        }
    }
}
