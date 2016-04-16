package com.payal.chatdemo.fragments;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.payal.chatdemo.R;
import com.payal.chatdemo.adapter.OnlineUsersAdapter;
import com.payal.chatdemo.parser.OnlineUsersList;

import b.p;

/**
 * Created by payal on 16/4/16.
 */
public class OnlineUsersFragment extends Fragment {
    RecyclerView mRecyclerView;
    OnlineUsersAdapter adapter;
    public static OnlineUsersList onlineUsersList;


    public static OnlineUsersFragment newInstance(OnlineUsersList online) {
        OnlineUsersFragment fragment = new OnlineUsersFragment();

        onlineUsersList = online;

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (container != null) {
            container.removeAllViews();
        }

        View v = inflater.inflate(R.layout.chatroom, container, false);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.rv_chatroom);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        ((FloatingActionButton) v.findViewById(R.id.add_chatroom)).setVisibility(View.GONE);
        adapter = new OnlineUsersAdapter(getActivity().getApplicationContext(), onlineUsersList.getUsers());
        mRecyclerView.setAdapter(adapter);


        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new OnlineUsersAdapter(getActivity().getApplicationContext(), onlineUsersList.getUsers());
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter.SetOnItemClickListener(new OnlineUsersAdapter.OnItemClickListener() {


            @Override
            public void onItemClick(View v, int position) {


                getFragmentManager().beginTransaction().addToBackStack("detail")
                        .replace(R.id.content_frame, ChatFragment.newInstance(onlineUsersList.getUsers().get(position).getId())).commit();

            }


        });

    }
}