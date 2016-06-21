package me.jinqiang.android.material.zhihu;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.VolleyError;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.jinqiang.android.material.main.ui.MainActivity;
import me.jinqiang.android.material.network.api.ApiRequests;
import me.jinqiang.android.material.network.zhihuInfo.BaseNewsContent;
import me.jinqiang.android.material.ui.LoadMoreAdapter;
import me.jinqiang.android.material.ui.OnVerticalScrollWithPagingSlopListener;
import me.jinqiang.android.material.util.LogUtils;
import me.jinqiang.android.material.util.RecyclerViewUtils;
import me.jinqiang.android.material.util.ViewUtils;
import me.zhanghai.android.customtabshelper.CustomTabsHelperFragment;
import me.jinqiang.android.material.R;
import me.jinqiang.android.material.app.RetainDataFragment;
import me.jinqiang.android.material.network.RequestFragment;
import me.jinqiang.android.material.network.api.ApiRequest;
import me.jinqiang.android.material.network.zhihuInfo.Latest;
import me.jinqiang.android.material.ui.AppBarManager;
import me.jinqiang.android.material.ui.NoChangeAnimationItemAnimator;

/**
 * @author  yanjinqiang
 * @time 2016/3/31.
 */
public class zhihuNewsFragment extends Fragment implements RequestFragment.Listener, zhihuAdapter.Listener {

    @Bind(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @Bind(R.id.news_list)
    RecyclerView mNewslist;
//    List<BaseNewsContent> mContent;
    zhihuAdapter mAdapter;
    LoadMoreAdapter mLoadMoreAdapter;
    //保存数据的fragment,防止被忽然销毁数据丢失.
    private RetainDataFragment mRetainDataFragment;
    private static final String KEY = zhihuNewsFragment.class.getName() + '.';
    private static final String RETAIN_DATA_KEY_NEWS_LIST = KEY + "zhihuNews_list";

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
    public void onStart() {
        super.onStart();
        initData();
    }

    //在销毁的时候做数据本地保存，启动的时候可以快点拿到数据
    @Override
    public void onStop() {
        super.onStop();
//        if(mAdapter)
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final MainActivity activity = (MainActivity) getActivity();
        CustomTabsHelperFragment.attachTo(this);
        //保存数据
        mRetainDataFragment = RetainDataFragment.attachTo(this);
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
        List<BaseNewsContent> mContent = mRetainDataFragment.remove(RETAIN_DATA_KEY_NEWS_LIST);

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
                initData();
            }
        });
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mRetainDataFragment.put(RETAIN_DATA_KEY_NEWS_LIST, mAdapter.getList());

    }

    /**
     * loading data
     */
    public void initData(){
        ApiRequest<Latest> request = ApiRequests.zhihuLatestRequest(getActivity());
        LoadzhihuNewsListState state = new LoadzhihuNewsListState(false, 20);
        RequestFragment.startRequest(0, request, state, this);
        onRefresh(true);

    }
    //刷新界面
    public void onRefresh(boolean isRefresh){
            mSwipeRefreshLayout.setEnabled(!isRefresh);
        if (!isRefresh) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }
    @Override
    public void onVolleyResponse(int requestCode, boolean successful, Object result, VolleyError error, Object requestState) {
        switch (requestCode) {
            case 0:
                // TODO
                if(result != null) {
                    LogUtils.e("Result successful" + result);
                    //接口返回数据
                    onNewsResponse((Latest) result,(LoadzhihuNewsListState) requestState);
                }
                else
                    LogUtils.e("VolleyError:"+error.getMessage());
                break;
        }
    }

    private void onNewsResponse(Latest result,LoadzhihuNewsListState state){
        if(result.date!=null) {
            LogUtils.e(result.date);
//            mContent = result.stories;
            mAdapter.replace(result.stories);

            onRefresh(false);
        }
    }

    //条目点击监听
    @Override
    public void onOpenBroadcast(BaseNewsContent content, View sharedView) {

    }

    private static class LoadzhihuNewsListState {

        public boolean loadMore;
        public int count;

        public LoadzhihuNewsListState(boolean loadMore, int count) {
            this.loadMore = loadMore;
            this.count = count;
        }
    }
}
