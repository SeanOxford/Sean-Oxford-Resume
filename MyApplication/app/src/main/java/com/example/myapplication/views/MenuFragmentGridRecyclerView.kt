package com.example.myapplication.views

import android.app.Application
import android.content.Context
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.MyApp
import com.example.myapplication.R
import com.example.myapplication.views.fragmentViews.MenuFragmentView
import com.squareup.otto.Bus
import kotlinx.android.synthetic.main.menu_grid_view.view.*
import javax.inject.Inject

class MenuFragmentGridRecyclerView(
    context: Context,
    dataList: ArrayList<MenuFragmentView.MenuItem>,
    callback: GridViewCallback
) : RecyclerView(context) {

    @Inject
    protected lateinit var mBus: Bus

    interface GridViewCallback {
        fun onMenuItemSelected(title: String)
    }

    val mCallBack = callback
    val mDataList = dataList
    var mAdapter: MenuFragmentGridAdapter? = null


    init {
        (context?.applicationContext as MyApp).myComponent.inject(this)
        layoutParams = FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)


        viewTreeObserver.addOnGlobalLayoutListener {
            if (mAdapter == null) {
                mAdapter = MenuFragmentGridAdapter()
                adapter = mAdapter
            }
        }

        initClickEating()
    }


    private fun initClickEating() {
        setOnTouchListener({ v, event ->
            true
        })
    }


    inner class MenuFragmentGridAdapter : RecyclerView.Adapter<MenuViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
            val inflatedView = inflate(context, R.layout.menu_grid_view, null)
            inflatedView.layoutParams = LayoutParams(0, 0)
            inflatedView.layoutParams.width = resources.displayMetrics.widthPixels / 2
            inflatedView.layoutParams.height = height / 3

            return MenuViewHolder(inflatedView)

        }

        override fun getItemCount(): Int {
            return mDataList.size
        }

        override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
            val menuItem = mDataList.get(position)

            holder.itemView.ImageView_menu_grid_view_image.setImageResource(menuItem.imageRes)
            holder.itemView.TextView_menu_grid_view_text.text = menuItem.title

            holder.itemView.setOnClickListener {
                mCallBack.onMenuItemSelected(menuItem.title)
            }
        }
    }


    class MenuViewHolder(view: View) : ViewHolder(view)


}



