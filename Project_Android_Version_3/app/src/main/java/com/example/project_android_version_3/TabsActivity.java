package com.example.project_android_version_3;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import com.example.project_android_version_3.Adapter.TabViewPagerAdapter;
import com.example.project_android_version_3.Fragment.FragmentCaSi;

public class TabsActivity extends AppCompatActivity {


    private TabLayout mainTab;
    private ViewPager mainViewPager;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frametabs);

        createTabsNav();
        if ((intent = getIntent()) != null)
        {
            TabLayout.Tab tab = mainTab.getTabAt(intent.getIntExtra("positionChuDe",0));
            tab.select();
        }

    }

    public void createTabsNav()
    {
        mainTab = (TabLayout) findViewById(R.id.mainTab);
        mainViewPager = (ViewPager) findViewById(R.id.mainViewPager);
        TabViewPagerAdapter tabViewPagerAdapter = new TabViewPagerAdapter(getSupportFragmentManager());
        tabViewPagerAdapter.addFragment(new FragmentCaSi(), "Nhạc trẻ");
        tabViewPagerAdapter.addFragment(new FragmentCaSi(), "Kpop");
        tabViewPagerAdapter.addFragment(new FragmentCaSi(), "Âu mỹ");
        tabViewPagerAdapter.addFragment(new FragmentCaSi(), "EDM");
        mainViewPager.setAdapter(tabViewPagerAdapter);
        mainTab.setupWithViewPager(mainViewPager);
    }
}
