package com.example.bkzola.activities;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bkzola.R;
import com.example.bkzola.model.Message;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.MessagesHolder> {

    private ArrayList<Message> messages;
    private String senderImg , receiverImg;
    private Context context;

    public MessagesAdapter(ArrayList<Message> messages, String senderImg, String receiverImg, Context context) {
        this.messages = messages;
        this.senderImg = senderImg;
        this.receiverImg = receiverImg;
        this.context = context;
    }

    @NonNull
    @Override
    public MessagesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.message_holder , parent, false);
        return new MessagesHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessagesHolder holder, int position) {
        holder.txtMessage.setText(messages.get(position).getContet());

        ConstraintLayout constraintLayout = holder.ccll;
        if(messages.get(position).getSender().equals(FirebaseAuth.getInstance().getCurrentUser().getEmail())) {
            Glide.with(context).load(senderImg).error(R.drawable.avatar_default).placeholder(R.drawable.avatar_default).into(holder.proImage);
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(constraintLayout);
            constraintSet.clear(R.id.profile_cardview,ConstraintSet.LEFT);
            constraintSet.clear(R.id.txt_messsage_content,ConstraintSet.LEFT);
            constraintSet.connect(R.id.profile_cardview, ConstraintSet.RIGHT, R.id.ccLayout,ConstraintSet.RIGHT,0);
            constraintSet.connect(R.id.txt_messsage_content, ConstraintSet.RIGHT, R.id.profile_cardview,ConstraintSet.LEFT,0);
            constraintSet.applyTo(constraintLayout);
        }
        else {
            Glide.with(context).load(receiverImg).error(R.drawable.avatar_default).placeholder(R.drawable.avatar_default).into(holder.proImage);
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(constraintLayout);
            constraintSet.clear(R.id.profile_cardview,ConstraintSet.RIGHT);
            constraintSet.clear(R.id.txt_messsage_content,ConstraintSet.RIGHT);
            constraintSet.connect(R.id.profile_cardview, ConstraintSet.LEFT, R.id.ccLayout,ConstraintSet.LEFT,0);
            constraintSet.connect(R.id.txt_messsage_content, ConstraintSet.LEFT, R.id.profile_cardview,ConstraintSet.RIGHT,0);
            constraintSet.applyTo(constraintLayout);
        }
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    class MessagesHolder extends RecyclerView.ViewHolder{

        ConstraintLayout ccll;
        TextView txtMessage;
        ImageView proImage;

        public MessagesHolder(@NonNull View itemView) {
            super(itemView);

            ccll = itemView.findViewById(R.id.ccLayout);
            txtMessage = itemView.findViewById(R.id.txt_messsage_content);
            proImage = itemView.findViewById(R.id.small_profile_img);
        }
    }
}
