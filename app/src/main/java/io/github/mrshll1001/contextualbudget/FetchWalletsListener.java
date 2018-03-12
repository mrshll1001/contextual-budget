package io.github.mrshll1001.contextualbudget;

import java.util.List;

import io.github.mrshll1001.contextualbudget.Database.Wallet;

/**
 * Created by marshall on 12/03/18.
 */

public interface FetchWalletsListener
{
    public void useWallets(List<Wallet> walletList);
}
