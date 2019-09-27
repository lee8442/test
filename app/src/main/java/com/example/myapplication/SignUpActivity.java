package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.example.VO.signUpVO;
import com.example.model.signUp_m;
import com.example.SharedPreferences.SharedPreference;

public class SignUpActivity extends AppCompatActivity {

    private signUp_m signUp_m;
    private signUpVO signUpVO;
    private SharedPreference SP;
    private boolean idCheckStatus = false;

    private EditText idInput;
    private EditText pwInput;
    private EditText nameInput;
    private EditText numberInput;
    private EditText addressInput;
    private RadioGroup genderGroup;
    private RadioButton manButton;
    private RadioButton womanButton;
    private Button idCheckBtn;
    private Button cancelBtn;
    private Button signUpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signUp_m = new signUp_m(SignUpActivity.this);
        SP = new SharedPreference();

        idInput = (EditText) findViewById(R.id.idInput); // 아이디 input
        pwInput = (EditText) findViewById(R.id.pwInput); // 패스워드 input
        nameInput = (EditText) findViewById(R.id.nameInput); // 이름 input
        numberInput = (EditText) findViewById(R.id.numberInput); // 휴대폰 번호 input
        addressInput = (EditText) findViewById(R.id.addressInput); // 주소 input
        genderGroup = (RadioGroup) findViewById(R.id.genderGroup); // 성별 RadioGroup
        manButton = (RadioButton) findViewById(R.id.gender_man); // 남성 RadioButton
        womanButton = (RadioButton) findViewById(R.id.gender_woman); // 여성 RadioButton
        idCheckBtn = (Button) findViewById(R.id.idCheckBtn); // id 체크 버튼
        cancelBtn = (Button) findViewById(R.id.cancelBtn); // 취소 버튼
        signUpBtn = (Button) findViewById(R.id.signUpBtn); // 회원가입 버튼

        // 기존 데이터 있을 경우 불러오기
        if(SP.getAttribute(SignUpActivity.this, "SAVE_DATA") != null) {
            idInput.setText(SP.getAttribute(SignUpActivity.this, "ID"));
            pwInput.setText(SP.getAttribute(SignUpActivity.this, "PW"));
            nameInput.setText(SP.getAttribute(SignUpActivity.this, "NAME"));
            numberInput.setText(SP.getAttribute(SignUpActivity.this, "NUMBER"));
            addressInput.setText(SP.getAttribute(SignUpActivity.this, "ADDRESS"));
            if(SP.getAttribute(SignUpActivity.this, "GENDER").equals("남성")) {
                manButton.setChecked(true);
            } else {
                womanButton.setChecked(true);
            }
        }

        // event handler
        idCheckBtn.setOnClickListener(new OnClickListener() { // 아이디 체크
            @Override
            public void onClick(View view) {
                if(signUp_m.idCheck(idInput.getText().toString())) {
                    Toast.makeText(getApplicationContext(),"중복된 아이디입니다. 변경해주세요.", Toast.LENGTH_SHORT).show();
                    idCheckStatus = false;
                } else {
                    Toast.makeText(getApplicationContext(),"가입할 수 있는 아이디입니다.", Toast.LENGTH_SHORT).show();
                    idCheckStatus = true;
                }
            }
        });

        signUpBtn.setOnClickListener(new OnClickListener() { // 회원가입
            @Override
            public void onClick(View view) {
                if (idCheckStatus) {
                    String id = idInput.getText().toString();
                    String pw = pwInput.getText().toString();
                    String name = nameInput.getText().toString();
                    String number = numberInput.getText().toString();
                    String address = addressInput.getText().toString();
                    RadioButton genderButton = (RadioButton) findViewById(genderGroup.getCheckedRadioButtonId());
                    String gender = genderButton.getText().toString();
                    signUpVO = new signUpVO(id, pw, name, number, address, gender);
                    boolean status = signUp_m.signUp(signUpVO);
                    if(status) {
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                        Toast.makeText(getApplicationContext(), "회원가입 되었습니다.", Toast.LENGTH_SHORT).show();
                        SP.removeAllAttribute(SignUpActivity.this);
                    } else {
                        Toast.makeText(getApplicationContext(), "회원가입에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "아이디 중복체크를 해주세요.", Toast.LENGTH_LONG).show();
                }
            }
        });

        cancelBtn.setOnClickListener(new OnClickListener() { // 취소
            @Override
            public void onClick(View view) {
                saveData();
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                Toast.makeText(getApplicationContext(),"회원가입이 취소되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public void onBackPressed()
    {
        saveData();
        super.onBackPressed();
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        Toast.makeText(getApplicationContext(),"회원가입이 취소되었습니다.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStop() {
        saveData();
        super.onStop();
    }

    // 데이터 저장
    private void saveData() {
        SP.setAttribute(SignUpActivity.this,"SAVE_DATA", "true");
        SP.setAttribute(SignUpActivity.this,"ID", idInput.getText().toString());
        SP.setAttribute(SignUpActivity.this,"PW", pwInput.getText().toString());
        SP.setAttribute(SignUpActivity.this,"NAME", nameInput.getText().toString());
        SP.setAttribute(SignUpActivity.this,"NUMBER", numberInput.getText().toString());
        SP.setAttribute(SignUpActivity.this,"ADDRESS", addressInput.getText().toString());
        RadioButton genderButton = (RadioButton) findViewById(genderGroup.getCheckedRadioButtonId());
        String gender = genderButton.getText().toString();
        SP.setAttribute(SignUpActivity.this,"GENDER", gender);
    }
}
