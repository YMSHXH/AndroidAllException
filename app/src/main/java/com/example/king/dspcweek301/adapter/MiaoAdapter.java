package com.example.king.dspcweek301.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.king.dspcweek301.R;
import com.example.king.dspcweek301.beans.GoodsBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

public class MiaoAdapter extends XRecyclerView.Adapter<MiaoAdapter.FlahVH> {

    private Context context;
    private List<GoodsBean.DataBean.MiaoshaBean.ListBean> list;

    public MiaoAdapter(Context context, List<GoodsBean.DataBean.MiaoshaBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public FlahVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_miaoi_item, viewGroup, false);
        FlahVH flahVH = new FlahVH(inflate);
        return flahVH;
    }

    @Override
    public void onBindViewHolder(@NonNull FlahVH flahVH, int i) {
        GoodsBean.DataBean.MiaoshaBean.ListBean listBean = list.get(i);
        flahVH.text_tui.setText(listBean.getPrice());
        flahVH.text_yuan.setText(listBean.getSalenum());
        String images = listBean.getImages();
        String[] split = images.split("\\|");
        Glide.with(context).load(split[0]).into(flahVH.f_img);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class  FlahVH extends XRecyclerView.ViewHolder{
        ImageView f_img;
        TextView text_tui;
        TextView text_yuan;
        public FlahVH(@NonNull View itemView) {
            super(itemView);
            f_img = itemView.findViewById(R.id.f_img);
            text_tui = itemView.findViewById(R.id.text_tui);
            text_yuan = itemView.findViewById(R.id.text_yuan);
        }
    }
}
