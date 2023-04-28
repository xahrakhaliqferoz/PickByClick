package com.abidingtech.pick_by_click;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.abidingtech.pick_by_click.databinding.ActivityHomectivityBinding;
import com.abidingtech.pick_by_click.databinding.FragmentUserBinding;
import com.google.firebase.auth.FirebaseAuth;


public class UserFragment extends Fragment {
 FragmentUserBinding binding;
    public UserFragment() {
        // Required empty public constructor

    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user, container, false);
       binding=FragmentUserBinding.inflate(getLayoutInflater());

        return  view;
    }
}