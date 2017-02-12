package br.apps.groupchatapp.ui.view.chat;


import android.support.v4.app.FragmentActivity;

import java.util.ArrayList;

import br.apps.groupchatapp.R;
import br.apps.groupchatapp.service.db.RealmDB;
import br.apps.groupchatapp.service.db.model.ChatDetails;
import br.apps.groupchatapp.service.db.model.Message;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Handles non ui logic for ChatFragment
 */
public class ChatPresenter {

    private IChatFragmentView mView;
    private FragmentActivity mActivity;

    public ChatPresenter(IChatFragmentView view, FragmentActivity activity) {
        this.mView = view;
        this.mActivity = activity;

    }

    void fetchMessageFromDb(final ArrayList<Message> messagesList) {
        Realm realm = RealmDB.newInstance(mActivity);
        RealmResults<Message> realmResults = realm.where(Message.class).findAll();
        messagesList.addAll(realmResults);
        mView.setUpListNotify();
    }

    public void saveFavouriteInDb(Message message) {
        Realm realm = RealmDB.newInstance(mActivity);
        //check if already present contest
        Message msg = realm.where(Message.class).equalTo("mId", message.getmId()).findFirst();
        ChatDetails details = realm.where(ChatDetails.class).equalTo("mUserName", message.getmUserName()).findFirst();

        if (!msg.getmIsFavouriteMessage()) {
            realm.beginTransaction();
            msg.setmIsFavouriteMessage(true);
            details.setmTotalFavouriteMessage(details.getmTotalFavouriteMessage() + 1);
            realm.commitTransaction();
            mView.addFavouritesNotify(R.string.save_favourite);
        } else {
            mView.addFavouritesNotify(R.string.save_favourite_no);
        }
    }
}
