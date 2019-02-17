package com.ahujafabrics.yarnit.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ahujafabrics.yarnit.R;
import com.ahujafabrics.yarnit.Repository.OrderItem;

import java.util.List;

public class ViewOrderDetailsAdapter extends RecyclerView.Adapter<ViewOrderDetailsAdapter.ViewHolder> {

    private Context context;
    private final List<OrderItem> orderItemsList;

    public ViewOrderDetailsAdapter(Context context, List<OrderItem> orderItemsList){
        this.orderItemsList = orderItemsList;
        this.context = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView shadeInCart;
        public TextView qtyInCart;

        public ViewHolder(View v){
            super(v);
            shadeInCart = v.findViewById(R.id.shadeInCart);
            qtyInCart = v.findViewById(R.id.qtyInCart);
        }
    }

    @Override
    public ViewOrderDetailsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        // Create a new View
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View v = inflater.inflate( R.layout.vieworderdetails , null);
        ViewOrderDetailsAdapter.ViewHolder vh = new ViewOrderDetailsAdapter.ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(ViewOrderDetailsAdapter.ViewHolder holder, int position){

        holder.shadeInCart.setText(orderItemsList.get(position).getShadeId());
        holder.qtyInCart.setText(String.valueOf(orderItemsList.get(position).getQuantity()));
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount(){
        return orderItemsList.size();
    }

}
