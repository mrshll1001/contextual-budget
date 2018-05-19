package io.github.mrshll1001.contextualbudget;

import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Room;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import io.github.mrshll1001.contextualbudget.Adapter.WalletListAdapter;
import io.github.mrshll1001.contextualbudget.AsyncTasks.FetchAllWalletsTask;
import io.github.mrshll1001.contextualbudget.AsyncTasks.InsertWalletsToDatabaseTask;
import io.github.mrshll1001.contextualbudget.Database.AppDatabase;
import io.github.mrshll1001.contextualbudget.Database.Wallet;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, FetchWalletsListener
{
    AppDatabase db;
    private RecyclerView walletRecyclerView;
    private WalletListAdapter viewAdapter;
    private RecyclerView.LayoutManager viewLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        /* Get application database based on our context  */
        db = AppDatabase.getDatabase(this);

        /* Query for all of our wallets and update the UI */
        updateUI();





        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {

                /* Show dialog box */
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Add Wallet");

                final View dialogLayout = getLayoutInflater().inflate(R.layout.dialog_new_wallet, null);
                final EditText nameField = (EditText) dialogLayout.findViewById(R.id.add_wallet_name);

                builder.setView(dialogLayout);

                builder.setPositiveButton("Add", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Wallet wallet = new Wallet();
                        wallet.setName(nameField.getText().toString());
                        wallet.setColour("#0066CC");

                        new InsertWalletsToDatabaseTask(db).execute(wallet);
                        updateUI();
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                builder.show();

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


    private void updateUI()
    {
        new FetchAllWalletsTask(this, db).execute();
    }

    @Override
    public void useWallets(List<Wallet> walletList)
    {
        populateRecyclerView(walletList);
    }

    /**
     * Specifically populates the Recycler view full of wallets when they've been fetched.
     * @param wallets
     */
    private void populateRecyclerView(List<Wallet> wallets)
    {
         /* Set up our Recycler View */
        walletRecyclerView = (RecyclerView) findViewById(R.id.main_wallets_view);
        viewLayoutManager = new LinearLayoutManager(this);
        walletRecyclerView.setLayoutManager(viewLayoutManager);

        viewAdapter = new WalletListAdapter(MainActivity.this, wallets);
        walletRecyclerView.setAdapter(viewAdapter);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
