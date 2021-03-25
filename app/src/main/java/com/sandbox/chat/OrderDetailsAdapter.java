package com.sandbox.chat;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

public class OrderDetailsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    public class OrderDetailsHolder extends RecyclerView.ViewHolder{
        public RelativeLayout parent;
        public Button button;
        private final Context context;
        public OrderDetailsHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            parent = itemView.findViewById(R.id.order_details_parent);
            button = itemView.findViewById(R.id.order_details_button);
        }
    }
    private LinkedList<String> orders;

    public OrderDetailsAdapter(LinkedList<String> orders) {
        this.orders = orders;
    }

    @NonNull
    @Override
    public OrderDetailsHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.order_details, viewGroup, false);

        return new OrderDetailsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof OrderDetailsHolder)
        {

            ((OrderDetailsHolder) holder).button.setText(orders.get(position));
            ((OrderDetailsHolder) holder).parent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(((OrderDetailsHolder) holder).context, OrderStatusActivity.class);
                    //TODO: Add details of order to the order status activity
                    ((OrderDetailsHolder) holder).context.startActivity(intent);
                }

            });
        }
        else
        {
             return;
        }

    }


    @Override
    public int getItemCount() {
        return orders.size();
    }
}
