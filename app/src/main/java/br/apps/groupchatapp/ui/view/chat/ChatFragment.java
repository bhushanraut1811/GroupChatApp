package br.apps.groupchatapp.ui.view.chat;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import br.apps.groupchatapp.R;
import br.apps.groupchatapp.service.db.model.Message;
import br.apps.groupchatapp.ui.adapter.ChatListAdapter;
import br.apps.groupchatapp.ui.view.home.GroupChatActivity;

/**
 * Group Chat fragment.
 */
public class ChatFragment extends Fragment implements IChatFragmentView {

    private RecyclerView mRecyclerChatView;
    private ChatListAdapter mAdapter;
    private ChatPresenter mChatPresenter;
    private ArrayList<Message> mMessageList = new ArrayList<>();

    public ChatFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static ChatFragment newInstance() {
        return new ChatFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        mMessageList.clear();
        mChatPresenter = new ChatPresenter(this, getActivity());
        mChatPresenter.fetchMessageFromDb(mMessageList);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_chat, container, false);
        initViews(rootView);
        return rootView;
    }

    public void setUpListNotify() {
        mAdapter = new ChatListAdapter(getContext(), mMessageList, new ChatListAdapter.OnItemClickListener() {
            @Override
            public void onItemLongClick(View v, int position) {
                mChatPresenter.saveFavouriteInDb(mMessageList.get(position));
            }
        });
        //setting layout Manager and adapter to recyclerView
        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(getContext());
        mRecyclerChatView.setLayoutManager(layoutManager);
        mRecyclerChatView.setAdapter(mAdapter);
    }

    @Override
    public void addFavouritesNotify(int messageId) {
        ((GroupChatActivity) getActivity()).showSnackBar(mRecyclerChatView, getActivity().getResources().getString(messageId));
        if (messageId == R.string.save_favourite) {
            ((GroupChatActivity) getActivity()).refreshAdapter();
        }
    }

    private void initViews(View rootView) {
        mRecyclerChatView = (RecyclerView) rootView.findViewById(R.id.rv_message_list);
    }
}