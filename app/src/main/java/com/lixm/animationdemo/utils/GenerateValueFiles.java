package com.lixm.animationdemo.utils;

import org.xutils.common.util.LogUtil;

import java.io.File;

/**
 * @author Lixm
 * @date 2017/11/22
 * @detail
 */

public class GenerateValueFiles {
    private int baseW;
    private int baseH;

    private String dirStr = "./res";
    private final static String WTemplate = "<dimen name=\"x{0}\">{1}px</dimen>\n";
    private final static String HTemplate = "<dimen name=\"y{0}\">{1}px</dimen>\n";

    private final static String VALUE_TEMPLATE = "values-{0}x{1}";
    private static final String SUPPORT_DIMESION = "320,480;480,800;480,854;540,960;600,1024;720,1184;" +
            "720,1196;720,1280;768,1024;800,1280;1080,1812;1080,1920;1440,2560;";
    private String supportStr = SUPPORT_DIMESION;


    public GenerateValueFiles(int baseX, int baseY, String supportStr) {
        this.baseW = baseX;
        this.baseH = baseY;
        if (!this.supportStr.equals(baseX + "," + baseY)) {
            this.supportStr += baseX + "," + baseY + ";";
        }
        this.supportStr += validateInput(supportStr);
        LogUtil.w("supportStr：" + supportStr);

        File dir = new File(dirStr);
        if (!dir.exists())
            dir.mkdir();
        LogUtil.i("file路径：" + dir.getAbsolutePath());
    }

    private String validateInput(String supportStr) {
        StringBuffer sb = new StringBuffer();
        String[] vals = supportStr.split("_");
        int w = -1;
        int h = -1;
        String[] wh;
        for (String val : vals) {
            try {
                if (val == null || val.trim().length() == 0)
                    continue;
                wh = val.split(",");
                w = Integer.parseInt(wh[0]);
                h = Integer.parseInt(wh[1]);
            } catch (Exception e) {
                e.printStackTrace();
                LogUtil.i("skip invalidate params ：w,h=" + val);
                continue;
            }
            sb.append(w + "," + h + ";");
        }
        return sb.toString();
    }

    public void generate(){
        String[] vals=supportStr.split(";");
//        for (String )
    }

}
