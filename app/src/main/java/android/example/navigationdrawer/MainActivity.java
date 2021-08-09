package android.example.navigationdrawer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    Toolbar toolbar;           //  androidx.widget.Toolbar
    NavigationView Niew;

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer);

        Niew = (NavigationView) findViewById(R.id.navigation_view);
        // Listener
        Niew.setNavigationItemSelectedListener(this);


        // Set Header
        View headerview = Niew.inflateHeaderView(R.layout.drawer_header);   // get the header view (We are not including  header from navigation view in xml but it is possible to do so)
        // For circle shape image in header
        CircleImageView drawerHeaderImage = (CircleImageView) headerview.findViewById(R.id.imgHeader);


        toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();

        // load default fragment

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container_fragment,new homeFrag());
        fragmentTransaction.commit();

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        // Invoking Individual fragments

        // Home
        if(item.getItemId() == R.id.home)
        {
            drawerLayout.closeDrawer(Gravity.START);    // Closes the drawer as soon as menu option is clicked
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_fragment, new homeFrag());
            toolbar.setTitle("Home");
            fragmentTransaction.commit();
        }

        // Department
        if(item.getItemId() == R.id.dep)
        {
            drawerLayout.closeDrawer(Gravity.START);    // Closes the drawer as soon as menu option is clicked
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_fragment, new fragment_department());
            toolbar.setTitle("Departments");
            fragmentTransaction.commit();
        }

        // Study Material

        if(item.getItemId() == R.id.study_material)
        {
            drawerLayout.closeDrawer(Gravity.START);    // Closes the drawer as soon as menu option is clicked
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_fragment,new studyMaterial());
            toolbar.setTitle("Study Material");
            fragmentTransaction.commit();
        }


        // Feedback
        if(item.getItemId() == R.id.feedback)
        {
            drawerLayout.closeDrawer(Gravity.START);    // Closes the drawer as soon as menu option is clicked
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_fragment, new feedback());
            toolbar.setTitle("Feedback");
            fragmentTransaction.commit();
        }
      return true;

    }



}