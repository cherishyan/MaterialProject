package me.jinqiang.android.material.live;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.pili.pldroid.player.PLMediaPlayer;
import com.pili.pldroid.player.widget.PLVideoView;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.jinqiang.android.material.R;
import me.jinqiang.android.material.live.Entity.DouyuEntity;
import me.jinqiang.android.material.live.Entity.RoomEntity;
import me.jinqiang.android.material.live.activity.LiveActivity;
import me.jinqiang.android.material.live.activity.RoomActivity;
import me.jinqiang.android.material.live.presenter.LiveAdapter;
import me.jinqiang.android.material.live.presenter.MediaController;
import me.jinqiang.android.material.main.ui.MainActivity;
import me.jinqiang.android.material.network.api.ApiContract;
import me.jinqiang.android.material.network.zhihuInfo.BaseNewsContent;
import me.jinqiang.android.material.network.zhihuInfo.ZhihuLatestDetail;
import me.jinqiang.android.material.network.zhihuInfo.zhihuService;
import me.jinqiang.android.material.ui.AppBarManager;
import me.jinqiang.android.material.ui.FriendlySwipeRefreshLayout;
import me.jinqiang.android.material.ui.LoadMoreAdapter;
import me.jinqiang.android.material.ui.OnVerticalScrollWithPagingSlopListener;
import me.jinqiang.android.material.ui.WebViewActivity;
import me.jinqiang.android.material.util.RecyclerViewUtils;
import me.jinqiang.android.material.util.TimeUtils;
import me.jinqiang.android.material.util.ViewUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author  yanjinqiang on 2016/7/7.
 */
public class DouyuLiveFragment extends Fragment implements LiveAdapter.Listener{

    @Bind(R.id.live_refreshlayout)
    FriendlySwipeRefreshLayout mRefreshLayout;
    @Bind(R.id.live_recyclerview)
    RecyclerView mRecyclerView;
    private LiveAdapter mAdapter;
    LoadMoreAdapter mLoadMoreAdapter;
    @Bind(R.id.progress)
    ProgressBar mProgress;

    public static DouyuLiveFragment newInstance() {
        //noinspection deprecation
        return new DouyuLiveFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.live_hot, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final LiveActivity activity = (LiveActivity) getActivity();

        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initData();
            }
        });
        mRecyclerView.setHasFixedSize(true);
        int columnCount = 2;
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(columnCount,
                StaggeredGridLayoutManager.VERTICAL));

        initData();
        ViewUtils.setVisibleOrGone(mProgress,true);
//        mVideoView.setOnErrorListener(new PLMediaPlayer.OnErrorListener() {
//            @Override
//            public boolean onError(PLMediaPlayer plMediaPlayer, int i) {
//                Log.e("yjq","播放错误"+i);
//                return false;
//            }
//        });
//        MediaController mMediaController = new MediaController(getContext());
//        mVideoView.setMediaController(mMediaController);
//        String path = "http://222.246.232.141/hdla.douyutv.com/live/17349r8fuIG4M8RD.flv?wsSecret=61823f29a56f69ff9f765ceb8ca6e8c9&wsTime=1468902792&wshc_tag=0&wsts_tag=578dad89&wsid_tag=ca696b32&wsiphost=ipdbm";
//        mVideoView.setVideoPath(path);
//        mVideoView.start();
    }

//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
////        initData();
//
//        MediaController mMediaController = new MediaController(getContext());
//        mVideoView.setMediaController(mMediaController);
//        String path = "http://222.246.232.142/hdl3a.douyutv.com/live/10903rpHniC3dS6f.flv?wsSecret=3f717c34ac278d04e8c63fbe42597472&wsTime=1467970365&wshc_tag=0&wsts_tag=577f733e&wsid_tag=ca696b32&wsiphost=ipdbm";
//        mVideoView.setVideoPath(path);
//        mVideoView.start();
//    }

    //刷新界面
    public void onRefresh(boolean isRefresh){
        mRefreshLayout.setEnabled(!isRefresh);
        if (!isRefresh) {
            mRefreshLayout.setRefreshing(false);
        }
    }

    public void initData(){
        onRefresh(true);
        String BaseUrl = ApiContract.Request.DOUYU_LIVE;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        DouyuInterface douyuInterface = retrofit.create(DouyuInterface.class);
        douyuInterface.getDouyuHotLive(20,0)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<DouyuEntity>() {
                            @Override
                            public void onCompleted() {
                                Log.e("yjq","Get 20 Live Success ");
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.e("yjq","Get 20 Live failed !"+e.getMessage());
                            }

                            @Override
                            public void onNext(DouyuEntity douyuEntity) {
                                Log.e("yjq",douyuEntity.toString());
                                onRefresh(false);
                                ViewUtils.setVisibleOrGone(mProgress,false);
                                if(mAdapter == null){
                                    mAdapter = new LiveAdapter(douyuEntity.getData(),DouyuLiveFragment.this,getContext());
                                    //添加加载更多adapter
                                    mLoadMoreAdapter = new LoadMoreAdapter(R.layout.load_more_card_item, mAdapter);
                                    //数据adapter和加载adapter都加入list
                                    mRecyclerView.setAdapter(mLoadMoreAdapter);
                                }else{
                                    mAdapter.replace(douyuEntity.getData());
                                }

                            }
                        });
    }


    @Override
    public void onOpenRoomActivity(DouyuEntity.DataBean dataBean) {
        String room_id = dataBean.getRoom_id();
        DecodeRtmpPath(room_id);
        ViewUtils.setVisibleOrGone(mProgress,true);
    }
    public void DecodeRtmpPath(String room_id){
        String api_url = "http://douyutv.com/api/v1/";
        long currentTimeMillis = System.currentTimeMillis();
        String room_url = "room/" + room_id + "?aid=android&client_sys=android&time=" + currentTimeMillis;
        final String url_json = api_url + room_url + "&auth=" + getMD5(room_url + "1231");
        Log.e("yjq","url______ "+url_json);
        //TODO 根据url_json 获取网页内容拿到rtmp_url+rtmp_live
        getRtmpFromHtml(room_id,currentTimeMillis+"",getMD5(room_url + "1231"));
//        try {
//            String  html = getHtml("http://hdl3.douyutv.com/live/266055riGh2BAHjG.flv?wsSecret=2345e582f4c159af88187f01490141cc&wsTime=1469014030");
//            Log.e("yjq",html);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        String url_rtmp = "http://63.159.216.111/hdla.douyutv.com/live/782355rjT2xi5PJA.flv?wsSecret=d566f7f1080f79da811366050d02c5e9&wsTime=1469010404&wsiphost=ipdb";
//        Intent intent = new Intent(getContext(), RoomActivity.class);
//        intent.putExtra("rtmp_path","http://hdl3.douyutv.com/live/266055riGh2BAHjG.flv?wsSecret=2345e582f4c159af88187f01490141cc&wsTime=1469014030");
//        startActivity(intent);
    }
    public String getMD5(String info)
    {
        try
        {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(info.getBytes("UTF-8"));
            byte[] encryption = md5.digest();

            StringBuffer strBuf = new StringBuffer();
            for (int i = 0; i < encryption.length; i++)
            {
                if (Integer.toHexString(0xff & encryption[i]).length() == 1)
                {
                    strBuf.append("0").append(Integer.toHexString(0xff & encryption[i]));
                }
                else
                {
                    strBuf.append(Integer.toHexString(0xff & encryption[i]));
                }
            }

            return strBuf.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            return "";
        }
        catch (UnsupportedEncodingException e)
        {
            return "";
        }
    }
    public void getRtmpFromHtml(String room_id,String time,String auth){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://douyutv.com/api/v1/room/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        DouyuInterface service = retrofit.create(DouyuInterface.class);
        Call<RoomEntity> repos = service.listRepos(room_id,"android","android",time,auth);
        repos.enqueue(new Callback<RoomEntity>() {
            @Override
            public void onResponse(Call<RoomEntity> call, Response<RoomEntity> response) {
                ViewUtils.setVisibleOrGone(mProgress,false);
                if(response!=null) {
                    Log.e("yjq", "Room success::::" + response.body().getData().getRtmp_url() + response.body().getData().getRtmp_live());
                    //http://hdla.douyutv.com/live/138286rKD7QzmUP9.flv?wsSecret=093045425a7c93563565cbe47e144fbd&wsTime=1469016721
                    String rtmp_path = response.body().getData().getRtmp_url() + "/" + response.body().getData().getRtmp_live();
                    Intent intent = new Intent(getContext(), RoomActivity.class);
                    intent.putExtra("rtmp_path", rtmp_path);
                    intent.putExtra("room_name", response.body().getData().getRoom_name());
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<RoomEntity> call, Throwable t) {
                Log.e("yjq","Room failed!!"+t.getMessage());
                ViewUtils.setVisibleOrGone(mProgress,false);
            }
        });
    }
    public static String getHtml(String path) throws Exception {
        // 通过网络地址创建URL对象
        URL url = new URL(path);
        // 根据URL
        // 打开连接，URL.openConnection函数会根据URL的类型，返回不同的URLConnection子类的对象，这里URL是一个http，因此实际返回的是HttpURLConnection
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        // 设定URL的请求类别，有POST、GET 两类
        conn.setRequestMethod("GET");
        //设置从主机读取数据超时（单位：毫秒）
        conn.setConnectTimeout(5000);
        //设置连接主机超时（单位：毫秒）
        conn.setReadTimeout(5000);
        // 通过打开的连接读取的输入流,获取html数据
        InputStream inStream = conn.getInputStream();
        // 得到html的二进制数据
        byte[] data = readInputStream(inStream);
        // 是用指定的字符集解码指定的字节数组构造一个新的字符串
        String html = new String(data, "utf-8");
        return html;
    }
    /**
     * 读取输入流，得到html的二进制数据
     *
     * @param inStream
     * @return
     * @throws Exception
     */
    public static byte[] readInputStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        inStream.close();
        return outStream.toByteArray();
    }

}
