package com.vitalipekelis.templet.ui.activities;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.Response;
import com.android.volley.error.VolleyError;
import com.vitalipekelis.templet.R;
import com.vitalipekelis.templet.interfaces.NavigationDrawerCallbacks;
import com.vitalipekelis.templet.services.controllers.MainController;
import com.vitalipekelis.templet.services.responses.QueryResult;
import com.vitalipekelis.templet.services.utils.RequestStringBuilder;
import com.vitalipekelis.templet.ui.fragments.NavigationDrawerFragment;
import com.vitalipekelis.templet.ui.fragments.PlaceholderFragment;
import com.vitalipekelis.templet.utils.FragmentSwapper;

public class MainActivity extends MyBaseActivity implements NavigationDrawerCallbacks {

    private final static String TAG = MainActivity.class.getSimpleName();

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    protected MainController mMainController;
    private Toolbar mToolbar;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            return true;
        }
        return super.onCreateOptionsMenu(menu);
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

    @Override
    protected void initScreen() {
        setContentView(R.layout.activity_main);
        initToolbar();
        initFragmentSwapper();
        swappFragment();
    }

    private void initFragmentSwapper() {
        if(mFragmentSwapper == null)
            mFragmentSwapper = new FragmentSwapper(getSupportFragmentManager());
    }

    private void swappFragment() {
        mFragmentSwapper.swapToFragment(PlaceholderFragment.class, null, R.id.container, false);
    }

    private void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        /*mToolbar.setTitle("");*/
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout),
                mToolbar);

    }


    @Override
    protected void onServiceConnected() {
        try
        {
            if (mMainController == null)
            {
                mMainController = getService().getController(MainController.class);
            }
            getTest();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    private void getTest() {
        if (mMainController != null) {
            mMainController.getRates(
                    RequestStringBuilder.getYahooFinanceXchange(),
                    new Response.Listener<QueryResult>() {
                        @Override
                        public void onResponse(QueryResult response) {
                            if (response != null) {
                                doOnResponceArived(response);
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });
        }
    }

    private void doOnResponceArived(QueryResult query) {
        PlaceholderFragment fragment = (PlaceholderFragment) mFragmentSwapper.findFragmentByTag(PlaceholderFragment.class.getSimpleName());
        if(fragment != null){
            fragment.setText(query);
        }
    }


    //-----------------------------------------------------------
    //   NavigationDrawerCallbacks - implementations
    //-----------------------------------------------------------
    @Override
    public void onNavigationDrawerItemSelected(int position) {
        //TODO:
    }



}
