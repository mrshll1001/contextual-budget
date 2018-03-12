package io.github.mrshll1001.contextualbudget.AsyncTasks;

import android.os.AsyncTask;

import io.github.mrshll1001.contextualbudget.Database.AppDatabase;
import io.github.mrshll1001.contextualbudget.Database.Wallet;
import io.github.mrshll1001.contextualbudget.MainActivity;

/**
 * Created by marshall on 12/03/18.
 */

public class InsertWalletsToDatabaseTask extends AsyncTask<Wallet, Double, Boolean>
{
    private AppDatabase db;

    public InsertWalletsToDatabaseTask(AppDatabase db)
    {
        this.db = db;
    }

    @Override
    protected Boolean doInBackground(Wallet... wallets) {
        this.db.walletDao().insertAll(wallets);
        return true;
    }
}
