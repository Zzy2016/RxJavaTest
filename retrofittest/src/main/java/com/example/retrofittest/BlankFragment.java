package com.example.retrofittest;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class BlankFragment extends BaseFragment {

    public static BlankFragment newInstance(String param1, String param2) {
        BlankFragment fragment = new BlankFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
        return fragment;
    }

    TextView textView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("BlankFragment","onCreate");

    }


    @Override
    public int getLayoutId() {
        Log.e("BlankFragment","getLayoutId");

        return R.layout.fragment_blank;
    }

    @Override
    public void initView(View view) {
        textView = (TextView) view.findViewById(R.id.text);
        textView.setText("0000000000000000");
        Log.e("BlankFragment","initView");
    }


    @Override
    public void initData() {
        Log.e("BlankFragment","initData");
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }


}
