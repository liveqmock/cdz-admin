package com.ga.cdz.util;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author:luqi
 * @description: 推送工具类
 * @date:2018/9/10_14:51
 */
@Slf4j
@Component("mPushUtil")
public class MPushUtil {

    @Value("${jpush.master_secret}")
    private String MASTER_SECRET;
    @Value("${jpush.app_key}")
    private String APP_KEY;

    private void exec(JPushClient jpushClient, PushPayload pushPayload) {
        try {
            PushResult result = jpushClient.sendPush(pushPayload);
            log.info("Got result - " + result);
        } catch (APIConnectionException e) {
            log.error("Connection error, should retry later" + e);
        } catch (APIRequestException e) {
            log.error("Should review the error, and fix the request" + e);
            log.info("HTTP Status: " + e.getStatus());
            log.info("Error Code: " + e.getErrorCode());
            log.info("Error Message: " + e.getErrorMessage());
        }
    }

    /**
     * release
     */
   /* public void sendRelease(String alias, String content) {
        JPushClient jpushClient = new JPushClient("23256f51561c48175b90d090", "10a2f38f78aab6c42a2be339", null, ClientConfig.getInstance());
        PushPayload payload = buildPushObject_all_alias_alert(alias, content);
        exec(jpushClient, payload);
    }*/

    /**
     * @author:luqi
     * @description: 推送给所有用户消息
     * @date:2018/9/10_16:04
     * @param: content
     * @return:
     */
    public void sendAll(String content) {
        JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY, null, ClientConfig.getInstance());
        PushPayload payload = buildPushObject_all_alert(content);
        exec(jpushClient, payload);
    }

    /**
     * @author:luqi
     * @description: 根据 alias 推送
     * @date:2018/9/10_15:36
     * @param: alias
     * @param: content
     * @return:
     */
    public void sendAlias(String alias, String content) {
        JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY, null, ClientConfig.getInstance());
        PushPayload payload = buildPushObject_all_alias_alert(alias, content);
        exec(jpushClient, payload);
    }

    /**
     * @author:luqi
     * @description: 根据 rid 推送
     * @date:2018/9/10_15:39
     * @param: rid
     * @param: content
     * @return:
     */
    public void sendRid(String rid, String content) {
        JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY, null, ClientConfig.getInstance());
        PushPayload payload = buildPushObject_all_rid_alert(rid, content);
        exec(jpushClient, payload);
    }

    /**
     * @author:luqi
     * @description: 发送自定义消息rid
     * @date:2018/9/10_15:40
     * @param: rid
     * @param: content
     * @param: message
     * @return:
     */
    public void sendRid(String rid, String content, Message message) {
        JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY, null, ClientConfig.getInstance());
        PushPayload payload = buildPushObject_all_rid_alert(rid, content, message);
        exec(jpushClient, payload);
    }

    /**
     * @author:luqi
     * @description: 发送自定义消息 rid
     * @date:2018/9/10_15:42
     * @param: alias
     * @param: content
     * @param: message
     * @return:
     */
    public void sendAlias(String alias, String content, Message message) {
        JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY, null, ClientConfig.getInstance());
        PushPayload payload = buildPushObject_all_alias_alert(alias, content, message);
        exec(jpushClient, payload);
    }

    /**
     * @author:luqi
     * @description: rid集合推送
     * @date:2018/9/10_15:43
     * @param: list
     * @param: content
     * @return:
     */
    public void sendRid(List<String> list, String content) {
        JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY, null, ClientConfig.getInstance());
        PushPayload payload = buildPushObject_all_list_rid_alert(list, content);
        exec(jpushClient, payload);
    }

    /**
     * @author:luqi
     * @description: 单tag发送
     * @date:2018/9/11_11:54
     * @param:
     * @return:
     */
    public void sendTag(String tag, String content) {
        JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY, null, ClientConfig.getInstance());
        PushPayload payload = buildPushObject_all_tag_alert(tag, content);
        exec(jpushClient, payload);
    }

    /**
     * @author:luqi
     * @description: 推送给所有用户
     * @date:2018/9/10_16:03
     * @param:
     * @return:
     */
    private PushPayload buildPushObject_all_alert(String content) {
        return PushPayload.newBuilder().setPlatform(Platform.all())
                .setAudience(Audience.all()).setNotification(Notification.alert(content))
                .build();
    }

    /**
     * @author:luqi
     * @description: 推送（安卓与苹果）平台，alias标记的用户
     * @date:2018/9/10_14:54
     * @param: alias
     * @param: content
     * @return: PushPayLoad
     */
    private PushPayload buildPushObject_all_alias_alert(String alias, String content) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.alias(alias))
                .setNotification(Notification.alert(content))
                .build();
    }


    /**
     * @author:luqi
     * @description: 推送（安卓与苹果）平台，alias标记的用户，自定义message
     * @date:2018/9/10_14:57
     * @param: alias
     * @param: content
     * @param: message
     * @return: PushPayLoad
     */
    private PushPayload buildPushObject_all_alias_alert(String alias, String content, Message message) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.alias(alias))
                .setMessage(message)
                .setNotification(Notification.alert(content))
                .build();
    }


    /**
     * @author:luqi
     * @description: 推送指定用户消息
     * @date:2018/9/10_14:58
     * @param: rid
     * @param: content
     * @return: PushPayLoad
     */
    private PushPayload buildPushObject_all_rid_alert(String rid, String content) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.registrationId(rid))
                .setNotification(Notification.alert(content))
                .build();
    }

    /**
     * @author:luqi
     * @description: 推送某个用户自定义信息
     * @date:2018/9/10_14:59
     * @param: rid
     * @param: content
     * @param: message
     * @return: PushPayLoad
     */
    private PushPayload buildPushObject_all_rid_alert(String rid, String content, Message message) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.registrationId(rid))
                .setMessage(message)
                .setNotification(Notification.alert(content))
                .build();
    }

    /**
     * @author:luqi
     * @description: 单tag 发送消息
     * @date:2018/9/11_11:53
     * @param:
     * @return:
     */
    private PushPayload buildPushObject_all_tag_alert(String tag, String content) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.tag(tag))
                .setNotification(Notification.alert(content))
                .build();
    }


    /**
     * @author:luqi
     * @description: 推送给多用户信息
     * @date:2018/9/10_15:00
     * @param: 用户rid集合
     * @param: content
     * @return: PushPayLoad
     */
    private PushPayload buildPushObject_all_list_rid_alert(List<String> list, String content) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.registrationId(list))
                .setNotification(Notification.alert(content))
                .build();
    }

    /**
     * @author:luqi
     * @description: 推送给多用户自定义信息
     * @date:2018/9/10_15:01
     * @param: 用户rid集合
     * @param: content
     * @param: message
     * @return:
     */
    private PushPayload buildPushObject_all_list_rid_alert(List<String> list, String content, Message message) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.registrationId(list))
                .setNotification(Notification.alert(content))
                .setMessage(message)
                .build();
    }


}
