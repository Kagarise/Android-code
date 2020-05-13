package cn.kagarise.test3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText et_name;
    private EditText et_password;
    private Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        Map<String, String> userInfo = SaveInfo.getUserInfo(this);
        if(userInfo != null){
            et_name.setText(userInfo.get("name"));
            et_password.setText(userInfo.get("password"));
        }
    }

    private void initView() {
        et_name = (EditText)findViewById(R.id.et_name);
        et_password = (EditText)findViewById(R.id.et_password);
        btn_login = (Button)findViewById(R.id.btn_sent);
        btn_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String name = et_name.getText().toString().trim();
        String password = et_password.getText().toString();
        if(TextUtils.isEmpty(name)){
            Toast.makeText(this, "请输入用户名", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
        boolean isSaveSuccess = SaveInfo.saveUserInfo(this, name, password);
        if(isSaveSuccess){
            Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "保存失败", Toast.LENGTH_SHORT).show();
        }
    }
}
