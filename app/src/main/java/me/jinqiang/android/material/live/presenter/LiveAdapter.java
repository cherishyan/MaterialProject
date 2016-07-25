package me.jinqiang.android.material.live.presenter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.jinqiang.android.material.R;
import me.jinqiang.android.material.broadcast.ui.BroadcastLayout;
import me.jinqiang.android.material.live.Entity.DouyuEntity;
import me.jinqiang.android.material.network.zhihuInfo.BaseNewsContent;
import me.jinqiang.android.material.ui.SimpleAdapter;
import me.jinqiang.android.material.util.ImageUtils;
import me.jinqiang.android.material.util.ViewUtils;

/**
 * @author  by yanjinqiang on 2016/7/20.
 */
public class LiveAdapter extends SimpleAdapter<DouyuEntity.DataBean,LiveAdapter.ViewHolder> {

    private Listener mListener;
    private Context mContext;

    public LiveAdapter(List<DouyuEntity.DataBean> Contents, Listener listener,Context context){
        super(Contents);
        this.mListener = listener;
        this.mContext = context;
        setHasStableIds(true);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(ViewUtils.inflate(R.layout.hot_recyclerview_item, parent));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final DouyuEntity.DataBean item = getItem(position);
        //表示条目的点击事件
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //在activity处理点击
                //TODO  rtmp
//                mListener.onOpenBroadcast(item, getSharedView(holder));
                mListener.onOpenRoomActivity(item);

            }
        });
        ImageUtils.loadImage(holder.mImg,item.getRoom_src(),mContext);
        holder.mName.setText(item.getNickname());
        holder.mDesc.setText(item.getRoom_name());
        holder.mOnline.setText("在线:"+item.getOnline());

    }

    //点击事件的监听
    public interface Listener{
        void onOpenRoomActivity(DouyuEntity.DataBean dataBean);
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.name)
        TextView mName;
        @Bind(R.id.live_online)
        TextView mOnline;
        @Bind(R.id.description)
        TextView mDesc;
        @Bind(R.id.img)
        ImageView mImg;
        @Bind(R.id.live_cardview)
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
