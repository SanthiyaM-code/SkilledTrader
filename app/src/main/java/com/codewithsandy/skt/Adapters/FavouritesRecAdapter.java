package com.codewithsandy.skt.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codewithsandy.skt.Models.Favourites;
import com.codewithsandy.skt.R;
import com.codewithsandy.skt.ViewHolders.FavouritesViewHolder;

import java.util.ArrayList;

public class FavouritesRecAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context context;
    ArrayList<Favourites> favList=new ArrayList<>();

    public FavouritesRecAdapter(Context context){
        this.context=context;
    }

    public void setFavList(ArrayList<Favourites> favs)
    {
        favList.addAll(favs);
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.fav_rec_view,parent,false);
        return new FavouritesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  RecyclerView.ViewHolder holder, int position) {

        FavouritesViewHolder favouritesViewHolder=(FavouritesViewHolder) holder;
        Favourites favouritesObject=favList.get(position);



        favouritesViewHolder.favs_name.setText(favouritesObject.getName());
        favouritesViewHolder.favs_category.setText(favouritesObject.getCategory());
        favouritesViewHolder.favs_phNo.setText(favouritesObject.getPhNo());
        favouritesViewHolder.favs_loc.setText(favouritesObject.getLocation());

    }

    @Override
    public int getItemCount() {
        return favList.size();
    }
}
