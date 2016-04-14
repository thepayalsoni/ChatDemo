package com.payal.chatdemo.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.payal.chatdemo.R;

/**
 * Created by payal on 4/14/2016.
 */
public class ProfileFragment extends Fragment {
    ImageLoader imloader;


    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();

        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (container != null) {
            container.removeAllViews();
        }

        imloader = ImageLoader.getInstance();
        imloader.init(ImageLoaderConfiguration.createDefault(getActivity().getApplicationContext()));
        View v = inflater.inflate(R.layout.profile_fragment, container, false);

        return v;
    }


}
