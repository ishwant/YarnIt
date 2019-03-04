package com.ahujafabrics.yarnit.Activity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.ahujafabrics.yarnit.R;
import com.ahujafabrics.yarnit.Repository.SQLiteHelper;
import com.ahujafabrics.yarnit.Repository.UserProfile;


public class Profile extends AppCompatActivity{

    SQLiteHelper dbHelper;

    EditText fname;
    EditText address1;
    EditText address2;
    EditText city;
    EditText contact;
    EditText email;
    Spinner role;

    Boolean isProfile;

    MenuInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        dbHelper = new SQLiteHelper(this);

        //updateProfilebtn = findViewById(R.id.updateProfile);
        fname = findViewById(R.id.nameId);
        address1 = findViewById(R.id.address1);
        address2 = findViewById(R.id.address2);
        city = findViewById(R.id.city);
        contact = findViewById(R.id.contact);
        email = findViewById(R.id.email);
        role = findViewById(R.id.roleSpinner);
        isProfile = false;
        //check if the profile exists
        if(dbHelper.checkIfProfileExists()) {

            isProfile = true;
            System.out.print("entered getProfile");
            //updateProfilebtn.setImageResource(R.drawable.ic_edit_black_24dp);

            Cursor profileData = dbHelper.getProfile();
            profileData.moveToFirst();
            fname.setText(profileData.getString(profileData.getColumnIndex("UserName")));
            address1.setText(profileData.getString(profileData.getColumnIndex("Address1")));
            address2.setText(profileData.getString(profileData.getColumnIndex("Address2")));
            city.setText(profileData.getString(profileData.getColumnIndex("City")));
            contact.setText(profileData.getString(profileData.getColumnIndex("Phone")));
            email.setText(profileData.getString(profileData.getColumnIndex("Email")));

            fname.setEnabled(false);
            address1.setEnabled(false);
            address2.setEnabled(false);
            city.setEnabled(false);
            contact.setEnabled(false);
            email.setEnabled(false);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        inflater = getMenuInflater();
        inflater.inflate(R.menu.savemenu, menu);
        if(isProfile){
            menu.getItem(0).setIcon(ContextCompat.getDrawable(this, R.drawable.ic_edit_black_24dp));
        }
        else
            menu.getItem(0).setIcon(ContextCompat.getDrawable(this, R.drawable.ic_save_black_24dp));
        return true;

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        Intent i;

        switch (item.getItemId()){
            case R.id.save_menu:

                if(isProfile){
                    fname.setEnabled(true);
                    address1.setEnabled(true);
                    address2.setEnabled(true);
                    city.setEnabled(true);
                    contact.setEnabled(true);
                    email.setEnabled(true);
                    item.setIcon(R.drawable.ic_save_black_24dp);
                    isProfile = false;
                }
                else{
                    if(fname.getText().toString().equals("") ||
                            address1.getText().toString().equals("")||
                            contact.getText().toString().equals(""))
                    {
                        Toast.makeText(this, "Please enter all the required details",
                                Toast.LENGTH_LONG).show();
                    }
                    else{
                        UserProfile user = new UserProfile(
                                fname.getText().toString(),
                                address1.getText().toString(),
                                address2.getText().toString(),
                                city.getText().toString(),
                                Long.parseLong(contact.getText().toString()),
                                email.getText().toString(),
                                "Boutique"
                        );
                        dbHelper.addData(user);
                        item.setIcon(R.drawable.ic_edit_black_24dp);
                        i = new Intent(this, Dashboard.class);
                        startActivity(i);
                        Toast.makeText(this, "Profile Submitted", Toast.LENGTH_LONG).show();
                    }
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
