package br.apps.groupchatapp.ui.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import br.apps.groupchatapp.R;
import br.apps.groupchatapp.service.db.model.ChatDetails;


public class DetailsListAdapter extends RecyclerView.Adapter<DetailsListAdapter.ItemViewHolder> {

    private ArrayList<ChatDetails> mList;
    private Context mContext;


    public DetailsListAdapter(Context context, ArrayList<ChatDetails> list) {
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detail, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        final ChatDetails details = mList.get(position);
        holder.mTvName.setText(details.getmName());
        holder.mTvTotalMessage.setText(String.valueOf(details.getmTotalMessage()));
        holder.mTvTotalFavouriteMessage.setText(String.valueOf(details.getmTotalFavouriteMessage()));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView mTvName;
        TextView mTvTotalMessage;
        TextView mTvTotalFavouriteMessage;

        ItemViewHolder(View itemView) {
            super(itemView);
            mTvName = (TextView) itemView.findViewById(R.id.tv_conv_name);
            mTvTotalMessage = (TextView) itemView.findViewById(R.id.tv_number_message);
            mTvTotalFavouriteMessage = (TextView) itemView.findViewById(R.id.tv_fav_number_message);
        }
    }
}
