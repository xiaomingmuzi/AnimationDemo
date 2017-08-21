package com.lixm.animationdemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lixm.animationdemo.R;
import com.lixm.animationdemo.bean.Cloud;
import com.lixm.animationdemo.bean.JsonContentBean;
import com.lixm.animationdemo.bean.LiveFourBean;
import com.lixm.animationdemo.bean.LiveRedJsonBean;
import com.lixm.animationdemo.bean.LiveThreeBean;
import com.lixm.animationdemo.bean.Prof;
import com.lixm.animationdemo.bean.Vad;
import com.lixm.animationdemo.utils.Utils;

import java.util.ArrayList;

public class JsonBeanActivity extends AppCompatActivity {

    private String Tag = getClass().getName();
    private TextView txt;
    private static String appKey="1351742***";
    private static String SecretKey="a3bed5523bbc020cf4a****";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_bean);

        LiveRedJsonBean liveRedJsonBean = new LiveRedJsonBean();
        liveRedJsonBean.setSysRedID("1111");
        liveRedJsonBean.setUserid("348391");

        LiveRedJsonBean.LiveSecondBean liveSecondBean = new LiveRedJsonBean.LiveSecondBean();
        liveSecondBean.setA("1");
        liveSecondBean.setB("2");

        LiveThreeBean liveThreeBean = new LiveThreeBean();
        liveThreeBean.setAge("20");
        liveThreeBean.setName("tom");

        ArrayList<LiveFourBean> liveFourBeens = new ArrayList<>();
        LiveFourBean liveFourBean = new LiveFourBean(1, "20160822");
        LiveFourBean liveFourBean2 = new LiveFourBean(2, "20160822");
        liveFourBeens.add(liveFourBean);
        liveFourBeens.add(liveFourBean2);

        liveRedJsonBean.setLiveSecondBean(liveSecondBean);
        liveRedJsonBean.setLiveThreeBean(liveThreeBean);
        liveRedJsonBean.setLiveFourBeen(liveFourBeens);
        String jsonContent = new Gson().toJson(liveRedJsonBean);

        String format = Utils.iJsonFormat(jsonContent, false);
        Log.w(Tag, format);
        txt = (TextView) findViewById(R.id.txt);
        txt.setText(format+"\n\n\n\n\n\n  ");
        toJson();
    }


    private void toJson(){
        Cloud cloud=new Cloud(1,"","ws://cloud.chivox.com:8080",20,60);
        Vad vad=new Vad(1,"this-is-vad-res-path",60,16000,1);
        Prof prof=new Prof(1,"log-file-path");
        JsonContentBean jsonContentBean=new JsonContentBean(appKey,SecretKey,"path-of-provision-profile",cloud,vad,prof);
        String jsonContent = new Gson().toJson(jsonContentBean);
        Log.e(Tag,jsonContentBean.toString());
        txt.setText(txt.getText().toString()+"\n\n"+Utils.iJsonFormat(jsonContent,false));
    }
}
