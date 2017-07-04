package com.zucc.pjx1337.mycurrencies;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    private DatabaseHelper dbHelper;
    private List<History> HisList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        initData();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        Adapter adapter = new Adapter(HisList);
        recyclerView.setAdapter(adapter);

    }

    private void initData() {
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
//                History history = new History(fCurrencynum, fCurrency, hCurrencynum, hCurrency);
//                HisList.add(history);
                Log.d("HistoryActivity", "fCurrencynum:"+fCurrencynum);
                Log.d("HistoryActivity", "fCurrency:"+fCurrency);
                Log.d("HistoryActivity", "hCurrencynum:"+hCurrencynum);
                Log.d("HistoryActivity", "hCurrency:"+hCurrency);

            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
    }
}