package com.zlgspace.news.ui;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import com.zlgspace.easyreqpermission.EasyReqPermission;
import com.zlgspace.easyreqpermission.annotation.ForbidPermission;
import com.zlgspace.easyreqpermission.annotation.NeedPermission;
import com.zlgspace.easyreqpermission.annotation.RefusePermission;
import com.zlgspace.news.MainActivity;
import com.zlgspace.news.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReqPermissionActivity extends Activity {

    private ReqPermissionActivity_ReqPermission reqPermission;

    @BindView(R.id.hintTv)
    protected TextView hintTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reqpermission);
        reqPermission = EasyReqPermission.bind(this);
        ButterKnife.bind(this);

        reqPermission.toMainActivity();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        Log.d("ReqPermissionActivity","onRequestPermissionsResult");
        reqPermission.onRequestPermissionsResult(requestCode,permissions,grantResults);
    }

    @NeedPermission(permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE},identifier = "toMainActivity")
    public void toMainActivity(){
        hintTv.setText("已获取权限，即将进入！");
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @RefusePermission(identifier = "toMainActivity")
    public void refusePermission(){
        forbidPermission();
    }

    @ForbidPermission(identifier = "toMainActivity")
    public void forbidPermission(){
//        Toast.makeText(this,"权限不足，即将退出！",Toast.LENGTH_LONG).show();
        hintTv.setText("权限不足，即将退出！");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        },3000);
    }
}
