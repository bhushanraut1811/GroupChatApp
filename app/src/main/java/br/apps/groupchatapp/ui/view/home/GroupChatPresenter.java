package br.apps.groupchatapp.ui.view.home;

import android.content.Context;

import java.util.ArrayList;

import br.apps.groupchatapp.service.api.ApiClient;
import br.apps.groupchatapp.service.api.IChatAPI;
import br.apps.groupchatapp.service.api.model.Conversation;
import br.apps.groupchatapp.service.api.model.Message;
import br.apps.groupchatapp.service.db.RealmDB;
import br.apps.groupchatapp.service.db.model.ChatDetails;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Presenter for GroupChatActivity which handles non ui logic
 */
public class GroupChatPresenter {
    private IGroupChatView mView;

    public GroupChatPresenter(IGroupChatView view) {
        this.mView = view;
    }

    public void fetchContestData() {
        IChatAPI apiService = ApiClient.getClient().
                create(IChatAPI.class);

        Call<Conversation> call = apiService.getChatConversation();
        call.enqueue(new Callback<Conversation>() {
            @Override
            public void onResponse(Call<Conversation> call,
                                   Response<Conversation> response) {
                // do it in background thread
                ArrayList<Message> list = (ArrayList<Message>) response.body().getMessages();
                //check previous messages size in database
                Realm realm = RealmDB.newInstance((Context) mView);
                RealmQuery query = realm.where(br.apps.groupchatapp.service.db.model.Message.class);
                RealmResults results = query.findAll();
                int messagesSize = results.size();

                //add to database if new messages found
                if (messagesSize < list.size()) {
                    addMessageToDb(list, list.size() - messagesSize);
                }
                //load UI
                mView.viewPagerSetupNotify();
            }

            @Override
            public void onFailure(Call<Conversation> call, Throwable t) {
                mView.viewPagerSetupNotify();
            }
        });
    }

    /**
     * Adds the messages in ot the database
     */
    private void addMessageToDb(ArrayList<Message> list, int noOfNewMessages) {
        Realm realm = RealmDB.newInstance((Context) mView);
        int newMessageIndex = list.size() - noOfNewMessages;
        realm.beginTransaction();
        for (int i = newMessageIndex; i < list.size(); i++) {
            //create model for realm specific data
            br.apps.groupchatapp.service.db.model.Message message = realm.createObject(br.apps.groupchatapp.service.db.model.Message.class);
            message.setmId(i);
            message.setmBody(list.get(i).getBody());
            message.setmName(list.get(i).getName());
            message.setmUserName(list.get(i).getUsername());
            message.setmMessageTime(list.get(i).getMessageTime());
            message.setmImageUrl(list.get(i).getImageUrl());
            message.setmIsFavouriteMessage(false);

            long count = realm.where(ChatDetails.class).equalTo("mUserName", list.get(i).getUsername()).count();
            if (count < 1) {
                ChatDetails details = realm.createObject(ChatDetails.class);
                details.setmUserName(list.get(i).getUsername());
                details.setmName(list.get(i).getName());
                details.setmTotalFavouriteMessage(0);
                details.setmTotalMessage(1);
                //auto increment no of messages
            } else {
                ChatDetails detail = realm.where(ChatDetails.class).equalTo("mUserName", list.get(i).getUsername()).findFirst();
                detail.setmTotalMessage(detail.getmTotalMessage() + 1);
            }
        }
        realm.commitTransaction();
    }
}

