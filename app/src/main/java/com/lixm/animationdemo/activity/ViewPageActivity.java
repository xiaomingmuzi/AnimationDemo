package com.lixm.animationdemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.lixm.animationdemo.R;
import com.lixm.animationdemo.fragment.NormalFragment;

import java.util.ArrayList;

public class ViewPageActivity extends BaseActivity {

    private Button btn;
    private ViewPager viewPager;
    private MyAdapter myAdapter;
    private ArrayList<Fragment> fragments;
    private  NormalFragment fragment4;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_page);
        btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragments.remove(fragments.size() - 1);
                NormalFragment fragment5 = new NormalFragment();
                Bundle bundle = new Bundle();
                bundle.putString("TAG", "fragment5  ragment5 ragment5");
                fragment5.setArguments(bundle);
                fragments.add(fragment5);

//                FragmentManager fm = getSupportFragmentManager();
//                FragmentTransaction ft = fm.beginTransaction();
//                List<Fragment> fragments = fm.getFragments();
//                if (fragments != null && fragments.contains(fragment4)) {
//                    LogUtil.i("删除fragment4");
//                    ft.remove(fragment4);
//                    ft.commit();
//                }

//                myAdapter.destroyItem(viewPager,myAdapter.getCount()-1,fragment4);
//                myAdapter.bindData(fragments);
//                myAdapter.instantiateItem(viewPager,myAdapter.getCount()-1);

                myAdapter.notifyDataSetChanged();
//                viewPager.setCurrentItem(0);
            }
        });
        viewPager = findViewById(R.id.vp);
        fragments = new ArrayList<>();
        NormalFragment fragment1 = new NormalFragment();
        NormalFragment fragment2 = new NormalFragment();
        NormalFragment fragment3 = new NormalFragment();
         fragment4 = new NormalFragment();
        Bundle bundle = new Bundle();
        bundle.putString("TAG", "fragment4");
        fragment4.setArguments(bundle);
        fragments.add(fragment1);
        fragments.add(fragment2);
        fragments.add(fragment3);
        fragments.add(fragment4);
        myAdapter = new MyAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(myAdapter);
    }

    class MyAdapter extends FragmentStatePagerAdapter {

        private ArrayList<Fragment> fragments;

        public MyAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }

        public void bindData(ArrayList<Fragment> fragments){
            this.fragments=fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getItemPosition(Object object) {
            return PagerAdapter.POSITION_NONE;
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            return super.instantiateItem(container, position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            super.destroyItem(container, position, object);
        }
    }
}
