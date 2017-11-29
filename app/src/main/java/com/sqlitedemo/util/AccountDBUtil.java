package com.sqlitedemo.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

/**
 * Created by Administrator on 2017/11/29.
 */

public class AccountDBUtil {
    private String TAG = "AccountDBUtil";
    private DBbaseUtil dBbaseUtil;

    public static final String ACCOUNT_TABLE_NAME = "account";//账号的表名称
    public static final String CREATE_TABLE_ACCOUNT = "create table if not exists "
            + ACCOUNT_TABLE_NAME
            + "(userid integer PRIMARY KEY AUTOINCREMENT , "//自增
            + "phone varchar(20), "
            + "nickname varchar(20),"
            + "sex  vaechar(20))";//账号的表

    public AccountDBUtil(Context context) {
        dBbaseUtil = new DBbaseUtil(context);
        dBbaseUtil.openSQLiteDatabase();
        if (!dBbaseUtil.tableIsExist(ACCOUNT_TABLE_NAME)) {//判断该表是否存在
            dBbaseUtil.createTable(CREATE_TABLE_ACCOUNT);//创建表
        }
    }

    /**
     * 插入数据
     */
    public void insert() {
        String[] phone = {"123", "456", "789", "2324", "34345", "789541"};
        for (int i = 0; i < 6; i++) {
            ContentValues values = new ContentValues();
            values.put("phone", phone[i]);
            values.put("nickname", "ABC");
            long num = dBbaseUtil.insert(Constant.ACCOUNT_TABLE_NAME, values);
            Log.i(TAG, "insert num=" + num);
        }
    }

    /**
     * 修改数据
     */
    public void update() {
      int num=  dBbaseUtil.update(Constant.ACCOUNT_TABLE_NAME, "phone", "123", "456");
        Log.i(TAG, "update num=" + num);
    }

    /**
     * 查询
     */
    public void query() {
//        dBbaseUtil.rawQuery(Constant.ACCOUNT_TABLE_NAME, "phone", "1234");
//        dBbaseUtil.query(Constant.ACCOUNT_TABLE_NAME, "phone", "1234");
        Cursor c = dBbaseUtil.query(ACCOUNT_TABLE_NAME);
        int acoun=c.getCount();
       int position= c.getPosition();
      int ColumnCount= c.getColumnCount();
        Log.i(TAG,"query acoun="+acoun);
        Log.i(TAG,"query position="+position);
        Log.i(TAG,"query ColumnCount="+ColumnCount);
        while (c.moveToNext()) {
            Log.e(TAG, "Column index userid=" + c.getInt(0)
                    + "  phone=" + c.getString(1)
                    + " nickname=" + c.getString(2));
            Log.e(TAG, "Column  name userid=" + c.getInt(c.getColumnIndex("userid"))
                    + "  phone=" + c.getString(c.getColumnIndex("phone"))
                    + " nickname=" + c.getString(c.getColumnIndex("nickname")));


        }
    }

    /**
     * 删除
     */
    public void delete() {
      int num= dBbaseUtil.delete(Constant.ACCOUNT_TABLE_NAME, "phone", "456");
        Log.i(TAG, "update num=" + num);
    }
}
