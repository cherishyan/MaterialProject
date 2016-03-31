package me.zhanghai.android.douya.network.zhihuInfo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * 最新消息 Bean
 * @author  yanjinqiang
 * @time 2016/3/31.
 */
public class Latest implements Parcelable{

    public String date;

    public ArrayList<BaseNewsContent> stories = new ArrayList<>();

    public ArrayList<BaseNewsContent> top_stories = new ArrayList<>();

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
