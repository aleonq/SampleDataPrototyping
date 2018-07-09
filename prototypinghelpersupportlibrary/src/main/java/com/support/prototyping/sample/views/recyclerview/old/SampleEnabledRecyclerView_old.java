package com.support.prototyping.sample.views.recyclerview.old;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.acme.news.prototypinghelpersupportlibrary.R;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Deprecated
public class SampleEnabledRecyclerView_old extends RecyclerView {

    private static final Class<?>[] RECYCLERVIEW_ADAPTER_CONSTRUCTOR_SIGNATURE
            = new Class[]{Integer.TYPE, Integer.TYPE, Integer.TYPE, List.class, List.class};

    public SampleEnabledRecyclerView_old(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        int listItemLayoutId = 0;
        CharSequence[] charSequence_textview1_data = null;
        TypedArray imageview1_data = null;
        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SampleEnabledRecyclerView_old, 0, 0);

            // read classname
            String testclassName = null;
            testclassName = a.getString(R.styleable.SampleEnabledRecyclerView_old_item_name_old);
            //            Toast.makeText(context, testclassName, Toast.LENGTH_SHORT).show();

            // read listTextview1Data array
            charSequence_textview1_data = a.getTextArray(R.styleable.SampleEnabledRecyclerView_old_listdata_text1_old);

            int imageview1DataArrayResourceId = a.getResourceId(R.styleable.SampleEnabledRecyclerView_old_listitem_image_icon_array_old, 0);
            imageview1_data = context.getResources().obtainTypedArray(imageview1DataArrayResourceId);
            List<Integer> imageview1_data_list = new ArrayList<>();
            for (int i = 0; i < imageview1_data.length(); i++) {
                imageview1_data_list.add(imageview1_data.getResourceId(i, 0));
            }
            imageview1_data.recycle();
            // read id of list item layout
            listItemLayoutId = a.getResourceId(R.styleable.SampleEnabledRecyclerView_old_listitem_old, 0);
            int tv_desc_id = a.getResourceId(R.styleable.SampleEnabledRecyclerView_old_listitem_text_description_old, 0);
            int iv_icon = a.getResourceId(R.styleable.SampleEnabledRecyclerView_old_listitem_image_icon_old, 0);
            String className = findMatchingAdapter(a, tv_desc_id, iv_icon).getName();
            try {
                ClassLoader classLoader;
                if (this.isInEditMode()) {
                    classLoader = this.getClass().getClassLoader();
                } else {
                    classLoader = context.getClassLoader();
                }
                Class<? extends RecyclerView.Adapter> recyclerViewAdapterClass = classLoader.loadClass(className).asSubclass(RecyclerView.Adapter.class);
                Object[] constructorArgs = null;

                Constructor constructor;
                try {
                    constructor = recyclerViewAdapterClass.getConstructor(RECYCLERVIEW_ADAPTER_CONSTRUCTOR_SIGNATURE);
                    constructorArgs = new Object[]{listItemLayoutId, tv_desc_id, iv_icon, Arrays.asList(charSequence_textview1_data), imageview1_data_list};
                } catch (NoSuchMethodException var13) {
                    try {
                        constructor = recyclerViewAdapterClass.getConstructor();
                    } catch (NoSuchMethodException e) {
                        e.initCause(var13);
                        throw new IllegalStateException(attrs.getPositionDescription() + ": Error creating LayoutManager " + className, e);
                    }
                }
                constructor.setAccessible(true);
                setAdapter((Adapter) constructor.newInstance(constructorArgs));
            } catch (ClassNotFoundException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
                e.printStackTrace();
            }
            a.recycle();

        } else {
            throw new RuntimeException("Must provide the required attributes");
        }
    }

    private Class findMatchingAdapter(TypedArray typedArray, int tv_desc_id, int iv_icon) {
        if (tv_desc_id != 0) {
            if (iv_icon != 0) {
                return SampleEnabledAdapter_old.class;
            }
        }
        throw new RuntimeException("Must provide required attributes, No Matching Adapter found");
    }
}

@Deprecated
class SampleEnabledAdapter_old extends RecyclerView.Adapter<SampleViewHolder_old> {

    private final int id_tv1;
    private final int id_iv1;
    private List<CharSequence> listTextview1Data;
    private List<Integer> listImageview1Data;
    private int listItemId;

    public SampleEnabledAdapter_old(int listItemId,
                                    int id_tv1, int id_iv1,
                                    List<CharSequence> listTextview1Data, List<Integer> listImageview1Data) {
        this.listItemId = listItemId;
        this.id_tv1 = id_tv1;
        this.id_iv1 = id_iv1;
        this.listTextview1Data = listTextview1Data;
        this.listImageview1Data = listImageview1Data;
    }

    @NonNull
    @Override
    public SampleViewHolder_old onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(listItemId, viewGroup, false);
        return new SampleViewHolder_old(view, id_tv1, id_iv1);
    }

    @Override
    public void onBindViewHolder(@NonNull SampleViewHolder_old sampleViewHolder_old, int position) {
        sampleViewHolder_old.imageView.setImageResource(listImageview1Data.get(position));
        Log.d(SampleEnabledAdapter_old.class.getSimpleName(), listImageview1Data.get(position).toString());
        sampleViewHolder_old.textView.setText(listTextview1Data.get(position));
    }

    @Override
    public int getItemCount() {
        return listTextview1Data.size();
    }
}

@Deprecated
class SampleViewHolder_old extends RecyclerView.ViewHolder {

    TextView textView;
    ImageView imageView;

    public SampleViewHolder_old(@NonNull View itemView, int id_tv1, int id_iv1) {
        super(itemView);
        textView = itemView.findViewById(id_tv1);
        imageView = itemView.findViewById(id_iv1);
    }
}
