package com.example.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static DBHelper sqLiteHelper = null;
    public static final String DATABASE_NAME = "test.db";
    public static final String TABLE_NAME = "member";
    public static final int DB_VERSION = 1;
    public static final String IDX = "IDX";
    public static final String COL_0 = "id";
    public static final String COL_1 = "pw";
    public static final String COL_2 = "name";
    public static final String COL_3 = "number";
    public static final String COL_4 = "address";
    public static final String COL_5 = "gender";

    private SQLiteDatabase db;

    public static DBHelper getInstance(Context context) {
        if(sqLiteHelper == null){
            sqLiteHelper = new DBHelper(context);
        }
        return sqLiteHelper;
    }

    private DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION);
        db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists " + TABLE_NAME + " ("
                + IDX + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_0 + " TEXT, "
                + COL_1 + " TEXT, "
                + COL_2 + " TEXT, "
                + COL_3 + " TEXT, "
                + COL_4 + " TEXT, "
                + COL_5 + " TEXT"
                + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void create() {
        onCreate(db);
        if(!isData()) {
            String sql = "insert into " + TABLE_NAME + "(id, pw, name, number, address, gender) values('traveller', 'abc123', '홍길동', '01000000000', '서울', '남성');";
            insertData(sql);
        }
    }

    // 데이터의 존재 유무 확인
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

    public void insertData(String sql) {
        db.execSQL(sql);
    }

    public String selectData() {
        String result = "";
        String sql = "select * from " + TABLE_NAME + ";";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToNext();
        result = cursor.getString(2);
        cursor.close();
        return result;
    }
}
