package br.apps.groupchatapp.service.db.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ChatDetails extends RealmObject {
    @PrimaryKey
    private String mUserName;

    private int mTotalMessage;
    private int mTotalFavouriteMessage;
    private String mName;

    public ChatDetails() {
    }

    public ChatDetails(String mName, int mTotalFavouriteMessage, int mTotalMessage, String mUserName) {
        this.mName = mName;
        this.mTotalFavouriteMessage = mTotalFavouriteMessage;
        this.mTotalMessage = mTotalMessage;
        this.mUserName = mUserName;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public int getmTotalFavouriteMessage() {
        return mTotalFavouriteMessage;
    }

    public void setmTotalFavouriteMessage(int mTotalFavouriteMessage) {
        this.mTotalFavouriteMessage = mTotalFavouriteMessage;
    }

    public int getmTotalMessage() {
        return mTotalMessage;
    }

    public void setmTotalMessage(int mTotalMessage) {
        this.mTotalMessage = mTotalMessage;
    }

    public String getmUserName() {
        return mUserName;
    }

    public void setmUserName(String mUserName) {
        this.mUserName = mUserName;
    }
}
