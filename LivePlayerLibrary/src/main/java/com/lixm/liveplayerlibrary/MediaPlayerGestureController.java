package com.lixm.liveplayerlibrary;

import android.content.Context;
import android.media.AudioManager;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.SeekBar;

import com.tencent.rtmp.TXLivePlayer;

/**
 * @author Lixm
 * @date 2017/8/28
 * @detail
 */

public class MediaPlayerGestureController {
    public static interface GestureOperationHelper {
        void onSingleTap();
    }


    private static enum AdjustType {
        None,
        Volume,
        Brightness,
        FastBackwardOrForward,
    }


    private Context mContext;
    private View mPlayerRootView;
    private AdjustType mAdjustType = AdjustType.None;
    private AdjustPanel mAdjustPanel;
    private ProgressAdjustPanel mProgressAdjustPanel;
    private FrameLayout mAdjustPanelContainer;
    private FrameLayout mProgressAdjustPanelContainer;
    private TXLivePlayer mMediaPlayer;
    private SeekBar mProgressBar;
    private GestureDetector mGestureDetector;
    private GestureOperationHelper mOperateHelper;


    public MediaPlayerGestureController(
            Context context, View playerRootView, GestureOperationHelper helper) {

        mContext = context;
        mOperateHelper = helper;
        mPlayerRootView = playerRootView;
        initGestureDetector();
    }

    public void handleTouchEvent(MotionEvent event) {
        mGestureDetector.onTouchEvent(event);

        int action = event.getActionMasked();
        if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_CANCEL) {
            mAdjustType = AdjustType.None;
            mAdjustPanel.hidePanel();
            mProgressAdjustPanel.hidePanel();
        }
    }

    public void setMediaPlayer(TXLivePlayer mediaPlayer, SeekBar progressBar) {
        mMediaPlayer = mediaPlayer;
        mProgressBar = progressBar;
    }

    public void setAdjustPanelContainer(FrameLayout layout) {
        mAdjustPanelContainer = layout;

        mAdjustPanel = new AdjustPanel(mContext);
        mAdjustPanelContainer.addView(mAdjustPanel, new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        mAdjustPanel.setVisibility(View.GONE);
    }

    public void setProgressAdjustPanelContainer(FrameLayout layout) {
        mProgressAdjustPanelContainer = layout;

        mProgressAdjustPanel = new ProgressAdjustPanel(mContext);
        mProgressAdjustPanel.setVisibility(View.GONE);
        mProgressAdjustPanelContainer.addView(mProgressAdjustPanel, new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
    }

    private Runnable mSwitchTitleRunnable = new Runnable() {
        @Override
        public void run() {
            mOperateHelper.onSingleTap();
        }
    };

    private void initGestureDetector() {
        mGestureDetector = new GestureDetector(mContext,
                new GestureDetector.SimpleOnGestureListener() {
                    @Override
                    public boolean onSingleTapUp(MotionEvent e) {
//                        ThreadManager.getInstance().postOnUIHandlerDelayed(
//                                mSwitchTitleRunnable, 200);
                        return true;
                    }

                    @Override
                    public boolean onDoubleTap(MotionEvent e) {
//                        ThreadManager.getInstance().getUIHandler().removeCallbacks(
//                                mSwitchTitleRunnable);

                            if (mMediaPlayer.isPlaying()) {
                                mMediaPlayer.pause();
                            } else {
                                mMediaPlayer.resume();
                            }

                        return true;
                    }

                    @Override
                    public boolean onScroll(MotionEvent e1, MotionEvent e2,
                                            float distanceX, float distanceY) {

                        if (mAdjustType == AdjustType.None) {
                            if (Math.abs(distanceX) > Math.abs(distanceY)) {
                                mAdjustType = AdjustType.FastBackwardOrForward;
                            } else {
                                if (e1.getX() < mPlayerRootView.getMeasuredWidth() / 2) {
                                    mAdjustType = AdjustType.Brightness;
                                } else {
                                    mAdjustType = AdjustType.Volume;
                                }
                            }
                        }

                        return adjustInternal(e1, e2, distanceX, distanceY);
                    }
                });
    }

    private boolean adjustInternal(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        if (mAdjustType == AdjustType.FastBackwardOrForward) {
            // Adjust Progress.
            if (mMediaPlayer == null || !mMediaPlayer.isPlaying()) {
                return false;
            }

            float percent = distanceX / (float) mPlayerRootView.getMeasuredWidth();
            int total = mProgressBar.getMax();
            int currPosition = mProgressBar.getProgress();
            int seekOffset = (int) (total * percent * -1);

            currPosition += seekOffset;
            if (currPosition < 0) {
                currPosition = 0;
            } else if (currPosition > total) {
                currPosition = total;
            }

            mMediaPlayer.seek(currPosition);
            mProgressBar.setProgress(currPosition);

            if (seekOffset > 0) {
                mProgressAdjustPanel.adjustForward(currPosition, total);
            } else {
                mProgressAdjustPanel.adjustBackward(currPosition, total);
            }

        } else if (mAdjustType == AdjustType.Brightness) {
            // Adjust Brightness.
            float percent = distanceY / (float) mPlayerRootView.getMeasuredHeight();
            float brightness = DeviceUtils.getBrightnessPercent(mContext);
            float brightnessOffset = percent * 5;

            brightness += brightnessOffset;
            if (brightness < 0) {
                brightness = 0;
            } else if (brightness > 1) {
                brightness = 1;
            }

            DeviceUtils.setBrightness(mContext, brightness);
            mAdjustPanel.adjustBrightness(brightness);
        } else if (mAdjustType == AdjustType.Volume) {
            // Adjust Volume.
            float percent = distanceY / (float) mPlayerRootView.getMeasuredHeight();

            AudioManager manager = (AudioManager)
                    mContext.getSystemService(Context.AUDIO_SERVICE);
            int maxVolume = manager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
            int currVolume = manager.getStreamVolume(AudioManager.STREAM_MUSIC);
            float volumeOffsetAccurate = maxVolume * percent * 5;
            int volumeOffset = (int) volumeOffsetAccurate;

            if (volumeOffset == 0 && Math.abs(volumeOffsetAccurate) > 0.2f) {
                if (distanceY > 0) {
                    volumeOffset = 1;
                } else if (distanceY < 0) {
                    volumeOffset = -1;
                }
            }

            currVolume += volumeOffset;
            if (currVolume < 0) {
                currVolume = 0;
            } else if (currVolume >= maxVolume) {
                currVolume = maxVolume;
            }

            manager.setStreamVolume(AudioManager.STREAM_MUSIC, currVolume, 0);

            float volumePercent = (float) currVolume / (float) maxVolume;
            mAdjustPanel.adjustVolume(volumePercent);
        }

        return true;
    }
}
