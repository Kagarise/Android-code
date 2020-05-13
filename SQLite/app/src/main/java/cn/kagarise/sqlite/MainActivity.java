package cn.kagarise.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private MyHelper myHelper;

    private EditText mEtName;
    private EditText mEtPhone;
    private ListView mLvShow;
//    private TextView mTvShow;

    private Button mBtAdd;
    private Button mBtDelete;
    private Button mBtUpdate;
    private Button mBtQuery;

    private String name;
    private String phone;

    private SQLiteDatabase db;
    private ContentValues values;

    private ListView mListView;
    private MyBaseAdapter mAdapter;

    List<Pair<String, String>> list = new ArrayList<Pair<String, String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myHelper = new MyHelper(this);
        initView();
    }

    private void initView() {
        mEtName = (EditText)findViewById(R.id.et_name);
        mEtPhone = (EditText)findViewById(R.id.et_phone);
//        mTvShow = (TextView) findViewById(R.id.tv_show);
        mLvShow = (ListView) findViewById(R.id.lv);

        mBtAdd = (Button) findViewById(R.id.btn_add);
        mBtDelete = (Button) findViewById(R.id.btn_delete);
        mBtUpdate = (Button) findViewById(R.id.btn_update);
        mBtQuery = (Button) findViewById(R.id.btn_query);

        mBtAdd.setOnClickListener(this);
        mBtDelete.setOnClickListener(this);
        mBtUpdate.setOnClickListener(this);
        mBtQuery.setOnClickListener(this);

        mListView = (ListView) findViewById(R.id.lv);
        mAdapter = new MyBaseAdapter();
        mListView.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_add:
                name = mEtName.getText().toString();
                phone = mEtPhone.getText().toString();

                db = myHelper.getWritableDatabase();

                values = new ContentValues();
                values.put("name", name);
                values.put("phone", phone);
                db.insert("information", null, values);
                Toast.makeText(this, "信息已添加", Toast.LENGTH_SHORT).show();
                db.close();
                break;
            case R.id.btn_delete:
                db = myHelper.getWritableDatabase();
                db.delete("information", null, null);
                Toast.makeText(this, "信息已删除", Toast.LENGTH_SHORT).show();
//                mTvShow.setText("");
                db.close();
                break;
            case R.id.btn_update:
                db = myHelper.getWritableDatabase();
                values = new ContentValues();
                values.put("phone", phone = mEtPhone.getText().toString());
                db.update("information", values, "name=?",new String[] {mEtName.getText().toString()});
                Toast.makeText(this, "信息已修改", Toast.LENGTH_SHORT).show();
                db.close();
                break;
            case R.id.btn_query:
                list.clear();
                db = myHelper.getReadableDatabase();
                Cursor cursor= db.query("information", null, null, null, null, null, null);

                if(cursor.getCount() == 0){
//                    mTvShow.setText("");
                    Toast.makeText(this, "没有数据", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "查询结果已显示", Toast.LENGTH_SHORT).show();
                    cursor.moveToFirst();
                    String first = cursor.getString(1);
                    String second = cursor.getString(2);
                    list.add(new Pair<>(first, second));
//                    mTvShow.setText("Name:"+cursor.getString(1) + " Tel:" + cursor.getString(2));
                }
                while(cursor.moveToNext()){
                    String first = cursor.getString(1);
                    String second = cursor.getString(2);
                    list.add(new Pair<>(first, second));
//                    mTvShow.append("\nName:" + cursor.getString(1) + " Tel:" + cursor.getString(2));
                }
                mAdapter.notifyDataSetChanged();
                cursor.close();
                db.close();
                break;
            default:
                break;
        }
    }

    public class MyBaseAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = View.inflate(MainActivity.this, R.layout.list_item, null);
            TextView mTvName = (TextView) view.findViewById(R.id.item_tv_name);
            TextView mTvPhone = (TextView) view.findViewById(R.id.item_tv_phone);

            Pair<String, String> pair = list.get(position);
            mTvName.setText("Name:" + pair.first);
            mTvPhone.setText("Phone:" + pair.second);
            return view;
        }
    }
}
