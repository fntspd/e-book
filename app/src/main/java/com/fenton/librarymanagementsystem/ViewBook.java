package com.fenton.librarymanagementsystem;

import android.location.Geocoder;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import de.hdodenhof.circleimageview.CircleImageView;

public class ViewBook extends FirebaseRecyclerAdapter<model, ViewBook.myViewholder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public ViewBook(@NonNull FirebaseRecyclerOptions<model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewholder holder, int position, @NonNull model model) {
        holder.bookname.setText(model.getBookname());
        holder.authorname.setText(model.getAuthorname());

        Glide.with(holder.img.getContext())
                .load(model.getSurl())
                .placeholder (com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                .circleCrop()
                .error (com.google.android.gms.base.R.drawable.common_google_signin_btn_icon_disabled)
                .into(holder.img);
    }

    @NonNull
    @Override
    public myViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items,parent,false);
        return new myViewholder(view);
    }

    class myViewholder extends RecyclerView.ViewHolder{
        CircleImageView img;
        TextView bookname, authorname;

        public myViewholder(@NonNull View itemView) {
            super(itemView);
            img = (CircleImageView) itemView. findViewById(R.id.img1);
            bookname = (TextView) itemView.findViewById(R.id.items_bookname);
            authorname = (TextView) itemView.findViewById (R.id.items_authorname);
        }
    }
}