package com.example.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ButtonActivity extends AppCompatActivity {
    class MyButton implements View.OnClickListener{
        public void onClick(View v){
            Toast.makeText(ButtonActivity.this, "按钮3：使用内部类", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnTwo = (Button) this.findViewById(R.id.btnTwo);
        btnTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ButtonActivity.this, "按钮2：使用匿名内部类：btn.setOnClickListener(new OnClickListener(){…})", Toast.LENGTH_SHORT).show();
            }
        });

        Button btnThree = (Button) this.findViewById(R.id.btnThree);
        MyButton listener = new MyButton();
        btnThree.setOnClickListener(listener);
    }
    public void btn_clickOne(View v){
        Toast.makeText(this, "按钮1：使用布局文件：android:onClick", Toast.LENGTH_SHORT).show();
    }
    public void btn_clickFour(View v){
        Toast.makeText(this, "按钮4：实现接口", Toast.LENGTH_SHORT).show();
    }
}
