package com.ahujafabrics.yarnit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class Dashboard extends AppCompatActivity implements View.OnClickListener {
    private CardView cartCard, profileCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        cartCard = (CardView) findViewById(R.id.cartId);
        profileCard = (CardView) findViewById(R.id.profilId);

        cartCard.setOnClickListener(this);
        profileCard.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        Intent i ;

        switch (v.getId()){
            case R.id.cartId :
                i = new Intent(this, order.class);
                startActivity(i);
                break;
         //   case R.id.profilId : i = new Intent(this, )

            default:break;
        }
    }
}
