package com.example.king.dspcweek301.model;

import com.example.king.dspcweek301.apis.GoodsApi;
import com.example.king.dspcweek301.contact.GoodsContact;
import com.example.king.dspcweek301.utils.OKHttpUtils;
import com.example.king.dspcweek301.utils.OKHttpUtilsCallBack;

import java.util.Map;

public class GoodsModel implements GoodsContact.IGoodsModel {
    @Override
    public void setData(Map<String, String> param, OKHttpUtilsCallBack okHttpUtilsCallBack) {
        OKHttpUtils.getInstance().toPost(GoodsApi.GOODS_API,param,okHttpUtilsCallBack);
    }
}
