package com.payal.chatdemo;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by payal on 4/14/2016.
 */
public class ChatVolley {
    private static RequestQueue mRequestQueue;

    private ChatVolley() {
        // no instances
    }

    static void init(Context context) {
        mRequestQueue = createVolleyRequestQue(context);
    }

    public static RequestQueue createVolleyRequestQue(Context context) {

            return Volley.newRequestQueue(context);
    }

    public static RequestQueue getRequestQueue(Context mContext) {
        if (mRequestQueue == null) {
        }
        return mRequestQueue;
    }
}