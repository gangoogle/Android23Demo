package com.google.android23demo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PhoneActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText mEtPhoneNumber;
    private Button mBtCall;
    private Button mSnackBar;
    private TextInputLayout mTlPassword;
    private Button mBtLogin;
    private Button mBtViewPager;
    private Button mBtFloatAction;

    private final int REQUEST_CALL_PHONE_PERMISSION=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * 5.0以上修改状态栏和导航栏的颜色
         */
//        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP)
//        {
//            Window window=getWindow();
//            window.setStatusBarColor(getResources().getColor(R.color.colorPrimary));
//            window.setNavigationBarColor(getResources().getColor(R.color.colorPrimary));
//        }
        /**
         * 4.4以上透明状态栏
         */
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT)
        {
            Window window=getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        /**
         * 5.0上状态栏改为全透明
         */
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP)
        {
            Window window=getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
        View contentView=View.inflate(this,R.layout.activity_phone,null);
        contentView.setPadding(0,getStatusBarHeight(),0,0);
        setContentView(contentView);
        mEtPhoneNumber=(EditText)findViewById(R.id.et_phone_number);
        mBtCall=(Button)findViewById(R.id.bt_call);
        mSnackBar=(Button)findViewById(R.id.bt_snackbar);
        mTlPassword=(TextInputLayout)findViewById(R.id.tl__password);
        mBtLogin=(Button)findViewById(R.id.bt_login);
        mBtViewPager=(Button)findViewById(R.id.bt_viewpager);
        mBtFloatAction=(Button)findViewById(R.id.bt_float_action);
        mBtCall.setOnClickListener(this);
        mSnackBar.setOnClickListener(this);
        mBtViewPager.setOnClickListener(this);
        mBtFloatAction.setOnClickListener(this);

        inputPassword();

    }

    /**
     * 处理密码输入域
     */
    public void inputPassword(){
       final EditText editText= mTlPassword.getEditText();
        mTlPassword.setHintTextAppearance(R.style.hintStyle);
        mBtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.getText().toString().trim().length() < 5) {
                    mTlPassword.setError("密码错误");
                    mTlPassword.setErrorEnabled(true);

                } else {
                    mTlPassword.setError(null);
                    mTlPassword.setErrorEnabled(false);
                }
            }
        });
    }
    @Override
    public void onClick(View v) {
        if(v==mBtCall)
        {
           String phoneNumber= mEtPhoneNumber.getText().toString().trim();
            if(TextUtils.isEmpty(phoneNumber))
            {
                Toast.makeText(this,"号码不能为空!",Toast.LENGTH_SHORT).show();
            }else{
                   //判断版本是否大于等于6.0
                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M) {
                    //判断是否有呼叫权限
                    int permissionStatus= ContextCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE);
                    if(permissionStatus== PackageManager.PERMISSION_DENIED)
                    {
                        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL_PHONE_PERMISSION);
                    }
                    else{
                        call();
                    }
                }else {
                    call();
                }

            }
        }
        else if(v== mSnackBar)
        {
            Snackbar.make(v,"SnackBarTest！！！",Snackbar.LENGTH_SHORT).show();
        }else if(v==mBtViewPager)
        {
            Intent intent=new Intent(this,ViewPagerActivity.class);
            startActivity(intent);
        }else if (v==mBtFloatAction){
            Intent intent = new Intent(this,CoordinatorLayout.class);
            startActivity(intent);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode)
        {
            case REQUEST_CALL_PHONE_PERMISSION:
                if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED)
                {
                    call();
                }
            break;
        }
    }

    private void call(){
        String phoneNumber= mEtPhoneNumber.getText().toString().trim();
        Intent callIntent=new Intent("android.intent.action.CALL", Uri.parse("tel:"+phoneNumber));
        startActivity(callIntent);
    }

    /**
     * 获取状态栏高度
     */
    public int  getStatusBarHeight(){
        int result=0;
        int resourceid=getResources().getIdentifier("status_bar_height","dimen","android");
        if(resourceid>0)
        {
            result=getResources().getDimensionPixelSize(resourceid);
        }
        Toast.makeText(this,result+"resourceid:"+resourceid,Toast.LENGTH_SHORT).show();
        return result;

    }
}
