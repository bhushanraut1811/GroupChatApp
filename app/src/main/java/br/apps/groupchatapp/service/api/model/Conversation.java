package br.apps.groupchatapp.service.api.model;

/**
 * Created by bhushan.raut on 2/11/2017.
 */

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Conversation {

    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("messages")
    @Expose
    private List<Message> messages = null;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

}