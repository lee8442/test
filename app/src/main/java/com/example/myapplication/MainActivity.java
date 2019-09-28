package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ImageView imgView;
    private Button beforeBtn;
    private Button afterBtn;
    private Button img1Btn;
    private Button img2Btn;
    private Button img3Btn;
    private Button img4Btn;
    private Button img5Btn;
    private TextView petSort;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        beforeBtn = (Button) findViewById(R.id.beforeBtn);
        afterBtn = (Button) findViewById(R.id.afterBtn);
        img1Btn = (Button) findViewById(R.id.button1);
        img2Btn = (Button) findViewById(R.id.button2);
        img3Btn = (Button) findViewById(R.id.button3);
        img4Btn = (Button) findViewById(R.id.button4);
        img5Btn = (Button) findViewById(R.id.button5);
        petSort = (TextView) findViewById(R.id.petSort);

        Intent intent = getIntent();
        String id = intent.getExtras().getString("id"); // 로그인 유저의 아이디
        Log.i("mainAct", "login ID : " + id);

        // 툴바 생성
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(id + " 님");
        setSupportActionBar(toolbar);

        // Glide
        imgView = (ImageView) findViewById(R.id.imgView);
        Glide.with(this).load(R.drawable.img1).into(imgView);
        petSort.setText("레오파드 게코 갤럭시");

        // event handler
        img1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Glide.with(MainActivity.this).load(R.drawable.img1).into(imgView);
                petSort.setText("레오파드 게코 갤럭시");
            }
        });

        img2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Glide.with(MainActivity.this).load(R.drawable.img2).into(imgView);
                petSort.setText("레오파드 게코 썬글로우");
            }
        });

        img3Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Glide.with(MainActivity.this).load(R.drawable.img3).into(imgView);
                petSort.setText("레오파드 게코 썬글로우");
            }
        });

        img4Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Glide.with(MainActivity.this).load(R.drawable.img4).into(imgView);
                petSort.setText("레오파드 게코 스이클");
            }
        });

        img5Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Glide.with(MainActivity.this).load(R.drawable.img5).into(imgView);
                petSort.setText("레오파드 게코 유니버스");
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
