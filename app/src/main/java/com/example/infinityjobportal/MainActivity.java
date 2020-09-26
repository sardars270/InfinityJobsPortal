package com.example.infinityjobportal;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    FirebaseAuth mAuth;

    AppBarConfiguration mAppBarConfiguration;
    DrawerLayout drawer;
    Toolbar toolbar;
    NavigationView navigationView;
    NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: has started.");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set up toolbar as we are using noActionBarTheme
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mAuth = FirebaseAuth.getInstance();

        if (mAuth.getCurrentUser() == null) {
            //User NOT logged In
            this.finish();
            startActivity(new Intent(getApplicationContext(), ClientLogin.class));
        }


        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        navController = Navigation.findNavController(this, R.id.fragment_container);


        //NavigationUI uses an AppBarConfiguration object to manage the behavior of the Navigation button in the upper-left corner of your app's display area.
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
//        mAppBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.nav_home, R.id.my_jobs, R.id.post_job, R.id.posted_jobs, R.id.about_us, R.id.logout)
//                .build();
//        Log.d(TAG, "onCreate: mAppBarConfiguration ended.");

        //By calling this method, the title in the Toolbar will automatically be updated when the destination changes (assuming there is a valid label).
        NavigationUI.setupWithNavController(navigationView, navController);
        NavigationUI.setupActionBarWithNavController(this, navController, drawer);


        Log.d(TAG, "onCreate: navController ended.");

        Log.d(TAG, "onCreate: has ended.");

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d(TAG, "onCreateOptionsMenu: has started.");
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        Log.d(TAG, "onSupportNavigateUp: has started");
        NavController navController = Navigation.findNavController(this, R.id.fragment_container);
        return NavigationUI.navigateUp(navController, drawer)
                || super.onSupportNavigateUp();
    }

    public void logOutFunction(MenuItem item) {
        Log.d(TAG, "logOutFunction: has started.");
        mAuth.signOut();
        startActivity(new Intent(getApplicationContext(), ClientLogin.class));
    }

}