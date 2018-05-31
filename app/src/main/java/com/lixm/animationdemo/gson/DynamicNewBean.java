package com.lixm.animationdemo.gson;

import com.lixm.animationdemo.bean.DynamicBean;

import java.util.List;

/**
 * @author Lixm
 * @date 2018/5/31 16:22
 * @detail
 */

public class DynamicNewBean {

    /**
     * result : 10000
     * msg : 数据获取成功
     * MerList : [{"Gabmit":[],"Dynamic":[{"SourceType":"1","UserID":348392,"UserIconUrl":"http://images-shichai.test.cnfol.com/thumbIcon/3483921478746728.png","NickName":"blog哈","UserName":"blog","Status":"2","VipPic":"","Target":"理财","isType":1,"IsFlag":0,"DynamicID":"63918","AddTime":"2018-05-31 16:20:17","LivingProductID":null,"IsTop":0,"IsOpening":3,"CommentCount":"0","PraiseCount":"0","ForwardCount":"0","Commentlist":[],"TopicID":0,"Isprise":0,"Content":"000005 平安银行","Title":"","BlogUrl":"","DynamicPic":[],"Iszhib":2,"Forward":[],"IsView":1,"LiveProductID":0,"IsShield":0,"Media":[],"Voice":[],"Video":[],"Knowledge":[],"data":[{"content":"平安银行","url":"http://thirdparty7.3g.cnfol.com/000001J.html"},{"content":"000005","url":"http://thirdparty7.3g.cnfol.com/000005J.html"}],"BrowseNum":0,"WatchCount":"0"},{"SourceType":"1","UserID":348392,"UserIconUrl":"http://images-shichai.test.cnfol.com/thumbIcon/3483921478746728.png","NickName":"blog哈","UserName":"blog","Status":"2","VipPic":"","Target":"理财","isType":1,"IsFlag":12,"DynamicID":"63917","AddTime":"2018-05-31 16:19:51","LivingProductID":null,"IsTop":0,"IsOpening":3,"CommentCount":"0","PraiseCount":"0","ForwardCount":"0","Commentlist":[],"TopicID":0,"Isprise":0,"Content":"我写了一篇专栏，《专栏标题：hhh》，点击查看详情","Title":"hhh","BlogUrl":"","DynamicPic":[],"Iszhib":2,"Forward":[],"IsView":1,"LiveProductID":0,"IsShield":0,"Media":[],"Voice":[],"Video":[],"Knowledge":{"KnowledgeUrl":"http://college.test.cnfol.com/index.php?r=Knowledge/Article&articleid=605&expertid=348392","Status":0},"data":[],"BrowseNum":0,"WatchCount":"0"},{"SourceType":"1","UserID":348392,"UserIconUrl":"http://images-shichai.test.cnfol.com/thumbIcon/3483921478746728.png","NickName":"blog哈","UserName":"blog","Status":"2","VipPic":"","Target":"理财","isType":2,"IsFlag":7,"DynamicID":"63916","AddTime":"2018-05-31 16:17:59","LivingProductID":null,"IsTop":0,"IsOpening":3,"CommentCount":"0","PraiseCount":"0","ForwardCount":"0","Commentlist":[],"TopicID":0,"Isprise":0,"Content":"ghh","Title":"","BlogUrl":"","DynamicPic":[],"Iszhib":2,"Forward":[],"IsView":1,"LiveProductID":0,"IsShield":0,"Media":[],"Voice":{"VoiceUrl":"http://images-shichai.test.cnfol.com/thumbnail/201805/vote_1527754679.amr","VoiceLength":8},"Video":[],"Knowledge":[],"data":[],"BrowseNum":0,"WatchCount":"0"},{"SourceType":"1","UserID":348392,"UserIconUrl":"http://images-shichai.test.cnfol.com/thumbIcon/3483921478746728.png","NickName":"blog哈","UserName":"blog","Status":"2","VipPic":"","Target":"理财","isType":3,"IsFlag":7,"DynamicID":"63915","AddTime":"2018-05-31 16:17:12","LivingProductID":null,"IsTop":0,"IsOpening":3,"CommentCount":"0","PraiseCount":"0","ForwardCount":"0","Commentlist":[],"TopicID":0,"Isprise":0,"Content":"bnh","Title":"bnh","BlogUrl":"","DynamicPic":[],"Iszhib":2,"Forward":[],"IsView":1,"LiveProductID":0,"IsShield":0,"Media":[],"Voice":[],"Video":{"VideoImgUrl":"http://1252336041.vod2.myqcloud.com/dea5e0favodgzp1252336041/16da24757447398156357634770/7447398156357634771.jpg","VideoUrl":"http://1252336041.vod2.myqcloud.com/dea5e0favodgzp1252336041/16da24757447398156357634770/qRyE8DktHkkA.mp4","VideoLength":202},"Knowledge":[],"data":[],"BrowseNum":0,"WatchCount":"0"},{"SourceType":"7","UserID":"349418","UserIconUrl":"http://images-shichai.test.cnfol.com/thumbIcon/3494181522202393.jpg","NickName":"赵微","UserName":"zw","Status":"2","VipPic":"","Target":"基金","isType":4,"IsFlag":10,"DynamicID":"63913","AddTime":"2018-05-31 14:05:30","LivingProductID":"0","IsTop":0,"IsOpening":3,"CommentCount":"0","PraiseCount":"0","ForwardCount":"0","Commentlist":[],"TopicID":0,"Isprise":0,"Content":"t","Title":"","BlogUrl":"","DynamicPic":[],"Iszhib":2,"Forward":[],"IsView":1,"LiveProductID":"268403","IsOpen":"0","LiveMsgID":"349418","videoUrl":"http://images-shichai.test.cnfol.com/original/201805/20180531135733451.jpg","VideoAddress":"http://1251266724.vod2.myqcloud.com/8f8ce2a3vodgzp1251266724/cbb078457447398156349954879/playlist.m3u8","IsShield":0,"Media":[],"Voice":[],"Video":[],"Knowledge":[],"data":[],"BrowseNum":"794","WatchCount":"794"},{"SourceType":"7","UserID":"349418","UserIconUrl":"http://images-shichai.test.cnfol.com/thumbIcon/3494181522202393.jpg","NickName":"赵微","UserName":"zw","Status":"2","VipPic":"","Target":"基金","isType":4,"IsFlag":10,"DynamicID":"63912","AddTime":"2018-05-31 13:34:55","LivingProductID":"0","IsTop":0,"IsOpening":3,"CommentCount":"0","PraiseCount":"0","ForwardCount":"0","Commentlist":[],"TopicID":0,"Isprise":0,"Content":"t","Title":"","BlogUrl":"","DynamicPic":[],"Iszhib":2,"Forward":[],"IsView":1,"LiveProductID":"268403","IsOpen":"0","LiveMsgID":"349418","videoUrl":"http://images-shichai.test.cnfol.com/original/201805/20180531133320608.jpg","VideoAddress":"http://1251266724.vod2.myqcloud.com/8f8ce2a3vodgzp1251266724/c46b5a1f7447398156349619866/playlist.m3u8","IsShield":0,"Media":[],"Voice":[],"Video":[],"Knowledge":[],"data":[],"BrowseNum":"56","WatchCount":"56"},{"SourceType":"7","UserID":"349418","UserIconUrl":"http://images-shichai.test.cnfol.com/thumbIcon/3494181522202393.jpg","NickName":"赵微","UserName":"zw","Status":"2","VipPic":"","Target":"基金","isType":4,"IsFlag":10,"DynamicID":"63911","AddTime":"2018-05-31 11:41:48","LivingProductID":"0","IsTop":0,"IsOpening":3,"CommentCount":"0","PraiseCount":"0","ForwardCount":"0","Commentlist":[],"TopicID":0,"Isprise":0,"Content":"t","Title":"","BlogUrl":"","DynamicPic":[],"Iszhib":2,"Forward":[],"IsView":1,"LiveProductID":"268403","IsOpen":"0","LiveMsgID":"349418","videoUrl":"http://images-shichai.test.cnfol.com/original/201805/2018053111395815.jpg","VideoAddress":"http://1251266724.vod2.myqcloud.com/8f8ce2a3vodgzp1251266724/73566a197447398156348497524/playlist.m3u8","IsShield":0,"Media":[],"Voice":[],"Video":[],"Knowledge":[],"data":[],"BrowseNum":"45","WatchCount":"45"},{"SourceType":"1","UserID":"627490","UserIconUrl":"http://head.cnfolimg.com/03/e5/627490/head.627490.96","NickName":"kwer","UserName":"k","Status":"2","VipPic":"","Target":"黄金","isType":4,"IsFlag":10,"DynamicID":"63910","AddTime":"2018-05-31 11:41:44","LivingProductID":"287702","IsTop":0,"IsOpening":3,"CommentCount":"0","PraiseCount":"0","ForwardCount":"0","Commentlist":[],"TopicID":0,"Isprise":0,"Content":"哈哈","Title":"","BlogUrl":"","DynamicPic":[],"Iszhib":2,"Forward":[],"IsView":1,"LiveProductID":"286645","IsOpen":"0","LiveMsgID":"627490","videoUrl":"http://images-shichai.test.cnfol.com/live/4.jpg","VideoAddress":"http://1251266724.vod2.myqcloud.com/8f8ce2a3vodgzp1251266724/72afc4987447398156348400899/playlist.m3u8","IsShield":0,"Media":[],"Voice":[],"Video":[],"Knowledge":[],"data":[],"BrowseNum":"311","WatchCount":"311"},{"SourceType":"1","UserID":"627490","UserIconUrl":"http://head.cnfolimg.com/03/e5/627490/head.627490.96","NickName":"kwer","UserName":"k","Status":"2","VipPic":"","Target":"黄金","isType":4,"IsFlag":10,"DynamicID":"63909","AddTime":"2018-05-31 11:13:11","LivingProductID":"287701","IsTop":0,"IsOpening":3,"CommentCount":"0","PraiseCount":"0","ForwardCount":"0","Commentlist":[],"TopicID":0,"Isprise":0,"Content":"赶快开工","Title":"","BlogUrl":"","DynamicPic":[],"Iszhib":2,"Forward":[],"IsView":1,"LiveProductID":"286645","IsOpen":"0","LiveMsgID":"627490","videoUrl":"http://images-shichai.test.cnfol.com/live/4.jpg","VideoAddress":"http://1251266724.vod2.myqcloud.com/8f8ce2a3vodgzp1251266724/2f675e627447398156347855788/playlist.m3u8","IsShield":0,"Media":[],"Voice":[],"Video":[],"Knowledge":[],"data":[],"BrowseNum":"444","WatchCount":"444"},{"SourceType":"1","UserID":"345582","UserIconUrl":"http://images-shichai.test.cnfol.com/thumbIcon/3455821526551482.jpg","NickName":"你的名字","UserName":"wlt","Status":"2","VipPic":"http://images-shichai.test.cnfol.com/Gambit/201708/20170831153115498.jpg","Target":"保险","isType":4,"IsFlag":10,"DynamicID":"63908","AddTime":"2018-05-31 10:52:33","LivingProductID":"1527734890","IsTop":0,"IsOpening":3,"CommentCount":"0","PraiseCount":"0","ForwardCount":"0","Commentlist":[],"TopicID":0,"Isprise":0,"Content":"否定的","Title":"","BlogUrl":"","DynamicPic":[],"Iszhib":2,"Forward":[],"IsView":1,"LiveProductID":"268163","IsOpen":"0","LiveMsgID":"345582","videoUrl":"http://images-shichai.test.cnfol.com/original/201805/20180528092815945.jpg","VideoAddress":"http://1251266724.vod2.myqcloud.com/fe7fcf86vodcq1251266724/616b08c87447398156300788003/playlist.m3u8","IsShield":0,"Media":[],"Voice":[],"Video":[],"Knowledge":[],"data":[],"BrowseNum":"880","WatchCount":"880"}]}]
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
        private List<?> Gabmit;
        private List<DynamicBean> Dynamic;

        public List<?> getGabmit() {
            return Gabmit;
        }

        public void setGabmit(List<?> Gabmit) {
            this.Gabmit = Gabmit;
        }

        public List<DynamicBean> getDynamic() {
            return Dynamic;
        }

        public void setDynamic(List<DynamicBean> Dynamic) {
            this.Dynamic = Dynamic;
        }
    }
}
