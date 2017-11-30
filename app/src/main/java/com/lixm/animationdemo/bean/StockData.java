package com.lixm.animationdemo.bean;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

public class StockData implements Serializable{

	private String stockID;// 个股ID
	private String stockName;// 个股名称
	private String stockPrice;// 个股价格
	private String stockType;// 个股类型
	private String stockChange;// 个股涨跌
	private String stockTurnOver;// 个股换手率
	private String stockVolume;// 个股成交量
	private String stockTransactionAmount;// 个股成交额
	private String stockCurrentPrice;// 个股当前价格
	private String lastClosePrice;// 个股昨收价
	private String stockQuotesFigure;// 个股行情图
	private Map<String, String> sellData = new LinkedHashMap<String, String>();// 个股委卖数据
	private Map<String, String> buyData = new LinkedHashMap<String, String>();// 个股委买数据

	public String getStockID() {
		return stockID;
	}

	public void setStockID(String stockID) {
		this.stockID = stockID;
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public String getStockPrice() {
		return stockPrice;
	}

	public void setStockPrice(String stockPrice) {
		this.stockPrice = stockPrice;
	}

	public String getStockType() {
		return stockType;
	}

	public void setStockType(String stockType) {
		this.stockType = stockType;
	}

	public String getStockChange() {
		return stockChange;
	}

	public void setStockChange(String stockChange) {
		this.stockChange = stockChange;
	}

	public String getStockTurnOver() {
		return stockTurnOver;
	}

	public void setStockTurnOver(String stockTurnOver) {
		this.stockTurnOver = stockTurnOver;
	}

	public String getStockVolume() {
		return stockVolume + "万手";
	}

	public void setStockVolume(String stockVolume) {
		this.stockVolume = stockVolume;
	}

	public String getStockTransactionAmount() {
		return stockTransactionAmount + "亿元";
	}

	public void setStockTransactionAmount(String stockTransactionAmount) {
		this.stockTransactionAmount = stockTransactionAmount;
	}

	public String getStockCurrentPrice() {
		return stockCurrentPrice;
	}

	public void setStockCurrentPrice(String stockCurrentPrice) {
		this.stockCurrentPrice = stockCurrentPrice;
	}

	public String getLastClosePrice() {
		return lastClosePrice;
	}

	public void setLastClosePrice(String lastClosePrice) {
		this.lastClosePrice = lastClosePrice;
	}

	public String getStockQuotesFigure() {
		return stockQuotesFigure;
	}

	public void setStockQuotesFigure(String stockQuotesFigure) {
		this.stockQuotesFigure = stockQuotesFigure;
	}

	public Map<String, String> getSellData() {
		return sellData;
	}

	public void setSellData(Map<String, String> sellData) {
		this.sellData = sellData;
	}

	public Map<String, String> getBuyData() {
		return buyData;
	}

	public void setBuyData(Map<String, String> buyData) {
		this.buyData = buyData;
	}

}
