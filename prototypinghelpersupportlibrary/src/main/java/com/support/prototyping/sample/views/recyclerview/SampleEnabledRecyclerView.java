package com.support.prototyping.sample.views.recyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.acme.news.prototypinghelpersupportlibrary.R;

public class SampleEnabledRecyclerView extends RecyclerView {

    public SampleEnabledRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        RecyclerViewManager recyclerViewManager = new RecyclerViewManager(attrs, context);

        // Should never be null
        assert (R.styleable.SampleEnabledRecyclerView != null);
        recyclerViewManager.init(R.styleable.SampleEnabledRecyclerView);

        setAdapter(recyclerViewManager.getAdapter());
    }
}
