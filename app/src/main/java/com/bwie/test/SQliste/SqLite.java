package com.bwie.test.SQliste;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * User: 张亚博
 * Date: 2017-09-05 19:35
 * Description：
 */
public class SqLite extends SQLiteOpenHelper {
    public SqLite(Context context) {
        super(context, "weitoutiao.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table Weitoutiao(type varchar(200),content text)");
        sqLiteDatabase.execSQL("create table Pindao(pindao varchar(200),pincontent text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
