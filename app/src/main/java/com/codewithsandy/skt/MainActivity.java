package com.codewithsandy.skt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.codewithsandy.skt.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        DAOEmployee daoEmployee=new DAOEmployee();

        binding.showAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,AllEmployers.class));
            }
        });



        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber="";

                FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
                if(user!=null)
                {
                    phoneNumber=user.getPhoneNumber();
                }

                //get valuse
                String name=binding.employerName.getText().toString();
                String category=binding.employerCategory.getText().toString();
                String phNo=binding.employerPhoneNumber.getText().toString();
                String location=binding.EmployerLocation.getText().toString();


                Employee employee=new Employee(name,category,phNo,location);
                daoEmployee.add(employee,phoneNumber).addOnSuccessListener(suc->
                {
                    Toast.makeText(MainActivity.this,"Saved!!",Toast.LENGTH_SHORT).show();
                }).addOnFailureListener(er->{
                    Toast.makeText(MainActivity.this,""+er.getMessage(),Toast.LENGTH_SHORT).show();

                });
            }
        });
        binding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String,Object> hashMap=new HashMap<>();

                String phoneNumber="";

                FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
                if(user!=null)
                {
                    phoneNumber=user.getPhoneNumber();
                }

                //get valuse
                String name=binding.employerName.getText().toString();
                String category=binding.employerCategory.getText().toString();
                String phNo=binding.employerPhoneNumber.getText().toString();
                String location=binding.EmployerLocation.getText().toString();
                hashMap.put("name",name);
                hashMap.put("category",category);
                hashMap.put("phNo",phNo);
                hashMap.put("location",location);
                daoEmployee.update(phoneNumber,hashMap).addOnSuccessListener(suc->
                {
                    Toast.makeText(MainActivity.this,"Updated!!",Toast.LENGTH_SHORT).show();
                }).addOnFailureListener(er->{
                    Toast.makeText(MainActivity.this,""+er.getMessage(),Toast.LENGTH_SHORT).show();

                });
            }
        });

        binding.btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String phoneNumber="";

                FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
                if(user!=null)
                {
                    phoneNumber=user.getPhoneNumber();
                }

                daoEmployee.remove(phoneNumber).addOnSuccessListener(suc->
                {
                    Toast.makeText(MainActivity.this,"Removed Successfully!!",Toast.LENGTH_SHORT).show();
                }).addOnFailureListener(er->{
                    Toast.makeText(MainActivity.this,""+er.getMessage(),Toast.LENGTH_SHORT).show();

                });

            }
        });







    }
}