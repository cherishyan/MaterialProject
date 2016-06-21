/*
 * Copyright (c) 2015 Zhang Hai <Dreaming.in.Code.ZH@Gmail.com>
 * All Rights Reserved.
 */

package me.jinqiang.android.material.notification.ui;

import android.content.Context;
import android.content.res.ColorStateList;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.jinqiang.android.material.ui.TimeTextView;
import me.jinqiang.android.material.util.RecyclerViewUtils;
import me.jinqiang.android.material.R;
import me.jinqiang.android.material.link.UriHandler;
import me.jinqiang.android.material.network.api.info.Notification;
import me.jinqiang.android.material.ui.SimpleAdapter;
import me.jinqiang.android.material.util.ViewUtils;

public class NotificationAdapter extends SimpleAdapter<Notification,
        NotificationAdapter.ViewHolder> {

    private final ColorStateList mTextColorPrimary;
    private final ColorStateList mTextColorSecondary;

    public NotificationAdapter(List<Notification> notificationList, Context context) {
        super(notificationList);

        mTextColorPrimary = ViewUtils.getColorStateListFromAttrRes(android.R.attr.textColorPrimary,
                context);
        mTextColorSecondary = ViewUtils.getColorStateListFromAttrRes(
                android.R.attr.textColorSecondary, context);

        setHasStableIds(true);
    }

    @Override
    public long getItemId(int position) {
        return getList().get(position).id;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(ViewUtils.inflate(R.layout.notification_item, parent));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Context context = RecyclerViewUtils.getContext(holder);
        final Notification notification = getItem(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                markNotificationAsRead(notification);
                UriHandler.open(notification.targetUri, context);
            }
        });
        holder.textText.setText(notification.text);
        holder.textText.setTextColor(notification.read ? mTextColorSecondary : mTextColorPrimary);
        holder.timeText.setDoubanTime(notification.time);
    }

    private void markNotificationAsRead(Notification notification) {
        notification.read = true;
        notifyItemChangedById(notification.id);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.text)
        public TextView textText;
        @Bind(R.id.time)
        public TimeTextView timeText;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
