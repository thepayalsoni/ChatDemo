package com.payal.chatdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.payal.chatdemo.R;

/**
 * Created by payal on 21/3/16.
 */
public class ChatroomAdapter extends RecyclerView.Adapter<ChatroomAdapter.ViewHolder> {

    LayoutInflater inflater;
    Context context;


    public ChatroomAdapter(Context context) {
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);



    }

    @Override
    public long getItemId(int position) {

        return 0;
    }

    @Override
    public int getItemCount() {

        return  0;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {







    }




    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_chatroom, viewGroup,
                false);
        return new ViewHolder(itemView);

    }


    public class ViewHolder extends RecyclerView.ViewHolder  {

        TextView tvChatRoom;


        public ViewHolder(View itemView) {
            super(itemView);
            tvChatRoom = (TextView) itemView.findViewById(R.id.tv_chatroom);


        }



    }




}