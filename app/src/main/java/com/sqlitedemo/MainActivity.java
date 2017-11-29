package com.sqlitedemo;

import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.sqlitedemo.util.AccountDBUtil;
import com.sqlitedemo.util.Constant;
import com.sqlitedemo.util.DBbaseUtil;

public class MainActivity extends AppCompatActivity {
    private String TAG = "MainActivity";
//    private DBbaseUtil bBbaseUtil;
    private AccountDBUtil accountDBUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        bBbaseUtil = new DBbaseUtil(MainActivity.this);
        accountDBUtil=new AccountDBUtil(MainActivity.this);
        initView();
    }

    private void initView() {
        //创建数据库
//        findViewById(R.id.createDB).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                accountDBUtil=new AccountDBUtil(MainActivity.this);
//            }
//        });
//        //获取版本号
//        findViewById(R.id.getversionId).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int version = bBbaseUtil.getVersion();
//                Log.i(TAG, "version=" + version);
//            }
//        });
//        //创建表
//        findViewById(R.id.createTable).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                bBbaseUtil.createTable(Constant.CREATE_TABLE_ACCOUNT);
//            }
//        });
        //插入数据
        findViewById(R.id.insert).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    accountDBUtil.insert();
            }
        });
        //查询数据
        findViewById(R.id.query).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                accountDBUtil.query();
            }
        });
        //修改数据
        findViewById(R.id.update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                accountDBUtil.update();
            }
        });
        //删除数据
        findViewById(R.id.delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                accountDBUtil.delete();
            }
        });
    }
}
