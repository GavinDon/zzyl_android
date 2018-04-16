package com.nanyixuan.zzyl_andorid.presenter;

import android.content.Context;
import android.util.Log;

import com.nanyixuan.zzyl_andorid.api.ApiManager;
import com.nanyixuan.zzyl_andorid.api.ApiObserver;
import com.nanyixuan.zzyl_andorid.bean.BaseData;
import com.nanyixuan.zzyl_andorid.bean.MainListData;
import com.nanyixuan.zzyl_andorid.bean.MainLooperData;
import com.nanyixuan.zzyl_andorid.bean.PagerData;
import com.nanyixuan.zzyl_andorid.view.fragment.MainContentFragmentController;

import java.util.List;

/**
 * Created by YixuanNan on 2017/3/25.
 *
 * @author YixuanNan
 *         MainContentFragment的Presenter.
 */

public class MainContentFragmentPresenter {
    private MainContentFragmentController mainContentFragmentController;
    private Context context;

    public MainContentFragmentPresenter(Context context, MainContentFragmentController mainContentFragmentController) {
        this.mainContentFragmentController = mainContentFragmentController;
        this.context = context;
    }

    public void init() {
        ApiManager.getMainLooper(1, 10, context, new ApiObserver<BaseData<PagerData<List<MainLooperData>>>>() {

            @Override
            public void onSuccess(BaseData<PagerData<List<MainLooperData>>> tBaseData) {
                mainContentFragmentController.setLooperView(tBaseData.getData().getContent());
            }

            @Override
            public void onFail(Throwable t) {
                Log.i("nanyixuan", "轮播图 onFail = " + t.getMessage());
            }
        });

        ApiManager.getMainBottomList(1, 10, context, new ApiObserver<MainListData>() {
            @Override
            public void onSuccess(MainListData tBaseData) {
                mainContentFragmentController.setBottomList(tBaseData);
            }

            @Override
            public void onFail(Throwable t) {

            }
        });
    }
}
