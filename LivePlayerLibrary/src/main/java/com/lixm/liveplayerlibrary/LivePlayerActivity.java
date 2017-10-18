package com.lixm.liveplayerlibrary;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.drawable.AnimationDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.tencent.rtmp.ITXLivePlayListener;
import com.tencent.rtmp.TXLiveConstants;
import com.tencent.rtmp.TXLivePlayConfig;
import com.tencent.rtmp.TXLivePlayer;
import com.tencent.rtmp.ui.TXCloudVideoView;

import java.io.UnsupportedEncodingException;

import static com.lixm.liveplayerlibrary.RoundView.TYPE_CIRCLE;


/**
 * @author LXM
 * @date 2016-09-26
 * @detail 视频回放界面
 */
public class LivePlayerActivity extends Activity implements ITXLivePlayListener, View.OnClickListener {

    private Context mContext;
    private RelativeLayout mPlayerPortraitLayout;
    private RoundView mHead;
    private TextView mName;
    private SeekBar mSeekBar;
    private TextView mPlayerTime;
    private ImageView mlivePause;
    private TextView mVoiceBtn;
    private TextView mBackeBtn;
    private TXCloudVideoView txvvPlayerView;
    private TextView mMaxTxt;
    private ImageView mLoadingView;
    private LinearLayout mHeadRootLayout;
    private RelativeLayout mPlayerBottom;
    private FrameLayout mPro;
    private FrameLayout mVol;
    private RelativeLayout.LayoutParams params;
    private RotateAnimation mLeftRotation;
    private RotateAnimation mRightRotation;
    private int mPlayType = TXLivePlayer.PLAY_TYPE_VOD_HLS;
    private TXLivePlayer mLivePlayer;
    private boolean bFirstPlay = true;
    private int duration = 0;

    private Handler mHandler;

    private final int MSG_RESUME_PLAY = 0x101;
    private boolean mPause = false;
    private String videoUrl;
    private boolean isSilent = false;//是否是静音模式，默认是false

    private String targetUrl = "";//分享的Url

    private TXLivePlayConfig mPlayConfig;
    private int mCurrentRenderMode;
    private int mCurrentRenderRotation;

    private boolean isClean = false;//是否是纯净模式
    private Animation mTopUpAni;//向上滑出
    private Animation mTopDownAni;//顶部布局向下滑入
    private Animation mBottomDownAni;//向下画出
    private Animation mBottomUpAni;//底部布局向上滑入

    private MediaPlayerGestureController controller;
    private MediaPlayerGestureController.GestureOperationHelper helper;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//继承自AppCompatActivity的话，会失效
        setContentView(R.layout.activity_live_player);
        initView();
        mContext = this;
        params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mLeftRotation = new RotateAnimation(0f, 90f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        mLeftRotation.setDuration(200);
        mLeftRotation.setFillAfter(true);
        mLivePlayer = new TXLivePlayer(this);
        mCurrentRenderMode = TXLiveConstants.RENDER_MODE_ADJUST_RESOLUTION;
        mCurrentRenderRotation = TXLiveConstants.RENDER_ROTATION_PORTRAIT;
        mPlayConfig = new TXLivePlayConfig();
        mPlayConfig.setAutoAdjustCacheTime(true);
        mLivePlayer.setRenderMode(mCurrentRenderMode);
        mLivePlayer.setPlayerView(txvvPlayerView);
        mLivePlayer.setConfig(mPlayConfig);

        mName.setText("杨豆腐");

        mHandler = new UIHandler(this);
        mPlayerPortraitLayout.setKeepScreenOn(true);

        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(mBroadcastReceiver, intentFilter);

//        videoUrl = "http://1252336041.vod2.myqcloud.com/dea5e0favodgzp1252336041/75cf1a8c9031868222953439193/playlist.f3.m3u8";
//        videoUrl = "http://1252336041.vod2.myqcloud.com/dea5e0favodgzp1252336041/b33fc8799031868222957863768/playlist.m3u8";
//        videoUrl = "http://1252336041.vod2.myqcloud.com/dea5e0favodgzp1252336041/6ca2a7e39031868222892770040/playlist.m3u8";
//        videoUrl = "http://1252336041.vod2.myqcloud.com/dea5e0favodgzp1252336041/8745cd9c9031868222943341042/playlist.m3u8";
//        videoUrl = "http://1252336041.vod2.myqcloud.com/dea5e0favodgzp1252336041/d99b3b8a9031868222968031980/playlist.m3u8";
        videoUrl = "https://cv2.jikexueyuan.com/android/course_custom_view/01/video/c63a_02_h264_sd_960_540.mp4?e=1507602899&amp;token=kvniiGWb17-XSxTMHiIVkclXvpopl7nuzl4ANRHA:d72e8D0kdgg-fEmLSZF4-VH5z_U=";
        playVideo(videoUrl);


        mTopUpAni = AnimationUtils.loadAnimation(mContext, R.anim.player_top_up);
        mBottomDownAni = AnimationUtils.loadAnimation(mContext, R.anim.player_bottom_down);
        mTopDownAni = AnimationUtils.loadAnimation(mContext, R.anim.player_top_down);
        mBottomUpAni = AnimationUtils.loadAnimation(mContext, R.anim.player_bottom_up);

        mSeekBar.setOnSeekBarChangeListener(mSeekBarListener);
        mlivePause.setOnClickListener(this);
        mBackeBtn.setOnClickListener(this);
        mVoiceBtn.setOnClickListener(this);
        mMaxTxt.setOnClickListener(this);

        helper = new MediaPlayerGestureController.GestureOperationHelper() {
            @Override
            public void onSingleTap() {
                LogUtil.w("=====onSingleTap=====");
            }
        };
        controller = new MediaPlayerGestureController(this, mPlayerPortraitLayout, helper);
        controller.setMediaPlayer(mLivePlayer, mSeekBar);
        controller.setAdjustPanelContainer(mVol);
        controller.setProgressAdjustPanelContainer(mPro);

    }

    private void initView() {
        mPlayerPortraitLayout = findViewById(R.id.live_player_portrait);
        mHead = findViewById(R.id.head_icon);
        mHead.setType(TYPE_CIRCLE);
        mName = findViewById(R.id.host_name);
        mSeekBar = findViewById(R.id.palyer_seekbar);
        mPlayerTime = findViewById(R.id.player_time);
        mlivePause = findViewById(R.id.livePause);
        mVoiceBtn = findViewById(R.id.voice_btn);
        mBackeBtn = findViewById(R.id.btn_back);
        txvvPlayerView = findViewById(R.id.txcvv_player);
        mVoiceBtn = findViewById(R.id.voice_btn);
        mMaxTxt = findViewById(R.id.live_max_btn);
        mLoadingView = findViewById(R.id.loadingImageView);
        mHeadRootLayout = findViewById(R.id.head_layout);
        mPlayerBottom = findViewById(R.id.bottom_layout);
        mPro = findViewById(R.id.pro);
        mVol = findViewById(R.id.vol);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        controller.handleTouchEvent(event);
        return super.onTouchEvent(event);
    }

    private void setBoolean(boolean enabled) {
        mPlayerPortraitLayout.setKeepScreenOn(enabled);
    }

    @Override
    public void onClick(View v) {
        if (v == mPlayerPortraitLayout) {
            if (!isClean) {
                mHeadRootLayout.clearAnimation();//向上隐藏
                mHeadRootLayout.setAnimation(mTopUpAni);
                mHeadRootLayout.startAnimation(mTopUpAni);
                mPlayerBottom.clearAnimation();//向下隐藏
                mPlayerBottom.setAnimation(mBottomDownAni);
                mPlayerBottom.startAnimation(mBottomDownAni);
                isClean = true;
            } else {
                mHeadRootLayout.clearAnimation();//向上隐藏
                mHeadRootLayout.setAnimation(mTopDownAni);
                mHeadRootLayout.startAnimation(mTopDownAni);
                mPlayerBottom.clearAnimation();//向下隐藏
                mPlayerBottom.setAnimation(mBottomUpAni);
                mPlayerBottom.startAnimation(mBottomUpAni);
                isClean = false;
            }
        } else if (v == mBackeBtn) {
            onBackPressed();
        } else if (v == mlivePause) {
            mPause = !mPause;
            if (mPause) {
                mlivePause.setImageResource(R.drawable.live_play);
                mLivePlayer.pause();
                setBoolean(false);
            } else {
                mlivePause.setImageResource(R.drawable.live_pause);
                mLivePlayer.resume();
                setBoolean(true);
//                    startLoadingAnimation();
            }
        } else if (v == mVoiceBtn) {//显示静音
            setMute();
        } else if (v == mMaxTxt) {
            if (mLivePlayer == null) {
                return;
            }

            if (mCurrentRenderRotation == TXLiveConstants.RENDER_ROTATION_PORTRAIT) {
                mMaxTxt.setBackgroundResource(R.drawable.player_vertical_screen);
                mCurrentRenderRotation = TXLiveConstants.RENDER_ROTATION_LANDSCAPE;
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

            } else if (mCurrentRenderRotation == TXLiveConstants.RENDER_ROTATION_LANDSCAPE) {
                mMaxTxt.setBackgroundResource(R.drawable.player_horizontal_screen);
                mCurrentRenderRotation = TXLiveConstants.RENDER_ROTATION_PORTRAIT;
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            }

        }
    }


    private void dealFileId(String videoUrl) {
        String Url = videoUrl;
        try {
            Url = new String(videoUrl.getBytes("gbk"), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        targetUrl = targetUrl + "&fid=" + Url;
    }
    /**
     * 设置是否静音
     */

    private void setMute() {
        isSilent = !isSilent;
        if (isSilent) {
            mVoiceBtn.setBackgroundResource(R.drawable.live_loud_mute);
            mLivePlayer.setMute(true);
        } else {
            mVoiceBtn.setBackgroundResource(R.drawable.live_loud_normal);
            mLivePlayer.setMute(false);
        }

    }

    /**
     * 开始播放
     *
     * @param url
     */
    private void playVideo(String url) {
        if (TextUtils.isEmpty(url)) {
            stopLoadingAnimation();
            Toast.makeText(this, "视频出错", Toast.LENGTH_SHORT).show();
            return;
        }
        dealFileId(url);
        startLoadingAnimation();
        txvvPlayerView.onResume();
        LogUtil.e("========MSG_RESUME_PLAY=======onResume=======");
        mHandler.sendEmptyMessage(MSG_RESUME_PLAY);
    }

    @Override
    protected void onPause() {
        super.onPause();
        txvvPlayerView.onPause();
        if (null != mLivePlayer) {
            mLivePlayer.pause();
            setBoolean(false);
            LogUtil.e("========暂停播放======");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!mPause) {//如果之前是播放状态，界面回来后，继续播放
            txvvPlayerView.onResume();
            LogUtil.e("========MSG_RESUME_PLAY=======onResume=======");
            mHandler.sendEmptyMessage(MSG_RESUME_PLAY);
            mlivePause.setImageResource(R.drawable.live_pause);
        }
    }

    @Override
    public int getChangingConfigurations() {
        return super.getChangingConfigurations();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        // 当新设置中，屏幕布局模式为横排时
        if (newConfig.orientation == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            LogUtil.e("------------竖屏");
        } else {
            LogUtil.e("==============横屏");
        }
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onPlayEvent(int event, Bundle param) {
        LogUtil.e("onPlayEvent->event:" + event + "|" + param.getString(TXLiveConstants.EVT_DESCRIPTION));
        //错误还是要明确的报一下
        if (event < 0) {
            Toast.makeText(this, param.getString(TXLiveConstants.EVT_DESCRIPTION), Toast.LENGTH_SHORT).show();
        }
        if (event == TXLiveConstants.PLAY_ERR_NET_DISCONNECT) {//-2301
            Toast.makeText(mContext, "视频回放未生成", Toast.LENGTH_SHORT).show();
        } else if (TXLiveConstants.PLAY_EVT_PLAY_BEGIN == event) {//2004 开始播放
            txvvPlayerView.setBackgroundColor(getResources().getColor(R.color.black));
            stopLoadingAnimation();
            LogUtil.e("==========开始播放============");
        } else if (TXLiveConstants.PLAY_EVT_PLAY_LOADING == event) { //2007 加载中
            LogUtil.e("==========视频缓冲中============");
        } else if (TXLiveConstants.PLAY_EVT_PLAY_END == event) {// 2006 播放结束
            LogUtil.e("=======播放结束=========");
            mPause = true;
            mlivePause.setImageResource(R.drawable.live_play);
            mLivePlayer.seek(1);
            mPlayerTime.setText("0:00" + "/" + TimeUtil.secondToString(duration));
            mLivePlayer.pause();
            mSeekBar.setProgress(0);
            setBoolean(false);
        } else if (TXLiveConstants.PLAY_EVT_PLAY_PROGRESS == event) {       // 2005 忽略process事件
            int progress = param.getInt(TXLiveConstants.EVT_PLAY_PROGRESS); //进度（秒数）
            duration = param.getInt(TXLiveConstants.EVT_PLAY_DURATION); //时间（秒数）
            // UI进度进行相应的调整
            mSeekBar.setProgress(progress);
            mPlayerTime.setText(TimeUtil.secondToString(progress) + "/" + TimeUtil.secondToString(duration));
            mSeekBar.setMax(duration);
            return;
        } else if (event == TXLiveConstants.PLAY_EVT_START_VIDEO_DECODER) {//2008 启动硬解|启动软解
            String des = param.getString(TXLiveConstants.EVT_DESCRIPTION);
            if (des.contains("软解")) {
                txvvPlayerView.setBackgroundColor(getResources().getColor(R.color.white));
            } else {
                txvvPlayerView.setBackgroundColor(getResources().getColor(R.color.black));
            }
        }
    }

    @Override
    public void onNetStatus(Bundle bundle) {

    }


    private SeekBar.OnSeekBarChangeListener mSeekBarListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            LogUtil.w("=============seekBar==progress：" + progress);
            mPlayerTime.setText(TimeUtil.secondToString(progress) + "/" + TimeUtil.secondToString(duration));
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            LogUtil.e("===========停止拖动======" + seekBar.getProgress());
            mLivePlayer.seek(seekBar.getProgress());
        }
    };

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    private class UIHandler extends Handler {

        LivePlayerActivity mActivtiy;

        public UIHandler(LivePlayerActivity activty) {
            mActivtiy = activty;
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_RESUME_PLAY:
                    LogUtil.e("=============" + (null != mLivePlayer));
                    if (null != mLivePlayer) {
                        if (!bFirstPlay) {
                            mLivePlayer.resume();
                            setBoolean(true);
                        } else {
                            mLivePlayer.setPlayListener(LivePlayerActivity.this);
                            mLivePlayer.stopPlay(false);
                            mLivePlayer.enableHardwareDecode(true);
                            mLivePlayer.startPlay(videoUrl, mPlayType);
                            LogUtil.e("==========开始播放视频==============" + View.VISIBLE + "====" + View.GONE + "===" + View.INVISIBLE);
                            bFirstPlay = false;
                        }
                    }
                    break;
            }
        }
    }

    @Override
    protected void onDestroy() {
        isSilent = true;
        setMute();
        if (mLivePlayer != null) {
            mLivePlayer.setPlayListener(null);
            mLivePlayer.stopPlay(false);
        }
        if (txvvPlayerView != null) {
            txvvPlayerView.onDestroy();
        }
        mSeekBar.setOnSeekBarChangeListener(null);
        //        stopPlayVideo();
        LogUtil.e("===========onDestroy============");
        try {
            if (mBroadcastReceiver != null)
                unregisterReceiver(mBroadcastReceiver);
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }


    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {//网络监听
                NetworkInfo.State wifiState = null;
                NetworkInfo.State mobileState = null;
                ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                wifiState = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
                mobileState = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
                if (wifiState != null && mobileState != null
                        && NetworkInfo.State.CONNECTED != wifiState
                        && NetworkInfo.State.CONNECTED == mobileState) {
                    // 手机网络连接成功
                } else if (wifiState != null && mobileState != null
                        && NetworkInfo.State.CONNECTED != wifiState
                        && NetworkInfo.State.CONNECTED != mobileState) {
                    // 手机没有任何的网络
                    Toast.makeText(mContext, "网络中断", Toast.LENGTH_SHORT).show();
                } else if (wifiState != null && NetworkInfo.State.CONNECTED == wifiState) {
                    // 无线网络连接成功
                    //                    FinancialToast.show(mContext, "已切换到无线网络！");
                } else if (NetworkInfo.State.CONNECTED == mobileState) {
                    //手机2G/3G/4G
                    Toast.makeText(mContext, "当前网络为移动网络", Toast.LENGTH_SHORT).show();
                }
            }
        }
    };


    private void startLoadingAnimation() {
        if (mLoadingView != null) {
            mLoadingView.setVisibility(View.VISIBLE);
            ((AnimationDrawable) mLoadingView.getDrawable()).start();
        }
    }

    private void stopLoadingAnimation() {
        if (mLoadingView != null) {
            mLoadingView.setVisibility(View.GONE);
            ((AnimationDrawable) mLoadingView.getDrawable()).stop();
        }
    }
}
