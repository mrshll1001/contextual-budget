package io.github.mrshll1001.contextualbudget.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import io.github.mrshll1001.contextualbudget.Database.Wallet;
import io.github.mrshll1001.contextualbudget.R;

/**
 * Created by marshall on 12/03/18.
 */

public class WalletListAdapter extends RecyclerView.Adapter<WalletListAdapter.ViewHolder>
{
    private Context context;
    private List<Wallet> data;

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView walletName;
        private Context context;
        private Wallet wallet;

        public ViewHolder(View v, Context c)
        {
            super(v);
            this.context = c;

            this.walletName = (TextView) v.findViewById(R.id.wallet_row_heading);

            // TODO set onclick listener
        }

        public void bindWallet(Wallet w)
        {
            wallet = w;
            walletName.setText(w.getName());
        }


    }

    public WalletListAdapter(Context context, List<Wallet> data)
    {
        this.context = context;
        this.data = data;
    }

    @Override
    public WalletListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext()).inflate(R.layout.wallet_item_row, parent, false);


        return new ViewHolder(inflatedView, this.context);
    }

    @Override
    public void onBindViewHolder(WalletListAdapter.ViewHolder holder, int position) {
        Wallet w = data.get(position);
        holder.bindWallet(w);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
