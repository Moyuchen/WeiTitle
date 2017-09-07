package com.bwie.test.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bwie.test.SQliste.SqLite;

/**
 * User: 张亚博
 * Date: 2017-09-05 19:38
 * Description：
 */
public class SQLiteUtils {

    private final SqLite sq;

    public SQLiteUtils(Context context) {
        sq = new SqLite(context);
    }

   public void  add(String type,String con){
       SQLiteDatabase write = sq.getWritableDatabase();
       ContentValues values=new ContentValues();
       values.put("type",type);
       values.put("content",con);
       write.insert("Weitoutiao",null,values);
        write.close();


   }
    public void  addPinDao(String pindao,String isSelected){
        SQLiteDatabase write = sq.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("pindao",pindao);
        values.put("isSelected",isSelected);
        write.insert("Pindao",null,values);
        write.close();


    }

public Cursor Select(String type){
    SQLiteDatabase write = sq.getWritableDatabase();
    Cursor weitoutiao = write.query("Weitoutiao",null,"type=?", new String[]{type}, null, null, null);
    write.close();
    return  weitoutiao;
}
public Cursor SelectPinDao(String pindao){
    SQLiteDatabase write = sq.getWritableDatabase();
    Cursor weitoutiao = write.query("Pindao",null,"pindao=?", new String[]{pindao}, null, null, null);
    write.close();
    return  weitoutiao;
}



}
