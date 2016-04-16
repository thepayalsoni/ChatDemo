package com.payal.chatdemo.parser;

import java.util.List;

public class ChatroomsList {
        List<Chatrooms> chatroomsList;

        public ChatroomsList(List<Chatrooms> chatroomsList) {
            this.chatroomsList = chatroomsList;
        }


    public List<Chatrooms> getChatroomsList() {
        return chatroomsList;
    }
}