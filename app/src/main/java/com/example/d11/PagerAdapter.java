package com.example.d11;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {

    int mNoOfTabs;
    public PagerAdapter(FragmentManager fm,int NoOfTabs)
    {
        super(fm);
        this.mNoOfTabs=NoOfTabs;
    }
    @Override
    public Fragment getItem(int position) {
         switch (position)
         {
             case 0:
                 Preview preview=new Preview();
                 return preview;
             case 1:
                 Team team = new Team();
                 return team;

              default:
                  return null;

         }
    }

    @Override
    public int getCount() {
        return mNoOfTabs;
    }
}
