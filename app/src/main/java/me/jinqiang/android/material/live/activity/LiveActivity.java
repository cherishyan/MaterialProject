package me.jinqiang.android.material.live.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.jinqiang.android.material.R;
import me.jinqiang.android.material.account.util.AccountUtils;
import me.jinqiang.android.material.home.HomeFragment;
import me.jinqiang.android.material.live.LiveHomeFragment;
import me.jinqiang.android.material.main.ui.MainActivity;
import me.jinqiang.android.material.notification.ui.NotificationListFragment;
import me.jinqiang.android.material.util.TransitionUtils;

/**
 * @author  yanjinqiang on 2016/7/5.
 */
public class LiveActivity extends AppCompatActivity implements NotificationListFragment.UnreadNotificationCountListener{
    @Bind(R.id.drawer)
    DrawerLayout mDrawerLayout;
    @Bind(R.id.navigation)
    NavigationView mNavigationView;
    private LinearLayout mNavigationHeaderLayout;
    private ImageView mNavigationHeaderAvatarImage;
    private TextView mNavigationHeaderNameText;
    @Bind(R.id.notification_list_drawer)
    View mNotificationDrawer;
    @Bind(R.id.container)
    FrameLayout mContainerLayout;

    private MenuItem mNotificationMenu;
    private int mUnreadNotificationCount;

    public static Intent makeIntent(Context context) {
        return new Intent(context, LiveActivity.class);
    }

    private NotificationListFragment mNotificationListFragment;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        TransitionUtils.setupTransitionBeforeDecorate(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        TransitionUtils.setupTransitionAfterSetContentView(this);
        ButterKnife.bind(this);

        mNavigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.navigation_home:
//                                onShowSettings();
                                startActivity(MainActivity.makeIntent(LiveActivity.this));
                                finish();
                                break;
                            case R.id.navigation_live: //live tv

                                break;
                        }
                        // TODO
                        if (menuItem.getGroupId() == R.id.navigation_group_primary) {
                            menuItem.setChecked(true);
                        }
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });
        mNavigationHeaderLayout = (LinearLayout) mNavigationView.getHeaderView(0);
        mNavigationHeaderAvatarImage = ButterKnife.findById(mNavigationHeaderLayout, R.id.avatar);
        mNavigationHeaderNameText = ButterKnife.findById(mNavigationHeaderLayout, R.id.name);
        mNavigationHeaderNameText.setText(AccountUtils.getUserName(this));
        // FIXME: Check remembered checked position.
        mNavigationView.getMenu().getItem(1).setChecked(true);

        mNotificationListFragment = (NotificationListFragment) getSupportFragmentManager()
                .findFragmentById(R.id.notification_list_fragment);
        mNotificationListFragment.setUnreadNotificationCountListener(this);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, LiveHomeFragment.newInstance())
                    .commit();
        }
    }

    @Override
    public void onUnreadNotificationUpdate(int count) {

    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(mNavigationView)) {
            mDrawerLayout.closeDrawer(mNavigationView);
        } else if (mDrawerLayout.isDrawerOpen(mNotificationDrawer)) {
            mDrawerLayout.closeDrawer(mNotificationDrawer);
        } else {
            super.onBackPressed();
        }
    }
    public void setToolbar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TransitionUtils.setupTransitionForAppBar(this);
    }

    public void refreshNotificationList() {
        mNotificationListFragment.refresh();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(mNavigationView);
                return true;
            case R.id.action_notification:
                mNotificationListFragment.refresh();
                mDrawerLayout.openDrawer(mNotificationDrawer);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
