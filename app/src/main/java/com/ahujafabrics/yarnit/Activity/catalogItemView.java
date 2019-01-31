package com.ahujafabrics.yarnit.Activity;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.ahujafabrics.yarnit.R;

import java.util.List;

public class catalogItemView extends BaseAdapter {

    private Context context;
    private final List shadeGridValues;

    public catalogItemView(Context context, List shadeGridValues) {

        this.context          = context;
        this.shadeGridValues  = shadeGridValues;
    }
    @Override
    public int getCount() {
        return shadeGridValues.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null) {

            gridView = new View(context);

            // get layout from grid_item.xml ( Defined Below )

            gridView = inflater.inflate( R.layout.catalogitem , null);

            // set value into textview

            TextView textView = (TextView) gridView
                    .findViewById(R.id.shade);

            textView.setText((String) shadeGridValues.get(position));

        } else {

            gridView = (View) convertView;
        }

        return gridView;
    }
}
