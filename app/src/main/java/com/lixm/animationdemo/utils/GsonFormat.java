package com.lixm.animationdemo.utils;

import java.util.List;

/**
 * @author Lixm
 * @date 2017/10/9
 * @detail
 */

public class GsonFormat {

    /**
     * result : 10000
     * msg : 查询成功
     * MerList : [{"VideoID":"5167","ExpertID":"345582","NickName":"好好看看斤","UserName":"wlt","IconUrl":"http://images-shichai.test.cnfol.com/thumbIcon/3455821505784172.jpg","VideoUrl":"http://1252336041.vod2.myqcloud.com/dea5e0favodgzp1252336041/15662fe79031868223276911284/nGHBauf5LPAA.mp4","ViewPoint":"回家","CoverPic":"http://1252336041.vod2.myqcloud.com/dea5e0favodgzp1252336041/15662fe79031868223276911284/9031868223276911285.jpg","OnlineMemberNum":"6972","FansNum":"313","LivingProductID":null,"AddTime":"2017-09-27 16:21:39","Duration":"11","DynamicID":"58605","isCare":1,"GoodLabel":"原油,保险"},{"VideoID":"5166","ExpertID":"345487","NickName":"安妮宝贝","UserName":"bridges","IconUrl":"http://images-shichai.test.cnfol.com/thumbIcon/3454871474940230.png","VideoUrl":"http://1251266724.vod2.myqcloud.com/8f8ce2a3vodgzp1251266724/c1ef0c509031868223275688670/playlist.m3u8","ViewPoint":"根据顾客","CoverPic":"http://images-shichai.test.cnfol.com/original/201709/20170926171522567.jpg","OnlineMemberNum":"8961","FansNum":"624","LivingProductID":"0","AddTime":"2017-09-27 16:01:39","Duration":"2636","DynamicID":"0","isCare":1,"GoodLabel":""},{"VideoID":"5165","ExpertID":"345487","NickName":"安妮宝贝","UserName":"bridges","IconUrl":"http://images-shichai.test.cnfol.com/thumbIcon/3454871474940230.png","VideoUrl":"http://1251266724.vod2.myqcloud.com/8f8ce2a3vodgzp1251266724/ced597b69031868223272114105/playlist.m3u8","ViewPoint":"妇女奶茶","CoverPic":"http://images-shichai.test.cnfol.com/original/201709/20170926171522567.jpg","OnlineMemberNum":"397","FansNum":"624","LivingProductID":"286002","AddTime":"2017-09-26 17:52:03","Duration":"403","DynamicID":"0","isCare":1,"GoodLabel":""},{"VideoID":"5164","ExpertID":"345487","NickName":"安妮宝贝","UserName":"bridges","IconUrl":"http://images-shichai.test.cnfol.com/thumbIcon/3454871474940230.png","VideoUrl":"http://1251266724.vod2.myqcloud.com/8f8ce2a3vodgzp1251266724/8fdb6c669031868223271716258/playlist.m3u8","ViewPoint":"windows","CoverPic":"http://images-shichai.test.cnfol.com/original/201709/20170926171522567.jpg","OnlineMemberNum":"356","FansNum":"624","LivingProductID":"0","AddTime":"2017-09-26 17:19:10","Duration":"226","DynamicID":"0","isCare":1,"GoodLabel":""},{"VideoID":"5163","ExpertID":"59712","NickName":"0121","UserName":"CM-59712","IconUrl":"http://images-shichai.test.cnfol.com/thumbIcon/597121476344550.png","VideoUrl":"http://1251266724.vod2.myqcloud.com/8f8ce2a3vodgzp1251266724/81cc1ec29031868223271109852/playlist.m3u8","ViewPoint":"处处长介绍","CoverPic":"http://images-shichai.test.cnfol.com/original/201611/20161109192310184.jpg","OnlineMemberNum":"412","FansNum":"123","LivingProductID":"0","AddTime":"2017-09-26 14:07:30","Duration":"582","DynamicID":"0","isCare":1,"GoodLabel":""},{"VideoID":"5162","ExpertID":"59712","NickName":"0121","UserName":"CM-59712","IconUrl":"http://images-shichai.test.cnfol.com/thumbIcon/597121476344550.png","VideoUrl":"http://1251266724.vod2.myqcloud.com/8f8ce2a3vodgzp1251266724/81cc11e29031868223271109546/playlist.m3u8","ViewPoint":"v 你们 v","CoverPic":"http://images-shichai.test.cnfol.com/original/201611/20161109192310184.jpg","OnlineMemberNum":"462","FansNum":"123","LivingProductID":"285999","AddTime":"2017-09-26 13:57:24","Duration":"71","DynamicID":"0","isCare":1,"GoodLabel":""},{"VideoID":"5161","ExpertID":"59712","NickName":"0121","UserName":"CM-59712","IconUrl":"http://images-shichai.test.cnfol.com/thumbIcon/597121476344550.png","VideoUrl":"http://1251266724.vod2.myqcloud.com/8f8ce2a3vodgzp1251266724/81cc013f9031868223271109174/playlist.m3u8","ViewPoint":"减肥减肥","CoverPic":"http://images-shichai.test.cnfol.com/original/201611/20161109192310184.jpg","OnlineMemberNum":"415","FansNum":"123","LivingProductID":"285998","AddTime":"2017-09-26 13:55:51","Duration":"91","DynamicID":"0","isCare":1,"GoodLabel":""},{"VideoID":"5160","ExpertID":"345716","NickName":"小螃蟹a","UserName":"wenh","IconUrl":"http://images-shichai.test.cnfol.com/thumbIcon/3457161499313017.png","VideoUrl":"http://1251266724.vod2.myqcloud.com/8f8ce2a3vodgzp1251266724/84c0c81e9031868223271293412/playlist.m3u8","ViewPoint":"v 恐惧感","CoverPic":"http://images-shichai.test.cnfol.com/original/201708/20170816140329222.jpg","OnlineMemberNum":"335","FansNum":"400","LivingProductID":"285997","AddTime":"2017-09-26 13:53:09","Duration":"84","DynamicID":"0","isCare":1,"GoodLabel":"股票,外汇"},{"VideoID":"5159","ExpertID":"345716","NickName":"小螃蟹a","UserName":"wenh","IconUrl":"http://images-shichai.test.cnfol.com/thumbIcon/3457161499313017.png","VideoUrl":"http://1251266724.vod2.myqcloud.com/8f8ce2a3vodgzp1251266724/fb5ec7e99031868223269054934/playlist.m3u8","ViewPoint":"福建结果我2","CoverPic":"http://images-shichai.test.cnfol.com/original/201708/20170816140329222.jpg","OnlineMemberNum":"323","FansNum":"400","LivingProductID":"285996","AddTime":"2017-09-26 13:51:29","Duration":"107","DynamicID":"0","isCare":1,"GoodLabel":"股票,外汇"},{"VideoID":"5158","ExpertID":"345716","NickName":"小螃蟹a","UserName":"wenh","IconUrl":"http://images-shichai.test.cnfol.com/thumbIcon/3457161499313017.png","VideoUrl":"http://1251266724.vod2.myqcloud.com/8f8ce2a3vodgzp1251266724/84c049289031868223271292765/playlist.m3u8","ViewPoint":"结果 v 呢","CoverPic":"http://images-shichai.test.cnfol.com/original/201708/20170816140329222.jpg","OnlineMemberNum":"492","FansNum":"400","LivingProductID":"285995","AddTime":"2017-09-26 13:49:12","Duration":"39","DynamicID":"0","isCare":1,"GoodLabel":"股票,外汇"}]
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
         * VideoID : 5167
         * ExpertID : 345582
         * NickName : 好好看看斤
         * UserName : wlt
         * IconUrl : http://images-shichai.test.cnfol.com/thumbIcon/3455821505784172.jpg
         * VideoUrl : http://1252336041.vod2.myqcloud.com/dea5e0favodgzp1252336041/15662fe79031868223276911284/nGHBauf5LPAA.mp4
         * ViewPoint : 回家
         * CoverPic : http://1252336041.vod2.myqcloud.com/dea5e0favodgzp1252336041/15662fe79031868223276911284/9031868223276911285.jpg
         * OnlineMemberNum : 6972
         * FansNum : 313
         * LivingProductID : null
         * AddTime : 2017-09-27 16:21:39
         * Duration : 11
         * DynamicID : 58605
         * isCare : 1
         * GoodLabel : 原油,保险
         */

        private String VideoID;
        private String ExpertID;
        private String NickName;
        private String UserName;
        private String IconUrl;
        private String VideoUrl;
        private String ViewPoint;
        private String CoverPic;
        private String OnlineMemberNum;
        private String FansNum;
        private Object LivingProductID;
        private String AddTime;
        private String Duration;
        private String DynamicID;
        private int isCare;
        private String GoodLabel;

        public String getVideoID() {
            return VideoID;
        }

        public void setVideoID(String VideoID) {
            this.VideoID = VideoID;
        }

        public String getExpertID() {
            return ExpertID;
        }

        public void setExpertID(String ExpertID) {
            this.ExpertID = ExpertID;
        }

        public String getNickName() {
            return NickName;
        }

        public void setNickName(String NickName) {
            this.NickName = NickName;
        }

        public String getUserName() {
            return UserName;
        }

        public void setUserName(String UserName) {
            this.UserName = UserName;
        }

        public String getIconUrl() {
            return IconUrl;
        }

        public void setIconUrl(String IconUrl) {
            this.IconUrl = IconUrl;
        }

        public String getVideoUrl() {
            return VideoUrl;
        }

        public void setVideoUrl(String VideoUrl) {
            this.VideoUrl = VideoUrl;
        }

        public String getViewPoint() {
            return ViewPoint;
        }

        public void setViewPoint(String ViewPoint) {
            this.ViewPoint = ViewPoint;
        }

        public String getCoverPic() {
            return CoverPic;
        }

        public void setCoverPic(String CoverPic) {
            this.CoverPic = CoverPic;
        }

        public String getOnlineMemberNum() {
            return OnlineMemberNum;
        }

        public void setOnlineMemberNum(String OnlineMemberNum) {
            this.OnlineMemberNum = OnlineMemberNum;
        }

        public String getFansNum() {
            return FansNum;
        }

        public void setFansNum(String FansNum) {
            this.FansNum = FansNum;
        }

        public Object getLivingProductID() {
            return LivingProductID;
        }

        public void setLivingProductID(Object LivingProductID) {
            this.LivingProductID = LivingProductID;
        }

        public String getAddTime() {
            return AddTime;
        }

        public void setAddTime(String AddTime) {
            this.AddTime = AddTime;
        }

        public String getDuration() {
            return Duration;
        }

        public void setDuration(String Duration) {
            this.Duration = Duration;
        }

        public String getDynamicID() {
            return DynamicID;
        }

        public void setDynamicID(String DynamicID) {
            this.DynamicID = DynamicID;
        }

        public int getIsCare() {
            return isCare;
        }

        public void setIsCare(int isCare) {
            this.isCare = isCare;
        }

        public String getGoodLabel() {
            return GoodLabel;
        }

        public void setGoodLabel(String GoodLabel) {
            this.GoodLabel = GoodLabel;
        }
    }
}
