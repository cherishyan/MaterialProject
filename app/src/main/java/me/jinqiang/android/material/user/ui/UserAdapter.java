/*
 * Copyright (c) 2015 Zhang Hai <Dreaming.in.Code.ZH@Gmail.com>
 * All Rights Reserved.
 */

package me.jinqiang.android.material.user.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.jinqiang.android.material.network.api.info.User;
import me.jinqiang.android.material.ui.SimpleAdapter;
import me.jinqiang.android.material.util.ImageUtils;
import me.jinqiang.android.material.util.RecyclerViewUtils;
import me.jinqiang.android.material.util.ViewUtils;
import me.jinqiang.android.material.R;
import me.jinqiang.android.material.link.UriHandler;

public class UserAdapter extends SimpleAdapter<User, UserAdapter.ViewHolder> {

    public UserAdapter(List<User> userList) {
        super(userList);

        setHasStableIds(true);
    }

    @Override
    public long getItemId(int position) {
        return getList().get(position).id;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(ViewUtils.inflate(R.layout.user_item, parent));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Context context = RecyclerViewUtils.getContext(holder);
        final User user = getItem(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UriHandler.open(user.alt, context);
            }
        });
        ImageUtils.loadAvatar(holder.avatarImage, user.avatar, context);
        holder.nameText.setText(user.name);
        holder.descriptionText.setText(user.uid);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.avatar)
        public ImageView avatarImage;
        @Bind(R.id.name)
        public TextView nameText;
        @Bind(R.id.description)
        public TextView descriptionText;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
