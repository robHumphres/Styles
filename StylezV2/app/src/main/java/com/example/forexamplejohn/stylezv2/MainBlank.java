package com.example.forexamplejohn.stylezv2;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainBlank extends AppCompatActivity implements Closet.OnFragmentInteractionListener,Settings.OnFragmentInteractionListener,MainPageInside.OnFragmentInteractionListener
,ProfileFragment.OnFragmentInteractionListener,TradeFragment.OnFragmentInteractionListener
{

    private static final String SETTINGS = "SET";
    public static final String FIRST_USE = "FIRST_USE";
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private String [] thearray;
    private ActionBarDrawerToggle mDrawerToggle;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_blank);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        //Get userName from LoginPage
        Intent intent = getIntent();
        String user = intent.getExtras().getString("userName");

        //Load Preference files
        SharedPreferences prefs = getSharedPreferences(SETTINGS, Context.MODE_PRIVATE);
        boolean firstUseBool = prefs.getBoolean(FIRST_USE, true);

        //Default start fragment
        ProfileFragment profileFragment = new ProfileFragment();
        FragmentTransaction pfFt = getFragmentManager().beginTransaction();
        pfFt.replace(R.id.frameLayout,profileFragment,"profileFragment");
        pfFt.addToBackStack("profileFragment");
        pfFt.commit();

        //if first time user
        if(firstUseBool){
            //currentFrag = "settings";
            Toast.makeText(MainBlank.this, "Welcome "+ user, Toast.LENGTH_SHORT).show();
            prefs.edit().putBoolean(FIRST_USE, false).apply();
            Settings set = new Settings();
            FragmentTransaction fm = getFragmentManager().beginTransaction();
            fm.replace(R.id.frameLayout,set);
            fm.addToBackStack("settings");
            fm.commit();
        }// end of first time user

        //INITIALIZE NAV DRAWER
        thearray = getResources().getStringArray(R.array.navBarMenu);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        mDrawerList.setAdapter(new ArrayAdapter<>(this,
                R.layout.nav_list_row,R.id.theView, thearray));


        //TODO: need to check current fragment so it can add to backstack then later easier to initialize if its already in the back stack
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0:
                        Toast.makeText(MainBlank.this, "My Closet", Toast.LENGTH_SHORT).show();
                        Closet closet = new Closet();
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        transaction.replace(R.id.frameLayout,closet,"closet");
                        transaction.addToBackStack("closet");
                        transaction.commit();
                        break;
                    case 1:
                        Toast.makeText(MainBlank.this, "Profile", Toast.LENGTH_SHORT).show();
                        ProfileFragment profileFragment = new ProfileFragment();
                        FragmentTransaction pfFt = getFragmentManager().beginTransaction();
                        pfFt.replace(R.id.frameLayout,profileFragment,"profileFragment");
                        pfFt.addToBackStack("profileFragment");
                        pfFt.commit();
                        break;
                    case 2:
                        Toast.makeText(MainBlank.this, "Menu", Toast.LENGTH_SHORT).show();
                        MainPageInside mainPageInside = new MainPageInside();
                        FragmentTransaction transMainPage = getFragmentManager().beginTransaction();
                        transMainPage.replace(R.id.frameLayout,mainPageInside,"mainPageInside");
                        transMainPage.addToBackStack("mainPageInside");
                        transMainPage.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                        transMainPage.commit();

                        break;
                    case 3:
                        Toast.makeText(MainBlank.this, "Groups", Toast.LENGTH_SHORT).show();
                        GroupsFragment groupFragment = new GroupsFragment();
                        FragmentTransaction groupTransaction = getFragmentManager().beginTransaction();
                        groupTransaction.replace(R.id.frameLayout,groupFragment,"groupFragment");
                        groupTransaction.addToBackStack("groupFragment");
                        groupTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                        groupTransaction.commit();
                        break;
                    case 4:
                        Toast.makeText(MainBlank.this, "Trade", Toast.LENGTH_SHORT).show();
                        TradeFragment tradeFragment = new TradeFragment();
                        FragmentTransaction tradeTransaction = getFragmentManager().beginTransaction();
                        tradeTransaction.replace(R.id.frameLayout,tradeFragment,"tradeFragment");
                        tradeTransaction.addToBackStack("tradeFragment");
                        tradeTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                        tradeTransaction.commit();
                        break;
                    case 5:
                        //if(!(currentFrag.equalsIgnoreCase("Settings"))) {
                            //currentFrag = "settings";
                        Toast.makeText(MainBlank.this, "Settings", Toast.LENGTH_SHORT).show();
                            Settings set = new Settings();
                            FragmentTransaction fm = getFragmentManager().beginTransaction();
                            fm.replace(R.id.frameLayout, set, "settings");
                            fm.addToBackStack("settings");
                            fm.commit();

                        break;
                    case 6:
                        Toast.makeText(MainBlank.this, "Sign Out", Toast.LENGTH_SHORT).show();
                        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which){
                                    case DialogInterface.BUTTON_POSITIVE:
                                        Intent intent = new Intent(MainBlank.this,LoginPage.class);
                                        startActivity(intent);
                                        break;

                                    case DialogInterface.BUTTON_NEGATIVE:
                                        //No button clicked
                                        break;
                                }
                            }
                        };
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainBlank.this);
                        builder.setMessage("Are you sure you want to sign out?").setPositiveButton("Yes", dialogClickListener)
                                .setNegativeButton("No", dialogClickListener).show();
                        break;

                }//end of switch
                mDrawerLayout.closeDrawers();
            }// on click
        });

        mDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout,
                toolbar,
                R.string.drawer_open,
                R.string.drawer_close
        ){
            public void onDrawerClosed(View view) {
                //setSupportActionBar(toolbar);
                super.onDrawerClosed(view);
                toolbar.setTitle("Stylez");
                supportInvalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                //getSupportActionBar().setTitle("Select a Page");
                super.onDrawerOpened(drawerView);
                toolbar.setTitle("Select a Page");
                supportInvalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();



    }// end of onCreate


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
        menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);

    }

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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login_page, menu);
        return super.onCreateOptionsMenu(menu);

    }

    public void onClick(View v){
        Toast.makeText(MainBlank.this, "Buttons work on Click", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Settings set = new Settings();
            FragmentTransaction fm = getFragmentManager().beginTransaction();
            fm.replace(R.id.frameLayout,set);
            fm.addToBackStack("settings");
            fm.commit();
        }

        if(id== R.id.action_about){
            Toast.makeText(MainBlank.this, "Robert Humphres Project Stylez", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if(getFragmentManager().getBackStackEntryCount() < 2) {
            super.onBackPressed();
        }
        else {
            getFragmentManager().popBackStack();
        }
    }

    @Override
    public void onFragmentInteraction(String key,String task) {
        //Toast.makeText(MainActivity.this, uri , Toast.LENGTH_SHORT).show();



    }


}//end of main blank



