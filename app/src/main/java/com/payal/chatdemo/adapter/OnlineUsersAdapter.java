package com.payal.chatdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.payal.chatdemo.R;
import com.payal.chatdemo.parser.OnlineUsers;

import java.util.List;

/**
 * Created by payal on 21/3/16.
 */
public class OnlineUsersAdapter extends RecyclerView.Adapter<OnlineUsersAdapter.ViewHolder> {

    LayoutInflater inflater;
    Context context;
    List<OnlineUsers> onlineUsersArrayList;

    public OnlineUsersAdapter(Context context, List<OnlineUsers> onlineUsersArrayList) {
        this.context = context;
        this.onlineUsersArrayList = onlineUsersArrayList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);



    }

    @Override
    public long getItemId(int position) {

        return onlineUsersArrayList==null?0:onlineUsersArrayList.size();
    }

    @Override
    public int getItemCount() {

        return  onlineUsersArrayList != null ? onlineUsersArrayList.size() : 0;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {




            holder.tvChatRoom.setText(onlineUsersArrayList.get(position).getN());


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