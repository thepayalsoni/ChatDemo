package com.payal.chatdemo;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v4.app.FragmentTransaction;
import android.telecom.Call;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.inscripts.callbacks.Callbacks;
import com.inscripts.callbacks.SubscribeCallbacks;
import com.inscripts.callbacks.SubscribeChatroomCallbacks;
import com.inscripts.cometchat.sdk.CometChat;
import com.inscripts.cometchat.sdk.CometChatroom;
import com.payal.chatdemo.fragments.AddChatroomDialogFragment;
import com.payal.chatdemo.fragments.ChatroomFragment;
import com.payal.chatdemo.fragments.OnlineUsersFragment;
import com.payal.chatdemo.fragments.ProfileFragment;
import com.payal.chatdemo.parser.Chatrooms;
import com.payal.chatdemo.parser.ChatroomsDeserializer;
import com.payal.chatdemo.parser.ChatroomsList;
import com.payal.chatdemo.parser.OnlineUsersDeserializer;
import com.payal.chatdemo.parser.OnlineUsersList;
import com.payal.chatdemo.parser.ProfileInfo;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by payal on 13/4/16.
 */
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawer;
    CometChat cometChat;
    CometChatroom cometChatroom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Menu menu = navigationView.getMenu();
        menu.add("My Profile");
        menu.add("Users");
        menu.add("Chatrooms");
        menu.add("Add Chatroom");
        menu.add("Logout");

        cometChat = CometChat.getInstance(getApplicationContext(), "10415x77177883eedf5255554e825180e563c1");

        cometChatroom = CometChatroom.getInstance(getApplicationContext());




    }

    @Override
    protected void onResume() {
        super.onResume();

        subscribeToChat();

    }

    ProfileInfo profileInfo;

    private void subscribeToChat() {
        cometChat.subscribe(true, new SubscribeCallbacks() {
            @Override
            public void onMessageReceived(JSONObject receivedMessage) {

                Log.d("got receivedMessage", receivedMessage + "");


            }

            @Override
            public void gotProfileInfo(JSONObject jsonOnject) {

                Log.d("got profileInfo", jsonOnject + "");

                Type proInfo = new TypeToken<ProfileInfo>() {
                }.getType();

                profileInfo = new Gson().fromJson(jsonOnject.toString(), proInfo);
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
    }

    @Override
    public void onBackPressed() {

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        if (item.getTitle().equals("My Profile")) {

            getSupportFragmentManager().beginTransaction().addToBackStack("profile")
                    .replace(R.id.content_frame, ProfileFragment.newInstance(profileInfo)).commit();
        } else if (item.getTitle().equals("Users")) {
            final ProgressDialog pro = new ProgressDialog(MainActivity.this);
            pro.setMessage("Getting List of Users ...");
            pro.show();


            cometChat.getOnlineUsers(new Callbacks() {
                @Override
                public void successCallback(JSONObject jsonObject) {
                    Log.d("getOnlineUsers su ", jsonObject + "");

                    GsonBuilder builder = new GsonBuilder();
                    Object o = builder.create().fromJson(jsonObject.toString(), Object.class);


                    pro.dismiss();


                    builder.registerTypeAdapter(OnlineUsersList.class, new OnlineUsersDeserializer());
                    Gson gson = builder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
                    OnlineUsersList list = gson.fromJson(jsonObject.toString(), OnlineUsersList.class);
                    getSupportFragmentManager().beginTransaction().addToBackStack("onlineusers")
                            .replace(R.id.content_frame, OnlineUsersFragment.newInstance(list)).commit();
                }

                @Override
                public void failCallback(JSONObject jsonObject) {
                    Log.d("getOnlineUsers fa", jsonObject + "");
                    pro.dismiss();
                    Toast.makeText(getApplicationContext(),"Failed to fetch online list of users",Toast.LENGTH_LONG).show();
                }
            });

        }

        else if (item.getTitle().equals("Add Chatroom")) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

            ft.addToBackStack("add chatroom");

            // Create and show the dialog.
            DialogFragment newFragment = AddChatroomDialogFragment.newInstance();
            newFragment.show(ft,"xyz");

        }
        else if (item.getTitle().equals("Chatrooms")) {
            final ProgressDialog pro = new ProgressDialog(MainActivity.this);
            pro.setMessage("Getting List of Chatrooms ...");
            pro.show();

            cometChatroom.getAllChatrooms(new Callbacks() {
                @Override
                public void successCallback(JSONObject chatroomList) {

                    Log.d("got chatroom", chatroomList + "");
                    pro.dismiss();

                    GsonBuilder builder = new GsonBuilder();
                    Object o = builder.create().fromJson(chatroomList.toString(), Object.class);


                    pro.dismiss();


                    builder.registerTypeAdapter(ChatroomsList.class, new ChatroomsDeserializer());
                    Gson gson = builder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
                    ChatroomsList list = gson.fromJson(chatroomList.toString(), ChatroomsList.class);
                    getSupportFragmentManager().beginTransaction().addToBackStack("chatrooms")
                            .replace(R.id.content_frame, ChatroomFragment.newInstance(list)).commit();



                }

                @Override
                public void failCallback(JSONObject jsonObject) {

                    pro.dismiss();
                    Toast.makeText(getApplicationContext(),"Failed to fetch Chatrroms",Toast.LENGTH_LONG).show();

                }
            });

            /*cometChatroom.subscribe(true, new SubscribeChatroomCallbacks() {
                @Override
                public void onMessageReceived(JSONObject receivedMessage) {

                    Log.d("got receivedMessage", receivedMessage + "");
                }

                @Override
                public void onLeaveChatroom(JSONObject leaveResponse) {

                    Log.d("got leaveResponse", leaveResponse + "");
                }

                @Override
                public void onError(JSONObject errorResponse) {

                    Log.d("got errorResponse", errorResponse + "");
                }

                @Override
                public void gotChatroomMembers(JSONObject chatroomMembers) {

                    Log.d("got chatroomMembers", chatroomMembers + "");

                }

                @Override
                public void gotChatroomList(JSONObject chatroomList) {

                    Log.d("got chatroom", chatroomList + "");
                    pro.dismiss();

                    GsonBuilder builder = new GsonBuilder();
                    Object o = builder.create().fromJson(chatroomList.toString(), Object.class);


                    pro.dismiss();


                    builder.registerTypeAdapter(ChatroomsList.class, new ChatroomsDeserializer());
                    Gson gson = builder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
                    ChatroomsList list = gson.fromJson(chatroomList.toString(), ChatroomsList.class);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.content_frame, ChatroomFragment.newInstance(list)).commit();


                }

                @Override
                public void onAVChatMessageReceived(JSONObject response) {

                    Log.d("got chatroom", response + "");
                }

                ;

                @Override
                public void onActionMessageReceived(JSONObject response) {

                    Log.d("got chatroom", response + "");
                }

                ;
            });

*/
        } else {


            AlertDialog.Builder e = new AlertDialog.Builder(this);
            e.setTitle("Logout?");
            e.setMessage("Are you sure you want to log out?");
            e.setCancelable(false);
            e.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {

                    dialog.dismiss();

                    final ProgressDialog pro = new ProgressDialog(MainActivity.this);
                    pro.setMessage("Loging out ...");
                    pro.show();
                    cometChat.logout(new Callbacks() {
                        @Override
                        public void successCallback(JSONObject response) {
                            if (pro.isShowing())
                                pro.dismiss();

                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(intent);

                            finish();
                        }

                        @Override
                        public void failCallback(JSONObject response) {

                            if (pro.isShowing())
                                pro.dismiss();
                        }
                    });
                }
            });

            e.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {

                    dialog.dismiss();

                }
            });


            e.create().show();


        }


        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}

