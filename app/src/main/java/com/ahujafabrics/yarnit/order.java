package com.ahujafabrics.yarnit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.checkoutmenu, menu);
        return true;

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
