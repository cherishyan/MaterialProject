/*
 * Copyright (c) 2015 Zhang Hai <Dreaming.in.Code.ZH@Gmail.com>
 * All Rights Reserved.
 */

package me.jinqiang.android.material.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonHelper {

    private static final Gson GSON = new GsonBuilder()
            .serializeNulls()
            .generateNonExecutableJson()
            // TODO
            //.registerTypeAdapter()
            .create();

    private GsonHelper() {}

    public static Gson get() {
        return GSON;
    }
}
