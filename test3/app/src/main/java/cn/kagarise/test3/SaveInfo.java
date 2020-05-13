package cn.kagarise.test3;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

public class SaveInfo {
    public static boolean saveUserInfo(Context context, String name, String password){
        /*
         *  SharePreferences
         */
        try {
            SharedPreferences sp = context.getSharedPreferences("data", Context.MODE_PRIVATE);
            SharedPreferences.Editor edit = sp.edit();
            edit.putString("name", name);
            edit.putString("password", password);
            edit.commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        /*
         *  File
         */
//        try{
//            FileOutputStream fos = context.openFileOutput("data.txt", Context.MODE_PRIVATE);
//            fos.write((name+":"+password).getBytes());
//            fos.close();
//            return true;
//        }catch (Exception e){
//            e.printStackTrace();
//            return false;
//        }
    }
    public static Map<String, String> getUserInfo(Context context){
        /*
         *  SharePreferences
         */
        try {
            SharedPreferences sp = context.getSharedPreferences("data", Context.MODE_PRIVATE);
            String name = sp.getString("name", null);
            String password = sp.getString("password", null);
            Map<String, String> userMap = new HashMap<String, String>();
            userMap.put("name", name);
            userMap.put("password", password);
            return userMap;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        /*
         *  File
         */
//        String content = "";
//        try{
//            FileInputStream fis = context.openFileInput("data.txt");
//            byte[] buffer = new byte[fis.available()];
//            fis.read(buffer);
//            content = new String(buffer);
//            Map<String, String> userMap = new HashMap<String, String>();
//            String[] infos = content.split(":");
//            userMap.put("name", infos[0]);
//            userMap.put("password", infos[1]);
//            fis.close();
//            return userMap;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
    }
}
