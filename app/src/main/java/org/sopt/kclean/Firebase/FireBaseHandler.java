package org.sopt.kclean.Controller;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.HashMap;
import java.util.Map;

public class FireBaseHandler {




  public static String passPushTokenToServer(){
       // String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        String token = FirebaseInstanceId.getInstance().getToken();
//        Map<String, Object> map = new HashMap<>();
//        map.put("token",token);
//      FirebaseDatabase.getInstance().getReference().child("users").child(uid).updateChildren(map);
       return token;
    }
}
