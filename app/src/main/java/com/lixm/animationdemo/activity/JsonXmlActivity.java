package com.lixm.animationdemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.lixm.animationdemo.R;
import com.lixm.animationdemo.utils.Utils;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class JsonXmlActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_xml);
        InputStream is= null;
        try {
            is = getAssets().open("zhuCeXieYi.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = xmlFile2Json(is);
        String jsonContent = jsonObject != null ? jsonObject.toString() : "";
        String format = Utils.iJsonFormat(jsonContent, false);
        Log.w("TAG", format);
        ((TextView) findViewById(R.id.jsonContent)).setText(format);
    }

    /**
     * 读取File文件xml转换成JSON
     *
     * @param is
     * @return
     */
    public static JSONObject xmlFile2Json(InputStream is) {
        System.out.println("xmlFile转换成json:");
        JSONObject json = new JSONObject();
        try {
            SAXBuilder sb = new SAXBuilder();
            Document doc = sb.build(is);
            Element root = doc.getRootElement();
            json.put(root.getName(), iterateElement(root));
            return json;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 迭代
     *
     * @param element
     * @return
     */
    @SuppressWarnings("unchecked")
    private static Map<String, Object> iterateElement(Element element) {
        List<Element> jiedian = element.getChildren();
        Element et = null;
        Map<String, Object> obj = new HashMap<String, Object>();
        List<Object> list = null;
        for (int i = 0; i < jiedian.size(); i++) {
            list = new LinkedList<Object>();
            et = (Element) jiedian.get(i);
            if (et.getTextTrim().equals("")) {
                if (et.getChildren().size() == 0)
                    continue;
                if (obj.containsKey(et.getName())) {
                    list = (List<Object>) obj.get(et.getName());
                }
                list.add(iterateElement(et));
                obj.put(et.getName(), list);
            } else {
                if (obj.containsKey(et.getName())) {
                    list = (List<Object>) obj.get(et.getName());
                }
                list.add(et.getTextTrim());
                obj.put(et.getName(), list);
            }
        }
        return obj;
    }
}
