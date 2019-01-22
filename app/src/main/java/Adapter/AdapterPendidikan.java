package Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import Fragment.Bsq;
import Fragment.Sii;


public class AdapterPendidikan extends FragmentPagerAdapter {

    public AdapterPendidikan(FragmentManager fm) {
        super(fm);
    }

    /**
     * Return fragment with respect to Position .
     */

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                Bsq bsq = new Bsq();
                return bsq;
            case 1:
                Sii sii = new Sii();
                return sii;
        }
        return null;
    }

    @Override
    public int getCount() {

        return 2;

    }

    /**
     * This method returns the title of the tab according to the position.
     */

    @Override
    public CharSequence getPageTitle(int position) {
        String namatab;
        if (position==0){
            namatab = "Bsq";
        }else{
            namatab = "Sii";
        }
        return namatab;
    }
}
