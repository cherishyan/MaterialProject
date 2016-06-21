package me.jinqiang.android.material.zhihu;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.jinqiang.android.material.broadcast.ui.BroadcastLayout;
import me.jinqiang.android.material.network.zhihuInfo.BaseNewsContent;
import me.jinqiang.android.material.util.ViewUtils;
import me.jinqiang.android.material.R;
import me.jinqiang.android.material.ui.SimpleAdapter;

/**
 * @author  yanjinqiang
 * @time 2016/4/13.
 */
public class zhihuAdapter extends SimpleAdapter<BaseNewsContent,zhihuAdapter.ViewHolder> {
    private Listener mListener;
    //构造函数
    public zhihuAdapter(List<BaseNewsContent> newsContents, Listener listener){
        super(newsContents);
        this.mListener = listener;
        setHasStableIds(true);
    }

    public void updateBroadcast(BaseNewsContent updatedNews) {
        int count = getItemCount();
        for (int i = 0; i < count; ++i) {
            BaseNewsContent newsContent = getItem(i);
            if (newsContent.id == updatedNews.id) {
                set(i, updatedNews);
            }
        }
    }
    public void removeBroadcastById(long newsId) {
        int count = getItemCount();
        for (int i = 0; i < count; ) {
            BaseNewsContent newsContent = getItem(i);
            if (newsContent.id == newsId) {
                remove(i);
                --count;
            } else {
                ++i;
            }
        }
    }
    private static View getSharedView(ViewHolder holder) {
        Context context = holder.itemView.getContext();
        // HACK: Transition is so hard to work with, but this gives a better effect.
        View view = ViewUtils.hasSw600dp(context) ? holder.cardView : holder.broadcastLayout;
        ViewCompat.setTransitionName(view, context.getString(R.string.transition_name_broadcast));
        return view;
    }

    @Override
    public long getItemId(int position) {
         return getList().get(position).id;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(ViewUtils.inflate(R.layout.broadcast_item, parent));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final BaseNewsContent content = getItem(position);
        //转播的
//        holder.rebroadcastedByText.setText(content);

        //表示条目的点击事件
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //在activity处理点击
                mListener.onOpenBroadcast(content, getSharedView(holder));
            }
        });
        holder.broadcastLayout.bindzhihuNews(content);

    }

    //点击事件的监听
    public interface Listener{
        void onOpenBroadcast(BaseNewsContent content, View sharedView);
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        @Bind(R.id.rebroadcasted_by)
        public TextView rebroadcastedByText;
        @Bind(R.id.card)
        public CardView cardView; //就是一个容器
        @Bind(R.id.broadcast)
        public BroadcastLayout broadcastLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
