package com.codewithsandy.skt.Adapters;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codewithsandy.skt.ViewHolders.AllEmployeeViewHolder;
import com.codewithsandy.skt.DAO.DAOFavourites;
import com.codewithsandy.skt.Models.Employee;
import com.codewithsandy.skt.Models.Favourites;
import com.codewithsandy.skt.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class AllEmpRecAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    DAOFavourites daoFavourites=new DAOFavourites();

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



        eviewHolder.all_name.setText(emp.getName());
        eviewHolder.all_category.setText(emp.getCategory());
        eviewHolder.all_phNo.setText(emp.getPhNo());
        eviewHolder.all_loc.setText(emp.getLocation());

        eviewHolder.fav_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String phoneNumber="";
                FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
                if(user!=null)
                {
                    phoneNumber=user.getPhoneNumber();
                }
                Favourites temp=new Favourites(emp.getName(),emp.getCategory(),emp.getPhNo(),emp.getLocation());
                daoFavourites.addFav(temp,phoneNumber,emp.getPhNo()).addOnSuccessListener(suc->
                {
                    Toast.makeText(v.getContext(), "Saved!!",Toast.LENGTH_SHORT).show();
                }).addOnFailureListener(er->{
                    Toast.makeText(v.getContext(),""+er.getMessage(),Toast.LENGTH_SHORT).show();

                });
                
            }
        });




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
