package com.sandbox.chat.interactors;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.sandbox.chat.models.User;

import java.util.HashMap;
import java.util.Map;

public class UserInteractor {
    private static final String TAG = "getEmail";
    private static FirebaseFirestore fStore = FirebaseFirestore.getInstance();
    private static String USER_TABLE = "users";
     ;

    public static void setData(User user, Context context){
        Map<String, Object> curUser = new HashMap<>();
//        curUser.put("uid", user.getUid());
        curUser.put("email", user.getEmail());
        curUser.put("rating", user.getRating());
        curUser.put("firebaseToken", user.getFirebaseToken());
        curUser.put("ratingCount", user.getRatingCount());

        DocumentReference documentReference = fStore.collection(USER_TABLE).document(user.getUid());
        documentReference.set(curUser).addOnSuccessListener(new OnSuccessListener<Void>(){
            @Override
            public void onSuccess(Void aVoid){
                Toast.makeText(context,"Successfully set: "+ user.getEmail(),Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context,"Sending failed",Toast.LENGTH_SHORT).show();
            }
        });

    }
    public static DocumentReference getUserDocument(String userID){
        return fStore.collection(USER_TABLE).document(userID);
    }


    public static void updateRating(float newRating, String userID){
        fStore.collection(USER_TABLE).document(userID).get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    int prevRating = document.getLong("rating").intValue();
                    int ratingCount = document.getLong("ratingCount").intValue();
                    float updatedRating = (prevRating + newRating);
                    Log.e("quickmath",  String.valueOf(prevRating) + " " + String.valueOf(newRating) + " " + String.valueOf(ratingCount + 1) );
                    fStore.collection(USER_TABLE).document(userID).update("rating", updatedRating);
                    fStore.collection(USER_TABLE).document(userID).update("ratingCount", ratingCount + 1);
                    Log.e("newrating", String.valueOf(updatedRating) + " "+ String.valueOf(ratingCount + 1));
                }
            }
        });
    }

    public static void calculateRating(String userID){
        fStore.collection(USER_TABLE).document(userID).get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            int compoundedRating = document.getLong("rating").intValue();
                            int ratingCount = document.getLong("ratingCount").intValue();
                            float rating = compoundedRating/ratingCount;

                        }
                    }
                });
    }

}

