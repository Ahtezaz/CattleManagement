package com.app.cattlemanagement.data.entity;

public class Feed {
    String consumed = "";
    String feedType = "";
    String remaining = "";
    String totalQuantity = "";

    public Feed(String consumed, String feedType, String remaining, String totalQuantity) {
        this.consumed = consumed;
        this.feedType = feedType;
        this.remaining = remaining;
        this.totalQuantity = totalQuantity;
    }

    @Override
    public String toString() {
        return "Feed{" +
                       "consumed='" + consumed + '\'' +
                       ", feedType='" + feedType + '\'' +
                       ", remaining='" + remaining + '\'' +
                       ", totalQuantity='" + totalQuantity + '\'' +
                       '}';
    }

    public Feed() {
    }

    public String getConsumed() {
        return consumed;
    }

    public String getFeedType() {
        return feedType;
    }

    public String getRemaining() {
        return remaining;
    }

    public String getTotalQuantity() {
        return totalQuantity;
    }
}
