/*
 * Copyright (c) 2016 Zhang Hai <Dreaming.in.Code.ZH@Gmail.com>
 * All Rights Reserved.
 */

package me.jinqiang.android.material.ui;

import android.content.Context;
import android.util.AttributeSet;

import org.threeten.bp.format.DateTimeParseException;

import me.jinqiang.android.material.R;
import me.jinqiang.android.material.network.api.info.Broadcast;
import me.jinqiang.android.material.util.LogUtils;
import me.jinqiang.android.material.util.TimeUtils;

public class TimeActionTextView extends TimeTextView {

    private String mAction;

    public TimeActionTextView(Context context) {
        super(context);
    }

    public TimeActionTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TimeActionTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public TimeActionTextView(Context context, AttributeSet attrs, int defStyleAttr,
                              int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void setDoubanTime(String doubanTime) {
        throw new UnsupportedOperationException("Use setDoubanTimeAndAction() instead.");
    }

    /**
     * Should behave the same as {@link Broadcast#getActionWithTime(Context)}.
     */
    public void setDoubanTimeAndAction(String doubanTime, String action) {
        mAction = action;
        try {
            setTime(TimeUtils.parseDoubanDateTime(doubanTime));
        } catch (DateTimeParseException e) {
            LogUtils.e("Unable to parse date time: " + doubanTime);
            e.printStackTrace();
            setTimeText(doubanTime);
        }
    }

    @Override
    protected void setTimeText(String timeText) {
        setText(getContext().getString(R.string.broadcast_time_action_format, timeText, mAction));
    }
}
