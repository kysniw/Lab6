package com.example.basiclab;

import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavGraphNavigator;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;

import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random rnd = new Random();
                Fragment test = getVisibleFragment();
                view = test.getView();
                int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
                view.setBackgroundColor(color);
                Snackbar.make(view, "Color changed!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }

        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        String message = "";

        switch(id)
        {
            case R.id.action_to_first:
                message = "Going to First Fragment...";
                NavHostFragment.findNavController(getVisibleFragment()).navigate(R.id.action_global_FirstFragment);
                break;

            case R.id.action_to_second:
                message = "Going to Second Fragment...";
                NavHostFragment.findNavController(getVisibleFragment()).navigate(R.id.action_global_SecondFragment);
                break;

            case R.id.action_to_third:
                message = "Going to Third Fragment...";
                NavHostFragment.findNavController(getVisibleFragment()).navigate(R.id.action_global_ThirdFragment);
                break;
        }

        Snackbar.make(findViewById(R.id.rootLayout), message, Snackbar.LENGTH_LONG).show();


        return super.onOptionsItemSelected(item);
    }

    public Fragment getVisibleFragment(){
        FragmentManager fragmentManager = MainActivity.this.getSupportFragmentManager();
        List<Fragment> fragments = fragmentManager.getFragments();
        for(Fragment fragment : fragments){
            if(fragment != null && fragment.isVisible())
                return (Fragment)fragment;
        }
        return null;
    }
}