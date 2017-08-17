package com.lixm.animationdemo.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lixm.animationdemo.R;
import com.lixm.animationdemo.adapter.GridAdapter;
import com.lixm.animationdemo.bean.MeiZi;
import com.lixm.animationdemo.utils.SnackbarUtil;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CollegeActivity extends BaseActivity {

    private Context mContext;
    private RecyclerView recyclerView;
    private CoordinatorLayout coordinatorLayout;
    private GridLayoutManager mLayoutManager;
    private SwipeRefreshLayout swipeRefreshLayout;
    private int page = 1;
    private ArrayList<MeiZi> meiZis;
    private GridAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college);
        mContext = this;
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        initView();//初始化布局
        setListener();//设置监听事件

    }

    private int lastVisibleItem = 0;

    private void initView() {
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.grid_coordinatorLayout);
        recyclerView = (RecyclerView) findViewById(R.id.grid_recycler);
        mLayoutManager = new GridLayoutManager(mContext, 3, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.grid_swipe_refresh);
        //调整SwipeRefreshLayout的位置
        swipeRefreshLayout.setProgressViewOffset(false, 0, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources().getDisplayMetrics()));
        //recyclerview滚动监听
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //0:当前屏幕停止滚动 ； 1时：屏幕在滚动 且 用户仍在碰触或者手指还在屏幕上； 2：随用户的操作，屏幕上产生的惯性滑动；
                //滑动状态停止并且剩余少于两个item是，自动加载下一页
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 2 >= mLayoutManager.getItemCount()) {
                    ++page;
                    getData();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //获取加载的最后一个可见视图在适配器的位置
                lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
            }
        });
        getData();
    }

    private void setListener() {
        //swipeRefreshLayout刷新监听
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                getData();
            }
        });
    }

    private void getData() {
        String url = "http://gank.io/api/data/福利/10/"+page;
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        Call call = okHttpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    String result = response.body().string();
                    if (!TextUtils.isEmpty(result)) {
                        JSONObject jsonObject;

                        String jsonData = null;
                        jsonObject = new JSONObject(result);
                        jsonData = jsonObject.optString("results");
                        Message message = Message.obtain();
                        message.what = 0;
                        message.obj = jsonData;
                        handler.sendMessage(message);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    dealMessage((String) msg.obj);
                    break;
            }
        }
    };

    private void dealMessage(String jsonData) {
        Gson gson = new Gson();
        if (meiZis == null || meiZis.size() == 0) {
            meiZis = gson.fromJson(jsonData, new TypeToken<ArrayList<MeiZi>>() {
            }.getType());
            MeiZi pages = new MeiZi();
            pages.setPage(page);
            meiZis.add(pages);//在数据链表中加入一个用于显示页数的item
        } else {
            ArrayList<MeiZi> more = gson.fromJson(jsonData, new TypeToken<ArrayList<MeiZi>>() {
            }.getType());
            meiZis.addAll(more);
            MeiZi pages = new MeiZi();
            pages.setPage(page);
            meiZis.add(pages);////在数据链表中加入一个用于显示页数的item
        }

        if (mAdapter == null) {
            recyclerView.setAdapter(mAdapter = new GridAdapter(mContext, meiZis));
            mAdapter.setOnItemClickListener(new GridAdapter.OnRecyclerViewItemClickListener() {
                @Override
                public void onItemClick(View view) {
                    int position = recyclerView.getChildAdapterPosition(view);
                    SnackbarUtil.ShortSnackbar(coordinatorLayout, "点击了第" + (position +1)+ "个", SnackbarUtil.Info).show();
                }

                @Override
                public void onItemLongClick(View view) {

                }
            });
        } else {
            //让适配器刷新数据
            mAdapter.notifyDataSetChanged();
        }
    }
}
