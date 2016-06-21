/*
 * Copyright (c) 2015 Zhang Hai <Dreaming.in.Code.ZH@Gmail.com>
 * All Rights Reserved.
 */

package me.jinqiang.android.material.eventbus;

import me.jinqiang.android.material.network.api.info.Broadcast;

public class BroadcastUpdatedEvent {

    public Broadcast broadcast;

    public BroadcastUpdatedEvent(Broadcast broadcast) {
        this.broadcast = broadcast;
    }
}
