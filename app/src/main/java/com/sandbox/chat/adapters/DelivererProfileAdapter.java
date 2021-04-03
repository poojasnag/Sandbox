package com.sandbox.chat.adapters;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.sandbox.chat.R;
import com.sandbox.chat.models.DelivererOffer;
import com.sandbox.chat.ui.activities.PlaceOrderActivity;
import com.sandbox.chat.ui.activities.UserRatingActivity;

import java.util.LinkedList;

/**
 * Populates the list of deliverers in ChooseDelivererActivity
 */
public class DelivererProfileAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LinkedList<String> deliverers;
    private DelivererOffer delivererOffer;
    private ViewGroup parent;
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
     * @param strings the list of deliverers
     */
    public DelivererProfileAdapter(LinkedList<String> strings, DelivererOffer delivererOffer) {this.deliverers = strings; this.delivererOffer = delivererOffer;} //passed in delivereroffer object
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
        parent = viewGroup;
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
                    Activity cur = (Activity)view.getContext();
                    Intent intent = cur.getIntent();
                    intent.putExtra("delivererOffer", delivererOffer);
                    Log.e("delivererpffer", delivererOffer.getDeliverer().getEmail());
                    intent.setComponent(new ComponentName(cur, PlaceOrderActivity.class));
                    cur.startActivity(intent);
                    //TODO: insert data about deliverer

//                    Intent intent = new Intent(((DelivererProfileHolder) holder).context, UserRatingActivity.class);
//                    ((DelivererProfileHolder) holder).context.startActivity(intent);
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
