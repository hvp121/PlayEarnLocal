package com.example.hp.playearnlocal.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.hp.playearnlocal.R;
import com.example.hp.playearnlocal.activity_1.CallBackInterface;
import com.example.hp.playearnlocal.activity_1.MainActivity;

public class Fragment_1 extends Fragment {

    View frag1_View;
    Button frag1_btnview;
    CallBackInterface callBackInterface;


    //Another method to pass class object to interface instance
    /*@Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof CallBackInterface) {
            callBackInterface = (CallBackInterface) context;
        } else {
            throw new ClassCastException(context.toString()
                    + " must implement MyListFragment.OnItemSelectedListener");
        }
    }*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        frag1_View= inflater.inflate(R.layout.fragment_1, container, false);
        initUI();
        return frag1_View;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(savedInstanceState!=null)
        callBackInterface = (MainActivity) getActivity();
    }

    private void initUI() {
        frag1_btnview = frag1_View.findViewById(R.id.frag1_btnview);
        frag1_btnview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("Frag1Click","CLicked on Frag1");
                if(callBackInterface != null) {
                    Log.i("Frag1Click","CallBack Not Null");
                    callBackInterface.callBackMethod("frag1");
                }
                else{
                    Log.i("Frag1Click","CallBack Null");

                }
            }
        });
    }

    public void setInterface(CallBackInterface callBackInterface) {
        this.callBackInterface = callBackInterface;
        //this.callBackInterface =(MainActivity) getActivity();

        Log.i("CallBACKINterface",""+callBackInterface);

    }
}
