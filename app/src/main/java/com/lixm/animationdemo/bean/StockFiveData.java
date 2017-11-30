package com.lixm.animationdemo.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Lixm
 * @date 2017/11/29
 * @detail
 */

public class StockFiveData  implements Serializable{

    public String Code;
    public String Msg;
    public ArrayList<FiveData> List;

    class FiveData{
        public String SeqNo;
        public String BidPrice;
        public String AskPrice;
        public String BidVol;
        public String AskVol;
    }



}
