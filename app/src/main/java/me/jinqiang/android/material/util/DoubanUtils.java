/*
 * Copyright (c) 2015 Zhang Hai <Dreaming.in.Code.ZH@Gmail.com>
 * All Rights Reserved.
 */

package me.jinqiang.android.material.util;

import me.jinqiang.android.material.network.api.info.User;

public class DoubanUtils {

    private DoubanUtils() {}

    public static String getAtUserString(String idOrUid) {
        return '@' + idOrUid + ' ';
    }

    public static String getAtUserString(User user) {
        return getAtUserString(user.uid);
    }
}
