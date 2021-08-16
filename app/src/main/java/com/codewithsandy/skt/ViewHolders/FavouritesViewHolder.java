package com.codewithsandy.skt.ViewHolders;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codewithsandy.skt.R;

public class FavouritesViewHolder extends RecyclerView.ViewHolder {
    public TextView favs_name,favs_category,favs_phNo,favs_loc;
    public ImageButton favs_btn;

    public FavouritesViewHolder(@NonNull  View itemView) {
        super(itemView);
        favs_name=itemView.findViewById(R.id.fav_name);

        favs_category=itemView.findViewById(R.id.fav_cat);

        favs_phNo=itemView.findViewById(R.id.fav_phNo);

        favs_loc=itemView.findViewById(R.id.fav_loc);

        favs_btn=itemView.findViewById(R.id.fav_btn);
    }
}
