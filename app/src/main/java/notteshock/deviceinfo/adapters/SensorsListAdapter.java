/*
 * This is the source code of 7 Device Info.
 * It is licensed under the The GNU General Public License v3.0.
 * You should have received a copy of the license in this repo (see LICENSE).
 *
 * Copyright lahds13, 2021.
 */

package notteshock.deviceinfo.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

import notteshock.deviceinfo.R;

public class SensorsListAdapter extends RecyclerView.Adapter<SensorsListAdapter.ViewHolder> {

    private ArrayList<HashMap<String, Object>> dataMap;
    private LayoutInflater layoutInflater;
    private ItemClickListener itemClickListener;

    public SensorsListAdapter(Context context, ArrayList<HashMap<String, Object>> data) {
        this.layoutInflater = LayoutInflater.from(context);
        this.dataMap = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.sensors_item, parent, false);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(layoutParams);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        View view = holder.itemView;

        final TextView sensor_name = view.findViewById(R.id.sensor_name);
        final TextView sensor_type = view.findViewById(R.id.sensor_type);
        final TextView sensor_vendor = view.findViewById(R.id.sensor_vendor);
        final TextView sensor_version = view.findViewById(R.id.sensor_version);
        final TextView sensor_resolution = view.findViewById(R.id.sensor_resolution);
        final TextView sensor_range = view.findViewById(R.id.sensor_range);
        final TextView sensor_power = view.findViewById(R.id.sensor_power);

        sensor_name.setText(dataMap.get(position).get("sensor").toString());
        if (dataMap.get(position).containsKey("type"))
            sensor_type.setText("Type: ".concat(dataMap.get(position).get("type").toString()));
        if (dataMap.get(position).containsKey("version"))
            sensor_version.setText("Version: ".concat(dataMap.get(position).get("version").toString()));
        if (dataMap.get(position).containsKey("range"))
            sensor_range.setText("Range: ".concat(dataMap.get(position).get("range").toString()));
        if (dataMap.get(position).containsKey("vendor"))
            sensor_vendor.setText("Vendor: ".concat(dataMap.get(position).get("vendor").toString()));
        if (dataMap.get(position).containsKey("resolution"))
            sensor_resolution.setText("Resolution: ".concat(dataMap.get(position).get("resolution").toString()));
        if (dataMap.get(position).containsKey("power"))
            sensor_power.setText("Power: ".concat(dataMap.get(position).get("power").toString()));
    }

    @Override
    public int getItemCount() {
        return dataMap.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onClick(View view) {
            if (itemClickListener != null) itemClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    HashMap<String, Object> getItem(int id) {
        return dataMap.get(id);
    }

    void setClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}