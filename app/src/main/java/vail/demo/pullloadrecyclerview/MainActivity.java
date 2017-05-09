package vail.demo.pullloadrecyclerview;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by VailWei on 2017/5/9/009.
 */

public class MainActivity extends FragmentActivity implements OnTouchUpListener{

    private PullLoadRecyclerViewLayout mLayout;
    private MyAdapter mAdapter;

    private View mHeaderView;
    private View mFooterView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLayout = (PullLoadRecyclerViewLayout) findViewById(R.id.pull_load_layout);

        init();
    }

    private void init() {
        mAdapter = new MyAdapter();
        LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
        mHeaderView = inflater.inflate(R.layout.co_refresh_header, null);
        mFooterView = inflater.inflate(R.layout.co_refresh_footer, null);
        mLayout.addHeaderView(mHeaderView, DisplayUtil.dpToPx(MainActivity.this, 60));
        mLayout.addFooterView(mFooterView, DisplayUtil.dpToPx(MainActivity.this, 40));
        mLayout.setMyRecyclerView(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false),
                mAdapter, true);
        mLayout.setItemDivider(new CustomDividerItemDecoration(MainActivity.this, LinearLayoutManager.HORIZONTAL,
                1, getResources().getColor(android.R.color.black)));
        mLayout.addOnTouchUpListener(this);
    }

    @Override
    public void OnRefreshing() {
        mLayout.setHeaderState(PullLoadRecyclerViewLayout.HEADER_STATE_REFRESHING);
        onDataRefreshing();
    }

    @Override
    public void OnLoading() {
        mLayout.setFooterState(PullLoadRecyclerViewLayout.FOOTER_STATE_LOADING);
        onDataLoadingMore();
    }

    private void onDataRefreshing() {
        //自己的逻辑
        mLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                onRefreshFinish(true);
            }
        }, 3000);
    }

    private void onDataLoadingMore() {
        //自己的逻辑
        mLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                onLoadMoreFinish(true);
            }
        }, 2000);
    }

    private void onRefreshFinish(final boolean success) {
        new Handler() {
            @Override
            public void handleMessage(Message message) {
                mLayout.setHeaderState(success ? PullLoadRecyclerViewLayout.HEADER_STATE_COMPLETE :
                PullLoadRecyclerViewLayout.HEADER_STATE_FAIL);
            }
        }.sendEmptyMessageDelayed(0, 100);
    }

    private void onLoadMoreFinish(final boolean success) {
        new Handler() {
            @Override
            public void handleMessage(Message message) {
                mLayout.setFooterState(success ? PullLoadRecyclerViewLayout.FOOTER_STATE_COMPLETE :
                        PullLoadRecyclerViewLayout.FOOTER_STATE_FAIL);
            }
        }.sendEmptyMessageDelayed(0, 100);
    }
}
