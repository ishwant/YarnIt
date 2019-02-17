package com.ahujafabrics.yarnit.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ahujafabrics.yarnit.Activity.ViewOrderDetails;
import com.ahujafabrics.yarnit.R;
import com.ahujafabrics.yarnit.Repository.Order;

import java.util.ArrayList;

public class ViewOrderAdapter extends RecyclerView.Adapter<ViewOrderAdapter.ViewHolder> {

    private Context context;
    private final ArrayList<Order> ordersList;

    public ViewOrderAdapter(Context context, ArrayList<Order> ordersList){
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
    public ViewOrderAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        // Create a new View
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View v = inflater.inflate( R.layout.vieworderlist , null);
        ViewOrderAdapter.ViewHolder vh = new ViewOrderAdapter.ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(ViewOrderAdapter.ViewHolder holder, final int position){

        holder.orderedBy.setText(ordersList.get(position).getUserID());
        holder.createdDate.setText(ordersList.get(position).getCreationDate());
        holder.itemView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ViewOrderDetails.class);
                intent.putExtra("SelectedOrder", ordersList.get(position));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
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
