package me.zhanghai.android.douya.zhihu;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.VolleyError;

import java.util.List;

import me.zhanghai.android.douya.broadcast.ui.BroadcastAdapter;
import me.zhanghai.android.douya.network.RequestFragment;
import me.zhanghai.android.douya.network.api.ApiRequest;
import me.zhanghai.android.douya.network.api.ApiRequests;
import me.zhanghai.android.douya.network.api.info.Broadcast;
import me.zhanghai.android.douya.network.zhihuInfo.Latest;
import me.zhanghai.android.douya.util.LogUtils;

/**
 * @author  yanjinqiang
 * @time 2016/3/31.
 */
public class zhihuNewsFragment extends Fragment implements RequestFragment.Listener{
    private final String TAG = zhihuNewsFragment.class.getName()+":";

    public static zhihuNewsFragment newInstance() {
        //noinspection deprecation
        return new zhihuNewsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    /**
     * loading data
     */
    public void initData(){
        ApiRequest<List<Latest>> request = ApiRequests.zhihuLatestRequest(getActivity());
        LoadBroadcastListState state = new LoadBroadcastListState(false, 20);
        RequestFragment.startRequest(0, request, state, this);

    }


    @Override
    public void onVolleyResponse(int requestCode, boolean successful, Object result, VolleyError error, Object requestState) {
        switch (requestCode) {
            case 0:
                // TODO
                LogUtils.e("123");
                break;
        }
    }

    private static class LoadBroadcastListState {

        public boolean loadMore;
        public int count;

        public LoadBroadcastListState(boolean loadMore, int count) {
            this.loadMore = loadMore;
            this.count = count;
        }
    }
}
