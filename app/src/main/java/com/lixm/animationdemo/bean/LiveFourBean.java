package com.lixm.animationdemo.bean;

/**
 * @author Lixm
 * @date 2017/8/18
 * @detail
 */

public class LiveFourBean {
    private int price;
    private String date;

    public LiveFourBean(int price, String date) {
        this.price = price;
        this.date = date;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
