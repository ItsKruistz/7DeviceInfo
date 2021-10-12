/*
 * This is the source code of 7 Device Info.
 * It is licensed under the The GNU General Public License v3.0.
 * You should have received a copy of the license in this repo (see LICENSE).
 *
 * Copyright someone work on this am bored, 2021.
 *
 * Copyright, Abhinandan, 2021. 
 * I contributed to this thing tho. 
 */
package notteshock.deviceinfo.activities;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import notteshock.deviceinfo.R;
import notteshock.deviceinfo.components.actionbar.BaseFragment;
import notteshock.deviceinfo.fragments.HomeFragment;
import notteshock.deviceinfo.fragments.InfoFragment;
import notteshock.deviceinfo.fragments.ToolsFragment;
import notteshock.deviceinfo.utilities.NotificationCenter;

public class HomeActivity extends BaseFragment {

    //i am bored, 69

    private Context context;

    public static HomeActivity newInstance() {
        return new HomeActivity();
    }

    @Override
    public boolean onFragmentCreate() {
        return super.onFragmentCreate();
    }

    @Override
    public void onFragmentDestroy() {
        super.onFragmentDestroy();
    }

    @Override
    public View createView(Context context) {
        this.context = context;
        LayoutInflater inflater = LayoutInflater.from(context);
        fragmentView = new FrameLayout(context);
        View view = inflater.inflate(R.layout.activity_home, (ViewGroup) fragmentView, false);
        ((ViewGroup) fragmentView).addView(view);
        actionBar.setAddToContainer(false);

        BottomNavigationView bottomNavigation = view.findViewById(R.id.bottomNavigation);

        bottomNavigation.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.homeTab:
                    presentFragment(new SensorsActivity());
                    break;
            }
            return false;
        });

        return fragmentView;
    }
}
