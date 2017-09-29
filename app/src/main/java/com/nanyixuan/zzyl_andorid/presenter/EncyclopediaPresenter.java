package com.nanyixuan.zzyl_andorid.presenter;

import android.content.Context;

import com.nanyixuan.zzyl_andorid.api.ApiManager;
import com.nanyixuan.zzyl_andorid.api.ApiObserver;
import com.nanyixuan.zzyl_andorid.bean.BaseData;
import com.nanyixuan.zzyl_andorid.bean.EncyclopediaData;
import com.nanyixuan.zzyl_andorid.view.fragment.EncyclopediaFragmentController;

import java.util.List;

/**
 * Created by YixuanNan on 2017/3/31.
 *
 * @author YixuanNan
 */

public class EncyclopediaPresenter {
    private Context context;
    private EncyclopediaFragmentController encyclopediaFragmentController;

    public EncyclopediaPresenter(Context context, EncyclopediaFragmentController encyclopediaFragmentController) {
        this.context = context;
        this.encyclopediaFragmentController = encyclopediaFragmentController;
    }

    public void init(){
        ApiManager.getEncyclopediaTypeListDataList(context, new ApiObserver<BaseData<List<EncyclopediaData>>>() {
            @Override
            public void onSuccess(BaseData<List<EncyclopediaData>> tBaseData) {
                encyclopediaFragmentController.setEncyclopediaList(tBaseData.getData());
            }

            @Override
            public void onFail(Throwable t) {

            }
        });
    }
}
