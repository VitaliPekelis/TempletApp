package com.vitalipekelis.templet.ui.activities;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;

import com.vitalipekelis.templet.interfaces.NavigationDrawerCallbacks;
import com.vitalipekelis.templet.services.controllers.MainController;
import com.vitalipekelis.templet.ui.fragments.NavigationDrawerFragment;
import com.vitalipekelis.templet.R;
import com.vitalipekelis.templet.utils.Constants.MyConstants;
import com.vitalipekelis.templet.utils.FragmentSwapper;

public class MainActivity extends MyBaseActivity
        implements NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    private CharSequence mTitle;

    protected MainController mMainController;
    private Toolbar mToolbar;





    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
        }
    }


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
        init();

    }

    private void init() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);

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

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(MyConstants.EXTRAS_KEY_ARG_SECTION_NUMBER));
        }
    }


    //-----------------------------------------------------------
    //   NavigationDrawerCallbacks - implementations
    //-----------------------------------------------------------
    @Override
    public void onNavigationDrawerItemSelected(int position) {

        if(mFragmentSwapper == null)
            mFragmentSwapper = new FragmentSwapper(getSupportFragmentManager());
        // update the main content by replacing fragments
        Bundle arguments = new Bundle();
        arguments.putInt(MyConstants.EXTRAS_KEY_ARG_SECTION_NUMBER, position + 1);
        mFragmentSwapper.swapToFragment(PlaceholderFragment.class, arguments, R.id.container, false);

    }



}
