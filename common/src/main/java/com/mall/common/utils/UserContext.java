package com.mall.common.utils;

public class UserContext {

    private static ThreadLocal<String> tl = new ThreadLocal<>();

    public static void setUserId(String userId) {
        tl.set(userId);
    }

    public static String getUserId() {
        return tl.get();
    }

    public static void clear() {
        tl.remove();
    }
}
