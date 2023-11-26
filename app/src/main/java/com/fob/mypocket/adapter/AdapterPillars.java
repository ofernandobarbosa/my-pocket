package com.fob.mypocket.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fob.mypocket.R;
import com.fob.mypocket.model.Pillar;

import java.util.List;

public class AdapterPillars extends RecyclerView.Adapter<AdapterPillars.MyViewHolder> {

    private List<Pillar> pillarList;

    public AdapterPillars(List<Pillar> list){
        this.pillarList = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_pillars_list, parent, false);

        return new AdapterPillars.MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Pillar itemList = pillarList.get(position);

        holder.title.setText(itemList.getTitle());
        holder.expendValue.setText(itemList.getExpendValue());
        holder.availableValue.setText(itemList.getAvailableValue());
        holder.representativeImage.setImageResource(itemList.getRepresentativeImage());

    }

    @Override
    public int getItemCount() {
        if (pillarList.size() > 10){
            return 5;
        }
        return pillarList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        TextView expendValue;
        TextView availableValue;
        ImageView representativeImage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.tv_pillar_card_title);
            expendValue = itemView.findViewById(R.id.tv_pillar_expend_value);
            availableValue = itemView.findViewById(R.id.tv_pillar_available_value);
            representativeImage = itemView.findViewById(R.id.iv_pillar_card);

        }
    }

}
