package com.sandbox.chat.adapters;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.sandbox.chat.R;
import com.sandbox.chat.interactors.UserInteractor;
import com.sandbox.chat.models.DelivererOffer;
import com.sandbox.chat.ui.activities.PlaceOrderActivity;

import java.util.LinkedList;

/**
 * Populates the list of deliverers in ChooseDelivererActivity
 * @see androidx.recyclerview.widget.RecyclerView.Adapter
 */
public class DelivererProfileAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LinkedList<DelivererOffer> deliverers;

    private ViewGroup parent;

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


    public DelivererProfileAdapter(LinkedList<DelivererOffer> deliverers) {this.deliverers = deliverers;} //passed in delivereroffer object


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
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof DelivererProfileHolder)
        {

            DelivererOffer deliverer = deliverers.get(position);
            ((DelivererProfileHolder) holder).delivererProfile.setText(String.format("Name: %s \nRate:$%.2f\nLocations: %s", deliverer.getDeliverer().getEmail(), deliverer.getDeliveryFee(), String.join(", ", deliverer.getDeliveryLocation())));
            UserInteractor.getUserDocument(deliverer.getDeliverer().getUid()).get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        int compoundedRating = document.getLong("rating").intValue();
                        int ratingCount = document.getLong("ratingCount").intValue();
                        if (ratingCount != 0) {
                            ((DelivererProfileHolder) holder).rating.setRating(compoundedRating / ratingCount);
                        } else {
                            ((DelivererProfileHolder) holder).rating.setRating(0);
                        }

                    }
                }
            });



            ((DelivererProfileHolder) holder).parent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Activity cur = (Activity)view.getContext();
                    Intent intent = cur.getIntent();
                    if(intent.hasExtra("delivererOffer"))
                    {
                        intent.removeExtra("delivererOffer");
                    }
                    intent.putExtra("delivererOffer", deliverer);


                    intent.setComponent(new ComponentName(cur, PlaceOrderActivity.class));
                    cur.startActivity(intent);


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
