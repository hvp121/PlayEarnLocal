package com.example.hp.playearnlocal.activity_1;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.hp.playearnlocal.R;
import com.example.hp.playearnlocal.Services.SampleService;
import com.example.hp.playearnlocal.Services.SampleService2;
import com.example.hp.playearnlocal.fragment.Fragment_1;
import com.example.hp.playearnlocal.fragment.Fragment_2;

public class MainActivity extends AppCompatActivity implements CallBackInterface{

    Fragment fragment,fragment2;
    Button startActivityButton,contentProvider,playEarn;
    int serviceCount =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(findViewById(R.id.activity_main_portrait) != null){
        if(savedInstanceState == null)
            addFrag1();
            contentProvider = (Button) findViewById(R.id.contentProvider);
            contentProvider.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(MainActivity.this,ContentProviderExample.class);
                    startActivity(i);
                }
            });
            startActivityButton = (Button) findViewById(R.id.startactivitybutton);
            startActivityButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getApplicationContext(),"Clicked .." ,Toast.LENGTH_SHORT).show();
                    serviceCount= serviceCount+1;
                    Intent i = new Intent(MainActivity.this, SampleService2.class);
                    i.putExtra("Count",serviceCount);
                    startService(i);
                }
            });

            playEarn = (Button) findViewById(R.id.playEarn);
            playEarn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(MainActivity.this,PlayEarnMainActivity.class);
                    startActivity(i);
                }
            });
        }
        else if(findViewById(R.id.activity_main_landscape) != null){
            if(savedInstanceState == null) {
                addFrag1();
            }
            else{
                addFrag2(R.id.add_frag2);
            }
        }

    }

    @Override
    public void callBackMethod(String frag) {
        //Toast.makeText(getApplicationContext(),"Adding New Frag",Toast.LENGTH_SHORT).show();
        if(findViewById(R.id.activity_main_portrait) != null) {

            fragment = getSupportFragmentManager().findFragmentById(R.id.add_frag);
            if (fragment instanceof Fragment_1)
                addFrag2();
            else
                addFrag1();
        } else if (findViewById(R.id.activity_main_landscape) != null) {
            if(frag.equals("frag2")){
                fragment = getSupportFragmentManager().findFragmentById(R.id.add_frag2);
                if (fragment instanceof Fragment_2) {
                    //fragment = getSupportFragmentManager().findFragmentById(R.id.add_frag);
                    addFrag1();
                } else {
                    addFrag2();
                }
            }
            /*Toast.makeText(getApplicationContext(),"Landscape Frag",Toast.LENGTH_SHORT).show();

            fragment = getSupportFragmentManager().findFragmentById(R.id.add_frag);
            if (fragment instanceof Fragment_1) {
                //fragment = getSupportFragmentManager().findFragmentById(R.id.add_frag);
                addFrag2();
            } else {
                addFrag1();
            }
            fragment2 = getSupportFragmentManager().findFragmentById(R.id.add_frag2);
            if(fragment2 instanceof Fragment_2)
                addFrag2(R.id.add_frag1);
            else
                addFrag1();*/

        }
    }


    public void addFrag1(){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment_1 first = new Fragment_1();
        first.setInterface(this);
        ft.replace(R.id.add_frag,first);
        //ft.addToBackStack(null);
        ft.commit();
    }

    public void addFrag2(){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment_2 second = new Fragment_2();
        second.setInterface(this);
        ft.replace(R.id.add_frag,second);
        //ft.addToBackStack(null);
        ft.commit();
    }
    public void addFrag2(int containerId){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment_2 second = new Fragment_2();
        second.setInterface(this);
        ft.replace(containerId,second);
        //ft.addToBackStack(null);
        ft.commit();
    }
}
