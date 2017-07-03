package com.zucc.pjx1337.mycurrencies;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by PJX on 2017/7/3.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String CREATE_CURRENCIES = "create table Currencies ("
            + "id integer primary key autoincrement, "
            + "fCurrencynum integer, "
            + "fCurrency text, "
            + "hCurrencynum integer, "
            + "hCurrency text)";
    private Context mContext;

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CURRENCIES);
        Toast.makeText(mContext, "Already", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
