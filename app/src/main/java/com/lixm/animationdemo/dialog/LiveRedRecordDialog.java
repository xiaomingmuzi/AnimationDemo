package com.lixm.animationdemo.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.lixm.animationdemo.R;
import com.lixm.animationdemo.customview.LiveRedRecordVpItem;
import com.lixm.animationdemo.fragment.DialogFragment;

import java.util.ArrayList;


/**
 * User: LXM
 * Date: 2017-07-12
 * Time: 10:16
 * Detail:查看红包记录
 */
public class LiveRedRecordDialog extends Dialog implements View.OnClickListener, RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener {
    private Context mContext;

    private LinearLayout mDetailClose;
    private RadioGroup mRg;
    private ViewPager mRedVp;
    private MyCouponVpAdapter myVpAdapter;

    private LiveRedRecordVpItem vpSendItem;
    private LiveRedRecordVpItem vpReceiveItem;
    private ArrayList<LiveRedRecordVpItem> views;

    private DialogFragment dialogFragment1;
    private DialogFragment dialogFragment2;
    private ArrayList<DialogFragment> fragments;
    private MyFragmentAdapter fragmentAdapter;

    public LiveRedRecordDialog(@NonNull Context context) {
        super(context);
        mContext = context;
        setContentView(R.layout.red_record_layout);
        init();
    }

    private void init() {


        mDetailClose = (LinearLayout) findViewById(R.id.red_record_close);
        mDetailClose.setOnClickListener(this);
        mRg = (RadioGroup) findViewById(R.id.red_record_rg);
        mRg.setOnCheckedChangeListener(this);
        mRedVp = (ViewPager)findViewById(R.id.red_record_viewpager);
//        mRedVp.addOnPageChangeListener(this);
        mRedVp.setCurrentItem(0);

        setVpAdapter();

//        WindowManager windowManager = ((Activity) mContext).getWindowManager();
//        Display display = windowManager.getDefaultDisplay();
//        Window dlgwin = this.getWindow();
//        WindowManager.LayoutParams lp = dlgwin.getAttributes();
//        dlgwin.setGravity(Gravity.CENTER);
//        lp.width = (int) (display.getWidth()); //设置宽度
//        lp.height = (int) (display.getHeight()); //设置高度
//
//        this.getWindow().setAttributes(lp);
    }


    private void setVpAdapter() {
        vpSendItem=new LiveRedRecordVpItem(mContext);
        vpSendItem.setData(0);
        vpReceiveItem=new LiveRedRecordVpItem(mContext);
        vpReceiveItem.setData(1);

        views = new ArrayList<>();
        views.add(0, vpSendItem);
        views.add(1, vpReceiveItem);

        myVpAdapter = new MyCouponVpAdapter();
        if (mRedVp != null)
            mRedVp.setAdapter(myVpAdapter);

//        dialogFragment1=new DialogFragment();
//        Bundle bundle=new Bundle();
//        bundle.putInt("TYPE",0);
//        dialogFragment1.setArguments(bundle);
//        dialogFragment2=new DialogFragment();
//        bundle.putInt("TYPE",1);
//        dialogFragment2.setArguments(bundle);
//        fragments=new ArrayList<>();
//        fragments.add(0,dialogFragment1);
//        fragments.add(1,dialogFragment2);
//        fragmentAdapter=new MyFragmentAdapter(Contants.manager,fragments);
//        mRedVp.setAdapter(fragmentAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.red_record_close:
                dismiss();
//                finish();
                break;

        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        switch (checkedId) {
            case R.id.send_red:
                mRedVp.setCurrentItem(0);
                break;
            case R.id.receive_red:
                mRedVp.setCurrentItem(1);
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        ((RadioButton) mRg.getChildAt(position)).setChecked(true);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    class MyCouponVpAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ((ViewPager) container).addView(views.get(position));
            return views.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            // 删除
            container.removeView(views.get(position));
        }
    }

    class MyFragmentAdapter extends FragmentStatePagerAdapter{
       private ArrayList<DialogFragment> fragments;

        public MyFragmentAdapter(FragmentManager fm,ArrayList<DialogFragment> fragments) {
            super(fm);
            this.fragments=fragments;
        }

        @Override
        public Fragment getItem(int position) {
            Log.e("======","===getItem："+position);
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }
}
