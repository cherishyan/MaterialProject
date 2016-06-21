/*
 * Copyright (c) 2015 Zhang Hai <Dreaming.in.Code.ZH@Gmail.com>
 * All Rights Reserved.
 */

package me.jinqiang.android.material.eventbus;

public class BroadcastDeletedEvent {

    public long broadcastId;

    public BroadcastDeletedEvent(long broadcastId) {
        this.broadcastId = broadcastId;
    }
}
