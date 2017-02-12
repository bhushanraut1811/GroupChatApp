package br.apps.groupchatapp.service.db.model;


import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * POJO class Holds Message details
 */
public class Message extends RealmObject {

    private int mId;
    private String mBody;
    private String mUserName;
    private String mName;
    private String mImageUrl;
    private String mMessageTime;
    private boolean mIsFavouriteMessage;


    public Message() {

    }

    public Message(String mBody, int mId, String mImageUrl, boolean mIsFavouriteMessage, String mMessageTime, String mName, String mUserName) {
        this.mBody = mBody;
        this.mId = mId;
        this.mImageUrl = mImageUrl;
        this.mIsFavouriteMessage = mIsFavouriteMessage;
        this.mMessageTime = mMessageTime;
        this.mName = mName;
        this.mUserName = mUserName;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public boolean ismIsFavouriteMessage() {
        return mIsFavouriteMessage;
    }

    public String getmBody() {
        return mBody;
    }

    public void setmBody(String mBody) {
        this.mBody = mBody;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public void setmImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }

    public String getmMessageTime() {
        return mMessageTime;
    }

    public void setmMessageTime(String mMessageTime) {
        this.mMessageTime = mMessageTime;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmUserName() {
        return mUserName;
    }

    public void setmUserName(String mUserName) {
        this.mUserName = mUserName;
    }

    public boolean getmIsFavouriteMessage() {
        return mIsFavouriteMessage;
    }

    public void setmIsFavouriteMessage(boolean mIsFavouriteMessage) {
        this.mIsFavouriteMessage = mIsFavouriteMessage;
    }
}
