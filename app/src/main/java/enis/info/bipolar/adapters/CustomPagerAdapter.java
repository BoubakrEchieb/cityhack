package enis.info.bipolar.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by be on 15/10/16.
 */
public class CustomPagerAdapter extends FragmentPagerAdapter {

    private Context context;
    private ArrayList<Fragment> items;


    public CustomPagerAdapter(Context context, ArrayList<Fragment> items, FragmentManager fm){
        super(fm);
        this.context = context;
        this.items = items;
    }



    @Override
    public Fragment getItem(int position) {
        return items.get(position);
    }

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        return items.size();
    }


}
