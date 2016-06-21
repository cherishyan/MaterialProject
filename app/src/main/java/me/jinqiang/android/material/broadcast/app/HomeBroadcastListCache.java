/*
 * Copyright (c) 2015 Zhang Hai <Dreaming.in.Code.ZH@Gmail.com>
 * All Rights Reserved.
 */

package me.jinqiang.android.material.broadcast.app;

import android.content.Context;
import android.os.Handler;

import com.google.gson.reflect.TypeToken;

import java.util.List;

import me.jinqiang.android.material.network.api.info.Broadcast;
import me.jinqiang.android.material.util.Callback;
import me.jinqiang.android.material.util.DiskCacheHelper;

public class HomeBroadcastListCache {

    private static final int MAX_LIST_SIZE = 20;

    private static final String KEY = HomeBroadcastListCache.class.getName();

    public static void get(Handler handler, Callback<List<Broadcast>> callback, Context context) {
        DiskCacheHelper.getGson(KEY, new TypeToken<List<Broadcast>>() {}, handler, callback,
                context);
    }

    public static void put(List<Broadcast> broadcastList, Context context) {
        if (broadcastList.size() > MAX_LIST_SIZE) {
            broadcastList = broadcastList.subList(0, MAX_LIST_SIZE);
        }
        DiskCacheHelper.putGson(KEY, broadcastList, new TypeToken<List<Broadcast>>() {}, context);
    }
}
