package com.zucc.pjx1337.mycurrencies;

/**
 * Created by PJX on 2017/7/4.
 */

public class History {
    private int fCurrencynum;
    private String fCurrency;
    private Float hCurrencynum;
    private String hCurrency;

    public History(int fCurrencynum, String fCurrency, Float hCurrencynum, String hCurrency){
        this.fCurrencynum = fCurrencynum;
        this.fCurrency = fCurrency;
        this.hCurrencynum = hCurrencynum;
        this.hCurrency = hCurrency;
    }

    public int getfCurrencynum() {
        return fCurrencynum;
    }

    public String getfCurrency() {
        return fCurrency;
    }

    public Float gethCurrencynum() {
        return hCurrencynum;
    }

    public String gethCurrency() {
        return hCurrency;
    }
}
