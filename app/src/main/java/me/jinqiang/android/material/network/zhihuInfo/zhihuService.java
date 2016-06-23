package me.jinqiang.android.material.network.zhihuInfo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author  yanjinqiang on 2016/6/23.
 */
public interface zhihuService {
    @GET("{id}")
    Call<ZhihuLatestDetail> listRepos(@Path("id") String id);
}
