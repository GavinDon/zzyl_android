package com.nanyixuan.zzyl_andorid.presenter;

import android.content.Context;

import com.nanyixuan.zzyl_andorid.api.ApiManager;
import com.nanyixuan.zzyl_andorid.api.ApiObserver;
import com.nanyixuan.zzyl_andorid.bean.BaseData;
import com.nanyixuan.zzyl_andorid.bean.PagerData;
import com.nanyixuan.zzyl_andorid.bean.TourRouteData;
import com.nanyixuan.zzyl_andorid.view.fragment.TourRouteFragmentController;

import java.util.List;

/**
 * Created by YixuanNan on 2017/3/25.
 *
 * @author YixuanNan
 *         TourRouteFragment控制类。
 */

public class TourRouteFragmentPresenter {
    TourRouteFragmentController tourRouteFragmentController;
    Context context;

    public TourRouteFragmentPresenter(TourRouteFragmentController tourRouteFragmentController, Context context) {
        this.tourRouteFragmentController = tourRouteFragmentController;
        this.context = context;
    }

    public void init() {
        ApiManager.getTourRoute(1, 10, context, new ApiObserver<BaseData<PagerData<List<TourRouteData>>>>() {
            @Override
            public void onSuccess(BaseData<PagerData<List<TourRouteData>>> tBaseData) {
                tourRouteFragmentController.setTourRouteList(tBaseData.getData().getContent());
            }

            @Override
            public void onFail(Throwable t) {

            }
        });
    }
}
