package com.codewithsandy.skt;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codewithsandy.skt.databinding.AllEmployeeRecViewBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AllEmployeeViewHolder extends RecyclerView.ViewHolder {
    public TextView all_name,all_category,all_phNo,all_loc;
   


    public AllEmployeeViewHolder(@NonNull  View itemView) {
        super(itemView);

        all_name=itemView.findViewById(R.id.all_name);
        all_category=itemView.findViewById(R.id.all_cat);
        all_phNo=itemView.findViewById(R.id.all_phNo);
        all_loc=itemView.findViewById(R.id.all_loc);



    }
}
