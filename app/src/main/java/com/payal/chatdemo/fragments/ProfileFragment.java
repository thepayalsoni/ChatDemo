package com.payal.chatdemo.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.payal.chatdemo.R;
import com.payal.chatdemo.parser.ProfileInfo;
import com.payal.chatdemo.views.RoundImageView;

/**
 * Created by payal on 4/14/2016.
 */
public class ProfileFragment extends Fragment {

    RoundImageView userImage;
    TextView status,userName, message;
    ProfileInfo detail;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (container != null) {
            container.removeAllViews();
        }



        detail =(ProfileInfo)getArguments().getSerializable("storydata");

        View v = inflater.inflate(R.layout.profile_fragment, container, false);

        userImage =(RoundImageView) v.findViewById(R.id.detail_user_image);
        userImage.setRadius(360);
        userName =(TextView) v.findViewById(R.id.user_name);
        status =(TextView) v.findViewById(R.id.status);
        message =(TextView) v.findViewById(R.id.message);


        if(detail.getName()!=null)
            userName.setText(detail.getName());
        if(detail.getS()!=null)
            status.setText(" ("+detail.getS()+") ");
        if(detail.getM()!=null)
            message.setText(detail.getM());





        return v;
    }

    public static ProfileFragment newInstance(ProfileInfo data) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putSerializable("storydata",data);
        fragment.setArguments(args);

        return fragment;
    }

}
