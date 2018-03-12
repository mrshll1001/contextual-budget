package io.github.mrshll1001.contextualbudget.AsyncTasks;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

import io.github.mrshll1001.contextualbudget.Database.AppDatabase;
import io.github.mrshll1001.contextualbudget.Database.Wallet;
import io.github.mrshll1001.contextualbudget.FetchWalletsListener;

/**
 * Created by marshall on 12/03/18.
 */

public class FetchAllWalletsTask extends AsyncTask<Void, Void, List<Wallet>>
{
    private FetchWalletsListener listener;
    private AppDatabase db;

    public FetchAllWalletsTask(FetchWalletsListener listener, AppDatabase db)
    {
        this.db = db;
        this.listener = listener;
    }

    @Override
    protected List<Wallet> doInBackground(Void... voids)
    {

        return this.db.walletDao().getAll();
    }

    @Override
    protected void onPostExecute(List<Wallet> wallets)
    {
        listener.useWallets(wallets);
    }
}
