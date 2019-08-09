package com.yisc;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import Fragment.NewsFeedFragment;
import Fragment.Pendaftaran;
import Fragment.Pendidikan;
import Fragment.Pesan;
import Fragment.Profile;
import Fragment.Kajian;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    TextView txtNama;
    Menu nav_menu;

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("YISC AL AZHAR");
//        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        View header = navigationView.inflateHeaderView(R.layout.nav_header_main);
        txtNama = (TextView) header.findViewById(R.id.txtnama);
        SharedPreferences sharedPref = getSharedPreferences("data", MODE_PRIVATE);
        String name = sharedPref.getString("name", "");
        if (name != "") {
            nav_menu = navigationView.getMenu();
            nav_menu.findItem(R.id.nav_login).setVisible(false);
            nav_menu.findItem(R.id.nav_pendaftaran).setVisible(false);
            nav_menu.findItem(R.id.nav_tentang).setVisible(false);
            nav_menu.findItem(R.id.nav_pendidikan).setVisible(true);
            nav_menu.findItem(R.id.nav_hubungi).setVisible(false);
            nav_menu.findItem(R.id.nav_logout).setVisible(true);
            txtNama.setText(name);
        } else {
            nav_menu = navigationView.getMenu();
            nav_menu.findItem(R.id.nav_login).setVisible(true);
            nav_menu.findItem(R.id.nav_pendaftaran).setVisible(true);
            nav_menu.findItem(R.id.nav_tentang).setVisible(true);
            nav_menu.findItem(R.id.nav_pendidikan).setVisible(false);
            nav_menu.findItem(R.id.nav_hubungi).setVisible(true);
            nav_menu.findItem(R.id.nav_logout).setVisible(false);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            finish();
            System.exit(0);
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
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.action_refresh) {
            Toast.makeText(getApplicationContext(), "Refresh", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    private void displaySelectedScreen(int itemId) {

        //creating fragment object
        Fragment fragment = null;

        //initializing the fragment object which is selected
        switch (itemId) {
            case R.id.nav_berita:
                fragment = new NewsFeedFragment();
                toolbar.setTitle("YISC AL AZHAR");
                break;
            case R.id.nav_tentang:
                fragment = new Profile();
                toolbar.setTitle("Tentang");
                break;
            case R.id.nav_pendidikan:
                fragment = new Pendidikan();
                toolbar.setTitle("Pendidikan");
                break;
            case R.id.nav_hubungi:
                fragment = new Pesan();
                toolbar.setTitle("Hubungi");
                break;
            case R.id.nav_pendaftaran:
                fragment = new Pendaftaran();
                toolbar.setTitle("Pendaftaran");
                break;
            case R.id.nav_kajian:
                fragment = new Kajian();
                toolbar.setTitle("Kajian");
                break;
            case R.id.nav_login:
                Intent k = new Intent(this, WelcomeActivity.class);
                startActivity(k);
                break;
            case R.id.nav_logout:
                SharedPreferences sharedPref = getSharedPreferences("data", MODE_PRIVATE);
                SharedPreferences.Editor prefEditor = sharedPref.edit();
                prefEditor.putInt("isLogged", 0);
                prefEditor.putString("name","");
                prefEditor.commit();
                Intent i = new Intent(this, Home.class);
                startActivity(i);
                break;

        }

        //replacing the fragment
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        displaySelectedScreen(id);
        return true;
    }


}