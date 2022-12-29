package com.example.bkzola.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bkzola.R;
import com.example.bkzola.model.User;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UserHoldel> {

    private ArrayList<User> users;
    private Context context;
    public OnUserClickListener onUserClickListener;

    public UsersAdapter(ArrayList<User> users, Context context, OnUserClickListener onUserClickListener) {
        this.users = users;
        this.context = context;
        this.onUserClickListener = onUserClickListener;
    }

    public interface OnUserClickListener{
        void onUserClicked(int position);
    }

    @androidx.annotation.NonNull
    @Override
    public UserHoldel onCreateViewHolder(@androidx.annotation.NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.user_holder,parent,false);

        return new UserHoldel(view);
    }

    @Override
    public void onBindViewHolder(@androidx.annotation.NonNull UserHoldel holder, int position) {
        holder.txt_username.setText(users.get(position).getUsername());
        Glide.with(context).load(users.get(position).getProfilePicture()).error(R.drawable.avatar_default).placeholder(R.drawable.avatar_default).into(holder.imageView);



    }




    @Override
    public int getItemCount() {
        return users.size();
    }

    class  UserHoldel extends RecyclerView.ViewHolder{

        TextView txt_username;
        ImageView imageView ;


        public UserHoldel(@NonNull View itemView){
            super(itemView);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onUserClickListener.onUserClicked(getAdapterPosition());
                }
            });


            txt_username = itemView.findViewById(R.id.txt_username);
            imageView = itemView.findViewById(R.id.img_friend);


        }
    }
}
