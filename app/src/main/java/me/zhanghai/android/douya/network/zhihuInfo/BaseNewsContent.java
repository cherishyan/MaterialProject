package me.zhanghai.android.douya.network.zhihuInfo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author  yanjinqiang
 * @time 2016/3/31.
 */
public class BaseNewsContent implements Parcelable {
    public String ga_prefix;
    public long id;
    public String images;
    public String titile;
    public long type;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
