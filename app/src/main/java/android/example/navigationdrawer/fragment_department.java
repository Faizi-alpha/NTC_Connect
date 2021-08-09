package android.example.navigationdrawer;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;
import java.util.ArrayList;

public class fragment_department extends Fragment {

    ViewPager viewPager;
    TabLayout tabLayout;
    ArrayList<Fragment> fragments;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_department,container,false);


        viewPager = view.findViewById(R.id.pager);
        tabLayout = view.findViewById(R.id.tabLayout);


        fragments =new ArrayList<>();

        fragments.add(new departmentAdapter());   // for computer science
        fragments.add(new departmentAdapter_TT());   // for textile technology


        DepartmentFrag_Adapter departmentFrag_adapter = new DepartmentFrag_Adapter(getChildFragmentManager(),getContext(),fragments);

        /* I used  getChildFragmentManager() instead of getFragmentManager because when i go to another menu (in Drawer menu , say home)  the contents of department used
            to disappear but using getChildFragmentManager() its working fine.
        */
        viewPager.setAdapter(departmentFrag_adapter);    // <----- Set adapter to viewpager
        tabLayout.setupWithViewPager(viewPager);        // <-------- set ViewPager to tabLayout


        // Naming Tabs , otherwise  blank will be displayed

        tabLayout.getTabAt(0).setText("Computer Science");
        tabLayout.getTabAt(1).setText("Textile Technology");

        return view;
    }
}
