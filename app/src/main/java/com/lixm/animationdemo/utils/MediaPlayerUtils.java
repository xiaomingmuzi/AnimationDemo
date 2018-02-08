package com.lixm.animationdemo.utils;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.widget.ImageView;

import com.lixm.animationdemo.R;

import org.xutils.common.util.LogUtil;

import java.io.File;
import java.io.FileInputStream;

/**
 * Created by lenovo on 2016/5/5.
 * 语音播放类
 */
public class MediaPlayerUtils {

    private static MediaPlayerUtils mediaPlayerUtils;
    private MediaPlayer mediaPlayer;
    private boolean flag=true;
    private String media_path;
    private ImageView media_imageView;
    private int drawable_ID= R.drawable.lcs_voice_receive;
    private int mipmap_ID=R.mipmap.lcs_voice_receive;
    public static String isOverNum = null;

    public static MediaPlayerUtils getInstense(){
        if (mediaPlayerUtils==null){
            mediaPlayerUtils=new MediaPlayerUtils();
        }
        return mediaPlayerUtils;
    }

    public void setMedia_ImageType(int i){
        switch (i){
            case 0:
                drawable_ID=R.drawable.lcs_voice_receive;
                mipmap_ID=R.mipmap.lcs_voice_receive;
                break;
            case 1:
                drawable_ID=R.drawable.voice_receive;
                mipmap_ID=R.mipmap.xinhao_3;
                break;
        }
    }

    public void setPlayorStop(String path, ImageView imageView){
        if (setTFVoice(path)) {
            media_path=path;
            media_imageView=imageView;
            AnimationDrawable animation = null;
            try {
                animation = (AnimationDrawable) media_imageView.getDrawable();
                if (animation != null) {
                    if (animation.isRunning()) {
                        flag = false;
                        return;
                    } else {
                        flag = true;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (flag) {
                    MediaPlayerUtils.getInstense().isOverNum = path;
                    flag = false;
                    mediaPlayer = new MediaPlayer();
                    //防止小米手机可能出现的 mediaplayer 卡顿 bug
                    mediaPlayer.setDisplay(null);
                    mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            LogUtil.w("=============onPrepared=======");
                            if (mp!=null) {
                                mp.start();
                                media_imageView.setImageResource(drawable_ID);
                                AnimationDrawable animationDrawable= (AnimationDrawable) media_imageView.getDrawable();
                                animationDrawable.start();
                            }
                        }
                    });
                    mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                        @Override
                        public boolean onError(MediaPlayer mp, int what, int extra) {
                            LogUtil.e("======onError："+what+"  extra："+extra);
                            return false;
                        }
                    });
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            if(flag){
                                isOverNum = null;
                            }
                            flag = true;
                            if (mp.isPlaying()) {
                                mp.release();// 释放资源
                            }
                            try {
                                AnimationDrawable animation = (AnimationDrawable) media_imageView.getDrawable();
                                if (animation != null && animation.isRunning()) {
                                    animation.stop();
                                }
                                media_imageView.setImageResource(mipmap_ID);
                            } catch (Exception e) {
                                e.toString();
                            }
                        }
                    });
                    if (path.indexOf("http") == -1 && path.indexOf("file") == -1) {
                        File file = new File(path);
                        FileInputStream fis = new FileInputStream(file);
                        mediaPlayer.setDataSource(fis.getFD());
//                        mediaPlayer.setDataSource(FileHelper.getRealFilePath(bean.getVoice_Url()));
                    } else {
                        mediaPlayer.setDataSource(path);
                    }
                    mediaPlayer.prepareAsync();// 缓冲,异步操作
                }

            } catch (Exception e) {
                flag = true;
                e.printStackTrace();
//                if (NetWorkHelper.isNetActive()) {
//                    FinancialToast.show(UIUtils.getContext(), "语音异常，加载失败");
//                } else {
//                    FinancialToast.show(UIUtils.getContext(), "网络异常，请稍后再试");
//                }
            }
        }
    }

    public void setPlayorStop(String path, ImageView imageView,String userid,String dynamicId,Context context){
        if (setTFVoice(path)) {

            media_path=path;
            media_imageView=imageView;
            AnimationDrawable animation = null;
            try {
                animation = (AnimationDrawable) media_imageView.getDrawable();
                if (animation != null) {
                    if (animation.isRunning()) {
                        flag = false;
                        return;
                    } else {
                        flag = true;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (flag) {
                    MediaPlayerUtils.getInstense().isOverNum = path;
                    flag = false;
                    mediaPlayer = new MediaPlayer();
                    //防止小米手机可能出现的 mediaplayer 卡顿 bug
                    mediaPlayer.setDisplay(null);
                    mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            if (mp!=null) {
                                mp.start();
                                media_imageView.setImageResource(drawable_ID);
                                AnimationDrawable animationDrawable= (AnimationDrawable) media_imageView.getDrawable();
                                animationDrawable.start();
                            }
                        }
                    });
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            if(flag){
                                isOverNum = null;
                            }
                            flag = true;
                            if (mp.isPlaying()) {
                                mp.release();// 释放资源
                            }
                            try {
                                AnimationDrawable animation = (AnimationDrawable) media_imageView.getDrawable();
                                if (animation != null && animation.isRunning()) {
                                    animation.stop();
                                }
                                media_imageView.setImageResource(mipmap_ID);
                            } catch (Exception e) {
                                e.toString();
                            }
                        }
                    });
                    if (path.indexOf("http") == -1 && path.indexOf("file") == -1) {
                        File file = new File(path);
                        FileInputStream fis = new FileInputStream(file);
                        mediaPlayer.setDataSource(fis.getFD());
//                        mediaPlayer.setDataSource(FileHelper.getRealFilePath(bean.getVoice_Url()));
                    } else {
                        mediaPlayer.setDataSource(path);
                    }
                    mediaPlayer.prepareAsync();// 缓冲,异步操作
                }

            } catch (Exception e) {
                flag = true;
                e.printStackTrace();
//                if (NetWorkHelper.isNetActive()) {
//                    FinancialToast.show(UIUtils.getContext(), "语音异常，加载失败");
//                } else {
//                    FinancialToast.show(UIUtils.getContext(), "网络异常，请稍后再试");
//                }
            }
        }
    }

    //继续播放动画
    public  void  keepUpPlay(){
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            try {
                media_imageView.setImageResource(drawable_ID);
                AnimationDrawable animation = (AnimationDrawable) media_imageView.getDrawable();
                if(!animation.isRunning()){
                    animation.start();
                }
            }catch (Exception e){
                e.toString();
            }
        }
    }

    public boolean setVoice_Stop(){
        if (mediaPlayer == null){
            return true;
        }else if (mediaPlayer != null&&mediaPlayer.isPlaying()){
            flag=true;
            mediaPlayer.stop();
            try {
                AnimationDrawable animation = (AnimationDrawable) media_imageView.getDrawable();
                if (animation != null && animation.isRunning()) {
                    animation.stop();
                }
                media_imageView.setImageResource(mipmap_ID);
            } catch (Exception e) {
                e.toString();
            }
            return false;
        }else if (mediaPlayer != null&&!mediaPlayer.isPlaying()){
            return true;
        }
         mediaPlayer=null;
        return false;
    }

    private boolean setTFVoice(String path){

        boolean mm=setVoice_Stop();

        if (mm){
            return true;
        }else {
            if (media_path.equals(path)) {
                return false;
            }else {
                return true;
            }
        }
    }
}
