package com.lixm.animationdemo.customview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.lixm.animationdemo.R;
import com.tencent.rtmp.ITXLivePlayListener;
import com.tencent.rtmp.TXLiveBase;
import com.tencent.rtmp.TXLiveConstants;
import com.tencent.rtmp.TXLivePlayConfig;
import com.tencent.rtmp.TXLivePlayer;
import com.tencent.rtmp.ui.TXCloudVideoView;

import org.xutils.common.util.LogUtil;



/**
 * @author Lixm
 * @date 2017/9/6
 * @detail 财视学院视频播放顶部处理
 */

public class CollegePlayerView extends LinearLayout implements ITXLivePlayListener, View.OnClickListener, View.OnTouchListener {

    private String TAG="CollegePlayerView";
    private Context mContext;
    private TXCloudVideoView mTxPlayerView;
    private TXLivePlayer mLivePlayer;
    private TXLivePlayConfig mPlayConfig;
    private int mCurrentRenderMode;
    private int mCurrentRenderRotation;
    private LinearLayout mBottomControl;
    private ImageView mPlayerPause;
    private SeekBar mSeekBar;
    private TextView mPlayerTime;
    private RelativeLayout mMixScreen;
    private ImageView mMixImg;
//    private ReturnAndShareController mReturnController;
//    private TryWatchController mTryWatchController;
    private FrameLayout mProgressLayout;
    private LinearLayout mTryFinishLayout;
    private Button mTryBuyBtn;
    private TextView mTryBuyTitle;
    private FrameLayout mClose;

    private final int MSG_RESUME_PLAY = 0x101;
    private boolean bFirstPlay = true;
    private String videoUrl = "";
    private int mPlayType = TXLivePlayer.PLAY_TYPE_VOD_HLS;
    private int duration;
    private boolean mPause = false;
    private UIHandler mHandler;
    private boolean IsSupportProved = false;
    private int tryWatchTime = 0;
    private boolean isHindController = false;

    public CollegePlayerView(Context context) {
        super(context);
        mContext=context;
        initView();
    }

    public CollegePlayerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext=context;
        initView();
    }

    private void initView() {
        LayoutInflater.from(mContext).inflate(R.layout.college_player, this);
        mTxPlayerView = (TXCloudVideoView) findViewById(R.id.txcvv_player);
        mTxPlayerView.setOnTouchListener(this);
        mBottomControl = (LinearLayout) findViewById(R.id.ll_bottom_control);
        mPlayerPause = (ImageView) findViewById(R.id.pause);
        mPlayerPause.setOnClickListener(this);
        mSeekBar = (SeekBar) findViewById(R.id.media_controller_progress);
        mSeekBar.setOnSeekBarChangeListener(mSeekBarListener);
        mPlayerTime = (TextView) findViewById(R.id.time);
        mMixScreen = (RelativeLayout) findViewById(R.id.rl_expand_shrink);
        mMixScreen.setOnClickListener(this);
        mMixImg = (ImageView) findViewById(R.id.shrink);
//        mReturnController = findViewById(R.id.return_share_controller);
//        mTryWatchController = findViewById(R.id.TryWatchController);
        mProgressLayout = (FrameLayout) findViewById(R.id.progressbar);
        mTryFinishLayout = (LinearLayout) findViewById(R.id.ll_try_watch_finish);
        mTryBuyBtn = (Button) findViewById(R.id.btn_finish_buy);
        mTryBuyBtn.setOnClickListener(this);
        mTryBuyTitle = (TextView) findViewById(R.id.tv_video_title);
        mClose = (FrameLayout) findViewById(R.id.video_close_view);
        mClose.setOnClickListener(this);

        mLivePlayer = new TXLivePlayer(mContext);
        mCurrentRenderMode = TXLiveConstants.RENDER_MODE_ADJUST_RESOLUTION;
        mCurrentRenderRotation = TXLiveConstants.RENDER_ROTATION_PORTRAIT;
        mPlayConfig = new TXLivePlayConfig();
        mPlayConfig.setAutoAdjustCacheTime(true);
        mLivePlayer.setRenderMode(mCurrentRenderMode);
        mLivePlayer.setPlayerView(mTxPlayerView);
        mLivePlayer.setConfig(mPlayConfig);

        mHandler = new UIHandler();
        String sdkver = TXLiveBase.getSDKVersionStr();
        LogUtil.d("liteavsdk"+ "liteav sdk version is : " + sdkver);

    }

    public void setUrlStart(String videoUrl, boolean isSupportTryWatch, int tryWatchTime) {
        this.videoUrl = videoUrl;
//        mPlayType = ConstantMethodUtils.initPlayType(videoUrl, false);
        this.IsSupportProved = isSupportTryWatch;
        this.tryWatchTime = tryWatchTime;//isFree分钟
//        mTryWatchController.setTryWatchTime(tryWatchTime);
        playVideo(videoUrl);
    }

    private void playVideo(String url) {
        LogUtil.e("playVideoURL===========" + url);
        if (TextUtils.isEmpty(url)) {
            mProgressLayout.setVisibility(GONE);
//            showDialog("视频出错", "是否退出！");
            return;
        }
        mProgressLayout.setVisibility(VISIBLE);
        mTxPlayerView.onResume();
        LogUtil.e("========MSG_RESUME_PLAY=======onResume=======");
        mHandler.sendEmptyMessage(MSG_RESUME_PLAY);
    }

    public void onRestart() {
        if (!mPause ) {//如果之前是播放状态，界面回来后，继续播放
            mTxPlayerView.onResume();
            LogUtil.e("========MSG_RESUME_PLAY=======onResume=======");
            mHandler.sendEmptyMessage(MSG_RESUME_PLAY);
            mPlayerPause.setImageResource(R.mipmap.back);
        }
    }

    public void onPause() {
        mTxPlayerView.onPause();
        if (null != mLivePlayer) {
            mLivePlayer.pause();
            setBoolean(false);
        }
    }

    /**
     * 始终显示控制器
     */
    public void alwaysShowController() {
//        mHandler.removeMessages(MSG_HIDE_CONTROLLER);
        mBottomControl.setVisibility(View.VISIBLE);
//        mReturnController.setVisibility(VISIBLE);
        isHindController = false;
    }

    /**
     * 始终影藏控制器
     */
    public void alwaysHindController() {
//        mHandler.removeMessages(MSG_HIDE_CONTROLLER);
        mBottomControl.setVisibility(View.GONE);
//        mReturnController.setVisibility(GONE);
        isHindController = true;
    }

    /**
     * 显示分享按钮
     */
    public void showShareButton() {
        //不隐藏分享控制器的时候
//        mReturnController.showShareButton();
    }


    /**
     * TODO 影藏缩小视屏的icon
     */
    public void hindExpandShrink() {
        mMixScreen.setVisibility(GONE);
    }

    /**
     * 显示放大缩小视屏按钮
     */
    public void showExpandShrink() {
        mMixScreen.setVisibility(VISIBLE);
        //
    }

    /**
     * 暂停播放
     */
    public void pausePlay() {
        mPlayerPause.setImageResource(R.mipmap.list_icon_play);
        mLivePlayer.pause();
        setBoolean(false);
        LogUtil.e("============暂停中========");
        mProgressLayout.setVisibility(GONE);
    }

    /***
     * 继续播放
     */
    public void goOnPlay() {
        mPlayerPause.setImageResource(R.mipmap.back);
        mLivePlayer.resume();
        setBoolean(true);
        mProgressLayout.setVisibility(VISIBLE);
        LogUtil.e("============播放中========");
    }

    public int getPlayPosition() {
        return duration;
    }


    /**
     * 关闭视频
     */
    public void close() {
        mPlayerPause.setImageResource(R.mipmap.list_icon_play);
        stopHideTimer(true);
        mLivePlayer.pause();
        mTxPlayerView.setVisibility(GONE);
    }

    private void stopHideTimer(boolean isShowController) {
//        mHandler.removeMessages(MSG_HIDE_CONTROLLER);
//        mMediaController.clearAnimation();
        mBottomControl.setVisibility(isShowController ? View.VISIBLE : View.GONE);
    }

    /**
     * 设置试看完成后的视频标题
     */
    public void setVideoTitle(String title) {
        mTryBuyTitle.setText(title);
    }

    /**
     * 显示试看结束后的布局
     */
    public void showTryWatchFinish() {
        mTryFinishLayout.setVisibility(VISIBLE);
    }

    /**
     * 显示返回和分享按钮
     */
    public void showReturnAndShare() {
//        mReturnController.setVisibility(VISIBLE);
    }

    /**
     * 影藏视频控制栏
     */
    public void hindMediaController() {
        mBottomControl.setVisibility(GONE);
    }

    /**
     * 影藏试看控制栏
     */
    public void setHindTryWatchController() {
//        mTryWatchController.setVisibility(GONE);
    }

    @Override
    public void onClick(View view) {
//        if (TimeUtil.isFastDoubleClick())
//            return;
        switch (view.getId()) {
            case R.id.pause:
                mPause = !mPause;
                if (mPause) {
                    mPlayerPause.setImageResource(R.mipmap.list_icon_play);
                    mLivePlayer.pause();
                    setBoolean(false);
                    LogUtil.e("============暂停中========");
                    mProgressLayout.setVisibility(GONE);
                } else {
                    mPlayerPause.setImageResource(R.mipmap.back);
                    mLivePlayer.resume();
                    setBoolean(true);
                    mProgressLayout.setVisibility(VISIBLE);
                    LogUtil.e("============播放中========");
                }
                break;
            case R.id.rl_expand_shrink:
                mMixImg.setBackgroundResource(R.drawable.biz_video_bar_bg);
                mMixImg.setBackgroundResource(R.drawable.biz_video_bar_bg);
                break;
            case R.id.btn_finish_buy:
                Intent intent = new Intent();
//                intent.setAction(PARAM.BUY_COURSE);
                mContext.sendBroadcast(intent);
                break;
            case R.id.video_close_view:
                break;
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {

            if (mTxPlayerView.getVisibility() == VISIBLE &&
                    mTryFinishLayout.getVisibility() != VISIBLE) {

                if (!isHindController) {
                    //底部控制栏显示的时候
                    showOrHideController();
                } else {
                    LogUtil.e(TAG + "=目前控制栏是影藏的点击显示=");
                    if (IsSupportProved) {
//                        mTryWatchController.setVisibility(GONE);
                    }
                }

            }
        }

        if (mTxPlayerView.getVisibility() == VISIBLE &&
                mTryFinishLayout.getVisibility() != VISIBLE) {
            return true;
        } else {
            //如果试看结束的布局显示，那么不响应触摸事件
            return false;
        }
    }

    /**
     * 控制器的显示与隐藏
     */
    private void showOrHideController() {
        if (mBottomControl.getVisibility() == View.VISIBLE) {
            //如果控制器是显示的，就影藏
            LogUtil.e(TAG + "=如果控制器是显示的，就影藏=");
            mBottomControl.setVisibility(View.GONE);
//            mReturnController.setVisibility(GONE);
            if (IsSupportProved) {
//                mTryWatchController.setVisibility(VISIBLE);
            }
        } else {
            //如果控制器是影藏的，就显示
            LogUtil.e(TAG + "=如果控制器是影藏的，就显示=");
            mBottomControl.setVisibility(View.VISIBLE);
//            mReturnController.setVisibility(VISIBLE);
            if (IsSupportProved) {
//                mTryWatchController.setVisibility(GONE);
            }
        }
    }


    private class UIHandler extends Handler {

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
                            mLivePlayer.setPlayListener(CollegePlayerView.this);
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

    private void setBoolean(boolean enabled) {
//        mPlayerPortraitLayout.setKeepScreenOn(enabled);
    }

    private SeekBar.OnSeekBarChangeListener mSeekBarListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            LogUtil.w("=============seekBar==progress：" + progress);
//            mPlayerTime.setText(TimeUtil.secondToString(progress) + "/" + TimeUtil.secondToString(duration));
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
    public void onPlayEvent(int event, Bundle param) {
        LogUtil.e("onPlayEvent->event:" + event + "|" + param.getString(TXLiveConstants.EVT_DESCRIPTION));
        //错误还是要明确的报一下
        if (event < 0) {
//            FinancialToast.show(mContext, param.getString(TXLiveConstants.EVT_DESCRIPTION));
        }
        if (event == TXLiveConstants.PLAY_ERR_NET_DISCONNECT) {//-2301
//            showDialog("视频回放生成中", "是否退出！");
        } else if (TXLiveConstants.PLAY_EVT_PLAY_BEGIN == event) {//2004 开始播放
            if (true)
                mTxPlayerView.setBackgroundColor(getResources().getColor(R.color.black));
            else mTxPlayerView.setBackgroundColor(getResources().getColor(R.color.white));
            mProgressLayout.setVisibility(GONE);
            LogUtil.e("==========开始播放============");
        } else if (TXLiveConstants.PLAY_EVT_PLAY_LOADING == event) { //2007 加载中
            LogUtil.e("==========视频缓冲中============");
        } else if (TXLiveConstants.PLAY_EVT_PLAY_END == event) {// 2006 播放结束
            LogUtil.e("=======播放结束=========");
            mPause = true;
            mPlayerPause.setImageResource(R.mipmap.back);
            mLivePlayer.seek(1);
            mPlayerTime.setText("0:00" + "/" + "0");
            mLivePlayer.pause();
            mSeekBar.setProgress(0);
            setBoolean(false);
        } else if (TXLiveConstants.PLAY_EVT_PLAY_PROGRESS == event) {       // 2005 忽略process事件
            int progress = param.getInt(TXLiveConstants.EVT_PLAY_PROGRESS); //进度（秒数）
            duration = param.getInt(TXLiveConstants.EVT_PLAY_DURATION); //时间（秒数）
            // UI进度进行相应的调整
            mSeekBar.setProgress(progress);
//            mPlayerTime.setText(TimeUtil.secondToString(progress) + "/" + TimeUtil.secondToString(duration));
            mSeekBar.setMax(duration);
            return;
        } else if (event == TXLiveConstants.PLAY_EVT_START_VIDEO_DECODER) {//2008 启动硬解|启动软解
            String des = param.getString(TXLiveConstants.EVT_DESCRIPTION);
            if (des.contains("软解")) {
                mTxPlayerView.setBackgroundColor(getResources().getColor(R.color.white));
            } else {
                mTxPlayerView.setBackgroundColor(getResources().getColor(R.color.black));
            }
        }
    }

    @Override
    public void onNetStatus(Bundle bundle) {

    }

    public void onDestory() {
        if (mLivePlayer != null) {
            mLivePlayer.setPlayListener(null);
            mLivePlayer.stopPlay(false);
        }
        if (mTxPlayerView != null) {
            mTxPlayerView.onDestroy();
        }
        mSeekBar.setOnSeekBarChangeListener(null);
        LogUtil.e("===========onDestroy============");
    }
}
