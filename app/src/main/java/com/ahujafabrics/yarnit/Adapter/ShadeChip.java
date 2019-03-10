package com.ahujafabrics.yarnit.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.chip.Chip;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ahujafabrics.yarnit.R;
import com.ahujafabrics.yarnit.Repository.ShadeCard;

import java.util.List;

public class ShadeChip extends RecyclerView.Adapter<ShadeChip.ViewHolder> {

    private Context context;
    private final List<ShadeCard> selectedShades;

    private static final String TAG = "ShadeChip";

    public ShadeChip(Context context, List<ShadeCard> selectedShades) {
        this.context = context;
        this.selectedShades = selectedShades;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public Chip shadeChip;

        public ViewHolder(View v){
            super(v);
            shadeChip = v.findViewById(R.id.shadeChiptemplate);
        }
    }

    @Override
    public ShadeChip.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View v = inflater.inflate( R.layout.shadechip , null);
        ShadeChip.ViewHolder vh = new ShadeChip.ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(ShadeChip.ViewHolder holder, int position) {
        Log.d(TAG, "Selected Shade Recycler " + selectedShades.get(position).getShade());
        holder.shadeChip.setText(selectedShades.get(position).getShade());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return selectedShades.size();
        //return 0;
    }

}
