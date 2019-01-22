package Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import Fragment.Biodata;
import Fragment.Keterampilan;
import Fragment.Wali;

import static Fragment.Profile.int_items;

public class AdapterProfile extends FragmentPagerAdapter {

    public AdapterProfile(FragmentManager fm) {
        super(fm);
    }

    /**
     * Return fragment with respect to Position .
     */

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                Biodata tab1 = new Biodata();
                return tab1;
            case 1:
                Wali tab2 = new Wali();
                return tab2;
            case 2:
                Keterampilan tab3 = new Keterampilan();
                return tab3;
        }
        return null;
    }

    @Override
    public int getCount() {

        return int_items;

    }

    /**
     * This method returns the title of the tab according to the position.
     */

    @Override
    public CharSequence getPageTitle(int position) {
        String namatab;
        if (position==0){
            namatab = "Biodata";
        }else if (position==1){
            namatab = "Wali";
        }else{
            namatab = "Keterampilan";
        }
        return namatab;
    }
}
