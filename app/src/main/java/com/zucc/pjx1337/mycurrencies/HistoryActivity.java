package com.zucc.pjx1337.mycurrencies;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends Activity implements SearchView.OnQueryTextListener{
    RecyclerView recyclerView;
    private DatabaseHelper dbHelper;
    private List<History> HisList = new ArrayList<>();
//    Button mSearchFCNButton;
//    Button mSearchFCButton;
//    Button mSearchHCNButton;
//    Button mSearchHCButton;
//    EditText mEdtSearch;
    SearchView mSearchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
//        mSearchFCNButton = (Button)findViewById(R.id.btn_searchFCN);
//        mSearchFCButton = (Button)findViewById(R.id.btn_searchFC);
//        mSearchHCNButton = (Button)findViewById(R.id.btn_searchHCN);
//        mSearchHCButton = (Button)findViewById(R.id.btn_searchHC);
//        mEdtSearch = (EditText)findViewById(R.id.edt_search);
        dbHelper = new DatabaseHelper(this, "Currencies.db", null, 3);
        mSearchView=(SearchView)findViewById(R.id.searchView);
        initData();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        Adapter adapter = new Adapter(HisList);
        recyclerView.setAdapter(adapter);
        //设置该SearchView默认是否自动缩小为图标
        mSearchView.setIconifiedByDefault(false);
        //为该SearchView组件设置事件监听器
        mSearchView.setOnQueryTextListener(this);
        //设置该SearchView显示搜索按钮
        mSearchView.setSubmitButtonEnabled(true);

        //设置该SearchView内默认显示的提示文本
        mSearchView.setQueryHint("查找");


//        mSearchFCNButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                HisList.clear();
//                SQLiteDatabase db = dbHelper.getWritableDatabase();
//                //查询数据
//                Cursor cursor = db.rawQuery(" select * from Currencies where fCurrencynum = ? ",
//                        new String[]{mEdtSearch.getText().toString()});
//                if (mEdtSearch.getText().length()<1) {
//                    initData();
//                    Adapter adapter = new Adapter(HisList);
//                    recyclerView.setAdapter(adapter);
//                } else {
//                    if (cursor.moveToFirst()) {
//                        do {
//                            //遍历Cursor对象
//                            int fCurrencynum = cursor.getInt(cursor.getColumnIndex("fCurrencynum"));
//                            String fCurrency = cursor.getString(cursor.getColumnIndex("fCurrency"));
//                            Float hCurrencynum = cursor.getFloat(cursor.getColumnIndex("hCurrencynum"));
//                            String hCurrency = cursor.getString(cursor.getColumnIndex("hCurrency"));
//                            History history = new History(fCurrencynum, fCurrency, hCurrencynum, hCurrency);
//                            HisList.add(history);
//                        } while (cursor.moveToNext());
//                    }
//                    cursor.close();
//                    Adapter adapter = new Adapter(HisList);
//                    recyclerView.setAdapter(adapter);
//                }
//            }
//        });
    }

    private void initData() {
        HisList.clear();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        //查询数据
        Cursor cursor = db.query("Currencies", null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                //遍历Cursor对象
                int fCurrencynum = cursor.getInt(cursor.getColumnIndex("fCurrencynum"));
                String fCurrency = cursor.getString(cursor.getColumnIndex("fCurrency"));
                Float hCurrencynum = cursor.getFloat(cursor.getColumnIndex("hCurrencynum"));
                String hCurrency = cursor.getString(cursor.getColumnIndex("hCurrency"));
                History history = new History(fCurrencynum, fCurrency, hCurrencynum, hCurrency);
                HisList.add(history);

            } while (cursor.moveToNext());
        }
        cursor.close();
    }

    //用户输入字符时激发该方法
    @Override
    public boolean onQueryTextChange(String newText) {
        // TODO Auto-generated method stub
        if(TextUtils.isEmpty(newText))
        {
            initData();
        }
        else
        {
            HisList.clear();
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            //查询数据
            Cursor cursor = db.rawQuery(" select * from Currencies where fCurrencynum = ? ",
                    new String[]{newText});

            if (cursor.moveToFirst()) {
                do {
                    //遍历Cursor对象
                    int fCurrencynum = cursor.getInt(cursor.getColumnIndex("fCurrencynum"));
                    String fCurrency = cursor.getString(cursor.getColumnIndex("fCurrency"));
                    Float hCurrencynum = cursor.getFloat(cursor.getColumnIndex("hCurrencynum"));
                    String hCurrency = cursor.getString(cursor.getColumnIndex("hCurrency"));
                    History history = new History(fCurrencynum, fCurrency, hCurrencynum, hCurrency);
                    HisList.add(history);
                } while (cursor.moveToNext());
            }
            cursor.close();
            Adapter adapter = new Adapter(HisList);
            recyclerView.setAdapter(adapter);
        }
        return true;

    }
    //单击搜索按钮时激发该方法
    @Override
    public boolean onQueryTextSubmit(String query) {
        // TODO Auto-generated method stub
        return false;
    }

}