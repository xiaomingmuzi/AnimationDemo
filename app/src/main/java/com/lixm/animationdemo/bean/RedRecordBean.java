package com.lixm.animationdemo.bean;

import java.io.Serializable;

/**
 * @author Lixm
 * @date 2017/7/13
 * @detail 红包记录Bean
 */

public class RedRecordBean implements Serializable {
    private String SysRedID;//红包id
    private String GetTime;//收红包时间
    private String RedBonus;//红包金额
    private String SendUser;//发红包人
    private String RedPacketType;//红包类型 1直播天降 0所有

    private String RedNumber;//红包数量
    private String StockRedPck;//红包剩余未领数量
    private String DataTime;//发红包时间

    public RedRecordBean() {
    }

    public String getSysRedID() {
        return SysRedID;
    }

    public void setSysRedID(String sysRedID) {
        SysRedID = sysRedID;
    }

    public String getGetTime() {
        return GetTime;
    }

    public void setGetTime(String getTime) {
        GetTime = getTime;
    }

    public String getRedBonus() {
        return RedBonus;
    }

    public void setRedBonus(String redBonus) {
        RedBonus = redBonus;
    }

    public String getSendUser() {
        return SendUser;
    }

    public void setSendUser(String sendUser) {
        SendUser = sendUser;
    }

    public String getRedPacketType() {
        return RedPacketType;
    }

    public void setRedPacketType(String redPacketType) {
        RedPacketType = redPacketType;
    }

    public String getRedNumber() {
        return RedNumber;
    }

    public void setRedNumber(String redNumber) {
        RedNumber = redNumber;
    }

    public String getStockRedPck() {
        return StockRedPck;
    }

    public void setStockRedPck(String stockRedPck) {
        StockRedPck = stockRedPck;
    }

    public String getDataTime() {
        return DataTime;
    }

    public void setDataTime(String dataTime) {
        DataTime = dataTime;
    }
}
