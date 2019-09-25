package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText idInput = (EditText)findViewById(R.id.idInput); // 아이디 input
        final EditText pwInput = (EditText)findViewById(R.id.pwInput); // 패스워드 input

        Button loginBtn = (Button) findViewById(R.id.loginBtn); // 로그인 버튼
        Button signUpBtn = (Button) findViewById(R.id.signUpBtn); // 회원가입 버튼

        // event handler
        loginBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                final String userId = idInput.getText().toString();
                final String userPw = pwInput.getText().toString();
                Log.i(userId, userPw);
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                finish();
            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                finish();
            }
        });
    }
}
