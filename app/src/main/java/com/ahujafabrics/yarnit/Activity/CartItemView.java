package com.ahujafabrics.yarnit.Activity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ahujafabrics.yarnit.R;
import com.ahujafabrics.yarnit.Repository.CartLineItem;

import java.util.ArrayList;

public class CartItemView extends RecyclerView.Adapter<CartItemView.ViewHolder> {

    private Context context;
    private final ArrayList<CartLineItem> cartItemList;

    public CartItemView(Context context, ArrayList<CartLineItem> cartItems){
        this.cartItemList = cartItems;
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
    public CartItemView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        // Create a new View
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View v = inflater.inflate( R.layout.cartitem , null);
        CartItemView.ViewHolder vh = new CartItemView.ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(CartItemView.ViewHolder holder, int position){

        holder.shadeInCart.setText(cartItemList.get(position).getShade());
        holder.qtyInCart.setText(String.valueOf(cartItemList.get(position).getQty()));
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount(){
        return cartItemList.size();
    }

}
