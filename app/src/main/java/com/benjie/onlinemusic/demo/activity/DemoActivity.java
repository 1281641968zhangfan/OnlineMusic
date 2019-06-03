package com.benjie.onlinemusic.demo.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.benjie.onlinemusic.R;
import com.benjie.onlinemusic.base.activity.BaseActivity;
import com.benjie.onlinemusic.base.enums.FailedReason;
import com.benjie.onlinemusic.demo.fragment.DemoFragment;
import com.benjie.onlinemusic.serial_port.SerialPortUtil;

/**
 * Created by zhangfan on 2019/6/2.
 */
public class DemoActivity extends BaseActivity implements DemoView {

    Button mBtn1, mBtn2, mBtn3;
    TextView mText1, mText2;
    DemoPresenter mPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_demo;
    }

    @Override
    protected void initViews() {
        mBtn1 = (Button) findViewById(R.id.btn_1);
        mBtn2 = (Button) findViewById(R.id.btn_2);
        mBtn3 = (Button) findViewById(R.id.btn_3);
        mText1 = (TextView) findViewById(R.id.text_1);
        mText2 = (TextView) findViewById(R.id.text_2);

        SerialPortUtil.openSerialPort("ttyS1",115200);
    }

    @Override
    protected void initViewListeners() {
        mBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mText1.setText(null);
                mPresenter.getData("success");
            }
        });
        mBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mText2.setText(null);
                mPresenter.getData("failure");
            }
        });
        mBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.container_fragments, new DemoFragment(), "DemoFragment")
                        .addToBackStack("DemoFragment")
                        .commitAllowingStateLoss();
            }
        });
    }

    @Override
    public void showData(String data) {
        mText1.setText(data);
    }

    @Override
    public void initPresenter() {
        mPresenter = new DemoPresenter();
        mPresenter.attachView(this);
    }

    @Override
    public void showError(FailedReason reason, String msg) {
        mText2.setText(reason.name() + "::" + msg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView(this);
    }
}
