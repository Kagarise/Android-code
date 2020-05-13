package cn.kagarise.weather;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tv_city;
    private TextView tv_weather;
    private TextView tv_temp;
    private TextView tv_wind;
    private TextView tv_pm;
    private ImageView iv_icon;
    private Map<String, String> map;
    private List<Map<String, String>> list;
    private String name, weather, temp, pm, wind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        try{
            InputStream is = this.getResources().openRawResource(R.raw.weather);
            List<WeatherInfo> weatherInfos = WeatherService.getInfosFromJson(is);
            list = new ArrayList<Map<String, String>>();
            for(WeatherInfo info : weatherInfos){
                map = new HashMap<String, String>();
                map.put("name", info.getName());
                map.put("weather", info.getWeather());
                map.put("temp", info.getTemp());
                map.put("pm", info.getPm());
                map.put("wind", info.getWind());
                list.add(map);
            }
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this, "解析信息失败", Toast.LENGTH_SHORT).show();
        }
        getMap(1, R.drawable.sun);
    }

    private void initView() {
        tv_city = (TextView) findViewById(R.id.tv_city);
        tv_weather = (TextView) findViewById(R.id.tv_weather);
        tv_temp = (TextView) findViewById(R.id.tv_temp);
        tv_pm = (TextView) findViewById(R.id.tv_pm);
        tv_wind = (TextView) findViewById(R.id.tv_wind);
        iv_icon = (ImageView) findViewById(R.id.iv_icon);
        findViewById(R.id.btn_cd).setOnClickListener(this);
        findViewById(R.id.btn_cq).setOnClickListener(this);
        findViewById(R.id.btn_xm).setOnClickListener(this);
    }

    private void getMap(int number, int iconNumber) {
        Map<String, String> cityMap = list.get(number);
        name = cityMap.get("name");
        temp = cityMap.get("temp");
        weather = cityMap.get("weather");
        pm = cityMap.get("pm");
        wind = cityMap.get("wind");
        tv_city.setText(name);
        tv_temp.setText(temp);
        tv_weather.setText(weather);
        tv_pm.setText(pm);
        tv_wind.setText(wind);
        iv_icon.setImageResource(iconNumber);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_cd:
                getMap(0, R.drawable.sun);
                break;
            case R.id.btn_cq:
                getMap(1, R.drawable.stc);
                break;
            case R.id.btn_xm:
                getMap(2, R.drawable.cloud);
                break;
            default:break;
        }
    }
}
