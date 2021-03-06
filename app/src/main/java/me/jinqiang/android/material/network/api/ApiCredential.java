/*
 * Copyright (c) 2016 Zhang Hai <Dreaming.in.Code.ZH@Gmail.com>
 * All Rights Reserved.
 */

package me.jinqiang.android.material.network.api;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import me.jinqiang.android.material.util.IoUtils;
import me.jinqiang.android.material.util.LogUtils;
import me.jinqiang.android.material.util.StandardCharsetsCompat;

interface ApiCredential {

    interface Douya {
        String KEY = "0dad551ec0f84ed02907ff5c42e8ec70";
        String SECRET = "9e8bb54dc3288cdf";
    }

    interface Frodo {
        String KEY = "0dad551ec0f84ed02907ff5c42e8ec70";
        String SECRET = "9e8bb54dc3288cdf";
//        String KEY = HackyApiCredentialHelper.loadStringFromFile(new File(
//                Environment.getExternalStorageDirectory(), "Douya/API_KEY"));
//        String SECRET = HackyApiCredentialHelper.loadStringFromFile(new File(
//                Environment.getExternalStorageDirectory(), "Douya/API_SECRET"));
    }
}

class HackyApiCredentialHelper {

    public static String loadStringFromFile(File file) {
        if (!file.exists()) {
            LogUtils.e("File " + file + " does not exist.");
            return "";
        }
        try {
            return IoUtils.inputStreamToString(new FileInputStream(file),
                    StandardCharsetsCompat.UTF_8.name())
                    .trim();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
