package me.jinqiang.android.material.network.zhihuInfo;

import java.io.Serializable;

/**
 * Created by yanjinqiang on 2016/5/23.
 */
public class oneBean implements Serializable {
    String index;
    String content;
    String imgUrl;

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
