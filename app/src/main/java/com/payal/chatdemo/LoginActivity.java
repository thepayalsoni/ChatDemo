package com.payal.chatdemo;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.inscripts.callbacks.Callbacks;
import com.inscripts.cometchat.sdk.CometChat;

import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by payal on 4/14/2016.
 */
public class LoginActivity extends Activity implements View.OnClickListener {

    CometChat cometChat;

    @Bind(R.id.input_username)
    TextView username;
    @Bind(R.id.input_password)
    TextView password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        ButterKnife.bind(this);
        cometChat = CometChat.getInstance(getBaseContext(), getResources().getString(R.string.api_key));

    }

    @Override
    public void onClick(View v) {
        final ProgressDialog pro = new ProgressDialog(this);
        pro.setMessage("Loging in ...");
        pro.show();
        cometChat.login(username.getText().toString(), password.getText().toString(), new Callbacks() {
            @Override
            public void successCallback(JSONObject success) {
                if (pro.isShowing())
                    pro.dismiss();

                Intent intent  = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);

                finish();


            }

            @Override
            public void failCallback(JSONObject fail) {
                if (pro.isShowing())
                    pro.dismiss();

            }
        });
    }
}
