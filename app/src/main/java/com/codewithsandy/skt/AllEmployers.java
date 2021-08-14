package com.codewithsandy.skt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.codewithsandy.skt.databinding.ActivityAllEmployersBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AllEmployers extends AppCompatActivity {
    private ActivityAllEmployersBinding binding;

    AllEmpRecAdapter adapter;
    DAOEmployee dao;

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