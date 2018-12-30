package com.ahujafabrics.yarnit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class order extends AppCompatActivity implements Button.OnClickListener{
    private Button checkoutButton;
    private EditText qty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        checkoutButton = findViewById(R.id.checkoutButtonId);
        checkoutButton.setOnClickListener(this);
    }

    public void onClick(View v){

        if(v.getId() == R.id.checkoutButtonId){

            /*for(int i=1; i<=3; i++){
                String quant = "qty"+ i;
                qty = getResources().getIdentifier()
            }*/
            qty = findViewById(R.id.qty1);

            String quantity = qty.getText().toString();

            TextView tv = findViewById(R.id.resultText);
            tv.setText(quantity);
        }


    }
}
