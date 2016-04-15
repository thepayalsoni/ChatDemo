package com.payal.chatdemo.parser;

import java.util.List;

public class OnlineUsersList {
        List<OnlineUsers> users;

        public OnlineUsersList(List<OnlineUsers> users) {
            this.users = users;
        }


    public List<OnlineUsers> getUsers() {
        return users;
    }
}