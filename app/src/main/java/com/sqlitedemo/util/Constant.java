package com.sqlitedemo.util;

/**
 * Created by Administrator on 2017/11/29.
 */

public class Constant {
    public static  final String ACCOUNT_TABLE_NAME="account";//账号的表名称
    public static  final String CREATE_TABLE_ACCOUNT= "create table if not exists "
            + ACCOUNT_TABLE_NAME
            + "( userid integer PRIMARY KEY AUTOINCREMENT , "//自增
            + "phone varchar(20), "
            + "nickname varchar(20),"
            +"sex  vaechar(20))";//账号的表
}
