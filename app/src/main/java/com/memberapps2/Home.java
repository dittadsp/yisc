package com.memberapps2;

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

import Fragment.Berita;
import Fragment.Fragment_home;
import Fragment.NewsFeedFragment;
import Fragment.Pendaftaran;
import Fragment.Pendidikan;
import Fragment.Pesan;
import Fragment.Profile;
import Fragment.Kajian;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    TextView txtNama;

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

        View header = navigationView.inflateHeaderView(R.layout.nav_header_main);
        txtNama = (TextView)header.findViewById(R.id.txtnama);
        SharedPreferences sharedPref = getSharedPreferences("data",MODE_PRIVATE);
        String name = sharedPref.getString("name", "");
        if(name!=""){
        txtNama.setText(name);}
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
            case R.id.nav_home:
                fragment = new NewsFeedFragment();
                toolbar.setTitle("YISC AL AZHAR");
                break;
            case R.id.nav_profil:
                fragment = new Profile();
                toolbar.setTitle("Profil");
                break;
            case R.id.nav_pendidikan:
                fragment = new Pendidikan();
                toolbar.setTitle("Pendidikan");
                break;
            case R.id.nav_pesan:
                fragment = new Pesan();
                toolbar.setTitle("Pesan");
                break;
            case R.id.nav_pendaftaran:
                fragment = new Pendaftaran();
                toolbar.setTitle("Pendaftaran");
                break;
            case R.id.nav_kajian:
                fragment = new Kajian();
                toolbar.setTitle("Kajian");
                break;
            case R.id.nav_logout:
                SharedPreferences sharedPref = getSharedPreferences("data",MODE_PRIVATE);
                SharedPreferences.Editor prefEditor = sharedPref.edit();
                prefEditor.putInt("isLogged",0);
                 prefEditor.commit();
                Intent i = new Intent(this, WelcomeActivity.class);
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