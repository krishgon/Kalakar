package com.krishapps.kalakar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private Button logOut_button;
    private Fragment homeFrag, accFrag, dmFrag;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // do initial rituals
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

        // hide the action bar
            getSupportActionBar().hide();

        // collect the fragment manager
            fragmentManager = getSupportFragmentManager();


        // collect the fragments
            homeFrag = new HomeFragment();
            accFrag = new AccountFragment();
            dmFrag = new DmFragment();

        // set up bottom navigation view
            BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
            bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()){
                        case R.id.homePage:
                            Log.d("krishlog", "onNavigationItemSelected: home clicked");
                            switchFragment(homeFrag);
                            return true;

                        case R.id.accountPage:
                            Log.d("krishlog", "onNavigationItemSelected: account clicked");
                            switchFragment(accFrag);
                            return true;

                        case R.id.DmPage:
                            Log.d("krishlog", "onNavigationItemSelected: DM clicked");
                            switchFragment(dmFrag);
                            return true;

                        default:
                            return false;
                    }
                }
            });

        // set the current fragment to home fragment
            if(savedInstanceState == null){
                bottomNavigationView.setSelectedItemId(R.id.homePage);
            }
    }

    public void switchFragment(Fragment fragment){
        fragmentManager.beginTransaction()
                .replace(R.id.main_fragment_container_view, fragment)
                .setReorderingAllowed(true)
                .addToBackStack(null)
                .commit();
    }
}