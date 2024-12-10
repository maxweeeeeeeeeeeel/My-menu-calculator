package com.example.myapplicationmenu;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class MainActivity1 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    NavigationView navigationView;
    ActionBarDrawerToggle toogle;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    Intent intent3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        /*-------------------------- Conteneurs ----------------------------*/
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        Menu menu= navigationView.getMenu();
        navigationView.bringToFront();
        toogle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toogle);
        toogle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();

        if (id == R.id.nav_home) {
            // Action pour "Home"
        } else if (id == R.id.nav_calculator) {
            intent3 = new Intent(MainActivity1.this, MainActivity.class);
            startActivity(intent3);
        } else if (id == R.id.nav_share) {
            // Afficher un message Toast
            Toast.makeText(this, "Share", Toast.LENGTH_LONG).show();
        }

        // Fermer le menu
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }



    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
