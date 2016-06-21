package me.jinqiang.android.material.network.zhihuInfo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * @author  yanjinqiang
 * @time 2016/3/31.
 */
public class BaseNewsContent implements Parcelable {
    public String ga_prefix;
    public long id;
    public ArrayList<String> images = new ArrayList<>();
    public String title;
    public long type;

    protected BaseNewsContent(Parcel in) {
        ga_prefix = in.readString();
        id = in.readLong();
        images = in.createStringArrayList();
        title = in.readString();
        type = in.readLong();
    }

    public static final Creator<BaseNewsContent> CREATOR = new Creator<BaseNewsContent>() {
        @Override
        public BaseNewsContent createFromParcel(Parcel in) {
            return new BaseNewsContent(in);
        }

        @Override
        public BaseNewsContent[] newArray(int size) {
            return new BaseNewsContent[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(ga_prefix);
        dest.writeLong(id);
        dest.writeStringList(images);
        dest.writeString(title);
        dest.writeLong(type);
    }
}
