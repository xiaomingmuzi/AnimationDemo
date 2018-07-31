package com.lixm.animationdemo.utils;

import com.lixm.animationdemo.bean.LiveGiftBean;
import com.lixm.animationdemo.bean.StockData;

import java.util.ArrayList;
import java.util.Timer;

/**
 * @author Lixm
 * @date 2017/11/28
 * @detail
 */

public class PARAM {

    public static final String REFRESH_FIVE_SPEED = "REFRESH_FIVE_SPEED";
    public static Timer REFRESH_FIVESPEED_TIMER;
    public static StockData stockData = new StockData();

    public static final String HOST = "http://gooke.api.3g.cnfol.com/";
    public static final String URL_STOCK_JBAI = HOST + "quote/Jbai.html";
    public static final String URL_STOCK_JBAI_FIVE = HOST + "quote/Jbaif.html";
    public static final String URL_STOCK_KBAI = HOST + "quote/Kbai.html";

    public static final String URL_TIMELY_QUOTE = "http://quotes.api.3g.cnfol.com/new/ajax/chart.html";// 涨跌数据

    public static final String BASE_STOCK_URL="http://v2.quotes.api.cnfol.com";

    public static LiveGiftBean liveGiftBean = null;//选中要发送的礼物
    public static ArrayList<LiveGiftBean> liveGiftBeans = null;//选中要购买的礼物的集合
    public static final String GiftClappingId = "2";//鼓掌
    public static final String GiftGeniusId = "3";//有才 3
    public static final String GiftFlowerId = "4";//鲜花 4
    public static final String GiftCheersId = "5";//干杯 5
    public static final String GiftKissId = "6";//kiss 6
    public static final String GiftCattleId = "7";//牛 7
    public static final String GiftNum66Id = "8";//num66 8
    public static final String GiftWeakId = "9";//week 9
    public static final String GiftRottenEggId = "10";//臭鸡蛋 10
    public static final String GiftLimitUpId = "11";//涨停 11
    public static final String GiftGoldBrickId = "12";//金砖 12
    public static String ActionGiftChoose = "com.cnfol.financialplanner.giftchoose";//礼物数量选择弹框
    public static String ActionGiftChange = "com.cnfol.financialplanner.giftchange";//礼物选择变化，更新连发按钮


}
