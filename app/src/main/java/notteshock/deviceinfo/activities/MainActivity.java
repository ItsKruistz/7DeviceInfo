/*
 * This is the source code of 7 Device Info.
 * It is licensed under the The GNU General Public License v3.0.
 * You should have received a copy of the license in this repo (see LICENSE).
 *
 * Copyright lahds13, 2021.
 */

package notteshock.deviceinfo.activities;

import android.app.Fragment;
import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import notteshock.deviceinfo.R;
import notteshock.deviceinfo.components.actionbar.ActionBarLayout;
import notteshock.deviceinfo.components.actionbar.BaseFragment;
import notteshock.deviceinfo.fragments.HomeFragment;
import notteshock.deviceinfo.utilities.NotificationCenter;

public class MainActivity extends AppCompatActivity {

    private ActionBarLayout actionBarLayout;
    private final ArrayList<BaseFragment> mainFragmentStack = new ArrayList<>();

    @Override
    public void onAttachedToWindow() {
        NotificationCenter.getInstance().addObserver(this, NotificationCenter.closeChats);
        super.onAttachedToWindow();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FrameLayout layout_container = findViewById(R.id.containerLayout);
        actionBarLayout = new ActionBarLayout(this);

        layout_container.addView(actionBarLayout);
        actionBarLayout.init(mainFragmentStack);
        actionBarLayout.setDelegate(new ActionBarLayout.ActionBarLayoutDelegate() {
            @Override
            public boolean onPreIme() {
                return false;
            }

            @Override
            public boolean needPresentFragment(BaseFragment fragment, boolean removeLast, boolean forceWithoutAnimation, ActionBarLayout layout) {
                return true;
            }

            @Override
            public boolean needAddFragmentToStack(BaseFragment fragment, ActionBarLayout layout) {
                return true;
            }

            @Override
            public boolean needCloseLastFragment(ActionBarLayout layout) {
                if(layout.fragmentsStack.size() <= 1){
                    finish();
                    return false;
                }
                return true;
            }

            @Override
            public void onRebuildAllFragments(ActionBarLayout layout) {

            }

        });
        actionBarLayout.presentFragment(new HomeActivity());
    }

    public void replaceFragment(Fragment fragment) {
        getFragmentManager().beginTransaction().replace(R.id.containerLayout, fragment).commit();
    }

    @Override
    public void onBackPressed() {
        actionBarLayout.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        NotificationCenter.getInstance().removeObserver(this, NotificationCenter.closeChats);
        super.onDestroy();
    }
}