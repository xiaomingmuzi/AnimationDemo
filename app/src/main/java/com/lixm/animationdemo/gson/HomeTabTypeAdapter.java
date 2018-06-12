package com.lixm.animationdemo.gson;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.lixm.animationdemo.bean.HomeTabBean;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Lixm
 * @date 2018/6/12 11:27
 * @detail
 */

public class HomeTabTypeAdapter extends TypeAdapter<HomeTabBean> {

    @Override
    public void write(JsonWriter out, HomeTabBean value) throws IOException {

    }

    @Override
    public HomeTabBean read(JsonReader in) throws IOException {
        HomeTabBean homeTabBean = new HomeTabBean();
        in.beginObject();
        while (in.hasNext()) {
            switch (in.nextName()) {
                case "result":
                    homeTabBean.setResult(in.nextString());
                    break;
                case "msg":
                    homeTabBean.setMsg(in.nextString());
                    break;
                case "MerList":
                    in.beginArray();
                    ArrayList<HomeTabBean.MerListBean> merListBeans = new ArrayList<>();
                    HomeTabBean.MerListBean bean = new HomeTabBean.MerListBean();
                    in.beginObject();
                    while (in.hasNext()) {
                        switch (in.nextName()) {
                            case "ID":
                                bean.setID(in.nextInt());
                                break;
                            case "Imgurl":
                                bean.setImgurl(in.nextString());
                                break;
                            case "Name":
                                bean.setName(in.nextString());
                                break;
                            case "Htmlurl":
                                bean.setHtmlurl(in.nextString());
                                break;
                            case "Type":
                                bean.setType(in.nextString());
                                break;
                            case "NativePageType":
                                bean.setNativePageType(in.nextInt());
                                break;
                        }
                        merListBeans.add(bean);
                    }
                    in.endObject();
                    in.endArray();
                    break;
            }
        }
        in.endObject();
        return homeTabBean;
    }
}
