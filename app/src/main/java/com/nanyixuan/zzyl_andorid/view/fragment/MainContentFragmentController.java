package com.nanyixuan.zzyl_andorid.view.fragment;

import com.nanyixuan.zzyl_andorid.bean.MainListData;
import com.nanyixuan.zzyl_andorid.bean.MainLooperData;

import java.util.List;

/**
 * Created by YixuanNan on 2017/3/25.
 *
 * @author YixuanNan
 *         MainContentFragment控制器。
 */

public interface MainContentFragmentController {

    void setLooperView(List<MainLooperData> imgs);
    void setBottomList(MainListData mainListDatas);
}
