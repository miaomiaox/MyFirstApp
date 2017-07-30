package com.xinluqishi.myfirstapp.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by shikeyue on 2017/7/27.
 */

public class User implements Parcelable{

    private String userId;

    private String userName;


    protected User(Parcel in) {
        userId = in.readString();
        userName = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(userId);
        dest.writeString(userName);
    }
}
