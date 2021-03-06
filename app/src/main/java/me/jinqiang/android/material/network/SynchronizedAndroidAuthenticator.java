/*
 * Copyright (c) 2015 Zhang Hai <Dreaming.in.Code.ZH@Gmail.com>
 * All Rights Reserved.
 */

package me.jinqiang.android.material.network;

import android.accounts.Account;
import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.toolbox.AndroidAuthenticator;

/** google验证应用安全保证用户数据的方法，将其实现为同步验证。
 * An {@link AndroidAuthenticator} with {@link #getAuthToken()} synchronized.
 */
public class SynchronizedAndroidAuthenticator extends AndroidAuthenticator {

    public SynchronizedAndroidAuthenticator(Context context, Account account,
                                            String authTokenType) {
        super(context, account, authTokenType);
    }

    public SynchronizedAndroidAuthenticator(Context context, Account account, String authTokenType,
                                            boolean notifyAuthFailure) {
        super(context, account, authTokenType, notifyAuthFailure);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized String getAuthToken() throws AuthFailureError {
        return super.getAuthToken();
    }
}
