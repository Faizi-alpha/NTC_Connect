package android.example.navigationdrawer;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class DepartmentFrag_Adapter extends FragmentPagerAdapter {

    Context cnt;
    ArrayList<Fragment> fragments;

   public DepartmentFrag_Adapter(FragmentManager fm , Context cnt, ArrayList<Fragment> fragments){
        super(fm);
        this.cnt = cnt;
        this.fragments = fragments;
   }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }
}
