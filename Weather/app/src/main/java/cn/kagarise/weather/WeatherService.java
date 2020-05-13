package cn.kagarise.weather;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.List;

public class WeatherService {
    public static List<WeatherInfo> getInfosFromJson(InputStream is)throws IOException{
        byte[] buffer = new byte[is.available()];
        is.read(buffer);
        String json = new String(buffer, "utf-8");
        Gson gson = new Gson();
        Type listType = new TypeToken<List<WeatherInfo>>(){}.getType();
        List<WeatherInfo> weatherInfos = gson.fromJson(json, listType);
        return weatherInfos;
    }
}
