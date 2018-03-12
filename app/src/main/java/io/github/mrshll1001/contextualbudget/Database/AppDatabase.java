package io.github.mrshll1001.contextualbudget.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by marshall on 12/03/18.
 */
@Database(entities = {Wallet.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase
{
    private static AppDatabase INSTANCE;

    public static AppDatabase getDatabase(Context context)
    {
        if (INSTANCE == null)
        {
            INSTANCE = Room.databaseBuilder(context, AppDatabase.class, "budget").build();
        }
        return INSTANCE;
    }

    public abstract WalletDao walletDao();
}
