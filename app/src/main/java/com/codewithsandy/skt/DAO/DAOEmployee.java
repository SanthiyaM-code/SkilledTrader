package com.codewithsandy.skt.DAO;

import com.codewithsandy.skt.Models.Employee;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.HashMap;

public class DAOEmployee {

    private DatabaseReference databaseReference;
    public DAOEmployee(){
        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference(Employee.class.getSimpleName());

    }

    public Task<Void> add(Employee emp,String phone)
    {
        return databaseReference.child(phone).child("Details").setValue(emp);
    }
    public Task<Void> update (String key, HashMap<String,Object> hashMap)
    {
        return databaseReference.child(key).updateChildren(hashMap);
    }
    public Task<Void> remove (String key)
    {
        return databaseReference.child(key).removeValue();
    }
    public Query get()
    {
        return databaseReference.orderByKey();
    }
}
