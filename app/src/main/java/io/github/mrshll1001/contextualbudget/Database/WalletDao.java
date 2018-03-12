package io.github.mrshll1001.contextualbudget.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by marshall on 12/03/18.
 */

@Dao
public interface WalletDao
{
    @Query("SELECT * FROM wallet")
    List<Wallet> getAll();

    @Query("SELECT * FROM wallet WHERE uid IN (:walletIds)")
    List<Wallet> loadAllByIds(int[] walletIds);

    @Insert
    void insertAll(Wallet... wallets);

    @Delete
    void delete(Wallet wallet);



}
