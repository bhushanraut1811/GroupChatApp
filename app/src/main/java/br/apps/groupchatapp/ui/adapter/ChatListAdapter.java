package br.apps.groupchatapp.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import br.apps.groupchatapp.R;
import br.apps.groupchatapp.service.db.model.Message;


public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.ItemViewHolder> {

    private ArrayList<Message> mList;
    private Context mContext;
    private OnItemClickListener mOnClickListener;

    public ChatListAdapter(Context context, ArrayList<Message> list, OnItemClickListener onClickListener) {
        this.mContext = context;
        this.mList = list;
        this.mOnClickListener = onClickListener;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message, parent, false);
        final ItemViewHolder itemViewHolder = new ItemViewHolder(view);
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mOnClickListener.onItemLongClick(view, itemViewHolder.getLayoutPosition());
                return true;
            }
        });
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        final Message message = mList.get(position);
        holder.mTvName.setText(message.getmName());
        holder.mTvMessage.setText(message.getmBody());

     /*   Picasso.with(mContext).load(challenge.getChallengeLogo())
                .placeholder(R.drawable.ic_launcher_grayscale).error(R.drawable.ic_launcher_grayscale)
                .resize(100, 100).into(holder.mIvLogo);
*/
        //holder.mBtnFavourite.setVisibility(View.VISIBLE);
        //bookmark button listeners
       /* holder.mBtnFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // save into realm db show snack bar on
                //?? should do from a presenter
                saveBookmark(challenge);
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public interface OnItemClickListener {
        void onItemLongClick(View v, int position);
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView mTvName;
        TextView mTvMessage;
        //ImageView mIvImage;
        //ImageButton mBtnFavourite;

        public ItemViewHolder(View itemView) {
            super(itemView);
            mTvName = (TextView) itemView.findViewById(R.id.tv_chat_name);
            mTvMessage = (TextView) itemView.findViewById(R.id.tv_chat_message);
            //mIvImage = (ImageView) itemView.findViewById(R.id.iv_logo);
            //mBtnFavourite = (ImageButton) itemView.findViewById(R.id.btn_bookmark);
        }
    }

/*
    *//**
     * Bookmark challenge,Saves in Realm Database
     *
     * @param challenge
     *//*
    private void saveBookmark(Message challenge) {
        Realm realm = RealmDB.newInstance(mContext);
        BaseActivity context = (ContestsHomeActivity) mContext;
        //check if already present contest
        long count = realm.where(BookmarkChallenge.class).equalTo("mChallengeName", challenge.getChallengeName()).count();
        if (count < 1) {
            realm.beginTransaction();
            //create model for realm specific data
            BookmarkChallenge bookmark = realm.createObject(BookmarkChallenge.class);
            bookmark.setmChallengeName(challenge.getChallengeName());
            bookmark.setmPlatformName(challenge.getPlatformName());
            bookmark.setmChallengeUrl(challenge.getChallengeUrl());
            bookmark.setmChallengeLogo(challenge.getChallengeLogo());
            bookmark.setmDateStr(challenge.getDateStr());
            bookmark.setmTimeStamp(Calendar.getInstance().getTimeInMillis());
            realm.commitTransaction();
            //show snack bar
            context.showSnackBar(context.findViewById(R.id.container), mContext.getResources().getString(R.string.save_bookmark));
        } else {
            context.showSnackBar(context.findViewById(R.id.container), mContext.getResources().getString(R.string.already_saved_bookmark));
        }
    }*/
}
