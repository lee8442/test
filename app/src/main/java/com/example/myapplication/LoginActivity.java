package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.VO.loginVO;
import com.example.model.login_m;

public class LoginActivity extends AppCompatActivity {

    private login_m login_m;
    private loginVO loginVO;
    private final long FINISH_INTERVAL_TIME = 2000;
    private long backPressedTime = 0;

    private EditText idInput;
    private EditText pwInput;
    private Button loginBtn;
    private Button signUpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_m = new login_m(LoginActivity.this);
        login_m.createTable();

        idInput = (EditText) findViewById(R.id.idInput); // 아이디 input
        pwInput = (EditText) findViewById(R.id.pwInput); // 패스워드 input
        loginBtn = (Button) findViewById(R.id.cancelBtn); // 로그인 버튼
        signUpBtn = (Button) findViewById(R.id.signUpBtn); // 회원가입 버튼

        // event handler
        loginBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String userId = idInput.getText().toString();
                String userPw = pwInput.getText().toString();
                Log.i(userId, userPw);
                loginVO = new loginVO(userId, userPw);
                if(login_m.login(loginVO)) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.putExtra("id", userId);
                    startActivity(intent);
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    Toast.makeText(getApplicationContext(),loginVO.getId() + "님 환영합니다. ",Toast.LENGTH_LONG).show();
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                    builder.setTitle("로그인 정보가 맞지 않습니다.");
                    builder.setPositiveButton("예",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(getApplicationContext(),"아이디 혹은 패스워드를 확인해주세요.",Toast.LENGTH_LONG).show();
                                }
                            });
                    builder.show();
                }
            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        long tempTime = System.currentTimeMillis();
        long intervalTime = tempTime - backPressedTime;

        if (0 <= intervalTime && FINISH_INTERVAL_TIME >= intervalTime)
        {
            super.onBackPressed();
            Toast.makeText(getApplicationContext(), "앱을 종료합니다.", Toast.LENGTH_SHORT).show();
        }
        else
        {
            backPressedTime = tempTime;
            Toast.makeText(getApplicationContext(), "한번 더 누르면 앱이 종료됩니다.", Toast.LENGTH_SHORT).show();
        }
    }
}
