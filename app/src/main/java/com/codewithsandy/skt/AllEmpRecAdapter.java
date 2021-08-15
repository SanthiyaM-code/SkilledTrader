package com.codewithsandy.skt;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AllEmpRecAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private Context context;
    ArrayList<Employee> empList=new ArrayList<>();
    ArrayList<Employee> copy;
    public AllEmpRecAdapter(Context context){
        this.context=context;
    }
    public void setEmpList(ArrayList<Employee> emp)
    {
        empList.addAll(emp);
        copy=new ArrayList<>();
        copy.addAll(empList);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.all_employee_rec_view,parent,false);
        return new AllEmployeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  RecyclerView.ViewHolder holder, int position) {
        AllEmployeeViewHolder eviewHolder=(AllEmployeeViewHolder) holder;
        Employee emp=empList.get(position);




    }

    @Override
    public int getItemCount() {
        return empList.size();
    }

    public void filter(CharSequence charSequence) {
        ArrayList<Employee> tempArray=new ArrayList<>();
        if(!TextUtils.isEmpty(charSequence))
        {
            for(Employee employee:empList)
            {
                if(employee.getName().toLowerCase().contains(charSequence))
                {
                    tempArray.add(employee);
                }
            }
        }
        else
        {
            tempArray.addAll(copy);

        }
        empList.clear();
        empList.addAll(tempArray);
        notifyDataSetChanged();
        tempArray.clear();

    }
}
