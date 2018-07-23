package com.example.thomaschaboud.mini_projet;

/**
 * Created by thomaschaboud on 28/03/2017.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

public class MeteoAdapter extends ArrayAdapter<Meteo>{
    public HashMap<String, Integer> pictos;

    public MeteoAdapter(Context context, List<Meteo> previsions, HashMap<String, Integer> pictos) {
        super(context, 0, previsions);
        this.pictos = pictos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_listview_item,parent, false);
        }

        MeteoViewHolder viewHolder = (MeteoViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new MeteoViewHolder();
            viewHolder.icomatin = (ImageView) convertView.findViewById(R.id.tempsmatin);
            viewHolder.icomidi = (ImageView) convertView.findViewById(R.id.tempsmidi);
            viewHolder.icoapmidi = (ImageView) convertView.findViewById(R.id.tempsapmidi);
            viewHolder.icosoir = (ImageView) convertView.findViewById(R.id.tempssoir);
            viewHolder.nom = (TextView) convertView.findViewById(R.id.titre);
            viewHolder.tempe_matin = (TextView) convertView.findViewById(R.id.tempe_matin);
            viewHolder.tempe_midi = (TextView) convertView.findViewById(R.id.tempe_midi);
            viewHolder.tempe_apmidi = (TextView) convertView.findViewById(R.id.tempe_apmidi);
            viewHolder.tempe_soir = (TextView) convertView.findViewById(R.id.tempe_soir);
            convertView.setTag(viewHolder);
        }

        Meteo m = getItem(position);
        viewHolder.nom.setText(m.getTitle());
        viewHolder.icomatin.setImageResource(pictos.get(m.getPicto_matin()));
        viewHolder.icomidi.setImageResource(pictos.get(m.getPicto_midi()));
        viewHolder.icoapmidi.setImageResource(pictos.get(m.getPicto_apmidi()));
        viewHolder.icosoir.setImageResource(pictos.get(m.getPicto_soir()));
        viewHolder.tempe_matin.setText(m.getTempe_matin());
        viewHolder.tempe_midi.setText(m.getTempe_midi());
        viewHolder.tempe_apmidi.setText(m.getTempe_apmidi());
        viewHolder.tempe_soir.setText(m.getTempe_soir());

        return convertView;
    }

    public class MeteoViewHolder {
        public ImageView icomatin;
        public ImageView icomidi;
        public ImageView icoapmidi;
        public ImageView icosoir;
        public TextView nom;
        public TextView tempe_matin;
        public TextView tempe_midi;
        public TextView tempe_apmidi;
        public TextView tempe_soir;
    }

}

