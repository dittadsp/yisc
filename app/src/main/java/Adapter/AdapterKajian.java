package adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import Fragment.InfoKajian;
import Fragment.VideoKajian;
import Fragment.ArtikelKajian;

import static Fragment.Kajian.int_items;

public class AdapterKajian extends FragmentPagerAdapter {

    public AdapterKajian(FragmentManager fm) {
        super(fm);
    }

    /**
     * Return fragment with respect to Position .
     */

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                InfoKajian tab1 = new InfoKajian();
                return tab1;
            case 1:
                VideoKajian tab2 = new VideoKajian();
                return tab2;
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
            namatab = "Info";
        }else{
            namatab = "Video";
        }
        return namatab;
    }
}
