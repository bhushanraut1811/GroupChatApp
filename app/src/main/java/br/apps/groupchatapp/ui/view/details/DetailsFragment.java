package br.apps.groupchatapp.ui.view.details;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import br.apps.groupchatapp.R;
import br.apps.groupchatapp.service.db.model.ChatDetails;
import br.apps.groupchatapp.ui.adapter.DetailsListAdapter;

/**
 * Group Chat fragment
 */
public class DetailsFragment extends Fragment implements IDetailsFragmentView {

    private RecyclerView mRecyclerDetailsView;
    private DetailsListAdapter mAdapter;
    private ArrayList<ChatDetails> mDetailsList = new ArrayList<>();
    private DetailsPresenter mPresenter;

    public DetailsFragment() {
    }

    public static DetailsFragment newInstance() {
        return new DetailsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_details, container, false);
        initViews(rootView);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mDetailsList.clear();
        mPresenter = new DetailsPresenter(this, getActivity());
        mPresenter.fetchDetailsFromDb(mDetailsList);
    }

    public void setUpListNotify() {
        mAdapter = new DetailsListAdapter(getContext(), mDetailsList);
        //setting layout Manager and adapter to recyclerView
        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(getContext());
        mRecyclerDetailsView.setLayoutManager(layoutManager);
        mRecyclerDetailsView.setAdapter(mAdapter);
    }

    private void initViews(View rootView) {
        mRecyclerDetailsView = (RecyclerView) rootView.findViewById(R.id.rv_details_list);
    }
}