package org.sopt.kclean.Firebase;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.HashMap;
import java.util.Map;

public class FireBaseHandler {




  public static String passPushTokenToServer(String userId){
        //String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
      FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        String token = FirebaseInstanceId.getInstance().getToken();
       Map<String, Object> map = new HashMap<>();
        map.put("token",token);
      FirebaseDatabase.getInstance().getReference().child("users").child(userId).updateChildren(map);
       return token;
    }
}
