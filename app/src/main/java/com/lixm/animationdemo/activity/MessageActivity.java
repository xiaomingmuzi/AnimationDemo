package com.lixm.animationdemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class MessageActivity extends AppCompatActivity {

    private String tag = "MessageActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //基本用户配置
//        TIMUserConfig userConfig = new TIMUserConfig()
//                //设置群组资料拉取字段
//                .setGroupSettings(initGroupSettings())
//                //设置资料关系链拉取字段
//                .setFriendshipSettings(initFriendshipSettings())
//                //设置用户状态变更事件监听器
//                .setUserStatusListener(new TIMUserStatusListener() {
//                    @Override
//                    public void onForceOffline() {
//                        //被其他终端踢下线
//                        Log.i(tag, "onForceOffline");
//                    }
//
//                    @Override
//                    public void onUserSigExpired() {
//                        //用户签名过期了，需要刷新userSig重新登录SDK
//                        Log.i(tag, "onUserSigExpired");
//                    }
//                })
//                //设置连接状态事件监听器
//                .setConnectionListener(new TIMConnListener() {
//                    @Override
//                    public void onConnected() {
//                        Log.i(tag, "onConnected");
//                    }
//
//                    @Override
//                    public void onDisconnected(int code, String desc) {
//                        Log.i(tag, "onDisconnected");
//                    }
//
//                    @Override
//                    public void onWifiNeedAuth(String name) {
//                        Log.i(tag, "onWifiNeedAuth");
//                    }
//                })
//                //设置群组事件监听器
//                .setGroupEventListener(new TIMGroupEventListener() {
//                    @Override
//                    public void onGroupTipsEvent(TIMGroupTipsElem elem) {
//                        Log.i(tag, "onGroupTipsEvent, type: " + elem.getTipsType());
//                    }
//                })
//                //设置会话刷新监听器
//                .setRefreshListener(new TIMRefreshListener() {
//                    @Override
//                    public void onRefresh() {
//                        Log.i(tag, "onRefresh");
//                    }
//
//                    @Override
//                    public void onRefreshConversation(List<TIMConversation> conversations) {
//                        Log.i(tag, "onRefreshConversation, conversation size: " + conversations.size());
//                    }
//                });

////消息扩展用户配置
//        userConfig = new TIMUserConfigMsgExt(userConfig)
//                //禁用消息存储
//                .enableStorage(false)
//                //开启消息已读回执
//                .enableReadReceipt(true);
//
////资料关系链扩展用户配置
//        userConfig = new TIMUserConfigSnsExt(userConfig)
//                //开启资料关系链本地存储
//                .enableFriendshipStorage(true)
//                //设置关系链变更事件监听器
//                .setFriendshipProxyListener(new TIMFriendshipProxyListener() {
//                    @Override
//                    public void OnAddFriends(List<TIMUserProfile> users) {
//                        Log.i(tag, "OnAddFriends");
//                    }
//
//                    @Override
//                    public void OnDelFriends(List<String> identifiers) {
//                        Log.i(tag, "OnDelFriends");
//                    }
//
//                    @Override
//                    public void OnFriendProfileUpdate(List<TIMUserProfile> profiles) {
//                        Log.i(tag, "OnFriendProfileUpdate");
//                    }
//
//                    @Override
//                    public void OnAddFriendReqs(List<TIMSNSChangeInfo> reqs) {
//                        Log.i(tag, "OnAddFriendReqs");
//                    }
//
//                });
//
////群组管理扩展用户配置
//        userConfig = new TIMUserConfigGroupExt(userConfig)
//                //开启群组资料本地存储
//                .enableGroupStorage(true)
//                //设置群组资料变更事件监听器
//                .setGroupAssistantListener(new TIMGroupAssistantListener() {
//                    @Override
//                    public void onMemberJoin(String groupId, List<TIMGroupMemberInfo> memberInfos) {
//                        Log.i(tag, "onMemberJoin");
//                    }
//
//                    @Override
//                    public void onMemberQuit(String groupId, List<String> members) {
//                        Log.i(tag, "onMemberQuit");
//                    }
//
//                    @Override
//                    public void onMemberUpdate(String groupId, List<TIMGroupMemberInfo> memberInfos) {
//                        Log.i(tag, "onMemberUpdate");
//                    }
//
//                    @Override
//                    public void onGroupAdd(TIMGroupCacheInfo groupCacheInfo) {
//                        Log.i(tag, "onGroupAdd");
//                    }
//
//                    @Override
//                    public void onGroupDelete(String groupId) {
//                        Log.i(tag, "onGroupDelete");
//                    }
//
//                    @Override
//                    public void onGroupUpdate(TIMGroupCacheInfo groupCacheInfo) {
//                        Log.i(tag, "onGroupUpdate");
//                    }
//                });
//
////将用户配置与通讯管理器进行绑定
//        TIMManager.getInstance().setUserConfig(userConfig);
//
//
//        //设置消息监听器，收到新消息时，通过此监听器回调
//        TIMManager.getInstance().addMessageListener(new TIMMessageListener() {
//            //消息监听器
//            @Override
//            public boolean onNewMessages(List<TIMMessage> list) {//收到新消息
//                //消息的内容解析请参考消息收发文档中的消息解析说明
//                return true; //返回true将终止回调链，不再调用下一个新消息监听器
//            }
//
//        });
//
//
//        // identifier为用户名，userSig 为用户登录凭证
//        TIMManager.getInstance().login("lixm1", "", new TIMCallBack() {
//            @Override
//            public void onError(int code, String desc) {
//                //错误码code和错误描述desc，可用于定位请求失败原因
//                //错误码code列表请参见错误码表
//                Log.d(tag, "login failed. code: " + code + " errmsg: " + desc);
//            }
//
//            @Override
//            public void onSuccess() {
//                Log.d(tag, "login succ");
//            }
//        });
    }

}
