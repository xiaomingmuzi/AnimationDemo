package com.lixm.animationdemo.bean;

import java.util.List;

/**
 * @author Lixm
 * @date 2018/6/12 11:27
 * @detail
 */

public class HomeTabBean {

    /**
     * result : 10000
     * msg : 获取数据成功
     * MerList : [{"ID":4,"Imgurl":"http://images.shichai.cnfol.com/original/201804/11049808_1524811993_58049.png","Name":"公开课","Htmlurl":"","Type":"1","NativePageType":0},{"ID":2,"Imgurl":"http://images.shichai.cnfol.com/original/201804/11049808_1524812108_9792.png","Htmlurl":"","Name":"知识专栏","Type":"1","NativePageType":0},{"ID":5,"Imgurl":"http://images.shichai.cnfol.com/original/201804/11049808_1524812059_94378.png","Name":"期货","Htmlurl":"","Type":"1","NativePageType":1},{"ID":6,"Imgurl":"http://images-shichai.test.cnfol.com/original/201801/85_1516074563_.png","Htmlurl":"","Name":"大学堂","Type":"1","NativePageType":1},{"ID":1,"Imgurl":"http://images.shichai.cnfol.com/original/201804/11049808_1524812164_21286.png","Htmlurl":"","Name":"排行榜","Type":"1","NativePageType":0},{"ID":7,"Imgurl":"http://images.shichai.cnfol.com/original/201804/11049808_1524812108_9792.png","Htmlurl":"","Name":"基金","Type":"1","NativePageType":1}]
     */

    private String result;
    private String msg;
    private List<MerListBean> MerList;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<MerListBean> getMerList() {
        return MerList;
    }

    public void setMerList(List<MerListBean> MerList) {
        this.MerList = MerList;
    }

    public static class MerListBean {
        /**
         * ID : 4
         * Imgurl : http://images.shichai.cnfol.com/original/201804/11049808_1524811993_58049.png
         * Name : 公开课
         * Htmlurl :
         * Type : 1
         * NativePageType : 0
         */

        private int ID;
        private String Imgurl;
        private String Name;
        private String Htmlurl;
        private String Type;
        private int NativePageType;

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public String getImgurl() {
            return Imgurl;
        }

        public void setImgurl(String Imgurl) {
            this.Imgurl = Imgurl;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public String getHtmlurl() {
            return Htmlurl;
        }

        public void setHtmlurl(String Htmlurl) {
            this.Htmlurl = Htmlurl;
        }

        public String getType() {
            return Type;
        }

        public void setType(String Type) {
            this.Type = Type;
        }

        public int getNativePageType() {
            return NativePageType;
        }

        public void setNativePageType(int NativePageType) {
            this.NativePageType = NativePageType;
        }

        @Override
        public String toString() {
            return "MerListBean{" +
                    "ID=" + ID +
                    ", Imgurl='" + Imgurl + '\'' +
                    ", Name='" + Name + '\'' +
                    ", Htmlurl='" + Htmlurl + '\'' +
                    ", Type='" + Type + '\'' +
                    ", NativePageType=" + NativePageType +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "HomeTabBean{" +
                "result='" + result + '\'' +
                ", msg='" + msg + '\'' +
                ", MerList=" + MerList +
                '}';
    }
}
