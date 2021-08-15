package com.codewithsandy.skt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.codewithsandy.skt.databinding.ActivityAllEmployersBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AllEmployers extends AppCompatActivity {
    private ActivityAllEmployersBinding binding;

    AllEmpRecAdapter adapter;
    DAOEmployee dao;

    FirebaseDatabase database=FirebaseDatabase.getInstance();
    DatabaseReference databaseReference,fav_ref,fav_list_ref;
    Boolean isFav;
    Employee employee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityAllEmployersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());




        binding.allEmployeesRec.setHasFixedSize(true);
        LinearLayoutManager manager=new LinearLayoutManager(this);
        binding.allEmployeesRec.setLayoutManager(manager);
        adapter=new AllEmpRecAdapter(this);
        binding.allEmployeesRec.setAdapter(adapter);


        dao=new DAOEmployee();
        loadData();

        binding.searchbar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.filter(s);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });





    }

    private void loadData() {
        dao.get().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {
                ArrayList<Employee> emps=new ArrayList<>();
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    Employee emp=dataSnapshot.getValue(Employee.class);
                    emps.add(emp);
                }
                adapter.setEmpList(emps);
                adapter.notifyDataSetChanged();

            }
            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

            }
        });
    }
}