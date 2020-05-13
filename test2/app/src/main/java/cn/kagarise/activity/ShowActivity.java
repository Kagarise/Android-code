package cn.kagarise.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ShowActivity extends AppCompatActivity {
    private ProgressBar mLifeProgress;
    private ProgressBar mAttackProgress;
    private ProgressBar mSpeedProgress;
    private TextView mLifeTV;
    private TextView mAttackTV;
    private TextView mSpeedTV;
    private TextView tv_name;
    private TextView tv_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String password = intent.getStringExtra("password");
        tv_name = (TextView)findViewById(R.id.tv_name);
        tv_password = (TextView)findViewById(R.id.tv_password);
        tv_name.setText("用户名："+name);
        tv_password.setText("密    码："+password);
        mLifeTV = (TextView)findViewById(R.id.tv_life_progress);
        mAttackTV = (TextView)findViewById(R.id.tv_attack_progress);
        mSpeedTV = (TextView)findViewById(R.id.tv_speed_progress);
        initProgress();
    }

    private void initProgress() {
        mLifeProgress = (ProgressBar) findViewById(R.id.progress_life);
        mAttackProgress = (ProgressBar) findViewById(R.id.progress_attack);
        mSpeedProgress = (ProgressBar) findViewById(R.id.progress_speed);
        mLifeProgress.setMax(100);
        mAttackProgress.setMax(100);
        mSpeedProgress.setMax(100);
    }

    public void click(View view){
        Intent intent = new Intent(this, ShopActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data != null){
            if(requestCode == 1){
                if(resultCode == 1){
                    ItemInfo info = (ItemInfo) data.getSerializableExtra("equipment");
                    updateProgress(info);
                }
            }
        }
    }

    private void updateProgress(ItemInfo info) {
        int p1 = mLifeProgress.getProgress();
        int p2 = mAttackProgress.getProgress();
        int p3 = mSpeedProgress.getProgress();
        mLifeProgress.setProgress(p1+info.getLife());
        mAttackProgress.setProgress(p2+info.getAttack());
        mSpeedProgress.setProgress(p3+info.getSpeed());
        mLifeTV.setText(mLifeProgress.getProgress()+"");
        mAttackTV.setText(mAttackProgress.getProgress()+"");
        mSpeedTV.setText(mSpeedProgress.getProgress()+"");
    }
}
