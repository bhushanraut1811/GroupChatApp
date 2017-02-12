package br.apps.groupchatapp.ui.view.home;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import br.apps.groupchatapp.R;
import br.apps.groupchatapp.ui.view.BaseActivity;
import br.apps.groupchatapp.ui.view.chat.ChatFragment;
import br.apps.groupchatapp.ui.view.details.DetailsFragment;

public class GroupChatActivity extends BaseActivity implements IGroupChatView {

    private static int NUMBER_OF_FRAGMENTS = 2;
    private static String TAB_CHAT = "Chats";
    private static String TAB_DETAILS = "Details";
    private GroupChatPresenter mPresenter;

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private Toolbar mToolbar;
    private TabLayout mTabLayout;
    private CoordinatorLayout mParentLayout;
    private ProgressDialog mProgressDialog;

    @Override
    protected void initViews() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mViewPager = (ViewPager) findViewById(R.id.container);
        mTabLayout = (TabLayout) findViewById(R.id.tabs);
        mParentLayout = (CoordinatorLayout) findViewById(R.id.cl_main_content);
    }

    public void viewPagerSetupNotify() {
        hideProgressDialog(mProgressDialog);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        setSupportActionBar(mToolbar);
        mPresenter = new GroupChatPresenter(this);
        mProgressDialog = new ProgressDialog(this);
        //check network connection
        if (checkNetworkStatus()) {
            showProgressDialog(mProgressDialog, getResources().getString(R.string.loading));
            //fetch data
            mPresenter.fetchContestData();
        } else {
            hideProgressDialog(mProgressDialog);
            showSnackBar(mParentLayout, getResources().getString(R.string.network_connection));
        }
    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentStatePagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return ChatFragment.newInstance();
                case 1:
                    return DetailsFragment.newInstance();
            }
            return null;
        }

        @Override
        public int getCount() {
            return NUMBER_OF_FRAGMENTS;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return TAB_CHAT;
                case 1:
                    return TAB_DETAILS;
            }
            return null;
        }
    }

    /* @Override
     public boolean onCreateOptionsMenu(Menu menu) {
         getMenuInflater().inflate(R.menu.menu, menu);
         return true;
     }

     @Override
     public boolean onOptionsItemSelected(MenuItem item) {
         int id = item.getItemId();
         switch (id) {
             case R.id.menu_favourite:
                 return true;
         }
         return super.onOptionsItemSelected(item);
     }*/
    public void refreshAdapter() {
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mSectionsPagerAdapter.notifyDataSetChanged();
    }

}
