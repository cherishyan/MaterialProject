/*
 * Copyright (c) 2015 Zhang Hai <Dreaming.in.Code.ZH@Gmail.com>
 * All Rights Reserved.
 */

package me.jinqiang.android.material.broadcast.ui;

import java.util.List;

import me.jinqiang.android.material.network.api.info.User;
import me.jinqiang.android.material.network.api.ApiRequest;
import me.jinqiang.android.material.network.api.ApiRequests;
import me.jinqiang.android.material.network.api.info.Broadcast;

public class BroadcastRebroadcastersListFragment extends BroadcastUserListFragment {

    /**
     * @deprecated Use {@link #newInstance(Broadcast)} instead.
     */
    public BroadcastRebroadcastersListFragment() {}

    public static BroadcastRebroadcastersListFragment newInstance(Broadcast broadcast) {
        //noinspection deprecation
        return (BroadcastRebroadcastersListFragment) new BroadcastRebroadcastersListFragment()
                .setArguments(broadcast);
    }

    @Override
    protected ApiRequest<List<User>> onCreateRequest(Integer start, Integer count) {
        return ApiRequests.newBroadcastRebroadcasterListRequest(getBroadcast().id, start, count,
                getActivity());
    }

    @Override
    protected boolean onUpdateBroadcast(Broadcast broadcast, List<User> userList) {
        if (broadcast.rebroadcastCount < userList.size()) {
            broadcast.rebroadcastCount = userList.size();
            return true;
        }
        return false;
    }
}
