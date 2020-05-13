package cn.kagarise.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ShopActivity extends AppCompatActivity implements View.OnClickListener {
    private ItemInfo itemInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        itemInfo = new ItemInfo("switch", 30, 20, 10);
        findViewById(R.id.rl).setOnClickListener(this);

        TextView mNameTV = (TextView) findViewById(R.id.tv_name);
        TextView mLifeTV = (TextView) findViewById(R.id.tv_life);
        TextView mAttackTV = (TextView) findViewById(R.id.tv_attack);
        TextView mSpeedTV = (TextView) findViewById(R.id.tv_speed);
        mNameTV.setText(itemInfo.getName() + "");
        mLifeTV.setText("生命值+" + itemInfo.getLife());
        mAttackTV.setText("攻击力+" + itemInfo.getAttack());
        mSpeedTV.setText("敏捷度+" + itemInfo.getSpeed());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl:
                Intent intent = new Intent();
                intent.putExtra("equipment", itemInfo);
                setResult(1, intent);
                finish();
                break;
        }
    }
}
