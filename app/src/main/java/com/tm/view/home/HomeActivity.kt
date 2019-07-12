package com.itc.app.views.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.itc.app.base.BaseActivity
import com.itc.app.components.Event
import com.itc.app.views.Navigator
import com.tm.R
import com.tm.view.home.HomeListAdapter
import com.tm.models.BaseResponseModal
import com.tm.models.home.CompatibilityQuestion
import com.tm.models.home.CompatibilityQuestions
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : BaseActivity() {

    private lateinit var mHomeViewModel: HomeViewModel
    private lateinit var mAdapter: HomeListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        mHomeViewModel = ViewModelProviders.of(this@HomeActivity).get(HomeViewModel::class.java)
        handleObserver()
        handleUI()
        getProducts()
    }

    private fun getProducts() {
        progress_bar.visibility = View.VISIBLE
        mHomeViewModel.getListRows()
    }

    /**
     * Method call to initialize observer
     */
    private fun handleObserver() {
        mHomeViewModel.mldProducts.observe(this, mProductsObserver)
        mHomeViewModel.mldError.observe(this@HomeActivity, onErrorObserver)
    }

    /**
     * Method call to handle UI
     */
    private fun handleUI() {
        mAdapter = HomeListAdapter(ArrayList<CompatibilityQuestion>(), onItemClick)
        var layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recycler_view_home.layoutManager = layoutManager
        recycler_view_home.adapter = mAdapter

    }

    /**
     * Item click listener
     */
    var onItemClick = View.OnClickListener {
        Navigator.instance.navigateToCarousel(this@HomeActivity, mAdapter.itemsList as ArrayList<CompatibilityQuestion>, it.getTag(R.id.position) as Int)
    }


    /**
     * Row data observer
     */
    private val mProductsObserver: Observer<CompatibilityQuestions> = Observer {
        mAdapter.addProducts((it.compatibility_questions))
        progress_bar.visibility = View.GONE
    }

    /**
     * Error Observer
     */
    private val onErrorObserver: Observer<Event<BaseResponseModal>> = Observer { event ->
        if (event?.getContentIfNotHandled() != null) {
            Toast.makeText(this@HomeActivity, event.peekContent().errorText, Toast.LENGTH_LONG).show()
        }
    }
}
