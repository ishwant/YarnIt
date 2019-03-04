package com.ahujafabrics.yarnit.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.chip.Chip;
import android.support.design.chip.ChipGroup;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;

import com.ahujafabrics.yarnit.R;
import com.ahujafabrics.yarnit.Repository.CartItem;
import com.ahujafabrics.yarnit.Repository.CartLineItem;
import com.ahujafabrics.yarnit.Repository.ShadeCard;

import java.util.ArrayList;
import java.util.List;

public class ShadeCatalog  extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    AutoCompleteTextView searchShade;
    Button addShadeButton;
    Spinner qtySpinner;
    ChipGroup shadeChipGroup;
    ArrayAdapter<CharSequence> qtySpinnerAdapter;
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
        //selectedShades = testshades();

        qtySpinnerAdapter = ArrayAdapter.createFromResource(this,
                                    R.array.Qty, android.R.layout.simple_spinner_item);
        qtySpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        qtySpinner.setAdapter(qtySpinnerAdapter);
        qtySpinner.setOnItemSelectedListener(this);
        qtySpinner.setDropDownHorizontalOffset(10);

        ArrayAdapter<String> autofillAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, shadesList);
        searchShade.setAdapter(autofillAdapter);

    /*    Chip chip = new Chip(this);
        chip.setText("your...text");
        chip.setCloseIconVisible(true);
        //chip.setCloseIconResource(R.drawable.your_icon);
        //chip.setChipIconResource(R.drawable.your_icon);
        chip.setChipBackgroundColorResource(R.color.shadeChip);
        //chip.setTextAppearanceResource(R.style.ChipTextStyle);
        //chip.setElevation(15);

        shadeChipGroup.addView(chip); */
    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()) {
            case R.id.addshade: {
                if(searchShade.getText().toString().equals("")){
                    AlertDialog.Builder noProfileAlert = new AlertDialog.Builder(this);
                    noProfileAlert.setMessage(R.string.EmptySearchShade)
                            .setCancelable(false)
                            .setPositiveButton(R.string.Ok,new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            }) ;
                    AlertDialog alert = noProfileAlert.create();
                    alert.setTitle("Alert!");
                    alert.show();
                }
                else {
                    //add the value to selectedShades
                    final String selectedShade = "S/" + searchShade.getText().toString();
                    selectedQty = qtySpinner.getSelectedItem().toString();
                    Log.d(TAG, "Shade: " + selectedShade + "  Qty: " + selectedQty);
                    selectedShades.add(new ShadeCard(selectedShade, selectedQty));

                    final Chip chip = new Chip(this);
                    chip.setText(selectedShade);
                    chip.setCloseIconVisible(true);
                    chip.setChipBackgroundColorResource(R.color.shadeChip);
                    chip.setOnCloseIconClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //Remove the shade from the cart
                            for (ShadeCard sc : selectedShades) {
                                if (sc.getShade().equals(chip.getText().toString())) {
                                    selectedShades.remove(sc);
                                    break;
                                }
                            }

                            //Remove the selected chip
                            shadeChipGroup.removeView(chip);
                        }
                    });
                    shadeChipGroup.addView(chip);

                    //Clear the search shade and Qty fields
                    searchShade.getText().clear();
                    qtySpinner.setSelection(qtySpinnerAdapter.getPosition("1"));
                }
            }
            break;

            default:
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.checkoutmenu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        Intent i;
        switch (item.getItemId()){
            case R.id.checkout_menu:
                i = new Intent(this, CartSummary.class);
                i.putExtra("SelectedShades", new CartItem("Thread",
                        convertShadeCardstoCartItems(selectedShades)));
                startActivityForResult(i, 1);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public ArrayList<CartLineItem> convertShadeCardstoCartItems(List<ShadeCard> selectedShades){
        ArrayList<CartLineItem> cartLineItems = new ArrayList<>();
        for (ShadeCard sc:selectedShades
                ) {
            if(!sc.getQty().equals("")) {
                cartLineItems.add(new CartLineItem(sc.getShade(),Integer.parseInt(sc.getQty())));
            }
        }
        return cartLineItems;
    }

    private List<String> setShades(){
        List<String> shadesList = new ArrayList<>();

        for(int i=1; i<=424; i++){
            String temp = Integer.toString(i);
            shadesList.add(temp);
        }
        return shadesList;
    }

    /*private List<ShadeCard> testshades(){
        List<ShadeCard> shadesList = new ArrayList<>();

        for(int i=1; i<=4; i++){
            String temp = "S/" + Integer.toString(i);
            shadesList.add(new ShadeCard(temp));
        }
        return shadesList;
    } */

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selectedQty = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}