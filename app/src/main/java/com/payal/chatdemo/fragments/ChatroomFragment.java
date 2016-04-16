package com.payal.chatdemo.fragments;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.payal.chatdemo.R;
import com.payal.chatdemo.adapter.ChatroomsAdapter;
import com.payal.chatdemo.adapter.OnlineUsersAdapter;
import com.payal.chatdemo.parser.ChatroomsList;

/**
 * Created by payal on 13/4/16.
 */
public class ChatroomFragment extends Fragment {

    RecyclerView mRecyclerView;
    ChatroomsAdapter adapter;
    FloatingActionButton button;
    public static ChatroomsList ChatroomsList;


    public static ChatroomFragment newInstance(ChatroomsList online) {
        ChatroomFragment fragment = new ChatroomFragment();

        ChatroomsList = online;

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (container != null) {
            container.removeAllViews();
        }

        View v = inflater.inflate(R.layout.chatroom, container, false);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.rv_chatroom);
        button = (FloatingActionButton) v.findViewById(R.id.add_chatroom);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction ft = getFragmentManager().beginTransaction();
                    Fragment prev = getFragmentManager().findFragmentByTag("dialog");
                    if (prev != null) {
                        ft.remove(prev);
                    }
                    ft.addToBackStack("chatroom");

                    // Create and show the dialog.
                    DialogFragment newFragment = AddChatroomDialogFragment.newInstance();
                    newFragment.show(ft, "dialog");
            }
        });

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new ChatroomsAdapter(getActivity().getApplicationContext(), ChatroomsList.getChatroomsList());
        mRecyclerView.setAdapter(adapter);


        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new ChatroomsAdapter(getActivity().getApplicationContext(), ChatroomsList.getChatroomsList());
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());



    }




}
