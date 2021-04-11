package com.sandbox.chat.adapters;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.sandbox.chat.R;
import com.sandbox.chat.mgr.DelivererOfferMgr;
import com.sandbox.chat.mgr.UserMgr;
import com.sandbox.chat.models.Deliverer;
import com.sandbox.chat.models.DelivererOffer;
import com.sandbox.chat.models.Eatery;
import com.sandbox.chat.models.Transaction;
import com.sandbox.chat.ui.activities.OrderStatusActivity;
import com.sandbox.chat.ui.activities.PlaceOrderActivity;

import java.util.LinkedList;

/**
 * Adapter class for list of orders in PendingOrdersActivity and ClosedOrdersActivity
 * @see androidx.recyclerview.widget.RecyclerView.Adapter
 */
public class OrderDetailsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

//    private LinkedList<String> orders;
    private LinkedList<Transaction> orders;


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
    public OrderDetailsAdapter(LinkedList<Transaction> orders) {
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
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.order_details, viewGroup, false);

        return new OrderDetailsHolder(view);
    }

    /**
     * Binds the data to a list item in the user interface
     * @param holder The DelivererProfileHolder that needs to be bound with data
     * @param position The position of the item in the list
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof OrderDetailsHolder)
        {
            Intent i = ((Activity)((OrderDetailsHolder) holder).context).getIntent();
            Transaction t = orders.get(position);
            DelivererOfferMgr.getDelivererOffer(t.getDelivererOfferID()).get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot delivererOfferDoc : task.getResult()) {
                                    ((OrderDetailsHolder) holder).button.setText(String.format("%s \t\t %s \n(%s) \t\t Deliver to: %s\n Eatery: %s\n%s", delivererOfferDoc.getString("email") , delivererOfferDoc.getString("etaDateTime"),t.isOrderStatus().toString() ,t.getBuyerLocation(), t.getEateryName(), t.getOrderDetails() ));
                                }
                            }
                        }
                    });

            ((OrderDetailsHolder) holder).button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Activity cur = (Activity)view.getContext();
                    Intent intent = cur.getIntent();
                    if(intent.hasExtra("Transaction"))
                    {
                        intent.removeExtra("Transaction");
                    }
                    if(intent.hasExtra("Eatery"))
                    {
                        intent.removeExtra("Eatery");
                    }
                    intent.putExtra("Transaction", t);

                    intent.setComponent(new ComponentName(cur, OrderStatusActivity.class));
                    cur.startActivity(intent);
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


//                             UserMgr.getUserDocument(t.getDelivererID())
//                                            .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//                                        @Override
//                                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                                            if (task.isSuccessful()) {
//                                                DocumentSnapshot document = task.getResult();
//                                                if (document.exists()) {
//                                                    ((OrderDetailsHolder) holder).button.setText(String.format("%s \t\t %s \nDeliver to: %s\n Eatery: %s\n%s", document.getString("email") , delivererOfferDoc.getString("etaTime"), t.getBuyerLocation(), ((Eatery)(i.getSerializableExtra("Eatery"))).getEateryName(), t.getOrderDetails() ));
//                                                }
//                                            }
//                                        }
//                                    });