package ru.tpu.lab2;

import android.content.Context;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

import androidx.annotation.Nullable;

public class ViewsContainer extends LinearLayout {

    public ArrayList<Entry> list;

    public ViewsContainer(Context context) {
        super(context);
        setSaveEnabled(true);
    }

    public ViewsContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
        setSaveEnabled(true);
        list = new ArrayList<>();
    }

    public void addView(Entry newEntry) {
        list.add(newEntry);
        Collections.sort(list);
        removeAllViews();

        for (Entry entry: list) {
            LinearLayout ll = new LinearLayout(getContext());
            LayoutParams parentParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
            parentParams.topMargin = dpsToPx(8);
            ll.setLayoutParams(parentParams);
            addView(ll);
            {
                TextView tvName = new TextView(this.getContext());
                LayoutParams params = new LayoutParams(dpsToPx(170), LinearLayout.LayoutParams.WRAP_CONTENT);
                tvName.setLayoutParams(params);
                tvName.setText(entry.name);
                tvName.setTextColor(getResources().getColor(R.color.colorBlack));
                ll.addView(tvName);
            }
            {
                View rect = new View(getContext());
                double width = entry.rating * 12;
                LayoutParams params = new LayoutParams(dpsToPx(width), dpsToPx(12));
                params.setMargins(dpsToPx(8), dpsToPx(4), dpsToPx(128 - width), 0);
                rect.setLayoutParams(params);
                rect.setBackgroundColor(getResources().getColor(R.color.colorBlack));
                ll.addView(rect);
            }
            {
                TextView tvRating = new TextView(this.getContext());
                tvRating.setText(String.valueOf(entry.rating));
                tvRating.setTextColor(getResources().getColor(R.color.colorBlack));
                ll.addView(tvRating);
            }
        }
    }

    @Nullable
    @Override
    protected Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();
        SavedState ss = new SavedState(superState);
        ss.list = list;
        return ss;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        SavedState ss = (SavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());
        for (Entry entry : ss.list) {
            addView(entry);
        }
    }

    int dpsToPx(double dps) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dps * scale + 0.5f);
    }
}
