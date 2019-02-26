package com.ahujafabrics.yarnit.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.chip.Chip;
import android.support.design.chip.ChipGroup;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;

import com.ahujafabrics.yarnit.R;
import com.ahujafabrics.yarnit.Repository.ShadeCard;

import java.util.ArrayList;
import java.util.List;

public class ShadeCatalog  extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    AutoCompleteTextView searchShade;
    Button addShadeButton;
    Spinner qtySpinner;
    ChipGroup shadeChipGroup;
    private List<String> shadesList;
    private List<ShadeCard> selectedShades;

    private String selectedQty;

    private static final String TAG = "ShadeCatalog";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shadecatalog);

        addShadeButton = findViewById(R.id.addshade);
        shadeChipGroup = findViewById(R.id.selectedShadeChipsGroup);
        qtySpinner = (Spinner) findViewById(R.id.QtyDropDown);
        searchShade = findViewById(R.id.searchShade);

        addShadeButton.setOnClickListener(this);

        shadesList = setShades();
        selectedShades = new ArrayList<>();
        selectedShades = testshades();

        ArrayAdapter<CharSequence> qtySpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.Qty, android.R.layout.simple_spinner_item);
        qtySpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        qtySpinner.setAdapter(qtySpinnerAdapter);
        qtySpinner.setOnItemSelectedListener(this);


        ArrayAdapter<String> autofillAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, shadesList);
        searchShade.setAdapter(autofillAdapter);

        Chip chip = new Chip(this);
        chip.setText("your...text");
        chip.setCloseIconVisible(true);
        //chip.setCloseIconResource(R.drawable.your_icon);
        //chip.setChipIconResource(R.drawable.your_icon);
        chip.setChipBackgroundColorResource(R.color.shadeChip);
        //chip.setTextAppearanceResource(R.style.ChipTextStyle);
        //chip.setElevation(15);

        shadeChipGroup.addView(chip);
    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()) {
            case R.id.addshade: {
                //add the value to selectedShades
                String selectedShade = ((AutoCompleteTextView)findViewById(R.id.searchShade))
                                        .getText().toString();
                Log.d(TAG, "Shade: "+ selectedShade + "  Qty: "+selectedQty );
                selectedShades.add(new ShadeCard(selectedShade, selectedQty));

                Chip chip = new Chip(this);
                chip.setText(selectedShade);
                chip.setCloseIconVisible(true);
                //chip.setBackgroundColor(getResources().getColor(R.color.shadeChip));
                shadeChipGroup.addView(chip);

            }
            break;

            default:
                break;
        }
    }

    private List<String> setShades(){
        List<String> shadesList = new ArrayList<>();

        for(int i=1; i<=424; i++){
            String temp = Integer.toString(i);
            shadesList.add(temp);
        }
        return shadesList;
    }

    private List<ShadeCard> testshades(){
        List<ShadeCard> shadesList = new ArrayList<>();

        for(int i=1; i<=4; i++){
            String temp = "S/" + Integer.toString(i);
            shadesList.add(new ShadeCard(temp));
        }
        return shadesList;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selectedQty = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}