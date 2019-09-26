package com.example.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.VO.loginVO;
import com.example.db.DBHelper;

public class login_m {

    private DBHelper DBHelper;
    private SQLiteDatabase db;
    private String TABLE_NAME = DBHelper.TABLE_NAME;

    public login_m(Context context) {
        DBHelper = com.example.db.DBHelper.getInstance(context);
        db = DBHelper.getDb();
    }

    public void createTable() {
        DBHelper.onCreate(db);
        if(!isData()) {
            String sql = "insert into " + TABLE_NAME + "(id, pw, name, number, address, gender) values('traveller', 'abc123', '홍길동', '01000000000', '서울', '남성');";
            db.execSQL(sql);
        }
    }

    public boolean isData(){
        boolean isData = false;
        String sql = "select * from " + TABLE_NAME + ";";
        Cursor result = db.rawQuery(sql, null);
        // result(Cursor)객체가 비어있으면 false 리턴
        if(result.moveToFirst()){
            isData = true;
        }
        result.close();
        return isData;
    }

    public boolean login(loginVO loginVO) {
        boolean result = false;
        String sql = "select * from " + TABLE_NAME + " where id = '" + loginVO.getId() +"' and pw = '" + loginVO.getPw() + "';";
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst()){
            result = true;
        }
        cursor.close();
        return result;
    }
}
