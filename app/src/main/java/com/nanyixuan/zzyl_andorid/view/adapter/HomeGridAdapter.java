package com.nanyixuan.zzyl_andorid.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nanyixuan.zzyl_andorid.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * description: 首页网格布局
 * Created by liNan on 2017/7/25 15:22
 */

public class HomeGridAdapter extends RecyclerView.Adapter<HomeGridAdapter.VhHomeGrid> implements View.OnClickListener {
    List<SparseArray<String>> list;

    public HomeGridAdapter(List<SparseArray<String>> gridList) {
        this.list = gridList;
    }


    @Override
    public VhHomeGrid onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_home_grid, null, false);
        return new VhHomeGrid(view);
    }

    @Override
    public void onBindViewHolder(VhHomeGrid holder, int position) {
        holder.adaHomeGridIv.setImageLevel(list.get(position).keyAt(0));
        holder.adaHomeGridTv.setText(list.get(position).valueAt(0));
        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onClick(View v) {
        int position = (int) v.getTag();
        mListener.hClickListener(position);
    }

    public interface GridOnClickListener {
        void hClickListener(int position);
    }

    private GridOnClickListener mListener;

    public void setOnclickListener(GridOnClickListener listener) {
        this.mListener = listener;
    }


    class VhHomeGrid extends RecyclerView.ViewHolder {
        @BindView(R.id.ada_home_grid_iv)
        ImageView adaHomeGridIv;
        @BindView(R.id.ada_home_grid_tv)
        TextView adaHomeGridTv;

        public VhHomeGrid(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
