package com.zucc.pjx1337.mycurrencies;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by PJX on 2017/7/4.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private List<History> HisList;

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView fCurrencynum;
        TextView fCurrency;
        TextView hCurrencynum;
        TextView hCurrency;

        public ViewHolder(View view) {
            super(view);
//            绑定xml布局中的控件
            fCurrencynum = (TextView)view.findViewById(R.id.fCurrencynum_txt);
            fCurrency = (TextView)view.findViewById(R.id.fCurrency_txt);
            hCurrencynum = (TextView)view.findViewById(R.id.hCurrencynum_txt);
            hCurrency = (TextView)view.findViewById(R.id.hCurrency_txt);

        }
    }

    public Adapter(List<History> hisList) {
        HisList = hisList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.data_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,final int position) {
        History history = HisList.get(position);
        holder.fCurrencynum.setText(String.valueOf(history.getfCurrencynum()));
        holder.fCurrency.setText(history.getfCurrency());
        holder.hCurrencynum.setText(String.valueOf(history.gethCurrencynum()));
        holder.hCurrency.setText(history.gethCurrency());
    }

    @Override
    public int getItemCount() {
        return HisList.size();
    }


}
