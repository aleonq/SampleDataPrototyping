package com.support.prototyping.sample.views.recyclerview.old;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class SampleEnabledAdapter_Text2_Image1 extends RecyclerView.Adapter<SampleViewHolder_Text2_Image1> {

    private List<CharSequence> listTextview1Data;
    private List<CharSequence> listTextview2Data;
    private List<Integer> listImageview1Data;
    private int listItemId;
    private int id_textview1;
    private int id_textview2;
    private int id_imageview1;

    public SampleEnabledAdapter_Text2_Image1(int listItemId,
                                             int id_textview1, int id_textview2, int id_imageview1,
                                             List<CharSequence> listTextview1Data, List<Integer> listImageview1Data, List<CharSequence> listTextview2Data) {
        this.listItemId = listItemId;
        this.id_textview1 = id_textview1;
        this.id_textview2 = id_textview2;
        this.id_imageview1 = id_imageview1;
        this.listTextview1Data = listTextview1Data;
        this.listTextview2Data = listTextview2Data;
        this.listImageview1Data = listImageview1Data;
    }

    @NonNull
    @Override
    public SampleViewHolder_Text2_Image1 onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(listItemId, viewGroup, false);
        return new SampleViewHolder_Text2_Image1(view, id_textview1, id_textview2, id_imageview1);
    }

    @Override
    public void onBindViewHolder(@NonNull SampleViewHolder_Text2_Image1 sampleViewHolder_text2_image1, int position) {
        sampleViewHolder_text2_image1.imageView.setImageResource(listImageview1Data.get(position));
        Log.d(SampleEnabledAdapter_Text2_Image1.class.getSimpleName(), listImageview1Data.get(position).toString());
        sampleViewHolder_text2_image1.textView1.setText(listTextview1Data.get(position));
        sampleViewHolder_text2_image1.textView2.setText(listTextview2Data.get(position));
    }

    @Override
    public int getItemCount() {
        return listTextview1Data.size();
    }
}

class SampleViewHolder_Text2_Image1 extends RecyclerView.ViewHolder {

    TextView textView1;
    TextView textView2;
    ImageView imageView;

    public SampleViewHolder_Text2_Image1(@NonNull View itemView, int id_textview1, int id_textview2, int id_imageview1) {
        super(itemView);
        textView1 = itemView.findViewById(id_textview1);
        textView2 = itemView.findViewById(id_textview2);
        imageView = itemView.findViewById(id_imageview1);
    }
}