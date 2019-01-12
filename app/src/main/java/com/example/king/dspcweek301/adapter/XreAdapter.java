package com.example.king.dspcweek301.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.king.dspcweek301.R;
import com.example.king.dspcweek301.beans.GoodsBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.stx.xhb.xbanner.XBanner;

import java.util.List;

public class XreAdapter extends XRecyclerView.Adapter<XRecyclerView.ViewHolder> {

    private Context context;
    private GoodsBean.DataBean data = new GoodsBean.DataBean();

    public XreAdapter(Context context, GoodsBean.DataBean data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public XRecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (getItemViewType(i) == 0) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.item_banner, viewGroup, false);
            XreBannVH xreBannVH = new XreBannVH(inflate);
            return xreBannVH;
        } else if (getItemViewType(i) == 1) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.item_fenlei, viewGroup, false);
            FLVH flvh = new FLVH(inflate);
            return flvh;
        } else if (getItemViewType(i) == 2) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.item_miao, viewGroup, false);
            MiaoVH miaoVH = new MiaoVH(inflate);
            return miaoVH;
        } else {
            View inflate = LayoutInflater.from(context).inflate(R.layout.item_tui, viewGroup, false);
            TuiVH tuiVH = new TuiVH(inflate);
            return tuiVH;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull XRecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof XreBannVH){
            final List<GoodsBean.DataBean.BannerBean> bannerlist = data.getBanner();
            ((XreBannVH)viewHolder).xbanner.setData(bannerlist,null);
            ((XreBannVH)viewHolder).xbanner.loadImage(new XBanner.XBannerAdapter() {
                @Override
                public void loadBanner(XBanner banner, Object model, View view, int position) {
                    Glide.with(context).load(bannerlist.get(position).getIcon()).into((ImageView) view);
                }
            });
            ((XreBannVH)viewHolder).xbanner.setIsClipChildrenMode(true);
        } else if (viewHolder instanceof FLVH) {
            List<GoodsBean.DataBean.FenleiBean> list = data.getFenlei();
            FLVHAdapter flvhAdapter = new FLVHAdapter(context,list);
            ((FLVH)viewHolder).recy.setLayoutManager(new GridLayoutManager(context,5));
            ((FLVH)viewHolder).recy.setAdapter(flvhAdapter);
        } else if (viewHolder instanceof MiaoVH) {
            GoodsBean.DataBean.MiaoshaBean miaosha = data.getMiaosha();
            ((MiaoVH)viewHolder).miao_text.setText(miaosha.getName());
            List<GoodsBean.DataBean.MiaoshaBean.ListBean> list = miaosha.getList();
            MiaoAdapter flvhAdapter = new MiaoAdapter(context,list);
            ((MiaoVH)viewHolder).recy.setLayoutManager(new LinearLayoutManager(context,0,false));
            ((MiaoVH)viewHolder).recy.setAdapter(flvhAdapter);
        } else  {
            GoodsBean.DataBean.TuijianBean tuijian = data.getTuijian();
            List<GoodsBean.DataBean.TuijianBean.ListBeanX> list = tuijian.getList();
            TuiAdapter tuiAdapter = new TuiAdapter(context,list);
            ((TuiVH)viewHolder).recy.setLayoutManager(new LinearLayoutManager(context));
            ((TuiVH)viewHolder).recy.setAdapter(tuiAdapter);
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 0;
        } else if (position == 1) {
            return 1;
        } else if (position == 2) {
            return 2;
        } else{
            return 3;
        }
    }


    class XreBannVH extends XRecyclerView.ViewHolder{
        XBanner xbanner;
        public XreBannVH(@NonNull View itemView) {
            super(itemView);
            xbanner = itemView.findViewById(R.id.xbanner);
        }
    }

    class FLVH extends XRecyclerView.ViewHolder{
        XRecyclerView recy;
        public FLVH(@NonNull View itemView) {
            super(itemView);
            recy = itemView.findViewById(R.id.fenlei_xrecy);
        }
    }

    class MiaoVH extends XRecyclerView.ViewHolder{
        XRecyclerView recy;
        TextView miao_text;
        public MiaoVH(@NonNull View itemView) {
            super(itemView);
            recy = itemView.findViewById(R.id.miao_xrecy);
            miao_text = itemView.findViewById(R.id.miao_text);
        }
    }

    class TuiVH extends XRecyclerView.ViewHolder{
        XRecyclerView recy;
        public TuiVH(@NonNull View itemView) {
            super(itemView);
            recy = itemView.findViewById(R.id.tui_xrecy);
        }
    }

}
