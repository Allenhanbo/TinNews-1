package com.laioffer.tinnews;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.MenuItem;

import com.laioffer.tinnews.common.ContainerFragment;
import com.laioffer.tinnews.common.TinBasicActivity;
import com.laioffer.tinnews.common.TinBasicFragment;
import com.laioffer.tinnews.common.TinFragmentPagerAdapter;

public class MainActivity extends TinBasicActivity {

    private ViewPager viewPager;
    private BottomNavigationView bottomBar;
    private TinFragmentPagerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //link view pager with view
        viewPager = findViewById(R.id.viewpager);
        adapter = new TinFragmentPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(TinFragmentPagerAdapter.FRAGMENT_NUMBER);

        bottomBar = findViewById(R.id.bottom_navigation);
        bottomBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                viewPager.setCurrentItem(ContainerFragment.getPositionById(item.getItemId()));
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getCurrentChildFragmentManager();
        if (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;

    }

    private FragmentManager getCurrentChildFragmentManager() {
        return adapter.getItem(viewPager.getCurrentItem()).getChildFragmentManager();
    }

    @Override
    public void doFragmentTransaction(TinBasicFragment basicFragment) {
        FragmentTransaction fragmentTransaction = getCurrentChildFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.child_fragment_container, basicFragment, basicFragment.getFragmentTag())
                .addToBackStack(null).commit();
    }

    @Override
    public void showSnackBar(String message) {

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
