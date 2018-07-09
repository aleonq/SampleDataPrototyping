package com.support.prototyping.sample.views.recyclerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.ListView;

import com.acme.news.prototypinghelpersupportlibrary.R;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.support.prototyping.sample.views.recyclerview.Constants.IMAGEVIEW_MAX_COUNT;
import static com.support.prototyping.sample.views.recyclerview.Constants.TEXTVIEW_MAX_COUNT;
import static com.support.prototyping.sample.views.recyclerview.Constants.TOTAL_VIEWS_COUNT;

public class SampleEnabledRecyclerView extends RecyclerView {

    public SampleEnabledRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        int listItemLayoutId = 0;
        CharSequence[] charSequence_textview1_data = null;
        CharSequence[] charSequence_textview2_data = null;
        TypedArray imageview1_data = null;

        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SampleEnabledRecyclerView, 0, 0);

            // read id of list item layout
            listItemLayoutId = typedArray.getResourceId(R.styleable.SampleEnabledRecyclerView_listitem, 0);

            Adapter adapter = getPreferredAdapter(listItemLayoutId, typedArray);
            setAdapter(adapter);
            typedArray.recycle();

        } else {
            throw new RuntimeException("Must provide the required attributes");
        }
    }

    private int getResourceId(String resName, Class clazz) {
        int id = 0;
        try {
            Field field = clazz.getField(resName);
            id = field.getInt(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    private Adapter getPreferredAdapter(int listItemId, TypedArray typedArray) {
        int viewsTemp[] = new int[TOTAL_VIEWS_COUNT];
        int count = 0;
        for (int i = 0; i < TEXTVIEW_MAX_COUNT; i++) {
            int viewId = typedArray.getResourceId(getResourceId("SampleEnabledRecyclerView_listitem_text" + (i + 1), R.styleable.class), 0);
            if (viewId != 0) {
                viewsTemp[count++] = viewId;
            }
        }
        for (int i = 0; i < IMAGEVIEW_MAX_COUNT; i++) {
            int viewId = typedArray.getResourceId(getResourceId("SampleEnabledRecyclerView_listitem_image" + (i + 1), R.styleable.class), 0);
            if (viewId != 0) {
                viewsTemp[count++] = viewId;
            }
        }
        int[] views = new int[count];
        for (int i = 0; i < count; i++) {
            views[i] = viewsTemp[i];
        }
        Object[] data = new Object[views.length];
        count = 0;
        for (int i = 0; i < TEXTVIEW_MAX_COUNT; i++) {
            CharSequence[] charSequence_textview1_data = typedArray.getTextArray(getResourceId("SampleEnabledRecyclerView_listdata_text" + (i + 1), R.styleable.class));
            if (charSequence_textview1_data != null && charSequence_textview1_data.length > 0) {
                data[count++] = Arrays.asList(charSequence_textview1_data);
            }
        }
        for (int i = 0; i < IMAGEVIEW_MAX_COUNT; i++) {
            int imageview1DataArrayResourceId = typedArray.getResourceId(getResourceId("SampleEnabledRecyclerView_listdata_image" + (i + 1), R.styleable.class), 0);
            if (imageview1DataArrayResourceId > 0) {
                TypedArray imageview1_data = getResources().obtainTypedArray(imageview1DataArrayResourceId);
                List<Integer> imageview1_data_list = new ArrayList<>();
                for (int j = 0; j < imageview1_data.length(); j++) {
                    imageview1_data_list.add(imageview1_data.getResourceId(j, 0));
                }
                imageview1_data.recycle();
                if (imageview1_data_list != null && imageview1_data_list.size() > 0) {
                    data[count++] = imageview1_data_list;
                }
            }
        }
        String temp = typedArray.getString(R.styleable.SampleEnabledRecyclerView_list_size);
        int sizeCalculationStrategy = SampleEnabledAdapter_Generic.SIZE_CALCULATION_STRATEGY_LARGEST_REPEAT_SMALLER;
        if (temp != null) {
            try {
                sizeCalculationStrategy = Integer.parseInt(temp);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return new SampleEnabledAdapter_Generic(listItemId, views, data, sizeCalculationStrategy);
    }
}
