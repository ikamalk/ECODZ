package com.example.root.ecodz;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.ArrayList;

import devlight.io.library.ntb.NavigationTabBar;

public class Main extends AppCompatActivity {

    NavigationTabBar navigationTabBar;
    ArrayList<NavigationTabBar.Model> models;
    TabsPagerAdapter myAdapter;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=this;
        navigationTabBar = (NavigationTabBar) findViewById(R.id.ntb);
        pageviwer();
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpage);
        myAdapter = new TabsPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(myAdapter);
        navigationTabBar.setModels(models);
        navigationTabBar.setViewPager(viewPager,0);



        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrollStateChanged(int state) {}
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            public void onPageSelected(int position) {

            }
        });
    }


    public void pageviwer(){
        models = new ArrayList<>();
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.oil),
                        getResources().getColor(R.color.noir)


                ).title("Pétrole")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.money),
                        getResources().getColor(R.color.vert)
                ).title("Monnaie")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.news),
                        getResources().getColor(R.color.cyan)
                ).title("News")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.information),
                        getResources().getColor(R.color.jaune)
                ).title("Information")
                        .build()
        );

    }




    }


