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

@Deprecated
public class SampleEnabledAdapter_Text1_Image1 extends RecyclerView.Adapter<SampleViewHolder_Text1_Image1> {

    private final int id_textview1;
    private final int id_imageview1;
    private List<CharSequence> listTextview1Data;
    private List<Integer> listImageview1Data;
    private int listItemId;

    public SampleEnabledAdapter_Text1_Image1(int listItemId,
                                             int id_textview1, int id_imageview1,
                                             List<CharSequence> listTextview1Data, List<Integer> listImageview1Data) {
        this.listItemId = listItemId;
        this.id_textview1 = id_textview1;
        this.id_imageview1 = id_imageview1;
        this.listTextview1Data = listTextview1Data;
        this.listImageview1Data = listImageview1Data;
    }

    @NonNull
    @Override
    public SampleViewHolder_Text1_Image1 onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(listItemId, viewGroup, false);
        return new SampleViewHolder_Text1_Image1(view, id_textview1, id_imageview1);
    }

    @Override
    public void onBindViewHolder(@NonNull SampleViewHolder_Text1_Image1 sampleViewHolderText1Image1, int position) {
        sampleViewHolderText1Image1.imageView.setImageResource(listImageview1Data.get(position));
        Log.d(SampleViewHolder_Text1_Image1.class.getSimpleName(), listImageview1Data.get(position).toString());
        sampleViewHolderText1Image1.textView.setText(listTextview1Data.get(position));
    }

    @Override
    public int getItemCount() {
        return listTextview1Data.size();
    }
}

@Deprecated
class SampleViewHolder_Text1_Image1 extends RecyclerView.ViewHolder {

    TextView textView;
    ImageView imageView;

    public SampleViewHolder_Text1_Image1(@NonNull View itemView, int id_textview1, int id_imageview1) {
        super(itemView);
        textView = itemView.findViewById(id_textview1);
        imageView = itemView.findViewById(id_imageview1);
    }
}