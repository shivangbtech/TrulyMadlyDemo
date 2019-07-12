package com.tm.view.carousel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import android.widget.TextView;
import androidx.viewpager.widget.PagerAdapter;
import com.itc.app.helper.GlideHelper;
import com.tm.R;
import com.tm.models.home.CompatibilityQuestion;

import java.util.List;


public class CarouselAdapter extends PagerAdapter {

    Context context;
    List<CompatibilityQuestion> compatibilityQuestions;
    int adapterType;

    public CarouselAdapter(Context context, List<CompatibilityQuestion> compatibilityQuestions, int adapterType) {
        this.context = context;
        this.compatibilityQuestions = compatibilityQuestions;
        this.adapterType=adapterType;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_carousel, null);
        try {

            LinearLayout linMain = (LinearLayout) view.findViewById(R.id.linMain);
            ImageView imageCover = (ImageView) view.findViewById(R.id.imageCover);
            TextView question = view.findViewById(R.id.tv_question);

            linMain.setTag(position);

            switch (adapterType)
            {
                case CarouselActivity.ADAPTER_TYPE_TOP:
                    linMain.setBackgroundResource(R.drawable.shadow);
                    question.setText(compatibilityQuestions.get(position).getQuestion());
                    break;
                case CarouselActivity.ADAPTER_TYPE_BOTTOM:
                    linMain.setBackgroundResource(0);

                    break;
            }

            GlideHelper.Companion.getInstance().loadImage(imageCover, compatibilityQuestions.get(position).getStyle().getLarge());

            container.addView(view);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return compatibilityQuestions.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == object);
    }

}