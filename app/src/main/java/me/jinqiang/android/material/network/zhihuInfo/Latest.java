package me.jinqiang.android.material.network.zhihuInfo;

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

    public ArrayList<TopNewsContent> top_stories = new ArrayList<>();


    protected Latest(Parcel in) {
        date = in.readString();
        stories = in.createTypedArrayList(BaseNewsContent.CREATOR);
        top_stories = in.createTypedArrayList(TopNewsContent.CREATOR);
    }

    public static final Creator<Latest> CREATOR = new Creator<Latest>() {
        @Override
        public Latest createFromParcel(Parcel in) {
            return new Latest(in);
        }

        @Override
        public Latest[] newArray(int size) {
            return new Latest[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(date);
        dest.writeTypedList(stories);
        dest.writeTypedList(top_stories);
    }
}
