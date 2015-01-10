package apps.jayceleathers.me.spectrum.Activities;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.SearchManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.ParseUser;

import apps.jayceleathers.me.spectrum.Fragments.CameraFragment;
import apps.jayceleathers.me.spectrum.Fragments.DescriptionFragment;
import apps.jayceleathers.me.spectrum.Fragments.IdentifyFragment;
import apps.jayceleathers.me.spectrum.Fragments.LoginFragment;
import apps.jayceleathers.me.spectrum.Fragments.MatchIdentifyFragment;
import apps.jayceleathers.me.spectrum.Fragments.ProfileFragment;
import apps.jayceleathers.me.spectrum.R;


public class MainActivity extends Activity {
    private String[] menu;
    private DrawerLayout dLayout;
    private ListView dList;
    private ArrayAdapter<String> adapter;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    ActionBarDrawerToggle mDrawerToggle;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTitle = mDrawerTitle = getTitle();
        menu = new String[]{"Edit your Spectrum", "Edit your Matches' Spectrums", "Change Your Profile Picture", "Edit Your Description", "Log Out"};
        dLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        dList = (ListView) findViewById(R.id.left_drawer);

        adapter = new ArrayAdapter<String>(this, R.layout.drawer_item, menu);
        dList.setAdapter(adapter);
        dList.setSelector(android.R.color.holo_blue_dark);
        dList.setOnItemClickListener(new DrawerItemClickListener());
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                dLayout,         /* DrawerLayout object */
                R.drawable.ic_drawer,  /* nav drawer image to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description for accessibility */
                R.string.drawer_close  /* "close drawer" description for accessibility */
        ) {
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {

                getActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        dLayout.setDrawerListener(mDrawerToggle);

        ParseUser currentUser = ParseUser.getCurrentUser();
        if (savedInstanceState == null) {
            SharedPreferences sp = getSharedPreferences(LaunchActivity.SP_DATA, MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();

            if (sp.getBoolean(LaunchActivity.KEY_FIRST_RUN, true)) {
                editor.putBoolean(LaunchActivity.KEY_FIRST_RUN, false);
                editor.commit();
            }
            if (currentUser != null) {

                getFragmentManager().beginTransaction()
                        .add(R.id.mainContainer, new ProfileFragment())
                        .commit();
            }
        }
    }

    private void selectItem(int position){
        Fragment newFragment = null;
        boolean newFlag = false;
        switch (position){
            case 0: {newFragment = new IdentifyFragment();
            break;}
            case 1: {newFragment = new MatchIdentifyFragment();
            break;}
            case 2: {newFragment = new CameraFragment();
            break;}
            case 3: {newFragment = new DescriptionFragment();
            break;}
            case 4: {ParseUser.getCurrentUser().logOut();
                    newFragment = new LoginFragment();
            break;}
            default: break;

        }
        if(newFragment != null) {
            FragmentManager fragmentManager = getFragmentManager();

            fragmentManager.beginTransaction().replace(R.id.mainContainer, newFragment).addToBackStack("null").commit();
        }

        dList.setItemChecked(position, true);
        //setTitle(menu[position]);
        dLayout.closeDrawer(dList);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /* Called whenever we call invalidateOptionsMenu() */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        boolean drawerOpen = dLayout.isDrawerOpen(dList);
        menu.findItem(R.id.action_websearch).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        // ActionBarDrawerToggle will take care of this.
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle action buttons
        switch(item.getItemId()) {
            case R.id.action_websearch:
                // create intent to perform web search for this planet
                Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                intent.putExtra(SearchManager.QUERY, getActionBar().getTitle());
                // catch event that there's no activity to handle intent
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "App not available", Toast.LENGTH_LONG).show();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /* The click listner for ListView in the navigation drawer */
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }


    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }

    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

}


