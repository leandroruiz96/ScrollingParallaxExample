package com.github.leandroruiz96.scrollingparallax;

import android.database.Observable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by leuleuleu on 25/9/17.
 */

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {

    RecyclerView reference;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_header,parent,false);
            return new HeaderViewHolder(v);
        } else {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_text, parent, false);
            return new ItemViewHolder(v);
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (getItemViewType(position) == 0) {

        } else {

        }
    }

    @Override
    public int getItemCount() {
        return 30 + 3;
    }

    @Override
    public int getItemViewType(int position) {
        return position%10==0?0:1;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        reference = recyclerView;
    }

    public abstract class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    private class ItemViewHolder extends ViewHolder {

        public ItemViewHolder(View itemView) {
            super(itemView);
        }
    }

    private class HeaderViewHolder extends ViewHolder {

        TextView mText;
        ImageView mImageView;

        public HeaderViewHolder(final View itemView) {
            super(itemView);

            mText = itemView.findViewById(R.id.mText);
            mImageView = itemView.findViewById(R.id.mImageView);


            reference.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    int currentDelta = HeaderViewHolder.this.itemView.getBottom()-reference.getTop();
                    int max = HeaderViewHolder.this.itemView.getHeight() + recyclerView.getHeight();
                    float percent = ((float)currentDelta)/(float)max;

                    float delta = HeaderViewHolder.this.itemView.getHeight() - mImageView.getHeight();
                    FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) mImageView.getLayoutParams();
                    params.topMargin = (int) (delta * percent);
                    mImageView.setLayoutParams(params);
                }
            });
        }
    }
}
