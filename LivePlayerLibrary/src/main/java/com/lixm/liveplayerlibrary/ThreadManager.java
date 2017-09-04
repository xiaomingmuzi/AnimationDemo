package com.lixm.liveplayerlibrary;

import android.os.Handler;
import android.os.Message;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Lixm
 * @date 2017/8/28
 * @detail
 */

public class ThreadManager {
   private static class ThreadManagerHolder{
       private static final ThreadManager INSTANCE=new ThreadManager();
   }

    private ThreadManager() {
    }

    public static final ThreadManager getInstance(){
        return ThreadManagerHolder.INSTANCE;
    }

    private Map<String, Handler> mMap = new HashMap<String, Handler>();

    /**
     * 根据键名添加handler到消息池
     *
     * @param className
     * @param handler
     */
    public static void addHandler(String className, Handler handler) {
        if (getInstance().mMap.get(className) == null) {
            getInstance().mMap.put(className, handler);
        }
    }

    /**
     * 根据类名将对应的handler移除消息池
     *
     * @param className
     */
    public static synchronized void removeHandler(String className) {
        getInstance().mMap.remove(className);
    }

    /**
     * 根据指定Handler发送消息
     *
     * @param handler
     * @param msgId
     * @param msgObj
     */
    public static void sendMessage(Handler handler, int msgId, Object msgObj) {
        Message message = handler.obtainMessage();
        message.what = msgId;
        message.obj = msgObj;
        handler.sendMessage(message);
    }

    /**
     * 全局发送消息--（只要存活的Act有抓取指定的msg就会执行接收消息)
     *
     * @param msgId
     * @param msgObj
     */
    public synchronized static void sendMessage(int msgId, Object msgObj) {
        synchronized (getInstance().mMap) {
            for (Map.Entry<String, Handler> entry : getInstance().mMap.entrySet()) {
                Handler handler = entry.getValue();
                sendMessage(handler, msgId, msgObj);
            }
        }
    }

    /**
     * 发送空消息
     *
     * @param msgId
     */
    public static void sendMessage(int msgId) {
        sendMessage(msgId, null);
    }

    /**
     * 根据指定类名发送消息
     *
     * @param className
     *            类名
     * @param msgId
     * @param msgObj
     */
    public static void sendMessage(String className, int msgId, Object msgObj) {
        Handler handler = getInstance().mMap.get(className);
        if (handler != null) {
            sendMessage(handler, msgId, msgObj);
        }
    }

    /**
     * 根据类名获取对应activity的Handler
     * @param cls
     * @return
     */
    public static Handler getHandler(@SuppressWarnings("rawtypes") Class cls) {
        return getInstance().mMap.get(cls.getName());
    }

    /**
     * 根据类名发送消息
     *
     * @param cls
     * @param msgId
     * @param msgObj
     */
    public static void sendMessage(@SuppressWarnings("rawtypes") Class cls,
                                   int msgId, Object msgObj) {
        sendMessage(cls.getName(), msgId, msgObj);
    }

    /**
     * 根据类名发送空消息
     *
     * @param cls
     * @param msgId
     */
    public static void sendMessage(@SuppressWarnings("rawtypes") Class cls,
                                   int msgId) {
        sendMessage(cls.getName(), msgId, null);
    }
}
