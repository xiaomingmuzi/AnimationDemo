package com.lixm.animationdemo.utils;

import com.lixm.animationdemo.bean.StockData;

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
    public static final String getStockFive=BASE_STOCK_URL+"stock.html?action=getStockFive";



}
