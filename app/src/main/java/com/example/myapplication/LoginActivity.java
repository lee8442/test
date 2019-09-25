package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import com.example.db.DBHelper;

public class LoginActivity extends AppCompatActivity {

    private DBHelper DBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        DBHelper = com.example.db.DBHelper.getInstance(LoginActivity.this);
        DBHelper.create();
        Log.i(DBHelper.selectData(), DBHelper.selectData());
        Log.i("22","33");

        final EditText idInput = (EditText)findViewById(R.id.idInput); // 아이디 input
        final EditText pwInput = (EditText)findViewById(R.id.pwInput); // 패스워드 input

        Button loginBtn = (Button) findViewById(R.id.cancelBtn); // 로그인 버튼
        Button signUpBtn = (Button) findViewById(R.id.signUpBtn); // 회원가입 버튼

        // event handler
        loginBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                final String userId = idInput.getText().toString();
                final String userPw = pwInput.getText().toString();
                Log.i(userId, userPw);
                Log.i(DBHelper.selectData(), DBHelper.selectData());
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                //finish();
            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                //finish();
            }
        });
    }
}
