package com.ahujafabrics.yarnit;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.ahujafabrics.yarnit.Repository.SQLiteHelper;
import com.ahujafabrics.yarnit.Repository.UserProfile;


public class Profile extends AppCompatActivity implements View.OnClickListener{

    SQLiteHelper dbHelper;
    FloatingActionButton updateProfilebtn;

    EditText fname;
    EditText address1;
    EditText address2;
    EditText city;
    EditText contact;
    EditText email;
    Spinner role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        dbHelper = new SQLiteHelper(this);

        updateProfilebtn = findViewById(R.id.updateProfile);
        fname = findViewById(R.id.nameId);
        address1 = findViewById(R.id.address1);
        address2 = findViewById(R.id.address2);
        city = findViewById(R.id.city);
        contact = findViewById(R.id.contact);
        email = findViewById(R.id.email);
        role = findViewById(R.id.roleSpinner);

        //check if the profile exists
        if(dbHelper.checkIfProfileExists()) {

            System.out.print("entered getProfile");
            updateProfilebtn.setImageResource(R.drawable.ic_edit_black_24dp);

            Cursor profileData = dbHelper.getProfile();
            profileData.moveToFirst();
            fname.setText(profileData.getString(profileData.getColumnIndex("UserName")));
            address1.setText(profileData.getString(profileData.getColumnIndex("Address1")));
            address2.setText(profileData.getString(profileData.getColumnIndex("Address2")));
            city.setText(profileData.getString(profileData.getColumnIndex("City")));
            contact.setText(profileData.getString(profileData.getColumnIndex("Phone")));
            email.setText(profileData.getString(profileData.getColumnIndex("Email")));
        }
        else{
            updateProfilebtn.setImageResource(R.drawable.ic_check_black_24dp);
        }
        updateProfilebtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        Intent i;

        switch (v.getId()){
            case R.id.updateProfile:
                //check the button from edit to save

                if(dbHelper.checkIfProfileExists()){

                    updateProfilebtn.setImageResource(R.drawable.ic_check_black_24dp);
                    updateProfilebtn.setBackgroundTintList(ColorStateList.valueOf(
                            ContextCompat.getColor(getApplicationContext(), R.color.deeppurple)));
                }
                else{

                    if(fname.getText().toString().equals("") ||
                        address1.getText().toString().equals("")||
                            address2.getText().toString().equals("")||
                            city.getText().toString().equals("")||
                            contact.getText().toString().equals("")||
                            email.getText().toString().equals(""))
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
                        Toast.makeText(this, "Profile Submitted", Toast.LENGTH_LONG).show();
                    }
                }
                break;

            default:break;
        }
    }
}
