package com.sqlitedemo.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import android.util.Log;

/**
 * 数据库类
 * Created by wbg on 2017/11/29.
 *
 * 创建数据库
 * 插入表
 * 在表中添加数据
 * 在表中更新数据
 * 查询表中的数据
 * 在表中删除数据
 *
 */

public class DBbaseUtil extends SQLiteOpenHelper{
    private String TAG="BDbaseUtil";
    private static final String DB_NAME="mySqlite.db";//数据可名称
    private static int version=1;
    public DBbaseUtil(Context context) {
        super(context, DB_NAME, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG,"onCreate");
//        db.execSQL(Constant.CREATE_TABLE_ACCOUNT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
    @Override
    public synchronized void close() {
        super.close();
    }
    //创建或打开数据库，已有数据库则打开，无数据库则创建
    public SQLiteDatabase openSQLiteDatabase(){
        return  this.getWritableDatabase();//读写模式
    }

    //获取版本号
    public int getVersion(){
        SQLiteDatabase db= this.getWritableDatabase();//读写模式
       return db.getVersion();
    }

    /**
     * 创建表
     * @param table 表名
     */
    public void createTable(String table){
        SQLiteDatabase db= this.openSQLiteDatabase();
        db.execSQL(table);
    }

    /**
     * 插入数据
     * @param table 表名
     * @param c 列键值对
     * @return 返回新插入的行的行ID，如果发生错误，则是-1。
     */
    public long insert(String table, ContentValues c){
        SQLiteDatabase db= this.openSQLiteDatabase();
       long id= db.insert(table,null,c);
       db.close();
       return id;
    }

    /**
     * 修改某参数的值
     * @param tableName 表名
     * @param args 字段
     * @param OldArgsValue 旧字段值
     * @param NewArgsValue 新的字段值
     * @return 受影响的行数
     */
    public int update(String tableName,String args,String OldArgsValue,String NewArgsValue){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues cv = new ContentValues();//实例化ContentValues
        cv.put(args,NewArgsValue);//添加要更改的字段及内容
        String whereClause = args+"=?";//修改条件
        String[] whereArgs = {OldArgsValue};//修改的旧参数
       int num= db.update(tableName,cv,whereClause,whereArgs);//执行修改
        db.close();
        return num;
    }
    /**
     * 查询表中某一个参数值对应的数据
     * @param tableName 表名
     * @param args 字段
     * @param argsValue 需要被查询的字段值
     * @return c
     */
    public Cursor rawQuery(String tableName,String args,String argsValue){
        SQLiteDatabase db= this.getReadableDatabase();//只读模式
        String sql = "select * from " + tableName + " where " + args+"=?";
        String[] selectionArgs= new String[] {argsValue };
        Cursor c = db.rawQuery(sql,selectionArgs);

        db.close();
        return c;
    }
    /**
     * 查询表中某一个参数值对应的数据
     * @param tableName 表名
     * @param args 字段
     * @param argsValue 需要被查询的字段值
     * @return
     */
    public Cursor query(String tableName,String args,String argsValue){
        SQLiteDatabase db= this.getReadableDatabase();//只读模式
        String selection= args+"=?";
        String[] selectionArgs= new String[] {argsValue };
        Cursor c = db.query(tableName,null,selection,selectionArgs,null,null,null);//查询并获得游标
        db.close();
        return c;
    }
    /**
     * 查询表中所有数据
     * @param tableName
     */
    public Cursor query(String tableName){
        SQLiteDatabase db= this.getReadableDatabase();//只读模式
        Cursor c = db.query(tableName,null,null,null,null,null,null);//查询并获得游标db.close();
        return c;
    }

    /**
     * 删除数据
     * @param tableName 表名
     * @param args 字段
     * @param argsValue 字段对应的值
     * @return
     */
    public int delete(String tableName,String args,String argsValue){
        SQLiteDatabase db= this.getWritableDatabase();
        String whereClause = args+"=?";//修改条件
        String[] whereArgs = {argsValue};//参数值
       int num= db.delete(tableName,whereClause,whereArgs);
       db.close();
       return num;
    }


    /**
     * 查看表是否已存在
     * @param tableName 表名
     * @return
     */
    public boolean tableIsExist(String tableName){
        boolean result = false;
        if(TextUtils.isEmpty(tableName)){
            return false;
        }
        SQLiteDatabase db = null;
        try {
            db = this.getReadableDatabase();
            String sql="select name from sqlite_master where type='table'";
            Cursor cursor = db.rawQuery(sql, null);
            while(cursor.moveToNext()){
                //遍历出表名
                String name = cursor.getString(0);
                if(tableName.equals(name)){
                    return true;
                }
            }


        } catch (Exception e) {
            // TODO: handle exception
        }
        return result;
    }

}
