package me.jinqiang.android.material.zhihu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.jinqiang.android.material.util.IoUtils;
import me.jinqiang.android.material.R;
import me.jinqiang.android.material.network.zhihuInfo.oneBean;
import me.jinqiang.android.material.util.ImageUtils;

/**
 * Created by yanjinqiang on 2016/5/23.
 */
public class oneFragment  extends Fragment {
    private static String TAG = oneFragment.class.getName();
    @Bind(R.id.imagelayout_image)
    ImageView imageView;
    @Bind(R.id.number)
    TextView txtNumber;
    @Bind(R.id.description)
    TextView txtDsc;

    private oneBean mOneBean;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.one_activity,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //注解
        ButterKnife.bind(this,view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getDataFromAsset();
        ImageUtils.loadImage(imageView,mOneBean.getImgUrl(),getContext());
        txtNumber.setText("VOL. "+mOneBean.getIndex());
        String decode = IoUtils.decodeUnicodeToUtf(mOneBean.getContent());
        txtDsc.setText(decode);

        Log.d(TAG, "onCreate11: "+mOneBean.getContent()+"after:"+decode);

    }
    public void getDataFromAsset(){
        try {
            InputStream open = getResources().getAssets().open("one_data.txt");
            int size = open.available();
            byte[] buffer = new byte[size];
            open.read(buffer);
            open.close();
            String text = new String(buffer, "UTF-8");
            Gson gson = new Gson();
            mOneBean = gson.fromJson(text, oneBean.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
