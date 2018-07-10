package com.support.prototyping.sample.views.recyclerview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.acme.news.prototypinghelpersupportlibrary.R;

import java.lang.ref.WeakReference;
import java.util.List;

public class SampleEnabledAdapter_Generic extends RecyclerView.Adapter<SampleViewHolder_Generic> {

    public static final int SIZE_CALCULATION_STRATEGY_FIXED = -1;
    public static final int SIZE_CALCULATION_STRATEGY_SMALLEST = 0;
    public static final int SIZE_CALCULATION_STRATEGY_LARGEST_REPEAT_SMALLER = 0;

    private Object[] arrayOfList;
    private int[] viewIds;
    private int listItemId;
    private int sizeCalculationStrategy;
    private Drawable drawable = null;

    public SampleEnabledAdapter_Generic(int listItemId, int[] viewIds, Object[] arrayOfList, int sizeCalculationStrategy) {
        this.listItemId = listItemId;
        this.viewIds = viewIds;
        this.arrayOfList = arrayOfList;
        this.sizeCalculationStrategy = sizeCalculationStrategy;
    }

    @NonNull
    @Override
    public SampleViewHolder_Generic onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(listItemId, viewGroup, false);
        drawable = view.getContext().getResources().getDrawable(R.drawable.ic_action_name);
        return new SampleViewHolder_Generic(view, viewIds);
    }

    @Override
    public void onBindViewHolder(@NonNull SampleViewHolder_Generic sampleViewHoldergeneric, int position) {

        for (int i = 0; i < sampleViewHoldergeneric.views.length; i++) {
            View view = sampleViewHoldergeneric.views[i];
            if (view instanceof ImageView) {
                ((ImageView) view).setImageDrawable(drawable);
                new ImageLoader(view, sampleViewHoldergeneric, position).execute((Integer) ((List) arrayOfList[i]).get(position));
            } else if (view instanceof TextView) {
                ((TextView) view).setText((CharSequence) ((List) arrayOfList[i]).get(position));
            }
        }
    }

    @Override
    public int getItemCount() {
        // ToDo: return the size taken by user. In that case bind the Mod of data to views if data runs short
        switch (sizeCalculationStrategy) {
            case SIZE_CALCULATION_STRATEGY_SMALLEST:
                return getSmallestListSize();
        }
        return sizeCalculationStrategy;
    }

    private int getSmallestListSize() {
        int minSize = Integer.MAX_VALUE;
        for (Object list : arrayOfList) {
            int size = ((List) list).size();
            if (size < minSize) {
                minSize = size;
            }
        }
        return minSize;
    }

    static class ImageLoader extends AsyncTask<Integer, Void, Bitmap> {

        BitmapFactory.Options options;
        private WeakReference<ImageView> view;
        private WeakReference<SampleViewHolder_Generic> viewHolder_generic;
        private int position;

        ImageLoader(View view, SampleViewHolder_Generic viewHolder_generic, int position) {
            this.view = new WeakReference<>((ImageView) view);
            this.viewHolder_generic = new WeakReference<>(viewHolder_generic);
            this.position = position;
            options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.ALPHA_8;
        }


        @Override
        protected Bitmap doInBackground(Integer... ids) {
            return getDrawable(ids[0]);
        }

        private Bitmap getDrawable(Integer id) {
            Bitmap bitmap = null;
            try {
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeResource(view.get().getContext().getResources(), id, options);
                options.inSampleSize = 4;
                options.inJustDecodeBounds = false;
                bitmap = BitmapFactory.decodeResource(view.get().getContext().getResources(), id, options);
                if (bitmap == null) {
                    // May be a vector asset
                    bitmap = getBitmapFromVectorDrawable(view.get().getContext(), id);
                }
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        private Bitmap getBitmapFromVectorDrawable(Context context, int drawableId) {
            Drawable drawable = ContextCompat.getDrawable(context, drawableId);
            drawable = (DrawableCompat.wrap(drawable)).mutate();
            Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
                    drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            try {
                if (position != viewHolder_generic.get().getAdapterPosition()) {

                } else {
                    view.get().setImageBitmap(bitmap);
                }
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }

    }
}

class ImageLoaderManager {

    public ImageLoaderManager() {

    }

}

class SampleViewHolder_Generic extends RecyclerView.ViewHolder {

    View[] views;

    SampleViewHolder_Generic(@NonNull View itemView, int... viewIds) {
        super(itemView);
        views = new View[viewIds.length];
        for (int i = 0; i < viewIds.length; i++) {
            views[i] = itemView.findViewById(viewIds[i]);
        }
    }
}