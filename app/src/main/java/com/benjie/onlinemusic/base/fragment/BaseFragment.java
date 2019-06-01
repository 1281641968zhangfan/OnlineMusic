package com.benjie.onlinemusic.base.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.benjie.onlinemusic.base.view.BaseView;

/**
 * Created by zhangfan on 2019/6/2.
 */
public abstract class BaseFragment extends Fragment implements BaseView {

    protected Context mContext;
    protected View mPage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        obtainDataFromMemory(savedInstanceState);
        mContext = getActivity();
        mPage = inflater.inflate(getLayoutId(), container, false);
        initViews();
        initViewListeners();
        initPresenter();
        return mPage;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        saveDataToMemory(outState);
    }

    @Override
    public void showLoading() {
        checkActivityAttached();
        if (mContext != null && mContext instanceof BaseView) {
            ((BaseView) mContext).showLoading();
        }
    }

    @Override
    public void hideLoading() {
        checkActivityAttached();
        if (mContext != null && mContext instanceof BaseView) {
            ((BaseView) mContext).hideLoading();
        }
    }

    protected boolean isAttachedContext() {
        return getActivity() != null;
    }

    public void checkActivityAttached() {
        if (getActivity() == null) {
            throw new ActivityNotAttachedException();
        }
    }

    public static class ActivityNotAttachedException extends RuntimeException {

        public ActivityNotAttachedException() {
            super("Fragment has disconnected from Activity");
        }
    }

    @LayoutRes
    protected abstract int getLayoutId();

    protected abstract void initViews();

    protected abstract void initViewListeners();

    protected void saveDataToMemory(Bundle outState) {}

    protected void obtainDataFromMemory(Bundle savedInstanceState) {}
}
