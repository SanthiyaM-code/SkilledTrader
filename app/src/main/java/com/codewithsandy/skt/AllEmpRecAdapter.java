package com.codewithsandy.skt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AllEmpRecAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    ArrayList<Employee> empList=new ArrayList<>();
    public AllEmpRecAdapter(Context context){  this.context=context;  }
    public void setEmpList(ArrayList<Employee> emp)
    {
        empList.addAll(emp);
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

        eviewHolder.all_name.setText(emp.getName());
        eviewHolder.all_category.setText(emp.getCategory());
        eviewHolder.all_phNo.setText(emp.getPhNo());
        eviewHolder.all_loc.setText(emp.getLocation());

    }

    @Override
    public int getItemCount() {
        return empList.size();
    }
}
