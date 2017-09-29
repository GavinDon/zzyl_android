package com.nanyixuan.zzyl_andorid.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2017/7/29.
 */

public class UrlIntentBean implements Parcelable {
    private String urlTitle;
    private String url;
    protected UrlIntentBean(Parcel in) {
        urlTitle = in.readString();
        url = in.readString();
    }
    public UrlIntentBean(String urlTitle, String url) {
        this.urlTitle = urlTitle;
        this.url = url;
    }

    public static final Creator<UrlIntentBean> CREATOR = new Creator<UrlIntentBean>() {
        @Override
        public UrlIntentBean createFromParcel(Parcel in) {
            return new UrlIntentBean(in);
        }

        @Override
        public UrlIntentBean[] newArray(int size) {
            return new UrlIntentBean[size];
        }
    };

    public String getUrlTitle() {
        return urlTitle;
    }

    public void setUrlTitle(String urlTitle) {
        this.urlTitle = urlTitle;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(urlTitle);
        parcel.writeString(url);
    }
}
