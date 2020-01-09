package com.example.myapplication.views

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import kotlinx.android.synthetic.main.menu_grid_view.view.*

class MenuFragmentGridRecyclerView(
    context: Context,
    dataList: ArrayList<MenuFragmentView.MenuItem>,
    callback: GridViewCallback
) : RecyclerView(context) {

    interface GridViewCallback {
        fun onMenuItemSelected(title: String)
    }

    val mParamHeight = (resources.displayMetrics.heightPixels * 0.7f).toInt()
    val mCallBack = callback
    val mDataList = dataList

    init {

        val relativelayoutParams = RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, mParamHeight)
        relativelayoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE)
        layoutParams = relativelayoutParams


        val gridLayoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
        layoutManager = gridLayoutManager

        val adapter = MenuFragmentGridAdapter()

        setAdapter(adapter)

        initClickEating()
    }


    private fun initClickEating() {
        setOnTouchListener({ v, event ->
            true
        })
    }

    private fun initData() {
    }


    inner class MenuFragmentGridAdapter : RecyclerView.Adapter<MenuViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
            val inflatedView = inflate(context, R.layout.menu_grid_view, null)
            inflatedView.layoutParams = LayoutParams(0, 0)

            inflatedView.layoutParams.width = resources.displayMetrics.widthPixels / 2
            inflatedView.layoutParams.height = mParamHeight / 3


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



