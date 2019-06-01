package com.benjie.onlinemusic.demo.fragment;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.benjie.onlinemusic.R;
import com.benjie.onlinemusic.base.enums.FailedReason;
import com.benjie.onlinemusic.base.fragment.BaseFragment;

/**
 * Created by zhangfan on 2019/6/2.
 */
public class DemoFragment extends BaseFragment implements DemoFrameView {

    Button mBtn1;
    TextView mText1;
    DemoFramePresenter mPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_demo;
    }

    @Override
    protected void initViews() {
        mBtn1 = (Button) mPage.findViewById(R.id.btn_1);
        mText1 = (TextView) mPage.findViewById(R.id.text_1);
    }

    @Override
    protected void initViewListeners() {
        mBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mText1.setText(null);
                mPresenter.getNewUrl();
            }
        });
    }

    @Override
    public void initPresenter() {
        mPresenter = new DemoFramePresenter();
        mPresenter.attachView(this);
    }

    @Override
    public void onDestroyView() {
        mPresenter.detachView(this);
        super.onDestroyView();
    }

    @Override
    public void showError(FailedReason reason, String msg) {
        mText1.setText(reason.name() + "::" + msg);
    }

    @Override
    public void updateNetUrl(String url) {
        mText1.setText(url);
    }
}
