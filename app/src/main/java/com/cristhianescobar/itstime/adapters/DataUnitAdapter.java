package com.cristhianescobar.itstime.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cristhianescobar.itstime.data.DataUnit;
import com.cristhianescobar.itstime.R;

import java.util.Collections;
import java.util.List;

/**
 * Created by cristhian.escobar on 6/17/15.
 */
public class DataUnitAdapter extends RecyclerView.Adapter<DataUnitAdapter.DataUnitViewHolder> {

    private Context mContext;
    private LayoutInflater inflator;

    private List<DataUnit> data = Collections.emptyList();

    public DataUnitAdapter(Context context, List<DataUnit> sourceData){
        inflator = LayoutInflater.from(context);
        mContext = context;
        data = sourceData;
    }

    public void addItem(){
        DataUnit d = new DataUnit();
        d.iconId = R.drawable.ic_textsms_white24dp;
        d.title = "Reminder";
        data.add(d);
        notifyDataSetChanged();
    }

    @Override
    public DataUnitViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View root = inflator.inflate(R.layout.reminder_item, parent , false);
        Log.d("DataUnitAdapter", "On CreateViewHolder called!");
        DataUnitViewHolder dataUnitViewHolder =  new DataUnitViewHolder(root);

        return dataUnitViewHolder;
    }

    @Override
    public void onBindViewHolder(DataUnitViewHolder holder, int position) {
        DataUnit current = data.get(position) ;
        Log.d("DataUnitAdapter", "On onBindViewHolder called " + position);
        holder.title.setText(current.title);
        holder.image.setImageResource(current.iconId);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    class DataUnitViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView title;
        ImageView image;

        public DataUnitViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.list_text);
            image = (ImageView) itemView.findViewById(R.id.list_icon);
            image.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
//            Toast.makeText(mContext,"Clicked " + getPosition(), Toast.LENGTH_SHORT).show();
            addItem();
        }
    }
}
