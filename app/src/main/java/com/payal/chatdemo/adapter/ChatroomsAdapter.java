package com.payal.chatdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.payal.chatdemo.R;
import com.payal.chatdemo.parser.Chatrooms;

import java.util.List;

/**
 * Created by payal on 21/3/16.
 */
public class ChatroomsAdapter extends RecyclerView.Adapter<ChatroomsAdapter.ViewHolder> {

    LayoutInflater inflater;
    Context context;
    List<Chatrooms> chatroomsList;

    public ChatroomsAdapter(Context context, List<Chatrooms> onlineUsersArrayList) {
        this.context = context;
        this.chatroomsList = onlineUsersArrayList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);



    }

    @Override
    public long getItemId(int position) {

        return chatroomsList ==null?0: chatroomsList.size();
    }

    @Override
    public int getItemCount() {

        return  chatroomsList != null ? chatroomsList.size() : 0;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {




            holder.tvChatRoom.setText(chatroomsList.get(position).getName());


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