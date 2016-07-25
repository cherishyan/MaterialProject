package me.jinqiang.android.material.live.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.pili.pldroid.player.PLMediaPlayer;
import com.pili.pldroid.player.widget.PLVideoView;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.jinqiang.android.material.R;
import me.jinqiang.android.material.live.presenter.MediaController;

/**
 * @author  yanjinqiang on 2016/7/20.
 */
public class RoomActivity extends AppCompatActivity {
    @Bind(R.id.plview)
    PLVideoView mVideoView;
    private String path ;
    @Bind(R.id.toolbar)
    Toolbar mToobar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.live_item);
        ButterKnife.bind(this);
        setSupportActionBar(mToobar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        path = getIntent().getStringExtra("rtmp_path");
        String room_name = getIntent().getStringExtra("room_name");
        View loadingView = findViewById(R.id.progress);
        mVideoView.setBufferingIndicator(loadingView);
        mVideoView.setDisplayAspectRatio(PLVideoView.ASPECT_RATIO_PAVED_PARENT);
        mToobar.setTitle(room_name);
        startPlay();
    }
    public void startPlay(){
        mVideoView.setOnErrorListener(new PLMediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(PLMediaPlayer plMediaPlayer, int i) {
                Log.e("yjq","播放错误"+i);
                return false;
            }
        });
        MediaController mMediaController = new MediaController(this);
        mVideoView.setMediaController(mMediaController);
//        String path = "http://222.246.232.141/hdla.douyutv.com/live/17349r8fuIG4M8RD.flv?wsSecret=61823f29a56f69ff9f765ceb8ca6e8c9&wsTime=1468902792&wshc_tag=0&wsts_tag=578dad89&wsid_tag=ca696b32&wsiphost=ipdbm";
        mVideoView.setVideoPath(path);
        mVideoView.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mVideoView.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mVideoView.stopPlayback();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mVideoView.setVideoPath(path);
        mVideoView.start();
    }
}
