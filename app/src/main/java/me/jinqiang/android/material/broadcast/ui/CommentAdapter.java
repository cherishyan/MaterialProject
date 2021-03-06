/*
 * Copyright (c) 2015 Zhang Hai <Dreaming.in.Code.ZH@Gmail.com>
 * All Rights Reserved.
 */

package me.jinqiang.android.material.broadcast.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.jinqiang.android.material.network.api.info.Comment;
import me.jinqiang.android.material.ui.ClickableSimpleAdapter;
import me.jinqiang.android.material.ui.TimeTextView;
import me.jinqiang.android.material.util.ImageUtils;
import me.jinqiang.android.material.util.RecyclerViewUtils;
import me.jinqiang.android.material.util.ViewUtils;
import me.jinqiang.android.material.R;
import me.jinqiang.android.material.link.UriHandler;

public class CommentAdapter extends ClickableSimpleAdapter<Comment, CommentAdapter.ViewHolder> {

    public CommentAdapter(List<Comment> commentList,
                          OnItemClickListener<Comment, CommentAdapter.ViewHolder> onItemClickListener) {
        super(commentList, onItemClickListener, null);

        setHasStableIds(true);
    }

    @Override
    public long getItemId(int position) {
        return getList().get(position).id;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder = new ViewHolder(ViewUtils.inflate(R.layout.broadcast_comment_item,
                parent));
        ViewUtils.setTextViewLinkClickable(holder.textText);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Context context = RecyclerViewUtils.getContext(holder);
        final Comment comment = getItem(position);
        ImageUtils.loadAvatar(holder.avatarImage, comment.author.avatar, context);
        holder.avatarImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UriHandler.open(comment.author.alt, context);
            }
        });
        holder.nameText.setText(comment.author.name);
        holder.timeText.setDoubanTime(comment.createdAt);
        holder.textText.setText(comment.getContentWithEntities(context));
    }

    @Override
    public void onViewRecycled(ViewHolder holder) {
        holder.avatarImage.setImageDrawable(null);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.avatar)
        public ImageView avatarImage;
        @Bind(R.id.name)
        public TextView nameText;
        @Bind(R.id.time)
        public TimeTextView timeText;
        @Bind(R.id.text)
        public TextView textText;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
