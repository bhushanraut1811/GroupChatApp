package br.apps.groupchatapp.ui.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public abstract class BaseActivity extends AppCompatActivity {

    /**
     * abstract method to initialise views in child activities
     */
    protected abstract void initViews();

    /**
     * shows message  on snack bar
     *
     * @param view
     * @param message
     */
    public void showSnackBar(final View view, final String message) {
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show();
    }

    /**
     * checks network status true if Network state is up else false
     */
    protected boolean checkNetworkStatus() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = manager.getActiveNetworkInfo();
        return (activeNetworkInfo != null && activeNetworkInfo.isConnected());
    }


    /**
     * Shows loading progress dialog
     *
     * @param message
     */
    protected void showProgressDialog(final ProgressDialog progressDialog, String message) {
        progressDialog.setCancelable(false);
        progressDialog.setMessage(message);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
    }

    /**
     * Dismisses progress dialog
     *
     * @param progressDialog
     */
    protected void hideProgressDialog(final ProgressDialog progressDialog) {
        progressDialog.dismiss();
    }
}
