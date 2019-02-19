package com.ahujafabrics.yarnit.Controllers;

import com.ahujafabrics.yarnit.Repository.CartItem;
import com.ahujafabrics.yarnit.Repository.CartLineItem;
import com.ahujafabrics.yarnit.Repository.Order;
import com.ahujafabrics.yarnit.Repository.OrderItem;
import com.ahujafabrics.yarnit.Repository.SQLiteHelper;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderController {
    private FirebaseDatabase mFirebaseDatabase;
    private static DatabaseReference ordersDb;

    public OrderController(){
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        ordersDb = mFirebaseDatabase.getReference().child("OrdersNew");
    }

    public void submitOrder(CartItem cartItem, String user){
        List<OrderItem> orderItemList = new ArrayList<>();
        int itemNumber = 0;
        for (CartLineItem cli: cartItem.getCartLineItems()) {
            orderItemList.add(new OrderItem(
                    itemNumber++,
                    cli.getShade(),
                    Integer.valueOf(cli.getQty())
            ));
        }

        Date date = new Date();
        Date newDate = new Date(date.getTime() + (604800000L * 2) + (24 * 60 * 60));
        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        String creationDate = dt.format(newDate);

        Order order = new Order(
                "1",
                user,
                creationDate,
                orderItemList,
                Order.OrderStatus.Submitted,
                "Threads"
        );
        ordersDb.push().setValue(order);
    }
}
