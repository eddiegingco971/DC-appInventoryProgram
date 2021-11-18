package com.example.loginui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginui.R;
import com.example.loginui.pojos.Orders;

import java.util.List;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.ViewHolder> {

    public List<Orders> ordersList;
    public Context context;

    public OrdersAdapter(List<Orders> ordersList, Context context) {
        this.ordersList = ordersList;
        this.context = context;
    }

    @NonNull
    @Override
    public OrdersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.orders_recyclerview_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrdersAdapter.ViewHolder holder, int position) {
        Orders order = ordersList.get(position);

        holder.product_name.setText(order.getProduct_name());
        holder.order_name.setText(order.getOrder_name());
        holder.address.setText(order.getAddress());
        holder.quantity_order.setText(order.getQuantity_order());
        holder.create_at.setText(order.getCreated_at());
        holder.updated_at.setText(order.getUpdated_at());
    }

    @Override
    public int getItemCount() {
        return ordersList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView product_name, order_name, address, quantity_order,create_at, updated_at;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            product_name = itemView.findViewById(R.id.productName);
            order_name = itemView.findViewById(R.id.orderName);
            address = itemView.findViewById(R.id.address);
            quantity_order = itemView.findViewById(R.id.quantityOrder);
            create_at = itemView.findViewById(R.id.createAt);
            updated_at = itemView.findViewById(R.id.updatedAt);
        }
    }
}
