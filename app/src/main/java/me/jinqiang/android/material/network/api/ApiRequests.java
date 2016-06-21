/*
 * Copyright (c) 2015 Zhang Hai <Dreaming.in.Code.ZH@Gmail.com>
 * All Rights Reserved.
 */

package me.jinqiang.android.material.network.api;

import android.annotation.SuppressLint;
import android.content.Context;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.google.gson.reflect.TypeToken;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.List;

import me.jinqiang.android.material.network.api.info.Comment;
import me.jinqiang.android.material.network.api.info.User;
import me.jinqiang.android.material.network.api.info.UserInfo;
import me.jinqiang.android.material.network.api.info.Broadcast;
import me.jinqiang.android.material.network.api.info.CommentList;
import me.jinqiang.android.material.network.api.info.Notification;
import me.jinqiang.android.material.network.api.info.NotificationList;
import me.jinqiang.android.material.network.zhihuInfo.Latest;

public class ApiRequests {

    private ApiRequests() {}

    public static ApiRequest<UserInfo> newUserInfoRequest(String userIdOrUid, Context context) {
        return new ApiRequest<>(ApiRequest.Method.GET,
                String.format(ApiContract.Request.UserInfo.URL_FORMAT, userIdOrUid),
                new TypeToken<UserInfo>() {}, context);
    }

    public static ApiRequest<UserInfo> newUserInfoRequest(long userId, Context context) {
        return newUserInfoRequest(String.valueOf(userId), context);
    }

    public static ApiRequest<UserInfo> newCurrentUserInfoRequest(Context context) {
        return newUserInfoRequest(ApiContract.Request.UserInfo.UID_CURRENT, context);
    }

    @Frodo
    public static ApiRequest<NotificationList> newNotificationListRequest(Integer start,
                                                                          Integer count,
                                                                          Context context) {

        ApiRequest<NotificationList> request = new FrodoRequest<NotificationList>(
                ApiRequest.Method.GET, ApiContract.Request.Notification.URL,
                new TypeToken<NotificationList>() {}, context) {
            @Override
            protected Response<NotificationList> parseNetworkResponse(NetworkResponse response) {
                Response<NotificationList> superResponse = super.parseNetworkResponse(response);
                if (superResponse.isSuccess()) {
                    // Fix for Frodo API.
                    for (Notification notification : superResponse.result.notifications) {
                        notification.fix();
                    }
                }
                return superResponse;
            }
        };

        if (start != null) {
            request.addParam(ApiContract.Request.Notification.START, String.valueOf(start));
        }
        if (count != null) {
            request.addParam(ApiContract.Request.Notification.COUNT, String.valueOf(count));
        }

        return request;
    }

    /**
     * 组装请求参数
     * @param userId
     * @param topic
     * @param untilId 如果是加载更多，则记录最后一个顺序Id，否则为0.
     * @param count  每页加载的数据条数.
     * @param context
     * @return
     */
    // Lint warns about formatting with an Object (Long), but not long.
    @SuppressLint("DefaultLocale")
    public static ApiRequest<List<Broadcast>> newBroadcastListRequest(Long userId, String topic,
                                                                      Long untilId, Integer count,
                                                                      Context context) {

        String url;
        if (userId == null && topic == null) {
            url = ApiContract.Request.BroadcastList.Urls.HOME;
        } else if (topic == null) {
            url = String.format(ApiContract.Request.BroadcastList.Urls.USER_FORMAT, userId);
        } else {
            url = ApiContract.Request.BroadcastList.Urls.TOPIC;
        }

        ApiRequest<List<Broadcast>> request = new ApiRequest<>(ApiRequest.Method.GET, url,
                new TypeToken<List<Broadcast>>() {}, context);

        if (untilId != null) {
            request.addParam(ApiContract.Request.BroadcastList.UNTIL_ID, String.valueOf(untilId));
        }
        if (count != null) {
            request.addParam(ApiContract.Request.BroadcastList.COUNT, String.valueOf(count));
        }
        if (topic != null) {
            try {
                @Frodo
                String encodedTopic = URLEncoder.encode(topic, Charset.defaultCharset().name());
                request.addParam(ApiContract.Request.BroadcastList.Q, encodedTopic);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        return request;
    }

    public static ApiRequest<Broadcast> newBroadcastRequest(long broadcastId, Context context) {

        return new ApiRequest<>(ApiRequest.Method.GET,
                String.format(ApiContract.Request.Broadcast.URL_FORMAT, broadcastId),
                new TypeToken<Broadcast>() {}, context);
    }

    public static ApiRequest<CommentList> newBroadcastCommentListRequest(long broadcastId,
                                                                         Integer start,
                                                                         Integer count,
                                                                         Context context) {

        ApiRequest<CommentList> request = new ApiRequest<>(ApiRequest.Method.GET,
                String.format(ApiContract.Request.BroadcastCommentList.URL_FORMAT, broadcastId),
                new TypeToken<CommentList>() {}, context);

        if (start != null) {
            request.addParam(ApiContract.Request.BroadcastCommentList.START, String.valueOf(start));
        }
        if (count != null) {
            request.addParam(ApiContract.Request.BroadcastCommentList.COUNT, String.valueOf(count));
        }

        return request;
    }

    public static ApiRequest<Broadcast> newLikeBroadcastRequest(long broadcastId, boolean like,
                                                                Context context) {

        return new ApiRequest<>(like ? ApiRequest.Method.POST : ApiRequest.Method.DELETE,
                String.format(ApiContract.Request.LikeBroadcast.URL_FORMAT, broadcastId),
                new TypeToken<Broadcast>() {}, context);
    }

    public static ApiRequest<Broadcast> newRebroadcastBroadcastRequest(long broadcastId,
                                                                       boolean rebroadcast,
                                                                       Context context) {

        return new ApiRequest<>(rebroadcast ? ApiRequest.Method.POST : ApiRequest.Method.DELETE,
                String.format(ApiContract.Request.RebroadcastBroadcast.URL_FORMAT, broadcastId),
                new TypeToken<Broadcast>() {}, context);
    }

    public static ApiRequest<List<User>> newBroadcastLikerListRequest(long broadcastId,
                                                                      Integer start, Integer count,
                                                                      Context context) {

        ApiRequest<List<User>> request = new ApiRequest<>(ApiRequest.Method.GET,
                String.format(ApiContract.Request.BroadcastLikerList.URL_FORMAT, broadcastId),
                new TypeToken<List<User>>() {}, context);

        if (start != null) {
            request.addParam(ApiContract.Request.BroadcastLikerList.START, String.valueOf(start));
        }
        if (count != null) {
            request.addParam(ApiContract.Request.BroadcastLikerList.COUNT, String.valueOf(count));
        }

        return request;
    }

    public static ApiRequest<List<User>> newBroadcastRebroadcasterListRequest(long broadcastId,
                                                                              Integer start,
                                                                              Integer count,
                                                                              Context context) {

        ApiRequest<List<User>> request = new ApiRequest<>(ApiRequest.Method.GET,
                String.format(ApiContract.Request.BroadcastRebroadcasterList.URL_FORMAT,
                        broadcastId), new TypeToken<List<User>>() {}, context);

        if (start != null) {
            request.addParam(ApiContract.Request.BroadcastRebroadcasterList.START,
                    String.valueOf(start));
        }
        if (count != null) {
            request.addParam(ApiContract.Request.BroadcastRebroadcasterList.COUNT,
                    String.valueOf(count));
        }

        return request;
    }

    public static ApiRequest<Boolean> newDeleteBroadcastCommentRequest(long broadcastId,
                                                                       long commentId,
                                                                       Context context) {
        return new ApiRequest<>(ApiRequest.Method.DELETE, String.format(
                ApiContract.Request.DeleteBroadcastComment.URL_FORMAT, broadcastId, commentId),
                new TypeToken<Boolean>() {}, context);
    }

    public static ApiRequest<Comment> newSendBroadcastCommentRequest(long broadcastId,
                                                                     String comment,
                                                                     Context context) {

        ApiRequest<Comment> request = new ApiRequest<>(ApiRequest.Method.POST,
                String.format(ApiContract.Request.SendBroadcastComment.URL_FORMAT, broadcastId),
                new TypeToken<Comment>() {}, context);

        request.addParam(ApiContract.Request.SendBroadcastComment.TEXT, comment);

        return request;
    }

    public static ApiRequest<Broadcast> newDeleteBroadcastRequest(long broadcastId,
                                                                  Context context) {
        return new ApiRequest<>(ApiRequest.Method.DELETE,
                String.format(ApiContract.Request.DeleteBroadcast.URL_FORMAT, broadcastId),
                new TypeToken<Broadcast>() {}, context);
    }


     /** zhihu Uri start */
    public static ApiRequest<Latest> zhihuLatestRequest(Context context){
        //请求地址,从ApiContract中获取
        String url;
        url = ApiContract.Request.ZhihuLatest.LATEST_NEWS;
        ApiRequest<Latest> request = new ApiRequest<Latest>(ApiRequest.Method.GET, url,
                new TypeToken<Latest>() {}, context);

        return request;
    }
}
