package com.tm.view.carousel

import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.itc.app.base.BaseActivity
import com.tm.R
import com.tm.components.CarouselEffectTransformer
import com.tm.constants.AppConstants
import com.tm.models.home.CompatibilityQuestion
import java.util.*

class CarouselActivity : BaseActivity() {

    companion object {
        @JvmField
        val ADAPTER_TYPE_TOP = 1
        @JvmField
        val ADAPTER_TYPE_BOTTOM = 2
    }

    private lateinit var viewpagerTop: ViewPager
    private lateinit var viewPagerBackground: ViewPager
    private lateinit var listItems: ArrayList<CompatibilityQuestion>
    private var selectedPosition: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carousel)
        listItems = intent.getParcelableArrayListExtra(AppConstants.PARAM_PRODUCT)
        selectedPosition = intent.getIntExtra(AppConstants.PARAM_POSITION, 0)
        init()
        setupViewPager()

    }


    /**
     * Initialize all required variables
     */
    private fun init() {
        viewpagerTop = findViewById(R.id.viewpagerTop)
        viewPagerBackground = findViewById(R.id.viewPagerbackground)

        viewpagerTop.clipChildren = false
        viewpagerTop.pageMargin = resources.getDimensionPixelOffset(R.dimen.pager_margin)
        viewpagerTop.offscreenPageLimit = 3
        viewpagerTop.currentItem = selectedPosition
        viewpagerTop.setPageTransformer(false, CarouselEffectTransformer(this)) // Set transformer
    }

    /**
     * Setup viewpager and it's events
     */
    private fun setupViewPager() {
        // Set Top ViewPager Adapter
        val adapter = CarouselAdapter(this, listItems, ADAPTER_TYPE_TOP)
        viewpagerTop.setAdapter(adapter)

        // Set Background ViewPager Adapter
        val adapterBackground = CarouselAdapter(this, listItems, ADAPTER_TYPE_BOTTOM)
        viewPagerBackground.setAdapter(adapterBackground)


        viewpagerTop.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            private var index = 0

            override fun onPageSelected(position: Int) {
                index = position

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                val width = viewPagerBackground.width
                viewPagerBackground.scrollTo((width * position + width * positionOffset).toInt(), 0)
            }

            override fun onPageScrollStateChanged(state: Int) {
                if (state == ViewPager.SCROLL_STATE_IDLE) {
                    viewPagerBackground.currentItem = index
                }

            }
        })
    }

    override fun onResume() {
        super.onResume()
        viewpagerTop.currentItem = selectedPosition
    }
}