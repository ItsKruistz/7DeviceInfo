/*
 * This is the source code of 7 Device Info.
 * It is licensed under the The GNU General Public License v3.0.
 * You should have received a copy of the license in this repo (see LICENSE).
 *
 * Copyright lahds13, 2021.
 */

package notteshock.deviceinfo.activities;

import static android.content.Context.SENSOR_SERVICE;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import notteshock.deviceinfo.R;
import notteshock.deviceinfo.adapters.SensorsListAdapter;
import notteshock.deviceinfo.components.actionbar.BaseFragment;
import notteshock.deviceinfo.utilities.NotificationCenter;

public class SensorsActivity extends BaseFragment {

    private Context context;
    private RecyclerView sensorsList;
    private HashMap<String, Object> Map = new HashMap<>();
    private static List<Sensor> sensors = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> sensorsMap = new ArrayList<>();

    public static SensorsActivity newInstance() {
        return new SensorsActivity();
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
        View view = inflater.inflate(R.layout.activity_sensors, (ViewGroup) fragmentView, false);
        ((ViewGroup) fragmentView).addView(view);
        actionBar.setAddToContainer(false);
        sensorsList = view.findViewById(R.id.sensorsList);
        LinearLayoutManager SensorsListManager = new LinearLayoutManager(getParentActivity(), LinearLayoutManager.VERTICAL, false);
        SensorsListManager.setStackFromEnd(false);
        sensorsList.setLayoutManager(SensorsListManager);
        getSensors();
        return fragmentView;
    }

    public void getSensors() {
        SensorManager sensorManager = (SensorManager) getParentActivity().getSystemService(SENSOR_SERVICE);
        List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        StringBuilder message = new StringBuilder(2048);
        message.append("The sensors on this device are:\n\n");

        for(Sensor sensor : sensors) {
            HashMap<String, Object> item = new HashMap<>();
            item.put("sensor", sensor.getName());
            item.put("type", sensor.getType());
            item.put("vendor", sensor.getVendor());
            item.put("version", sensor.getVersion());
            item.put("resolution", sensor.getResolution());
            item.put("range", sensor.getMaximumRange());
            item.put("power", sensor.getPower());
            sensorsMap.add(item);
            sensorsList.setAdapter(new SensorsListAdapter(context, sensorsMap));
        }
    }
}