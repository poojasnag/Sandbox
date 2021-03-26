package com.sandbox.chat;

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

import java.util.LinkedList;

public class DelivererProfileAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LinkedList<String> deliverers;
    //TODO: Replace this with a list of deliver offers

    public class DelivererProfileHolder extends RecyclerView.ViewHolder{
        public LinearLayout parent;
        public TextView delivererProfile;
        public RatingBar rating;
        private final Context context;
        public DelivererProfileHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            parent = itemView.findViewById(R.id.deliverer_profile_parent);
            delivererProfile = itemView.findViewById(R.id.deliverer_profile_details);
            rating = itemView.findViewById(R.id.deliverer_profile_rating);

        }
    }

    public DelivererProfileAdapter(LinkedList<String> strings) {this.deliverers = strings;}
    //TODO: Replace this with the list of deliverers


    @NonNull
    @Override
    public DelivererProfileHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.deliverer_profile, viewGroup, false);

        return new DelivererProfileHolder(view);

    }

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
