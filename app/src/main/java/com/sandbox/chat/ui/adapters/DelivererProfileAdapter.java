package com.sandbox.chat.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sandbox.chat.R;
import com.sandbox.chat.ui.activities.PlaceOrderActivity;

import java.util.LinkedList;

/**
 * Populates the list of deliverers in ChooseDelivererActivity
 */
public class DelivererProfileAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LinkedList<String> deliverers;
    //TODO: Replace this with a list of deliver offers

    /**
     * Represents each of the items inside the list of deliverers
     */
    public class DelivererProfileHolder extends RecyclerView.ViewHolder{
        public LinearLayout parent;
        public TextView delivererProfile;
        public RatingBar rating;
        private final Context context;
        /**
         * Creates an item in the list
         */
        public DelivererProfileHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            parent = itemView.findViewById(R.id.deliverer_profile_parent);
            delivererProfile = itemView.findViewById(R.id.deliverer_profile_details);
            rating = itemView.findViewById(R.id.deliverer_profile_rating);
        }
    }

    /**
     * Loads the data to the adapter
     * @param strings
     */
    public DelivererProfileAdapter(LinkedList<String> strings) {this.deliverers = strings;}
    //TODO: Replace this with the list of deliverers


    /**
     * Adds a list item to the user interface
     * @param viewGroup
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public DelivererProfileHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.deliverer_profile, viewGroup, false);

        return new DelivererProfileHolder(view);

    }

    /**
     * Binds the data to a list item in the user interface
     * @param holder The DelivererProfileHolder that needs to be bound with data
     * @param position The position of the item in the list
     */
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if(holder instanceof DelivererProfileHolder)
        {
            String deliverer = deliverers.get(position);
            ((DelivererProfileHolder) holder).delivererProfile.setText(deliverer);
            ((DelivererProfileHolder) holder).rating.setRating((float) 4.2);
            //TODO: replace rating
            ((DelivererProfileHolder) holder).parent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(((DelivererProfileHolder) holder).context, PlaceOrderActivity.class);
                    //TODO: insert data about deliverer
                    ((DelivererProfileHolder) holder).context.startActivity(intent);
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
        return deliverers.size();
    }
}
