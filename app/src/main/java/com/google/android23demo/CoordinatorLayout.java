package com.google.android23demo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class CoordinatorLayout extends AppCompatActivity {
   private FloatingActionButton mfab;
       Snackbar snackbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator_layout);
        mfab=(FloatingActionButton)findViewById(R.id.fab);
        mfab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CoordinatorLayout.this,snackbar+"",Toast.LENGTH_SHORT).show();
                if(snackbar==null)
                {
                  snackbar =Snackbar.make(v,"floatActionButton",Snackbar.LENGTH_LONG);
                    snackbar .setAction("do", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                }
                            }).show();
                }
                else{
                    if(snackbar.isShown())
                    {
                        snackbar.dismiss();
                    }else {
                        snackbar.show();
                    }

                }

            }
        });
    }
}
