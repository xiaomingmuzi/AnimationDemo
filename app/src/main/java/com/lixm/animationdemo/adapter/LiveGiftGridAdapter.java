package com.lixm.animationdemo.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lixm.animationdemo.R;
import com.lixm.animationdemo.bean.LiveGiftBean;
import com.lixm.animationdemo.listener.PostReceiverDataListener;
import com.lixm.animationdemo.utils.PARAM;

import org.xutils.common.util.LogUtil;

import java.util.ArrayList;
import java.util.List;


/**
 * User: LXM
 * Date: 2017-01-24
 * Time: 11:14
 * Detail:视频直播室礼物适配器
 */
public class LiveGiftGridAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<LiveGiftBean> mDataList;
    private int type;//0 我的礼物，一次只能选择一个  1 购买礼物可以选择多个购买
    private PostReceiverDataListener postReceiverDataListener;
    private int pageNum;//0,1
    private Drawable clapping = null;
    private Drawable genius = null;
    private Drawable flower = null;
    private Drawable cheers = null;
    private Drawable kiss = null;
    private Drawable ox = null;
    private Drawable num666 = null;
    private Drawable weak = null;
    private Drawable egg = null;
    private Drawable up = null;
    private Drawable brick = null;

    public LiveGiftGridAdapter(Context context, List<? extends Object> datas, int type, PostReceiverDataListener postReceiverDataListener, int pageNum) {
        mContext = context;
        mDataList = (ArrayList<LiveGiftBean>) datas;
        this.type = type;
        this.postReceiverDataListener = postReceiverDataListener;
        this.pageNum = pageNum;
//        initDrawable();
        initDrawable1();
    }

    /**
     * 绑定数据
     *
     * @param datas
     */
    public void bindData(List<? extends Object> datas) {
        mDataList = (ArrayList<LiveGiftBean>) datas;
    }

    @Override
    public void notifyDataSetInvalidated() {
        super.notifyDataSetInvalidated();
    }

    @Override
    public int getCount() {
        return mDataList != null ? mDataList.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    ViewHolder holder;

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.gift_item, null);
            holder = new ViewHolder();
            convertView.setTag(holder);
            holder.rl_gift_bg = convertView.findViewById(R.id.rl_gift_bg);
            holder.imgBg = (ImageView) convertView.findViewById(R.id.grid_gift_img_background);
            holder.imgGift = (ImageView) convertView.findViewById(R.id.grid_gift_img);
            holder.giftTxt = (TextView) convertView.findViewById(R.id.gift_txt);
            holder.giftNum = (TextView) convertView.findViewById(R.id.gift_num);
        } else holder = (ViewHolder) convertView.getTag();

        final LiveGiftBean liveGiftBean = mDataList.get(position);
//        initImg(liveGiftBean);
        startImg(liveGiftBean);

        if (type == 0)
            holder.giftTxt.setText(liveGiftBean.getGiftName());
        else {
            double price = Double.parseDouble(liveGiftBean.getProductPrice());
            price = price / 100;
            holder.giftTxt.setText(liveGiftBean.getGiftName() + " ¥" + price);
        }
        if (liveGiftBean.getGiftNum().equals("0"))
            holder.giftNum.setVisibility(View.GONE);
        else {
            holder.giftNum.setVisibility(View.VISIBLE);
            int gNum = Integer.parseInt(liveGiftBean.getGiftNum());
            if (gNum > 99)
                holder.giftNum.setText("99+");
            else
                holder.giftNum.setText(gNum + "");
        }

        holder.imgGift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animatable animatable = (Animatable) ((ImageView) v).getDrawable();
                if (animatable != null) {
                    if (animatable.isRunning()) {
                        animatable.stop();
                        animatable.start();
                        LogUtil.i("======(stop)");
                    } else {
                        animatable.start();
                        LogUtil.i("======(start)");
                    }
                } else {
                    LogUtil.i("======(animatable==null)");
                }
                try {
                    if (type == 0) {//我的礼物，需要保证选中的礼物唯一被选中
                        liveGiftBean.setCheck(!liveGiftBean.isCheck());
                        mDataList.set(position, liveGiftBean);//更新选中的对象在数组集合中的状态
                        LogUtil.e("我的礼物界面，选中的礼物：" + liveGiftBean.toString());
                        if (PARAM.liveGiftBean != null) {//原有选中的礼物对象
                            if (!PARAM.liveGiftBean.getProductSeq().equals(liveGiftBean.getProductSeq())) {
                                PARAM.liveGiftBean.setCheck(false);
                                if (PARAM.liveGiftBean.getPageNum() == pageNum) {//选中的是同一界面的对象
                                    mDataList.set(PARAM.liveGiftBean.getPoi(), PARAM.liveGiftBean);//更新选中的对象在数组集合中的状态
                                } else {
                                    postReceiverDataListener.postReceiverData(2, 2, PARAM.liveGiftBean);
                                }
                                postReceiverDataListener.postReceiverData(3, 3, PARAM.liveGiftBean);
                            }
                        }
                        updateAnimation(position,  liveGiftBean);
                    } else {
                        if (!liveGiftBean.isCheck()) {
                            liveGiftBean.setCheck(!liveGiftBean.isCheck());
                            mDataList.set(position, liveGiftBean);//更新选中的对象在数组集合中的状态
                        }
                        liveGiftBean.setPageNum(pageNum);
                        liveGiftBean.setPoi(position);
                        postReceiverDataListener.postReceiverData(1, 1, liveGiftBean);
                        notifyDataSetInvalidated();
                        updateAnimation(position, liveGiftBean);
                    }
//                    notifyDataSetChanged();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        if (holder.imgBg.getVisibility() == View.VISIBLE && !liveGiftBean.isCheck())
            holder.imgBg.setVisibility(View.GONE);
        return convertView;
    }

    private void initImg(LiveGiftBean liveGiftBean) {
        try {
            holder.imgGift.setImageDrawable(null);
            switch (liveGiftBean.getProductSeq()) {
                case PARAM.GiftClappingId:
                    holder.imgGift.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.clapping_7));
                    break;
                case PARAM.GiftGeniusId:
//                    holder.imgGift.setImageDrawable(genius);
//                    holder.imgGift.setImageLevel(6);
                    holder.imgGift.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.genius_6));
                    break;
                case PARAM.GiftFlowerId:
//                    holder.imgGift.setImageDrawable(flower);
                    holder.imgGift.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.flower_10));
//                    holder.imgGift.setImageLevel(10);
                    break;
                case PARAM.GiftCheersId:
//                    holder.imgGift.setImageDrawable(cheers);
                    holder.imgGift.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.cheers_6));
//                    holder.imgGift.setImageLevel(6);
                    break;
                case PARAM.GiftKissId:
//                    holder.imgGift.setImageDrawable(kiss);
                    holder.imgGift.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.kiss_7));
//                    holder.imgGift.setImageLevel(7);
                    break;
                case PARAM.GiftCattleId:
//                    holder.imgGift.setImageDrawable(ox);
//                    holder.imgGift.setImageLevel(7);
                    holder.imgGift.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.cattle_7));
                    break;
                case PARAM.GiftNum66Id:
//                    holder.imgGift.setImageDrawable(num666);
//                    holder.imgGift.setImageLevel(10);
                    holder.imgGift.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.num_666_9));
                    break;
                case PARAM.GiftWeakId:
//                    holder.imgGift.setImageDrawable(weak);
//                    holder.imgGift.setImageLevel(9);
                    holder.imgGift.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.weak_9));
                    break;
                case PARAM.GiftRottenEggId:
//                    holder.imgGift.setImageDrawable(egg);
//                    holder.imgGift.setImageLevel(8);
                    holder.imgGift.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.rotten_egg_13));
                    break;
                case PARAM.GiftLimitUpId:
//                    holder.imgGift.setImageDrawable(up);
                    holder.imgGift.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.item_limit_up_7));
//                    holder.imgGift.setImageLevel(7);
                    break;
                case PARAM.GiftGoldBrickId:
//                    holder.imgGift.setImageDrawable(brick);
//                    holder.imgGift.setImageLevel(7);
                    holder.imgGift.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.gold_brick_14));
                    break;
            }
//            AnimationDrawable drawable = ((AnimationDrawable) holder.imgGift.getDrawable());
//            holder.imgGift.setImageDrawable(drawable.getFrame(drawable.getNumberOfFrames()-1));
//            holder.imgGift.setImageDrawable(null);
        } catch (OutOfMemoryError error) {
            error.printStackTrace();
        }
    }

    private void startImg(LiveGiftBean liveGiftBean) {
        try {
            holder.imgGift.setImageDrawable(null);
            switch (liveGiftBean.getProductSeq()) {
                case PARAM.GiftClappingId:
                    holder.imgGift.setImageDrawable(clapping);
                    break;
                case PARAM.GiftGeniusId:
                    holder.imgGift.setImageDrawable(genius);
//                    holder.imgGift.setImageLevel(6);
                    break;
                case PARAM.GiftFlowerId:
                    holder.imgGift.setImageDrawable(flower);
//                    holder.imgGift.setImageLevel(10);
                    break;
                case PARAM.GiftCheersId:
                    holder.imgGift.setImageDrawable(cheers);
//                    holder.imgGift.setImageLevel(6);
                    break;
                case PARAM.GiftKissId:
                    holder.imgGift.setImageDrawable(kiss);
//                    holder.imgGift.setImageLevel(7);
                    break;
                case PARAM.GiftCattleId:
                    holder.imgGift.setImageDrawable(ox);
//                    holder.imgGift.setImageLevel(7);
                    break;
                case PARAM.GiftNum66Id:
                    holder.imgGift.setImageDrawable(num666);
//                    holder.imgGift.setImageLevel(10);
                    break;
                case PARAM.GiftWeakId:
                    holder.imgGift.setImageDrawable(weak);
//                    holder.imgGift.setImageLevel(9);
                    break;
                case PARAM.GiftRottenEggId:
                    holder.imgGift.setImageDrawable(egg);
//                    holder.imgGift.setImageLevel(8);
                    break;
                case PARAM.GiftLimitUpId:
                    holder.imgGift.setImageDrawable(up);
//                    holder.imgGift.setImageLevel(7);
                    break;
                case PARAM.GiftGoldBrickId:
                    holder.imgGift.setImageDrawable(brick);
//                    holder.imgGift.setImageLevel(7);
                    break;
            }
        } catch (OutOfMemoryError error) {
            error.printStackTrace();
        }
    }

//    private void initDrawable() {
//        clapping = mContext.getResources().getDrawable(R.drawable.live_item_clapping);
//        genius = mContext.getResources().getDrawable(R.drawable.live_item_genius);
//        flower = mContext.getResources().getDrawable(R.drawable.live_item_flower);
//        cheers = mContext.getResources().getDrawable(R.drawable.live_item_cheers);
//        kiss = mContext.getResources().getDrawable(R.drawable.live_item_kiss);
//        ox = mContext.getResources().getDrawable(R.drawable.live_item_ox);
//        num666 = mContext.getResources().getDrawable(R.drawable.live_item_num_666);
//        weak = mContext.getResources().getDrawable(R.drawable.live_item_weak);
//        egg = mContext.getResources().getDrawable(R.drawable.live_item_rotten_egg);
//        up = mContext.getResources().getDrawable(R.drawable.live_item_limit_up);
//        brick = mContext.getResources().getDrawable(R.drawable.live_item_rotten_gold_brick);
//    }

    private void initDrawable1() {
        clapping = mContext.getResources().getDrawable(R.drawable.live_item_clapping1);
        genius = mContext.getResources().getDrawable(R.drawable.live_item_genius1);
        flower = mContext.getResources().getDrawable(R.drawable.live_item_flower1);
        cheers = mContext.getResources().getDrawable(R.drawable.live_item_cheers1);
        kiss = mContext.getResources().getDrawable(R.drawable.live_item_kiss1);
        ox = mContext.getResources().getDrawable(R.drawable.live_item_ox1);
        num666 = mContext.getResources().getDrawable(R.drawable.live_item_num_6661);
        weak = mContext.getResources().getDrawable(R.drawable.live_item_weak1);
        egg = mContext.getResources().getDrawable(R.drawable.live_item_rotten_egg1);
        up = mContext.getResources().getDrawable(R.drawable.live_item_limit_up1);
        brick = mContext.getResources().getDrawable(R.drawable.live_item_rotten_gold_brick1);
    }

    public void updateAnimation(int position,  LiveGiftBean liveGiftBean) {

        if (liveGiftBean.getGiftNum().equals("0"))
            holder.giftNum.setVisibility(View.GONE);
        else {
            holder.giftNum.setVisibility(View.VISIBLE);
            int gNum = Integer.parseInt(liveGiftBean.getGiftNum());
            if (gNum > 99)
                holder.giftNum.setText("99+");
            else
                holder.giftNum.setText(gNum + "");
        }

        if (liveGiftBean.isCheck()) {
            holder.imgBg.setVisibility(View.VISIBLE);
//            startImg(liveGiftBean);
//            holder.imgGift.post(new Runnable() {
//                @Override
//                public void run() {
//                    ((AnimationDrawable) holder.imgGift.getDrawable()).start();
//                }
//            });
//            AnimationDrawable animationDrawable = (AnimationDrawable) holder.imgGift.getDrawable();
//            animationDrawable.start();
//            handler.sendMessageDelayed(Message.obtain(), animationDrawable.getNumberOfFrames() * 50);

//            final int maxLevel = getMaxLevel(liveGiftBean);
//            LogUtil.e("=====maxLevel："+maxLevel);
//            ObjectAnimator objectAnimator = ObjectAnimator.ofInt(holder.imgGift, "imageLevel", 0,maxLevel);
//            objectAnimator.setDuration(getMaxLevel(liveGiftBean) * 150);
//            objectAnimator.addListener(new AnimatorListenerAdapter() {
//                @Override
//                public void onAnimationEnd(Animator animation) {
//                    holder.imgGift.setImageLevel(maxLevel);
//                    LogUtil.e("========onAnimationEnd============");
//                }
//
//                @Override
//                public void onAnimationStart(Animator animation) {
//                    LogUtil.e("========onAnimationStart============");
//                }
//            });
//            objectAnimator.start();
//            liveGiftBean.startObjectAnimator();
            if (type == 0) {
                if (PARAM.liveGiftBean != null && !PARAM.liveGiftBean.getProductSeq().equals(liveGiftBean.getProductSeq())) {//选中的礼物与上次不一样，如果当前为连发状态，则更新连发按钮
                    LogUtil.w("==========原来有选中的礼物：" + PARAM.liveGiftBean.toString());
//                    tempImg = holder.imgGift;
//                    tempImgBg = holder.imgBg;

                    Intent intent = new Intent(PARAM.ActionGiftChange);
                    mContext.sendBroadcast(intent);
                }
                PARAM.liveGiftBean = liveGiftBean;
                PARAM.liveGiftBean.setPageNum(pageNum);
                PARAM.liveGiftBean.setPoi(position);
            }
            holder.giftTxt.setTextColor(mContext.getResources().getColor(R.color.live_gift_txt_check));
            //            giftNum.setTextSize(12);
        } else {
            holder.imgBg.setVisibility(View.GONE);
            holder.giftTxt.setTextColor(mContext.getResources().getColor(R.color.live_gift_txt_normal));
            //            giftNum.setTextSize(10);
        }
    }

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            AnimationDrawable animationDrawable = (AnimationDrawable) holder.imgGift.getDrawable();
            if (animationDrawable != null)
                animationDrawable.stop();
            return false;
        }
    });

    private int getMaxLevel(LiveGiftBean liveGiftBean) {
        int maxLevel = 0;
        switch (liveGiftBean.getProductSeq()) {
            case PARAM.GiftClappingId:
                maxLevel = 6;
                break;
            case PARAM.GiftGeniusId:
                maxLevel = 6;
                break;
            case PARAM.GiftFlowerId:
                maxLevel = 10;
                break;
            case PARAM.GiftCheersId:
                maxLevel = 6;
                break;
            case PARAM.GiftKissId:
                maxLevel = 7;
                break;
            case PARAM.GiftCattleId:
                maxLevel = 7;
                break;
            case PARAM.GiftNum66Id:
                maxLevel = 10;
                break;
            case PARAM.GiftWeakId:
                maxLevel = 9;
                break;
            case PARAM.GiftRottenEggId:
                maxLevel = 8;
                break;
            case PARAM.GiftLimitUpId:
                maxLevel = 7;
                break;
            case PARAM.GiftGoldBrickId:
                maxLevel = 7;
                break;
        }
        return maxLevel;
    }

    class ViewHolder {
        RelativeLayout rl_gift_bg;
        ImageView imgBg;
        ImageView imgGift;
        TextView giftTxt;
        TextView giftNum;
    }

    public void clearBitmap() {
        clapping.setCallback(null);
        genius.setCallback(null);
        flower.setCallback(null);
        cheers.setCallback(null);
        kiss.setCallback(null);
        ox.setCallback(null);
        num666.setCallback(null);
        weak.setCallback(null);
        egg.setCallback(null);
        up.setCallback(null);
        brick.setCallback(null);
    }
}
