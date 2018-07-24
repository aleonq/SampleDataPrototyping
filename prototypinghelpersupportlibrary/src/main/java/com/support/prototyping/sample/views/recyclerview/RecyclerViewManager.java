package com.support.prototyping.sample.views.recyclerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.acme.news.prototypinghelpersupportlibrary.R;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.support.prototyping.sample.views.recyclerview.Constants.IMAGEVIEW_MAX_COUNT;
import static com.support.prototyping.sample.views.recyclerview.Constants.TEXTVIEW_MAX_COUNT;
import static com.support.prototyping.sample.views.recyclerview.Constants.TOTAL_VIEWS_COUNT;
import static com.support.prototyping.sample.views.recyclerview.ErrorMessage.IMAGEVIEW_DATASOURCE_EMPTY;
import static com.support.prototyping.sample.views.recyclerview.ErrorMessage.IMAGEVIEW_DATASOURCE_MISSING;
import static com.support.prototyping.sample.views.recyclerview.ErrorMessage.IMAGEVIEW_ID_MISSING;
import static com.support.prototyping.sample.views.recyclerview.ErrorMessage.TEXTVIEW_DATASOURCE_EMPTY;
import static com.support.prototyping.sample.views.recyclerview.ErrorMessage.TEXTVIEW_DATASOURCE_MISSING;
import static com.support.prototyping.sample.views.recyclerview.ErrorMessage.TEXTVIEW_ID_MISSING;

class RecyclerViewManager {

    private AttributeSet attrs;
    private Context context;
    private RecyclerView.Adapter adapter;

    RecyclerViewManager(AttributeSet attrs, Context context) {
        this.attrs = attrs;
        this.context = context;
    }

    void init(int styleable[]) {
        //        validate attributes
        if (attrs == null) {
            String msg = String.format("%s%s%s", ErrorMessage.ATTRIBUTE_NULL, ", AttributeSet: ", "null");
            throw new IllegalArgumentException(msg);
        }
        TypedArray typedArray = context.obtainStyledAttributes(attrs, styleable, 0, 0);

        // read id of list item layout
        int listItemLayoutId = typedArray.getResourceId(R.styleable.SampleEnabledRecyclerView_listitem, 0);
        if (listItemLayoutId == 0) {
            // listitem ID is not defined in xml
            String msg = String.format("%s %s %s",
                    ErrorMessage.LISTITEM_MISSING,
                    ErrorMessage.MISSING_ATTRIBUTE,
                    "listitem");
            throw new IllegalArgumentException(msg);
        }
        this.adapter = getAdapter(listItemLayoutId, typedArray);
        typedArray.recycle();
    }

    RecyclerView.Adapter getAdapter() {
        return adapter;
    }

    private RecyclerView.Adapter getAdapter(int listItemId, TypedArray typedArray) {

        int viewsTemp[] = new int[TOTAL_VIEWS_COUNT];
        Object[] tempData = new Object[TOTAL_VIEWS_COUNT];

        int count = 0;

        // Find TextView IDs and data source(String's array) and put in view ID array and data array
        for (int i = 0; i < TEXTVIEW_MAX_COUNT; i++) {
            int viewId = typedArray.getResourceId(getResourceId("SampleEnabledRecyclerView_listitem_text" + (i + 1), R.styleable.class), 0);
            CharSequence[] charSequence_textview_data = typedArray.getTextArray(getResourceId("SampleEnabledRecyclerView_listdata_text" + (i + 1), R.styleable.class));

            if (viewId != 0 && (charSequence_textview_data != null && charSequence_textview_data.length > 0)) {
                viewsTemp[count] = viewId;
                tempData[count] = Arrays.asList(charSequence_textview_data);
                count++;
            } else {
                if (viewId == 0 && (charSequence_textview_data == null || charSequence_textview_data.length == 0)) {
                    // Do nothing
                } else if (viewId != 0 && charSequence_textview_data == null) {
                    String msg = String.format("%s %s %s",
                            TEXTVIEW_DATASOURCE_MISSING,
                            ErrorMessage.MISSING_ATTRIBUTE,
                            "charSequence_textview_data");
                    throw new IllegalArgumentException(msg);
                } else if (viewId != 0) {
                    String msg = String.format("%s %s %s",
                            TEXTVIEW_DATASOURCE_EMPTY,
                            ErrorMessage.EMPTY_DATA,
                            "charSequence_textview_data");
                    throw new IllegalArgumentException(msg);
                } else {
                    String msg = String.format("%s %s %s",
                            TEXTVIEW_ID_MISSING,
                            ErrorMessage.MISSING_ATTRIBUTE,
                            "SampleEnabledRecyclerView_listitem_text");
                    throw new IllegalArgumentException(msg);
                }
            }
        }

        // Find ImageView IDs and data source(drawables' array) and put in view ID array and data array
        for (int i = 0; i < IMAGEVIEW_MAX_COUNT; i++) {
            int viewId = typedArray.getResourceId(getResourceId("SampleEnabledRecyclerView_listitem_image" + (i + 1), R.styleable.class), 0);
            int imageviewDataArrayResourceId = typedArray.getResourceId(getResourceId("SampleEnabledRecyclerView_listdata_image" + (i + 1), R.styleable.class), 0);

            if (viewId != 0 && imageviewDataArrayResourceId > 0) {

                TypedArray imageview1_data = context.getResources().obtainTypedArray(imageviewDataArrayResourceId);
                List<Integer> imageview1_data_list = new ArrayList<>();
                for (int j = 0; j < imageview1_data.length(); j++) {
                    imageview1_data_list.add(imageview1_data.getResourceId(j, 0));
                }
                imageview1_data.recycle();
                if (imageview1_data_list.size() > 0) {
                    viewsTemp[count] = viewId;
                    tempData[count] = imageview1_data_list;
                    count++;
                } else {
                    String msg = String.format("%s %s %s",
                            IMAGEVIEW_DATASOURCE_EMPTY,
                            ErrorMessage.EMPTY_DATA,
                            "SampleEnabledRecyclerView_listdata_image");
                    throw new IllegalArgumentException(msg);
                }
            } else {
                if (viewId == 0 && imageviewDataArrayResourceId <= 0) {
                    // Do Nothing
                } else if (viewId != 0) {
                    String msg = String.format("%s %s %s",
                            IMAGEVIEW_DATASOURCE_MISSING,
                            ErrorMessage.MISSING_ATTRIBUTE,
                            "imageview1DataArrayResourceId");
                    throw new IllegalArgumentException(msg);
                } else {
                    String msg = String.format("%s %s %s",
                            IMAGEVIEW_ID_MISSING,
                            ErrorMessage.MISSING_ATTRIBUTE,
                            "SampleEnabledRecyclerView_listitem_image");
                    throw new IllegalArgumentException(msg);
                }
            }
        }

        // Copy the temp array data to exact length array
        int[] views = new int[count];
        System.arraycopy(viewsTemp, 0, views, 0, count);

        Object[] data = new Object[count];
        System.arraycopy(tempData, 0, data, 0, count);

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
}
