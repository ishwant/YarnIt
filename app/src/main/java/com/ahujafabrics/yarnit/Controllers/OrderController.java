package com.ahujafabrics.yarnit.Controllers;

import com.ahujafabrics.yarnit.Repository.CartItem;
import com.ahujafabrics.yarnit.Repository.CartLineItem;
import com.ahujafabrics.yarnit.Repository.Order;
import com.ahujafabrics.yarnit.Repository.OrderItem;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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

    public void submitOrder(CartItem cartItem){
        List<OrderItem> orderItemList = new ArrayList<>();
        int itemNumber = 0;
        for (CartLineItem cli: cartItem.getCartLineItems()) {
            orderItemList.add(new OrderItem(
                    itemNumber++,
                    cartItem.getProductType(),
                    cli.getShade(),
                    Integer.valueOf(cli.getQty())
            ));
        }
        Order order = new Order(
                "1",
                "ishwant",
                new Date().getTime(),
                orderItemList,
                Order.OrderStatus.Submitted
        );
        ordersDb.push().setValue(order);
    }
}
