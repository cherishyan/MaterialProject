package me.jinqiang.android.material.live;

import me.jinqiang.android.material.live.Entity.DouyuEntity;
import me.jinqiang.android.material.live.Entity.RoomEntity;
import me.jinqiang.android.material.network.zhihuInfo.ZhihuLatestDetail;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;
import rx.observers.Observers;

/**
 * Created by yanjinqiang on 2016/7/7.
 */
public interface DouyuInterface {
    //http://capi.douyucdn.cn/api/v1/live?limit=20&offset=0
    @GET("/api/v1/live")
    Observable<DouyuEntity> getDouyuHotLive(@Query("limit") int limit,@Query("offset") int offset);

    //http://douyutv.com/api/v1/room/138286?aid=android&client_sys=android&time=1469015943391&auth=f01ebde0c274fe60de19be6b5a04f634
    @GET("{room}")
    Call<RoomEntity> listRepos(@Path("room") String room,@Query("aid")String aid,@Query("client_sys")String client_sys,@Query("time") String time,@Query("auth") String query);
}
