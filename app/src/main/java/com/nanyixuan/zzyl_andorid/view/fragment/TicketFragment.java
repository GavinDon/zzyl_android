package com.nanyixuan.zzyl_andorid.view.fragment;

import android.os.Bundle;
import android.view.View;

import com.blankj.utilcode.util.FragmentUtils;
import com.nanyixuan.zzyl_andorid.R;
import com.nanyixuan.zzyl_andorid.base.BaseFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class TicketFragment extends BaseFragment {
    public static final String TAG = "TicketFragment";
    Unbinder unbinder;

    public static TicketFragment newInstance() {
        return new TicketFragment();
    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.fragment_ticket, container, false);
//        unbinder = ButterKnife.bind(this, view);
//        return view;
//    }

    @Override
    public int setLayout() {
        return R.layout.fragment_ticket;
    }

    @Override
    public void initViews(View self, Bundle savedInstanceState) {
        unbinder = ButterKnife.bind(this, self);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.btn_showSideMenu)
    public void onViewClicked() {
        FragmentUtils.popFragment(getFragmentManager());
    }
}
