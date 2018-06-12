package com.example.hp.playearnlocal.fragment;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.hp.playearnlocal.R;
import com.example.hp.playearnlocal.activity_1.CallBackInterface;
import com.example.hp.playearnlocal.activity_1.MainActivity;

public class Fragment_2 extends Fragment {

    View frag2_View;
    Button frag2_btnview;
    CallBackInterface callBackInterface;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        frag2_View = inflater.inflate(R.layout.fragment_2, container, false);
        initUI();
        return frag2_View;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(savedInstanceState!=null)
            callBackInterface = (MainActivity) getActivity();
    }
    public void setInterface(CallBackInterface callBackInterface){
        this.callBackInterface = callBackInterface;
    }
    private void initUI() {
        MainActivity m = (MainActivity) getActivity();
        if(m.findViewById(R.id.activity_main_portrait)!= null) {
            frag2_btnview = frag2_View.findViewById(R.id.frag2_btnview);
            frag2_btnview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i("Frag2Click", "CLicked on Frag1");
                    if (callBackInterface != null) {
                        Log.i("Frag2Click", "CallBack Not Null");
                        callBackInterface.callBackMethod("frag2");
                    } else {
                        Log.i("Frag1Click", "CallBack Null");

                    }
                }
            });
        }else{
            frag2_btnview = frag2_View.findViewById(R.id.frag2_btnview);
            frag2_btnview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i("Frag2Click", "CLicked on Frag1");
                    if (callBackInterface != null) {
                        Log.i("Frag2Click", "CallBack Not Null");
                        callBackInterface.callBackMethod("abc");
                    } else {
                        Log.i("Frag1Click", "CallBack Null");

                    }
                }
            });
        }
    }

}
