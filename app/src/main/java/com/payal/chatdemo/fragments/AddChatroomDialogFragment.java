package com.payal.chatdemo.fragments;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.inscripts.callbacks.Callbacks;
import com.inscripts.cometchat.sdk.CometChatroom;
import com.inscripts.enums.ChatroomType;
import com.payal.chatdemo.R;

import org.json.JSONObject;

/**
 * Created by payal on 16/4/16.
 */
public class AddChatroomDialogFragment extends DialogFragment {

    EditText name, password;
    Spinner type;

    CometChatroom cometChatroom;

    public static AddChatroomDialogFragment newInstance() {
        AddChatroomDialogFragment f = new AddChatroomDialogFragment();



        return f;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Pick a style based on the num.
        int style = DialogFragment.STYLE_NORMAL;

        cometChatroom = CometChatroom.getInstance(getActivity().getApplicationContext());

        setStyle(style, DialogFragment.STYLE_NO_TITLE);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.add_chatroom_fragment, container, false);
        View tv = v.findViewById(R.id.text);

        name =(EditText) v.findViewById(R.id.input_name);
        password =(EditText) v.findViewById(R.id.input_password);

        Button button = (Button)v.findViewById(R.id.btn_add_chatroom);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                cometChatroom.createChatroom(name.getText().toString(), password.getText().toString() == null ? "" : password.getText().toString(), ChatroomType.PUBLIC_CHATROOM, new Callbacks() {
                    @Override
                    public void successCallback(JSONObject jsonObject) {

                        Log.d("created", jsonObject.toString());
                    }

                    @Override
                    public void failCallback(JSONObject jsonObject) {
                        Log.d("failed", jsonObject.toString());

                    }
                });

            }
        });

        return v;
    }

}
