package com.tm.view.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.tm.R;
import com.tm.helper.GlideHelper;
import com.tm.models.home.CompatibilityQuestion;

import java.util.List;

public class HomeListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<CompatibilityQuestion> itemsList;
    private View.OnClickListener mOnClickListener;

    public HomeListAdapter(List<CompatibilityQuestion> itemsList, View.OnClickListener clickListener) {
        this.itemsList = itemsList;
        mOnClickListener = clickListener;
    }

    @Override
    public ProductHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list_home, null);
        ProductHolder mh = new ProductHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof ProductHolder) {
            ProductHolder holder = (ProductHolder) viewHolder;
            holder.tvTitle.setText(itemsList.get(viewHolder.getAdapterPosition()).getQuestion());
            GlideHelper.Companion.getInstance().loadImage(holder.ivThumb, itemsList.get(viewHolder.getAdapterPosition()).getStyle().getThumb());
            holder.itemView.setTag(R.id.product, itemsList.get(viewHolder.getAdapterPosition()));
            holder.itemView.setTag(R.id.position, viewHolder.getAdapterPosition());
        }
    }

    @Override
    public int getItemCount() {
        return (null != itemsList ? itemsList.size() : 0);
    }

    public class ProductHolder extends RecyclerView.ViewHolder {

        protected TextView tvTitle;
        protected ImageView ivThumb;

        public ProductHolder(View view) {
            super(view);

            this.tvTitle = (TextView) view.findViewById(R.id.tv_title);
            this.ivThumb = (ImageView) view.findViewById(R.id.iv_thumb);

            view.setOnClickListener(mOnClickListener);
        }
    }

    public void addProducts(List<CompatibilityQuestion> products) {
        itemsList.addAll(products);
        notifyItemRangeChanged(itemsList.size() - products.size() - 1, products.size());
    }

    public List<CompatibilityQuestion> getItemsList() {
        return itemsList;
    }
}