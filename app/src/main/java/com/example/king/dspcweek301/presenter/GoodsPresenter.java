package com.example.king.dspcweek301.presenter;

import com.example.king.dspcweek301.contact.GoodsContact;
import com.example.king.dspcweek301.model.GoodsModel;
import com.example.king.dspcweek301.utils.OKHttpUtilsCallBack;

import java.util.Map;

public class GoodsPresenter {

    private GoodsContact.IGoodsView iGoodsView;
    private GoodsModel goodsModel;

    public GoodsPresenter(GoodsContact.IGoodsView iGoodsView) {
        this.iGoodsView = iGoodsView;
        this.goodsModel = new GoodsModel();
    }


    public void toGoods(Map<String,String> param) {
        if (iGoodsView != null) {
            goodsModel.setData(param, new OKHttpUtilsCallBack() {
                @Override
                public void onSuccess(String meg) {
                    iGoodsView.onSuccess(meg);
                }

                @Override
                public void onFile(String s) {
                    iGoodsView.onFile(s);
                }
            });
        }
    }
}
