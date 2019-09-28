package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;

import com.bumptech.glide.Glide;

public class Main2Activity extends AppCompatActivity {

    private Toolbar toolbar;
    private ImageView imgView;
    private Button beforeBtn;
    private Button afterBtn;
    private GridView gridview;
    private GridLayoutManager GridLayoutManager;
    private Integer[] mThumbIds;

    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        beforeBtn = findViewById(R.id.beforeBtn);
        afterBtn = findViewById(R.id.afterBtn);

        Intent intent = getIntent();
        id = intent.getExtras().getString("id"); // 로그인 유저의 아이디
        Log.i("mainAct", "login ID : " + id);

        // 툴바 생성
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(id + " 님");
        setSupportActionBar(toolbar);

        mThumbIds = new Integer[]{
                R.drawable.img1, R.drawable.img2,
                R.drawable.img3, R.drawable.img4,
                R.drawable.img5
        };

        gridview = (GridView) findViewById(R.id.grid);
        //gridview.setAdapter(new ImageAdapter(this));
       // gridview.setOnItemClickListener(gridviewOnItemClickListener);

        // Glide
        /*imgView = (ImageView) findViewById(R.id.imgView);
        Glide.with(this).load(R.drawable.img1).into(imgView);
        petSort.setText("레오파드 게코 갤럭시");*/

        // event handler
        beforeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) { // 툴바 메뉴 생성
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { // 메뉴 아이템 클릭 시
        switch (item.getItemId()){
            case R.id.logout: // 로그아웃 클릭 시
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                break;
        }
        return true;
    }
}
