package br.apps.groupchatapp.ui.view.details;

import android.support.v4.app.FragmentActivity;

import java.util.ArrayList;

import br.apps.groupchatapp.service.db.RealmDB;
import br.apps.groupchatapp.service.db.model.ChatDetails;
import io.realm.Realm;
import io.realm.RealmResults;

public class DetailsPresenter {

    private IDetailsFragmentView mView;
    private FragmentActivity mActivity;

    public DetailsPresenter(IDetailsFragmentView view, FragmentActivity activity) {
        this.mView = view;
        this.mActivity = activity;
    }

    public void fetchDetailsFromDb(final ArrayList<ChatDetails> detailsList) {
        Realm realm = RealmDB.newInstance(mActivity);
        RealmResults<ChatDetails> realmResults = realm.where(ChatDetails.class).findAll();
        detailsList.addAll(realmResults);
        mView.setUpListNotify();
    }
}
