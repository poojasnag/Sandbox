package com.sandbox.chat.interactors;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.test.platform.app.InstrumentationRegistry;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import static com.google.common.truth.Truth.*;
import org.junit.Test;

import java.util.Map;

public class DelivererOfferInteractorTest {
    Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

    @Test
    public void getDelivererOffer_valid()
    {
        Query q = com.sandbox.chat.interactors.DelivererOfferInteractor.getDelivererOffer("1617939828-test@test.com");
        q.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                assertThat(Double.toString(document.getDouble("deliveryFee"))).isEqualTo("2");
                                assertThat(document.getString("etaDateTime")).isEqualTo("2021-04-09 03:43");
                                Map cur = ((Map) document.get("eatery"));

                            }
                        }
                    }

                });
    }
}