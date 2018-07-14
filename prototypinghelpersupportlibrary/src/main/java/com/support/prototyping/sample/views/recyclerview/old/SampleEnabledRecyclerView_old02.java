package com.support.prototyping.sample.views.recyclerview.old;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.acme.news.prototypinghelpersupportlibrary.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Deprecated
public class SampleEnabledRecyclerView_old02 extends RecyclerView {

    public SampleEnabledRecyclerView_old02(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        int listItemLayoutId = 0;
        CharSequence[] charSequence_textview1_data = null;
        CharSequence[] charSequence_textview2_data = null;
        TypedArray imageview1_data = null;

        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SampleEnabledRecyclerView, 0, 0);

            // read listTextview1Data array
            charSequence_textview1_data = typedArray.getTextArray(R.styleable.SampleEnabledRecyclerView_listdata_text1);
            charSequence_textview2_data = typedArray.getTextArray(R.styleable.SampleEnabledRecyclerView_listdata_text2);
            int imageview1DataArrayResourceId = typedArray.getResourceId(R.styleable.SampleEnabledRecyclerView_listdata_image1, 0);
            imageview1_data = context.getResources().obtainTypedArray(imageview1DataArrayResourceId);
            List<Integer> imageview1_data_list = new ArrayList<>();
            for (int i = 0; i < imageview1_data.length(); i++) {
                imageview1_data_list.add(imageview1_data.getResourceId(i, 0));
            }
            imageview1_data.recycle();
            // read id of list item layout
            listItemLayoutId = typedArray.getResourceId(R.styleable.SampleEnabledRecyclerView_listitem, 0);

            Class className = findMatchingAdapter(typedArray);
            Adapter adapter = getPreferredAdapter(className, typedArray, listItemLayoutId, Arrays.asList(charSequence_textview1_data), imageview1_data_list, Arrays.asList(charSequence_textview2_data));
            setAdapter(adapter);
            typedArray.recycle();

        } else {
            throw new RuntimeException("Must provide the required attributes");
        }
    }

    private Class findMatchingAdapter(TypedArray typedArray) {
        int tv1_id = typedArray.getResourceId(R.styleable.SampleEnabledRecyclerView_listitem_text1, 0);
        int tv2_id = typedArray.getResourceId(R.styleable.SampleEnabledRecyclerView_listitem_text1, 0);
        int iv1_id = typedArray.getResourceId(R.styleable.SampleEnabledRecyclerView_listitem_image1, 0);
        if (tv1_id != 0) {
            if (tv2_id != 0) {
                if (iv1_id != 0) {
                    return SampleEnabledAdapter_Text2_Image1.class;
                }
            }
            if (iv1_id != 0) {
                return SampleEnabledAdapter_Text1_Image1.class;
            }
        }
        throw new RuntimeException("Must provide required attributes, No Matching Adapter found");
    }

    private Adapter getPreferredAdapter(Class clazz, TypedArray typedArray, Object... params) {

        if (clazz == SampleEnabledAdapter_Text1_Image1.class) {
            int tv1_id = typedArray.getResourceId(R.styleable.SampleEnabledRecyclerView_listitem_text1, 0);
            int iv1_id = typedArray.getResourceId(R.styleable.SampleEnabledRecyclerView_listitem_image1, 0);
            return new SampleEnabledAdapter_Text1_Image1((Integer) params[0],
                    tv1_id, iv1_id,
                    (List<CharSequence>) params[1], (List<Integer>) params[2]);
        } else if (clazz == SampleEnabledAdapter_Text2_Image1.class) {
            int tv1_id = typedArray.getResourceId(R.styleable.SampleEnabledRecyclerView_listitem_text1, 0);
            int tv2_id = typedArray.getResourceId(R.styleable.SampleEnabledRecyclerView_listitem_text2, 0);
            int iv1_id = typedArray.getResourceId(R.styleable.SampleEnabledRecyclerView_listitem_image1, 0);
            return new SampleEnabledAdapter_Text2_Image1((Integer) params[0],
                    tv1_id, tv2_id, iv1_id,
                    (List<CharSequence>) params[1], (List<Integer>) params[2], (List<CharSequence>) params[3]);
        }
        return null;
    }
}
