package com.example.king.dspcweek301.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.king.dspcweek301.R;
import com.example.king.dspcweek301.activity.MainActivity;
import com.example.king.dspcweek301.adapter.XreAdapter;
import com.example.king.dspcweek301.beans.GoodsBean;
import com.example.king.dspcweek301.contact.GoodsContact;
import com.example.king.dspcweek301.presenter.GoodsPresenter;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class HomeFragment extends Fragment implements GoodsContact.IGoodsView {

    private GoodsPresenter goodsPresenter;
    private Unbinder bind;

    @BindView(R.id.xrecy)
    XRecyclerView xrecy;
    private XreAdapter xreAdapter;
    @BindView(R.id.two_img)
    ImageView two_img;
    @BindView(R.id.sheng_img)
    ImageView sheng_img;
    @BindView(R.id.two_btn)
    Button two_btn;
    @BindView(R.id.toExcep)
    Button toExcep;
    private int REQUEST_CODE = 1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_home,container,false);
        bind = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initData();
    }

    private void initData() {
        goodsPresenter = new GoodsPresenter(this);
        Map<String,String> param = new HashMap<>();
        goodsPresenter.toGoods(param);

        two_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CaptureActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
        two_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bitmap mBitmap = CodeUtils.createImage("我最帅", 400, 400, BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
                sheng_img.setImageBitmap(mBitmap);
            }
        });
        toExcep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getActivity(), "程序崩溃", Toast.LENGTH_SHORT).show();
                // 除数为0 导致程序崩溃
                int a = 1 / 0;
            }
        });
    }

    private void initView(View view) {
        xrecy.setLayoutManager(new LinearLayoutManager(getActivity()));
    }


    @Override
    public void onSuccess(String meg) {
        //Toast.makeText(getActivity(),meg,Toast.LENGTH_SHORT).show();
        GoodsBean goodsBean = new Gson().fromJson(meg, GoodsBean.class);
        GoodsBean.DataBean data = goodsBean.getData();
//        List<GoodsBean.DataBean.BannerBean> banner = data.getBanner();
//        List<GoodsBean.DataBean.FenleiBean> fenlei = data.getFenlei();
//        GoodsBean.DataBean.MiaoshaBean miaosha = data.getMiaosha();
//        GoodsBean.DataBean.TuijianBean tuijian = data.getTuijian();

        xreAdapter = new XreAdapter(getActivity(),data);
        xrecy.setAdapter(xreAdapter);
    }

    @Override
    public void onFile(String s) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /**
         * 处理二维码扫描结果
         */
        if (requestCode == REQUEST_CODE) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    Toast.makeText(getActivity(), "解析结果:" + result, Toast.LENGTH_LONG).show();
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(getActivity(), "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
