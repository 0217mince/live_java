package com.ke.live.miniApp;

import java.io.Serializable;

/**
 * @author 小凡
 */
public class MiniAppAccessToken implements Serializable {
    private static final long serialVersionUID = -5797043534815132568L;
    private final String id;
    private final long expiresIn;
    private final long lastModify;

    public MiniAppAccessToken(String id, int expiresIn) {
        this.id = id;
        this.expiresIn = (long)(expiresIn * 1000);
        this.lastModify = System.currentTimeMillis();
    }

    public String getId() {
        return this.id;
    }

    public long getExpiresIn() {
        return this.expiresIn;
    }

    public boolean isExpired() {
        long now = System.currentTimeMillis();
        return now > this.lastModify + this.expiresIn;
    }

    public long getLastModify() {
        return this.lastModify;
    }
}

