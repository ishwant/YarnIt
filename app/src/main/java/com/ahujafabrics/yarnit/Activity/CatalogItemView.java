package com.ahujafabrics.yarnit.Activity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.ahujafabrics.yarnit.R;
import com.ahujafabrics.yarnit.Repository.OnCatalogItemClick;
import com.ahujafabrics.yarnit.Repository.ShadeCard;

import java.util.List;

public class CatalogItemView extends RecyclerView.Adapter<CatalogItemView.ViewHolder> {

    private Context context;
    private final List<ShadeCard> shadeGridValues;
    private OnCatalogItemClick ctlgItemClick;

    public CatalogItemView(Context context, List shadeGridValues, OnCatalogItemClick listener){
        this.shadeGridValues = shadeGridValues;
        this.context = context;
        this.ctlgItemClick = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView shadeLabel;
        public EditText qty;

        public MyCustomEditTextListener myCustomEditTextListener;

        public ViewHolder(View v, MyCustomEditTextListener myCustomEditTextListener){
            super(v);
            shadeLabel = v.findViewById(R.id.shade);
            qty = v.findViewById(R.id.qty);

            this.myCustomEditTextListener = myCustomEditTextListener;
            this.qty.addTextChangedListener(myCustomEditTextListener);
        }
    }

    @Override
    public CatalogItemView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        // Create a new View
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View v = inflater.inflate( R.layout.catalogitem , null);
        ViewHolder vh = new ViewHolder(v,new MyCustomEditTextListener());

        return vh;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position){

        holder.myCustomEditTextListener.updatePosition(holder.getAdapterPosition());

        holder.shadeLabel.setText(shadeGridValues.get(position).getShade());
        holder.qty.setText(shadeGridValues.get(position).getQty());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount(){
        return shadeGridValues.size();
    }

    private class MyCustomEditTextListener implements TextWatcher {
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
}
