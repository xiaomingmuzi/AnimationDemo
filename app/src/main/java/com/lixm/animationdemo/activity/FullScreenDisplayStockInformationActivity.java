package com.lixm.animationdemo.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lixm.animationdemo.R;
import com.lixm.animationdemo.bean.StockData;
import com.lixm.animationdemo.service.RefreshFiveSpeedService;
import com.lixm.animationdemo.utils.PARAM;

import org.json.JSONArray;
import org.xutils.common.Callback;
import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Timer;
import java.util.TimerTask;

public class FullScreenDisplayStockInformationActivity extends Activity implements OnClickListener {
    private String TAG = "FullScreenDisplayStockInformationActivity";
    private WebView webView;

    private Button btnStockTimeShare;
    private Button btnStockFiveDay;
    private Button btnStockDay;
    private Button btnStockWeek;
    private Button btnStockMonth;

    private TextView textStockName;// 个股名称
    private TextView textStockCount;// 个股价格
    private TextView textStockChange;// 个股涨跌
    private TextView textStockTurnOver;// 个股换手率
    private TextView textStockVolume;// 个股成交量
    private TextView textStockDealCount;// 个股成交额
    private double stockLastClosePrice = 0.00d;// 个股昨收价

    // 分时相关
    private TextView textStockCurrentPrice;// 个股当前价格
    private TextView textStockTimeShareSellFifthFirstData;
    private TextView textStockTimeShareSellFifthSecondData;
    private TextView textStockTimeShareSellFourthFirstData;
    private TextView textStockTimeShareSellFourthSecondData;
    private TextView textStockTimeShareSellThirdFirstData;
    private TextView textStockTimeShareSellThirdSecondData;
    private TextView textStockTimeShareSellSecondFirstData;
    private TextView textStockTimeShareSellSecondSecondData;
    private TextView textStockTimeShareSellFirstFirstData;
    private TextView textStockTimeShareSellFirstSecondData;
    private TextView textStockTimeShareBuyFifthFirstData;
    private TextView textStockTimeShareBuyFifthSecondData;
    private TextView textStockTimeShareBuyFourthFirstData;
    private TextView textStockTimeShareBuyFourthSecondData;
    private TextView textStockTimeShareBuyThirdFirstData;
    private TextView textStockTimeShareBuyThirdSecondData;
    private TextView textStockTimeShareBuySecondFirstData;
    private TextView textStockTimeShareBuySecondSecondData;
    private TextView textStockTimeShareBuyFirstFirstData;
    private TextView textStockTimeShareBuyFirstSecondData;

    private LinearLayout timeShare;

    //	private TradedFiveSpeedRequest tradedFiveSpeedRequest;
    private int tradedFiveSpeedFlag = 0;
    //	private TimelyQuoteRequest timelyQuoteRequest;
    private int timelyQuoteFlag = 1;

    private int suspension;
    private String code;
    private String name;

    private int screenWidth = 0;
    private int screenHeight = 0;
    private String webViewUrl;

    private Timer timer;
    private TimerTask timerTask;
    private StockData stockData = new StockData();

    private double width; // 正常宽度
    private double height; // 正常高度
    private double timeHight; // 分时高度(与其他不一样)
    private double fiveDayHight;// 五日高度(与其他不一样)
    private double kHeight;

    private SMSBroadcastReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_full_screen_display_stock_information);

//        code = getIntent().getStringExtra("code");
        code = "000001k";
        code = code.toUpperCase();
        name = "平安银行";
        suspension = getIntent().getIntExtra("isSuspended", 1);

        if (suspension != 1) {
            Intent intent = new Intent(this, RefreshFiveSpeedService.class);
            PARAM.stockData.setStockID(code);
            startService(intent);
        }

        initView();

        webView = (WebView) findViewById(R.id.webView_full_screen);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);

        changeBtnBG(btnStockTimeShare, btnStockFiveDay, btnStockDay, btnStockWeek, btnStockMonth);
        webViewUrl = "http://gooke.api.3g.cnfol.com/quote/Jbai.html" + "?code=" + code + "&w=" + width + "&h=" + timeHight + "&limit=1";
        // PARAM.print("FullScreenDisplayStockInformationActivity", webViewUrl);
        LogUtil.i("分时:" + webViewUrl);
        // PARAM.print(TAG, "webViewUrl==>>" + webViewUrl);
        webView.loadUrl(webViewUrl);

        netForTimelyQuote();
        netForTradedFiveSpeed();
    }

    public void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter(PARAM.REFRESH_FIVE_SPEED);
        receiver = new SMSBroadcastReceiver();
        registerReceiver(receiver, filter);
        LogUtil.i("[[[注册行情图_通知更新买卖的广播]]]");
    }

    public void onPause() {
        super.onPause();
        if (receiver != null) {
            LogUtil.i("[[[销毁行情图_通知更新买卖的广播]]]");
            try {
                unregisterReceiver(receiver);
            } catch (Exception e) {
                LogUtil.i(e.getMessage());
            }
        }
    }

    private class SMSBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            stockData.setBuyData(PARAM.stockData.getBuyData());
            stockData.setSellData(PARAM.stockData.getSellData());
            setTradedFiveSpeedData();
            LogUtil.i("[接收到_更新行情图_广播]");
        }
    }

    @Override
    protected void onDestroy() {
        Intent intent = new Intent(this, RefreshFiveSpeedService.class);
        stopService(intent);
        super.onDestroy();
    }

    private void initView() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        screenWidth = dm.widthPixels;
        screenHeight = dm.heightPixels;

        width = 1000.0;
        if (screenHeight > 700) {// 1920*1080 1280*720
            timeHight = 570.0;
            fiveDayHight = 440.0;
            kHeight = 440.0;
        } else {// 854*480分辨率
            timeHight = 590.0;
            fiveDayHight = 440.0;
            kHeight = 350.0;
        }

        // 1196-768
        if (screenWidth == 1196 && screenHeight == 768) {
            timeHight = 670.0;
            fiveDayHight = 500.0;
            kHeight = 525.0;
        }

        // 960*540
        if (screenWidth == 960 && screenHeight == 540) {
            timeHight = 580.0;
            kHeight = 440.0;
        }

        // 800 * 480
        if (screenWidth == 800 && screenHeight == 480) {
            timeHight = 620.0;
            kHeight = 450.0;
        }

        // 顶部相关
        btnStockTimeShare = (Button) findViewById(R.id.btn_stock_timeshare);
        btnStockFiveDay = (Button) findViewById(R.id.btn_stock_fiveday);
        btnStockDay = (Button) findViewById(R.id.btn_stock_day);
        btnStockWeek = (Button) findViewById(R.id.btn_stock_week);
        btnStockMonth = (Button) findViewById(R.id.btn_stock_month);

        btnStockTimeShare.setOnClickListener(this);
        btnStockFiveDay.setOnClickListener(this);
        btnStockDay.setOnClickListener(this);
        btnStockWeek.setOnClickListener(this);
        btnStockMonth.setOnClickListener(this);

        textStockName = (TextView) findViewById(R.id.text_stock_name);
        textStockName.setText(name);
        textStockCount = (TextView) findViewById(R.id.text_stock_count);
        textStockChange = (TextView) findViewById(R.id.text_stock_change_count_percent);
        textStockTurnOver = (TextView) findViewById(R.id.text_stock_turnover_count);
        textStockVolume = (TextView) findViewById(R.id.text_stock_volume_count);
        textStockDealCount = (TextView) findViewById(R.id.text_stock_deal_count);
        // 分时相关
        textStockCurrentPrice = (TextView) findViewById(R.id.text_stock_timeshare_current_price);
        textStockTimeShareSellFifthFirstData = (TextView) findViewById(R.id.text_stock_timeshare_sell_fifth_first_data);
        textStockTimeShareSellFifthSecondData = (TextView) findViewById(R.id.text_stock_timeshare_sell_fifth_second_data);
        textStockTimeShareSellFourthFirstData = (TextView) findViewById(R.id.text_stock_timeshare_sell_fourth_first_data);
        textStockTimeShareSellFourthSecondData = (TextView) findViewById(R.id.text_stock_timeshare_sell_fourth_second_data);
        textStockTimeShareSellThirdFirstData = (TextView) findViewById(R.id.text_stock_timeshare_sell_third_first_data);
        textStockTimeShareSellThirdSecondData = (TextView) findViewById(R.id.text_stock_timeshare_sell_third_second_data);
        textStockTimeShareSellSecondFirstData = (TextView) findViewById(R.id.text_stock_timeshare_sell_second_first_data);
        textStockTimeShareSellSecondSecondData = (TextView) findViewById(R.id.text_stock_timeshare_sell_second_second_data);
        textStockTimeShareSellFirstFirstData = (TextView) findViewById(R.id.text_stock_timeshare_sell_first_first_data);
        textStockTimeShareSellFirstSecondData = (TextView) findViewById(R.id.text_stock_timeshare_sell_first_second_data);
        textStockTimeShareBuyFifthFirstData = (TextView) findViewById(R.id.text_stock_timeshare_buy_fifth_first_data);
        textStockTimeShareBuyFifthSecondData = (TextView) findViewById(R.id.text_stock_timeshare_buy_fifth_second_data);
        textStockTimeShareBuyFourthFirstData = (TextView) findViewById(R.id.text_stock_timeshare_buy_fourth_first_data);
        textStockTimeShareBuyFourthSecondData = (TextView) findViewById(R.id.text_stock_timeshare_buy_fourth_second_data);
        textStockTimeShareBuyThirdFirstData = (TextView) findViewById(R.id.text_stock_timeshare_buy_third_first_data);
        textStockTimeShareBuyThirdSecondData = (TextView) findViewById(R.id.text_stock_timeshare_buy_third_second_data);
        textStockTimeShareBuySecondFirstData = (TextView) findViewById(R.id.text_stock_timeshare_buy_second_first_data);
        textStockTimeShareBuySecondSecondData = (TextView) findViewById(R.id.text_stock_timeshare_buy_second_second_data);
        textStockTimeShareBuyFirstFirstData = (TextView) findViewById(R.id.text_stock_timeshare_buy_first_first_data);
        textStockTimeShareBuyFirstSecondData = (TextView) findViewById(R.id.text_stock_timeshare_buy_first_second_data);
        timeShare = (LinearLayout) findViewById(R.id.timeshare);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_stock_timeshare:
                timeShare.setVisibility(View.VISIBLE);
                changeBtnBG(btnStockTimeShare, btnStockFiveDay, btnStockDay, btnStockWeek, btnStockMonth);
                webViewUrl = PARAM.URL_STOCK_JBAI + "?code=" + code + "&w=" + width + "&h=" + timeHight + "&limit=1";
                LogUtil.i("分时:" + webViewUrl);
                webView.loadUrl(webViewUrl);
                break;
            case R.id.btn_stock_fiveday:
                timeShare.setVisibility(View.GONE);
                changeBtnBG(btnStockFiveDay, btnStockTimeShare, btnStockDay, btnStockWeek, btnStockMonth);
                webViewUrl = PARAM.URL_STOCK_JBAI_FIVE + "?code=" + code + "&w=" + width + "&h=" + fiveDayHight + "&limit=5";
                LogUtil.i("五日:" + webViewUrl);
                webView.loadUrl(webViewUrl);
                break;
            case R.id.btn_stock_day:
                timeShare.setVisibility(View.GONE);
                changeBtnBG(btnStockDay, btnStockTimeShare, btnStockFiveDay, btnStockWeek, btnStockMonth);
                webViewUrl = PARAM.URL_STOCK_KBAI + "?code=" + code + "&w=" + width + "&h=" + kHeight + "&limit=200&type=1";
                LogUtil.i("日K:" + webViewUrl);
                webView.loadUrl(webViewUrl);
                break;
            case R.id.btn_stock_week:
                timeShare.setVisibility(View.GONE);
                changeBtnBG(btnStockWeek, btnStockTimeShare, btnStockFiveDay, btnStockDay, btnStockMonth);
                webViewUrl = PARAM.URL_STOCK_KBAI + "?code=" + code + "&w=" + width + "&h=" + kHeight + "&limit=400&type=2";
                LogUtil.i("周K:" + webViewUrl);
                webView.loadUrl(webViewUrl);
                break;
            case R.id.btn_stock_month:
                timeShare.setVisibility(View.GONE);
                changeBtnBG(btnStockMonth, btnStockTimeShare, btnStockFiveDay, btnStockDay, btnStockWeek);
                webViewUrl = PARAM.URL_STOCK_KBAI + "?code=" + code + "&w=" + width + "&h=" + kHeight + "&limit=2000&type=3";
                LogUtil.i("月K:" + webViewUrl);
                webView.loadUrl(webViewUrl);
                break;
        }
    }

    private void netForTimelyQuote() {

        RequestParams params = new RequestParams(PARAM.URL_TIMELY_QUOTE);
        params.addBodyParameter("action", "stockPrice");
        params.addBodyParameter("stID", "00001");
        params.addBodyParameter("flag", "p");
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONArray jsonArray = new JSONArray(result.toString());
                    int length = jsonArray.length();
                    for (int i = 0; i < length; i++) {
                        JSONArray jsonArray2 = jsonArray.getJSONArray(i);
                        String stockCode = (String) jsonArray2.get(0);
                        String stockName = (String) jsonArray2.get(1);
                        String stockCurrentPrice = String.valueOf(jsonArray2.get(2));
                        String stockLastClosePrice = String.valueOf(jsonArray2.get(3));
                        String stockChangeAmount = String.valueOf(jsonArray2.get(4));
                        String stockChangePrice = String.valueOf(jsonArray2.get(5) + "%");
                        String stockChange = stockChangePrice + "(" + stockChangeAmount + ")";
                        String stockTurnOver = String.valueOf(jsonArray2.get(6) + "%");
                        String stockVolume = String.valueOf(jsonArray2.get(7));
                        String stockTransactionAmount = String.valueOf(jsonArray2.get(8));
                        stockData.setStockID(stockCode);
                        stockData.setStockName(stockName);
                        stockData.setStockCurrentPrice(stockCurrentPrice);
                        stockData.setLastClosePrice(stockLastClosePrice);
                        stockData.setStockChange(stockChange);
                        stockData.setStockTurnOver(stockTurnOver);
                        stockData.setStockVolume(stockVolume);
                        stockData.setStockTransactionAmount(stockTransactionAmount);
                        setTimelyQuoteData();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtil.e(ex.toString());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    private void netForTradedFiveSpeed() {

        RequestParams params = new RequestParams(PARAM.URL_TIMELY_QUOTE);
        params.addBodyParameter("action", "stockFive");
        params.addBodyParameter("stID", "00001");
        params.addBodyParameter("flag", "p");
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONArray jsonArray = new JSONArray(result.toString());
                    int length = jsonArray.length();
                    Map<String, String> buyData = new LinkedHashMap<String, String>();
                    Map<String, String> sellData = new LinkedHashMap<String, String>();
                    for (int i = 0; i < length; i++) {
                        JSONArray data = jsonArray.getJSONArray(i);
                        String buyPrice = String.valueOf(data.get(0));
                        String sellPrice = String.valueOf(data.get(1));
                        String buyAmount = String.valueOf(data.get(2));
                        String sellAmount = String.valueOf(data.get(3));
                        buyData.put(buyPrice + "-" + i, buyAmount);
                        sellData.put(sellPrice + "-" + i, sellAmount);
                    }
                    stockData.setBuyData(buyData);
                    stockData.setSellData(sellData);
                    setTradedFiveSpeedData();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtil.e(ex.toString());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    private void setTradedFiveSpeedData() {
        if (suspension == 1) {
            textStockTimeShareBuyFirstFirstData.setText("0.00");
            textStockTimeShareBuyFirstSecondData.setText("0");
            textStockTimeShareBuySecondFirstData.setText("0.00");
            textStockTimeShareBuySecondSecondData.setText("0");
            textStockTimeShareBuyThirdFirstData.setText("0.00");
            textStockTimeShareBuyThirdSecondData.setText("0");
            textStockTimeShareBuyFourthFirstData.setText("0.00");
            textStockTimeShareBuyFourthSecondData.setText("0");
            textStockTimeShareBuyFifthFirstData.setText("0.00");
            textStockTimeShareBuyFifthSecondData.setText("0");
            textStockTimeShareSellFirstFirstData.setText("0.00");
            textStockTimeShareSellFirstSecondData.setText("0");
            textStockTimeShareSellSecondFirstData.setText("0.00");
            textStockTimeShareSellSecondSecondData.setText("0");
            textStockTimeShareSellThirdFirstData.setText("0.00");
            textStockTimeShareSellThirdSecondData.setText("0");
            textStockTimeShareSellFourthFirstData.setText("0.00");
            textStockTimeShareSellFourthSecondData.setText("0");
            textStockTimeShareSellFifthFirstData.setText("0.00");
            textStockTimeShareSellFifthSecondData.setText("0");
        } else {
            Iterator<Entry<String, String>> buyIt = stockData.getBuyData().entrySet().iterator();
            int i = 0;
            while (buyIt.hasNext()) {
                Entry<String, String> entry = buyIt.next();
                String buyPriceDataStr = entry.getKey().toString();
                String buyAmountData = entry.getValue().toString();
                String buyPriceData = buyPriceDataStr.replaceAll("-" + i, "");
                double stockBuyPrice = Double.parseDouble(buyPriceData);
                if (i == 0) {
                    textStockTimeShareBuyFirstFirstData.setText(buyPriceData);
                    if (stockBuyPrice > stockLastClosePrice) {
                        textStockTimeShareBuyFirstFirstData.setTextColor(Color.parseColor("#FF07C973"));
                    } else if (stockBuyPrice == stockLastClosePrice) {
                        textStockTimeShareBuyFirstFirstData.setTextColor(Color.parseColor("#FF333333"));
                    } else {
                        textStockTimeShareBuyFirstFirstData.setTextColor(Color.parseColor("#FFFF4F45"));
                    }
                    textStockTimeShareBuyFirstSecondData.setText(buyAmountData);
                } else if (i == 1) {
                    textStockTimeShareBuySecondFirstData.setText(buyPriceData);
                    if (stockBuyPrice > stockLastClosePrice) {
                        textStockTimeShareBuySecondFirstData.setTextColor(Color.parseColor("#FF07C973"));
                    } else if (stockBuyPrice == stockLastClosePrice) {
                        textStockTimeShareBuySecondFirstData.setTextColor(Color.parseColor("#FF333333"));
                    } else {
                        textStockTimeShareBuySecondFirstData.setTextColor(Color.parseColor("#FFFF4F45"));
                    }
                    textStockTimeShareBuySecondSecondData.setText(buyAmountData);
                } else if (i == 2) {
                    textStockTimeShareBuyThirdFirstData.setText(buyPriceData);
                    if (stockBuyPrice > stockLastClosePrice) {
                        textStockTimeShareBuyThirdFirstData.setTextColor(Color.parseColor("#FF07C973"));
                    } else if (stockBuyPrice == stockLastClosePrice) {
                        textStockTimeShareBuyThirdFirstData.setTextColor(Color.parseColor("#FF333333"));
                    } else {
                        textStockTimeShareBuyThirdFirstData.setTextColor(Color.parseColor("#FFFF4F45"));
                    }
                    textStockTimeShareBuyThirdSecondData.setText(buyAmountData);
                } else if (i == 3) {
                    textStockTimeShareBuyFourthFirstData.setText(buyPriceData);
                    if (stockBuyPrice > stockLastClosePrice) {
                        textStockTimeShareBuyFourthFirstData.setTextColor(Color.parseColor("#FF07C973"));
                    } else if (stockBuyPrice == stockLastClosePrice) {
                        textStockTimeShareBuyFourthFirstData.setTextColor(Color.parseColor("#FF333333"));
                    } else {
                        textStockTimeShareBuyFourthFirstData.setTextColor(Color.parseColor("#FFFF4F45"));
                    }
                    textStockTimeShareBuyFourthSecondData.setText(buyAmountData);
                } else if (i == 4) {
                    textStockTimeShareBuyFifthFirstData.setText(buyPriceData);
                    if (stockBuyPrice > stockLastClosePrice) {
                        textStockTimeShareBuyFifthFirstData.setTextColor(Color.parseColor("#FF07C973"));
                    } else if (stockBuyPrice == stockLastClosePrice) {
                        textStockTimeShareBuyFifthFirstData.setTextColor(Color.parseColor("#FF333333"));
                    } else {
                        textStockTimeShareBuyFifthFirstData.setTextColor(Color.parseColor("#FFFF4F45"));
                    }
                    textStockTimeShareBuyFifthSecondData.setText(buyAmountData);
                }
                i++;
            }
            Iterator<Entry<String, String>> sellIt = stockData.getSellData().entrySet().iterator();
            int j = 0;
            while (sellIt.hasNext()) {
                Entry<String, String> entry = sellIt.next();
                String sellPriceDataStr = entry.getKey().toString();
                String sellPriceData = sellPriceDataStr.replaceAll("-" + j, "");
                String sellAmountData = entry.getValue().toString();
                double stockSellPrice = Double.parseDouble(sellPriceData);
                if (j == 0) {
                    textStockTimeShareSellFirstFirstData.setText(sellPriceData);
                    if (stockSellPrice > stockLastClosePrice) {
                        textStockTimeShareSellFirstFirstData.setTextColor(Color.parseColor("#FF07C973"));
                    } else if (stockSellPrice == stockLastClosePrice) {
                        textStockTimeShareSellFirstFirstData.setTextColor(Color.parseColor("#FF333333"));
                    } else {
                        textStockTimeShareSellFirstFirstData.setTextColor(Color.parseColor("#FFFF4F45"));
                    }
                    textStockTimeShareSellFirstSecondData.setText(sellAmountData);
                } else if (j == 1) {
                    textStockTimeShareSellSecondFirstData.setText(sellPriceData);
                    if (stockSellPrice > stockLastClosePrice) {
                        textStockTimeShareSellSecondFirstData.setTextColor(Color.parseColor("#FF07C973"));
                    } else if (stockSellPrice == stockLastClosePrice) {
                        textStockTimeShareSellSecondFirstData.setTextColor(Color.parseColor("#FF333333"));
                    } else {
                        textStockTimeShareSellSecondFirstData.setTextColor(Color.parseColor("#FFFF4F45"));
                    }
                    textStockTimeShareSellSecondSecondData.setText(sellAmountData);
                } else if (j == 2) {
                    textStockTimeShareSellThirdFirstData.setText(sellPriceData);
                    if (stockSellPrice > stockLastClosePrice) {
                        textStockTimeShareSellThirdFirstData.setTextColor(Color.parseColor("#FF07C973"));
                    } else if (stockSellPrice == stockLastClosePrice) {
                        textStockTimeShareSellThirdFirstData.setTextColor(Color.parseColor("#FF333333"));
                    } else {
                        textStockTimeShareSellThirdFirstData.setTextColor(Color.parseColor("#FFFF4F45"));
                    }
                    textStockTimeShareSellThirdSecondData.setText(sellAmountData);
                } else if (j == 3) {
                    textStockTimeShareSellFourthFirstData.setText(sellPriceData);
                    if (stockSellPrice > stockLastClosePrice) {
                        textStockTimeShareSellFourthFirstData.setTextColor(Color.parseColor("#FF07C973"));
                    } else if (stockSellPrice == stockLastClosePrice) {
                        textStockTimeShareSellFourthFirstData.setTextColor(Color.parseColor("#FF333333"));
                    } else {
                        textStockTimeShareSellFourthFirstData.setTextColor(Color.parseColor("#FFFF4F45"));
                    }
                    textStockTimeShareSellFourthSecondData.setText(sellAmountData);
                } else if (j == 4) {
                    textStockTimeShareSellFifthFirstData.setText(sellPriceData);
                    if (stockSellPrice > stockLastClosePrice) {
                        textStockTimeShareSellFifthFirstData.setTextColor(Color.parseColor("#FF07C973"));
                    } else if (stockSellPrice == stockLastClosePrice) {
                        textStockTimeShareSellFifthFirstData.setTextColor(Color.parseColor("#FF333333"));
                    } else {
                        textStockTimeShareSellFifthFirstData.setTextColor(Color.parseColor("#FFFF4F45"));
                    }
                    textStockTimeShareSellFifthSecondData.setText(sellAmountData);
                }
                j++;
            }
        }
    }

    private void setTimelyQuoteData() {
        textStockName.setText(stockData.getStockName());
        if (suspension == 1) {
            textStockCount.setText("0.00");
            textStockChange.setText("0.00%(0.00)");
            textStockTurnOver.setText("0.00%");
            textStockVolume.setText("0.00万手");
            textStockDealCount.setText("0.00亿元");
            textStockCurrentPrice.setText("当前价格(元):0.00");
        } else {
            stockLastClosePrice = Double.parseDouble(stockData.getLastClosePrice());
            textStockCount.setText(stockData.getStockCurrentPrice());
            textStockChange.setText(stockData.getStockChange());
            textStockTurnOver.setText(stockData.getStockTurnOver());
            textStockVolume.setText(stockData.getStockVolume());
            textStockDealCount.setText(stockData.getStockTransactionAmount());
            textStockCurrentPrice.setText("当前价格(元):" + stockData.getStockCurrentPrice());
        }
    }

    private void changeBtnBG(Button btn0, Button btn1, Button btn2, Button btn3, Button btn4) {
        btn0.setBackgroundResource(R.mipmap.invite_down);
        btn1.setBackgroundResource(R.mipmap.invite_up);
        btn2.setBackgroundResource(R.mipmap.invite_up);
        btn3.setBackgroundResource(R.mipmap.invite_up);
        btn4.setBackgroundResource(R.mipmap.invite_up);
    }
}
