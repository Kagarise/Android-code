package cn.kagarise.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText et_name;
        private EditText et_password;
        private Button btn_sent;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            et_name = (EditText) findViewById(R.id.et_name);
            et_password = (EditText) findViewById(R.id.et_password);
            btn_sent = (Button) findViewById(R.id.btn_sent);
            btn_sent.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    passData();
                }
            });
        }
        public void passData(){
            Intent intent = new Intent(this, ShowActivity.class);
            intent.putExtra("name", et_name.getText().toString().trim());
            intent.putExtra("password", et_password.getText().toString().trim());
            startActivity(intent);
    }
}
