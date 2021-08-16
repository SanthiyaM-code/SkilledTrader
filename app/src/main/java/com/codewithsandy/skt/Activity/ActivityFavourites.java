package com.codewithsandy.skt.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;

import com.codewithsandy.skt.Adapters.AllEmpRecAdapter;
import com.codewithsandy.skt.Adapters.FavouritesRecAdapter;
import com.codewithsandy.skt.DAO.DAOEmployee;
import com.codewithsandy.skt.DAO.DAOFavourites;
import com.codewithsandy.skt.Models.Employee;
import com.codewithsandy.skt.Models.Favourites;
import com.codewithsandy.skt.R;
import com.codewithsandy.skt.databinding.ActivityAllEmployersBinding;
import com.codewithsandy.skt.databinding.ActivityFavouritesBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ActivityFavourites extends AppCompatActivity {
    private ActivityFavouritesBinding binding;

    FavouritesRecAdapter adapter;
    DAOFavourites dao;
    String phoneNumber="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityFavouritesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.FavRec.setHasFixedSize(true);
        LinearLayoutManager manager=new LinearLayoutManager(this);
        binding.FavRec.setLayoutManager(manager);
        adapter=new FavouritesRecAdapter(this);
        binding.FavRec.setAdapter(adapter);

        dao=new DAOFavourites();
        loadData();
    }
    private void loadData() {

        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null)
        {
            phoneNumber=user.getPhoneNumber();
        }

        dao.get().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Favourites> favs=new ArrayList<>();
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    if ((String.valueOf(dataSnapshot.getKey())).equals(phoneNumber))
                    {
                        for (DataSnapshot childds : dataSnapshot.getChildren()) {
                            if ((String.valueOf(childds.getKey()).equals("Favourites"))) {
                                for (DataSnapshot num : childds.getChildren()) {
                                    Log.e("sandy value is", String.valueOf(num.getKey()));
                                    Favourites favourites = num.getValue(Favourites.class);
                                    favs.add(favourites);
                                }

                                break;
                            }
                        }
                        break;
                    }

                }
                adapter.setFavList(favs);
                adapter.notifyDataSetChanged();

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}