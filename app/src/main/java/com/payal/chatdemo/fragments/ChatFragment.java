package com.payal.chatdemo.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.inscripts.callbacks.Callbacks;
import com.inscripts.callbacks.SubscribeCallbacks;
import com.inscripts.cometchat.sdk.CometChat;
import com.payal.chatdemo.R;
import com.payal.chatdemo.parser.ChatSentResponse;
import com.payal.chatdemo.parser.ProfileInfo;

import org.json.JSONObject;

import java.lang.reflect.Type;

/**
 * Created by payal on 16/4/16.
 */
public class ChatFragment extends Fragment {

    TextView chatHistory;
    EditText chat;
    Button send;

    CometChat cometChat;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (container != null) {
            container.removeAllViews();
        }


        View v = inflater.inflate(R.layout.chat_fragment, container, false);

        chatHistory = (TextView) v.findViewById(R.id.chat_history);
        chat = (EditText) v.findViewById(R.id.et_chat);
        send = (Button) v.findViewById(R.id.send);

        cometChat = CometChat.getInstance(getActivity().getApplicationContext(), "10415x77177883eedf5255554e825180e563c1");



        cometChat.subscribe(true, new SubscribeCallbacks() {
            @Override
            public void onMessageReceived(JSONObject receivedMessage) {

                Log.d("got receivedMessage", receivedMessage + "");

                Type proInfo = new TypeToken<ChatSentResponse>() {
                }.getType();

               ChatSentResponse csr= new Gson().fromJson(receivedMessage.toString(), proInfo);

                chatHistory.append(csr.getMessage()+"\n\r");

            }

            @Override
            public void gotProfileInfo(JSONObject jsonOnject) {

            }

            @Override
            public void gotOnlineList(JSONObject onlineUsers) {

                Log.d("got onlineUsers", onlineUsers + "");
            }

            @Override
            public void onError(JSONObject errorResponse) {

                Log.d("got errorResponse", errorResponse + "");
            }

            @Override
            public void gotAnnouncement(JSONObject announcement) {

                Log.d("got announcement", announcement + "");
            }

            @Override
            public void onAVChatMessageReceived(JSONObject response) {

                Log.d("got response", response + "");
            }

            @Override
            public void onActionMessageReceived(JSONObject response) {
                Log.d("got response", response + "");
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cometChat.sendMessage(getArguments().getString("id"), chat.getText().toString(), new Callbacks() {
                    @Override
                    public void successCallback(JSONObject response) {
                        chatHistory.append("ME: " + chat.getText().toString() + "\n\r");
                        chat.setText("");




                    }

                    @Override
                    public void failCallback(JSONObject response) {
                        chatHistory.append("Failed");

                    }
                });


            }
        });



        return v;
    }




    public static ChatFragment newInstance(String id) {
        ChatFragment fragment = new ChatFragment();
        Bundle args = new Bundle();
        args.putString("id", id);
        fragment.setArguments(args);

        return fragment;
    }
}
