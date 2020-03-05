//package com.example.retrofittest;
//
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//
///**
// * @author: Administrator
// * @date: 2020-03-04
// */
//public abstract class BaseFragment extends Fragment {
//
//    private boolean isVisible=false;//当前Fragment是否可见
//    private boolean isInitView=false;//是否与View建立映射关系
//    private boolean isFirstLoad=true;//是否第一次加载数据
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(getLayoutId(), container, false);
//        initData();
//        initView(view);
//
//        return view;
//    }
//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//    }
//
//
//
//    public abstract int getLayoutId();
//
//    public abstract void initView(View view);
//
//    public abstract void initData();
//}