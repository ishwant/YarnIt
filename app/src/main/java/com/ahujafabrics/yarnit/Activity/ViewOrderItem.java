package com.ahujafabrics.yarnit.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ahujafabrics.yarnit.R;
import com.ahujafabrics.yarnit.Repository.Order;

import java.util.ArrayList;

public class ViewOrderItem extends RecyclerView.Adapter<ViewOrderItem.ViewHolder> {

    private Context context;
    private final ArrayList<Order> ordersList;

    public ViewOrderItem(Context context, ArrayList<Order> ordersList){
        this.ordersList = ordersList;
        this.context = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView orderedBy;
        public TextView createdDate;

        public ViewHolder(View v){
            super(v);
            orderedBy = v.findViewById(R.id.orderedBy);
            createdDate = v.findViewById(R.id.createdDate);
        }
    }

    @Override
    public ViewOrderItem.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        // Create a new View
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View v = inflater.inflate( R.layout.vieworderlist , null);
        ViewOrderItem.ViewHolder vh = new ViewOrderItem.ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(ViewOrderItem.ViewHolder holder, final int position){

        holder.orderedBy.setText(ordersList.get(position).getUserID());
        holder.createdDate.setText(String.valueOf(ordersList.get(position).getCreationDate()));
        holder.itemView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ViewOrderDetails.class);
                intent.putExtra("SelectedOrder", ordersList.get(position));
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount(){
        return ordersList.size();
    }

}
