/*
 * Copyright (c) 2016 Zhang Hai <Dreaming.in.Code.ZH@Gmail.com>
 * All Rights Reserved.
 */

package me.jinqiang.android.material.ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;

import org.threeten.bp.ZonedDateTime;
import org.threeten.bp.format.DateTimeParseException;

import me.jinqiang.android.material.util.LogUtils;
import me.jinqiang.android.material.util.TimeUtils;

public class TimeTextView extends TextView {

    private static final int UPDATE_TIME_TEXT_INTERVAL_MILLI = 60 * 1000;

    private final Runnable mUpdateTimeTextRunnable = new Runnable() {
        @Override
        public void run() {
            updateTimeText();
        }
    };

    private ZonedDateTime mTime;

    public TimeTextView(Context context) {
        super(context);
    }

    public TimeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TimeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public TimeTextView(Context context, AttributeSet attrs, int defStyleAttr,
                        int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public ZonedDateTime getTime() {
        return mTime;
    }

    public void setTime(ZonedDateTime time) {
        mTime = time;
        updateTimeText();
    }

    /**
     * Should behave the same as {@link TimeUtils#formatDoubanDateTime(String, Context)}.
     */
    public void setDoubanTime(String doubanTime) {
        try {
            setTime(TimeUtils.parseDoubanDateTime(doubanTime));
        } catch (DateTimeParseException e) {
            LogUtils.e("Unable to parse date time: " + doubanTime);
            e.printStackTrace();
            setText(doubanTime);
        }
    }

    private void updateTimeText() {
        removeCallbacks(mUpdateTimeTextRunnable);
        if (mTime != null) {
            setTimeText(TimeUtils.formatDateTime(mTime, getContext()));
            postDelayed(mUpdateTimeTextRunnable, UPDATE_TIME_TEXT_INTERVAL_MILLI);
        }
    }

    protected void setTimeText(String timeText) {
        setText(timeText);
    }
}
