/*
 * Copyright (c) 2015 Zhang Hai <Dreaming.in.Code.ZH@Gmail.com>
 * All Rights Reserved.
 */

package me.jinqiang.android.material.account.info;

import me.jinqiang.android.material.BuildConfig;

public class AccountContract {

    public static final String ACCOUNT_TYPE = BuildConfig.APPLICATION_ID;

    public static final String AUTH_TOKEN_TYPE = BuildConfig.APPLICATION_ID;

    public static final String KEY_USER_NAME = BuildConfig.APPLICATION_ID + ".user_name";
    public static final String KEY_USER_ID = BuildConfig.APPLICATION_ID + ".user_id";
    public static final long INVALID_USER_ID = -1;
    public static final String KEY_REFRESH_TOKEN = BuildConfig.APPLICATION_ID
            + ".refresh_token";

    private AccountContract() {}
}
