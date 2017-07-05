package com.zucc.pjx1337.mycurrencies;

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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    private DatabaseHelper dbHelper;
    private List<History> HisList = new ArrayList<>();
    SearchView mSearchView;
    Button mSearchFCNButton;
    Button mSearchFCButton;
    Button mSearchHCNButton;
    Button mSearchHCButton;
    EditText mEdtSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        mSearchFCNButton = (Button)findViewById(R.id.btn_searchFCN);
        mSearchFCButton = (Button)findViewById(R.id.btn_searchFC);
        mSearchHCNButton = (Button)findViewById(R.id.btn_searchHCN);
        mSearchHCButton = (Button)findViewById(R.id.btn_searchHC);
        mEdtSearch = (EditText)findViewById(R.id.edt_search);
        dbHelper = new DatabaseHelper(this, "Currencies.db", null, 3);
        initData();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        Adapter adapter = new Adapter(HisList);
        recyclerView.setAdapter(adapter);

//        // 设置搜索文本监听
//        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            // 当点击搜索按钮时触发该方法
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            // 当搜索内容改变时触发该方法
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                if (!TextUtils.isEmpty(newText)){
//                    HisList.clear();
//                    SQLiteDatabase db = dbHelper.getWritableDatabase();
//                    //查询数据
//                    Cursor cursor = db.rawQuery(" select * from Currencies where fCurrencynum = ? ",
//                            new String[]{mEdtSearch.getText().toString()});
//
//                        if (cursor.moveToFirst()) {
//                            do {
//                                //遍历Cursor对象
//                                int fCurrencynum = cursor.getInt(cursor.getColumnIndex("fCurrencynum"));
//                                String fCurrency = cursor.getString(cursor.getColumnIndex("fCurrency"));
//                                Float hCurrencynum = cursor.getFloat(cursor.getColumnIndex("hCurrencynum"));
//                                String hCurrency = cursor.getString(cursor.getColumnIndex("hCurrency"));
//                                History history = new History(fCurrencynum, fCurrency, hCurrencynum, hCurrency);
//                                HisList.add(history);
//                            } while (cursor.moveToNext());
//                        }
//                        cursor.close();
//                        Adapter adapter = new Adapter(HisList);
//                        recyclerView.setAdapter(adapter);
//
//                }else{
//                initData();
//                Adapter adapter = new Adapter(HisList);
//                recyclerView.setAdapter(adapter);
//                }
//                return false;
//            }
//        });

        mSearchFCNButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HisList.clear();
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                //查询数据
                Cursor cursor = db.rawQuery(" select * from Currencies where fCurrencynum = ? ",
                        new String[]{mEdtSearch.getText().toString()});
                if (mEdtSearch.getText().length()<1) {
                    initData();
                    Adapter adapter = new Adapter(HisList);
                    recyclerView.setAdapter(adapter);
                } else {
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
            }
        });
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
}