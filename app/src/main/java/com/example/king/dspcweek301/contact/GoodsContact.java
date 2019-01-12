package com.example.king.dspcweek301.contact;

import android.telecom.Call;

import com.example.king.dspcweek301.utils.OKHttpUtilsCallBack;

import java.util.Map;

public interface GoodsContact {

    interface IGoodsModel{
        void setData(Map<String,String> param, final OKHttpUtilsCallBack okHttpUtilsCallBack);
    }

    interface  IGoodsView{
        void onSuccess(String meg);
        void onFile(String s);
    }
}
