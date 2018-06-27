package com.lixm.animationdemo.bean;

import java.net.Socket;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * Describe:
 * <p>
 * Author: Lixm
 * Date: 2018/6/27
 */
public class SocketManager {
    private Map m=new WeakHashMap();
    public void setUser(Socket s,User u){
        m.put(s,u);
    }
    public User getUser(Socket s){
        return (User) m.get(s);
    }
}
