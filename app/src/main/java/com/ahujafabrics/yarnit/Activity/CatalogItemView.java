package com.ahujafabrics.yarnit.Activity;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.ahujafabrics.yarnit.R;
import com.ahujafabrics.yarnit.Repository.OnCatalogItemClick;
import com.ahujafabrics.yarnit.Repository.ShadeCard;

import java.util.ArrayList;
import java.util.List;

public class CatalogItemView extends RecyclerView.Adapter<CatalogItemView.ViewHolder> implements Filterable {

    private static final String TAG = "CatalogItemView";

    private Context context;
    private final List<ShadeCard> shadeGridValues;
    private List<ShadeCard> shadeGridValuesFull;
    private OnCatalogItemClick ctlgItemClick;

    public CatalogItemView(Context context, List shadeGridValues, OnCatalogItemClick listener){
        this.shadeGridValues = shadeGridValues;
        this.context = context;
        this.ctlgItemClick = listener;

        shadeGridValuesFull = new ArrayList<>(shadeGridValues);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView shadeLabel;
        public EditText qty;
        public CardView shadeCard;

        public QtyChangeEditTextListener qtyChangeEditTextListener;

        public ViewHolder(View v, QtyChangeEditTextListener qtyChangeEditTextListener){
            super(v);
            shadeLabel = v.findViewById(R.id.shade);
            qty = v.findViewById(R.id.qty);
            shadeCard = v.findViewById(R.id.shadCard);

            this.qtyChangeEditTextListener = qtyChangeEditTextListener;
            this.qty.addTextChangedListener(qtyChangeEditTextListener);
        }
    }

    @Override
    public CatalogItemView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        // Create a new View
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View v = inflater.inflate( R.layout.catalogitem , null);
        ViewHolder vh = new ViewHolder(v,new QtyChangeEditTextListener());

        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){

        holder.qtyChangeEditTextListener.updatePosition(holder.getAdapterPosition());

        holder.shadeLabel.setText(shadeGridValues.get(position).getShade());
        holder.qty.setText(shadeGridValues.get(position).getQty());

        if(!holder.qty.getText().toString().equals(""))
            holder.shadeCard.setCardBackgroundColor(ContextCompat.getColor(context, R.color.cardBackground));
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount(){
        return shadeGridValues.size();
    }

    private class QtyChangeEditTextListener implements TextWatcher
    {
        private int position;

        public void updatePosition(int position) {
            this.position = position;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            // no op
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            shadeGridValues.get(position).setQty(charSequence.toString());
            ctlgItemClick.onCatalogClick(shadeGridValues);

        }

        @Override
        public void afterTextChanged(Editable editable) {
            // no op

        }
    }

    @Override
    public Filter getFilter() {
        return shadeFilter;
    }

    private Filter shadeFilter = new Filter(){

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<ShadeCard> filteredShades = new ArrayList<>();

            if(constraint == null || constraint.length() ==0){
                filteredShades = shadeGridValuesFull;
            }
            else{
                String filterPattern = constraint.toString().toLowerCase().trim();

                for(ShadeCard sc : shadeGridValuesFull){
                    if(sc.getShade().toLowerCase().contains(filterPattern)){
                        filteredShades.add(sc);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredShades;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            shadeGridValues.clear();
            shadeGridValues.addAll((ArrayList) results.values);
            notifyDataSetChanged();
        }
    };
}
