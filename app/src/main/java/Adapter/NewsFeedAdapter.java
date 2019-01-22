package Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import Fragment.Fragment_home;
import Fragment.Events;
import Fragment.Merchandise;


public class NewsFeedAdapter extends FragmentPagerAdapter {

    public NewsFeedAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * Return fragment with respect to Position .
     */

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                Fragment_home tab1 = new Fragment_home();
                return tab1;
            case 1:
                Events tab2 = new Events();
                return tab2;
            case 2:
                Merchandise tab3 = new Merchandise();
                return tab3;
        }
        return null;
    }

    @Override
    public int getCount() {

        return 3;

    }

    /**
     * This method returns the title of the tab according to the position.
     */

    @Override
    public CharSequence getPageTitle(int position) {
        String namatab;
        if (position==0){
            namatab = "NewsFeed";
        }else if (position==1){
            namatab = "Events";
        }else{
            namatab = "Merchandise";
        }
        return namatab;
    }
}
