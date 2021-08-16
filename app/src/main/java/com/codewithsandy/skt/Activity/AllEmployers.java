package com.codewithsandy.skt.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

import com.codewithsandy.skt.Adapters.AllEmpRecAdapter;
import com.codewithsandy.skt.DAO.DAOEmployee;
import com.codewithsandy.skt.Models.Employee;
import com.codewithsandy.skt.databinding.ActivityAllEmployersBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AllEmployers extends AppCompatActivity {
    private ActivityAllEmployersBinding binding;

    AllEmpRecAdapter adapter;
    DAOEmployee dao;
    String phoneNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityAllEmployersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        phoneNumber=user.getPhoneNumber();




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

                        for (DataSnapshot details:dataSnapshot.getChildren()) {
                            Log.e("sandy value is", String.valueOf(details.getKey()));


                            if ((String.valueOf(details.getKey()).equals("Details"))) {
                                Employee emp = details.getValue(Employee.class);
                                emps.add(emp);
                                break;
                            }
                        }

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