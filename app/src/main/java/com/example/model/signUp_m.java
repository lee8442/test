package com.example.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.VO.signUpVO;
import com.example.db.DBHelper;

public class signUp_m {

    private DBHelper DBHelper;
    private SQLiteDatabase db;
    private String TABLE_NAME = DBHelper.TABLE_NAME;

    public signUp_m(Context context) {
        DBHelper = com.example.db.DBHelper.getInstance(context);
        db = DBHelper.getDb();
    }

    public boolean signUp(signUpVO signUpVO) {
        boolean result = false;
        if(!idCheck(signUpVO.getId())) {
            String sql = "insert into " + TABLE_NAME + "(id, pw, name, number, address, gender) " +
                    "values('" + signUpVO.getId() + "', '" + signUpVO.getPw() + "', '" + signUpVO.getName() + "', '" + signUpVO.getNumber() + "', '" + signUpVO.getAddress() + "', '" + signUpVO.getGender() + "');";
            db.execSQL(sql);
            result = true;
        }
        return result;
    }

    public boolean idCheck(String id) {
        boolean result = false;
        String sql = "select * from " + TABLE_NAME + " where id = '" + id + "';";
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst()){
            result = true;
        }
        cursor.close();
        return result;
    }
}
