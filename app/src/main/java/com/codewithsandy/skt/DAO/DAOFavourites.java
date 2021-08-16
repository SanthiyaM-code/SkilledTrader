package com.codewithsandy.skt.DAO;

import com.codewithsandy.skt.Models.Favourites;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class DAOFavourites {
    private DatabaseReference databaseReference;
    public DAOFavourites(){
        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("Employee");

    }

    public Task<Void> addFav(Favourites phoneOfOthers, String phone, String addedPh)
    {
        String phoneNumber="";
        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null)
        {
            phoneNumber=user.getPhoneNumber();
        }
        return databaseReference.child(phoneNumber).child("Favourites").child(addedPh).setValue(phoneOfOthers);
    }
    public Task<Void> removeFav (String key)
    {
        return databaseReference.child(key).removeValue();
    }
    public Query get()
    {
        return databaseReference.orderByKey();
    }

}
