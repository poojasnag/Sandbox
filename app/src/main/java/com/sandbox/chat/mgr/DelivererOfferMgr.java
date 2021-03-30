//package com.sandbox.chat.mgr;
//
//import android.content.Context;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//
//import com.google.android.gms.tasks.OnFailureListener;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.firebase.firestore.DocumentReference;
//import com.google.firebase.firestore.FirebaseFirestore;
//import com.sandbox.chat.models.DelivererOffer;
//import com.sandbox.chat.models.User;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class DelivererOfferMgr {
//    private static FirebaseFirestore fStore = FirebaseFirestore.getInstance();;
//    private static String USER_TABLE = "delivererOffer";
//    DelivererOfferMgr(){}
//    public static void setData(DelivererOffer delivererOffer, Context context){
//        Map<String, Object> curUser = new HashMap<>();
////        curUser.put("uid", user.getUid());
//        curUser.put("email", user.getEmail());
//        curUser.put("rating", user.getRating());
//        curUser.put("firebaseToken", user.getFirebaseToken());
//        DocumentReference documentReference = fStore.collection(USER_TABLE).document(user.getUid());
//        documentReference.set(curUser).addOnSuccessListener(new OnSuccessListener<Void>(){
//            @Override
//            public void onSuccess(Void aVoid){
//                Toast.makeText(context,"Successfully set: "+ user.getEmail(),Toast.LENGTH_SHORT).show();
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Toast.makeText(context,"Sending failed",Toast.LENGTH_SHORT).show();
//            }
//        });
//
//    }
//
//}
