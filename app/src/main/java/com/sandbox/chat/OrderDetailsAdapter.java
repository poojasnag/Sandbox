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

/**
 * Populates lists of orders
 */
public class OrderDetailsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LinkedList<String> orders;

    /**
     * Represents each of the items inside the list of orders
     */
    public class OrderDetailsHolder extends RecyclerView.ViewHolder{
        public RelativeLayout parent;
        public Button button;
        private final Context context;
        /**
         * Creates an item in the list
         */
        public OrderDetailsHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            parent = itemView.findViewById(R.id.order_details_parent);
            button = itemView.findViewById(R.id.order_details_button);
        }
    }

    /**
     * Loads the data to the adapter
     * @param orders the list of orders
     */
    public OrderDetailsAdapter(LinkedList<String> orders) {
        this.orders = orders;
    }

    @NonNull
    @Override
    /**
     * Adds a list item to the user interface
     * @param viewGroup
     * @param viewType
     * @return
     */
    public OrderDetailsHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        //TODO: Retrieve all orders.
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.order_details, viewGroup, false);

        return new OrderDetailsHolder(view);
    }

    /**
     * Binds the data to a list item in the user interface
     * @param holder The DelivererProfileHolder that needs to be bound with data
     * @param position The position of the item in the list
     */

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
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
