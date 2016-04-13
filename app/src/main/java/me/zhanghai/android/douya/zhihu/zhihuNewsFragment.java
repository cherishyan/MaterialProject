package me.zhanghai.android.douya.zhihu;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.VolleyError;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.zhanghai.android.customtabshelper.CustomTabsHelperFragment;
import me.zhanghai.android.douya.R;
import me.zhanghai.android.douya.broadcast.ui.BroadcastAdapter;
import me.zhanghai.android.douya.main.ui.MainActivity;
import me.zhanghai.android.douya.network.RequestFragment;
import me.zhanghai.android.douya.network.api.ApiRequest;
import me.zhanghai.android.douya.network.api.ApiRequests;
import me.zhanghai.android.douya.network.api.info.Broadcast;
import me.zhanghai.android.douya.network.zhihuInfo.BaseNewsContent;
import me.zhanghai.android.douya.network.zhihuInfo.Latest;
import me.zhanghai.android.douya.ui.AppBarManager;
import me.zhanghai.android.douya.ui.LoadMoreAdapter;
import me.zhanghai.android.douya.ui.NoChangeAnimationItemAnimator;
import me.zhanghai.android.douya.ui.OnVerticalScrollWithPagingSlopListener;
import me.zhanghai.android.douya.util.LogUtils;
import me.zhanghai.android.douya.util.RecyclerViewUtils;
import me.zhanghai.android.douya.util.ViewUtils;

/**
 * @author  yanjinqiang
 * @time 2016/3/31.
 */
public class zhihuNewsFragment extends Fragment implements RequestFragment.Listener, zhihuAdapter.Listener {

    @Bind(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @Bind(R.id.news_list)

    RecyclerView mNewslist;
    boolean reFresh = false;
    List<BaseNewsContent> mContent;
    zhihuAdapter mAdapter;
    LoadMoreAdapter mLoadMoreAdapter;

    public static zhihuNewsFragment newInstance() {
        //noinspection deprecation
        return new zhihuNewsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.zhihu_layout, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //注解
        ButterKnife.bind(this,view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final MainActivity activity = (MainActivity) getActivity();
        CustomTabsHelperFragment.attachTo(this);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initData();
            }
        });
        mNewslist.setHasFixedSize(true);
        mNewslist.setItemAnimator(new NoChangeAnimationItemAnimator());
        if (ViewUtils.hasSw600dp(activity)) {
            int columnCount = ViewUtils.isInLandscape(activity) ? 3 : 2;
            mNewslist.setLayoutManager(new StaggeredGridLayoutManager(columnCount,
                    StaggeredGridLayoutManager.VERTICAL));
        } else {
            mNewslist.setLayoutManager(new LinearLayoutManager(activity));
        }
        final AppBarManager appBarManager = (AppBarManager) getParentFragment();
        //数据adapter
        mAdapter = new zhihuAdapter(mContent, this);
        //添加加载更多adapter
        mLoadMoreAdapter = new LoadMoreAdapter(R.layout.load_more_card_item, mAdapter);
        //数据adapter和加载adapter都加入list
        mNewslist.setAdapter(mLoadMoreAdapter);
        //滑动监听，使其下滑的时候去掉appbar
        mNewslist.addOnScrollListener(new OnVerticalScrollWithPagingSlopListener(activity) {
            @Override
            public void onScrolledUp(int dy) {
                if (!RecyclerViewUtils.hasFirstChildReachedTop(mNewslist)) {
                    onShow();
                } else {
                    super.onScrolledUp(dy);
                }
            }

            @Override
            public void onScrolledUp() {
                onShow();
            }

            private void onShow() {
                appBarManager.showAppBar();
//                mSendFab.show();
            }

            @Override
            public void onScrolledDown() {
                if (RecyclerViewUtils.hasFirstChildReachedTop(mNewslist)) {
                    appBarManager.hideAppBar();
//                    mSendFab.hide();
                }
            }

            @Override
            public void onScrolledToBottom() {
                reFresh = true;
                initData();
            }
        });
    }

    /**
     * loading data
     */
    public void initData(){
        ApiRequest<Latest> request = ApiRequests.zhihuLatestRequest(getActivity());
        LoadBroadcastListState state = new LoadBroadcastListState(false, 20);
        RequestFragment.startRequest(0, request, state, this);
        onRefresh(true);

    }
    //刷新界面
    public void onRefresh(boolean isRefresh){
            mSwipeRefreshLayout.setRefreshing(!isRefresh);
    }
    @Override
    public void onVolleyResponse(int requestCode, boolean successful, Object result, VolleyError error, Object requestState) {
        switch (requestCode) {
            case 0:
                // TODO
                if(result != null) {
                    LogUtils.e("Result successful" + result);
                    //接口返回数据
                    onNewsResponse((Latest) result);
                }
                else
                    LogUtils.e("VolleyError:"+error.getMessage());
                break;
        }
    }

    private void onNewsResponse(Latest result){
        if(result.date!=null) {
            LogUtils.e(result.date);
            mContent = result.stories;
        }
    }

    //条目点击监听
    @Override
    public void onOpenBroadcast(BaseNewsContent content, View sharedView) {

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
