package com.example.myapplication.views

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.RelativeLayout
import com.example.myapplication.R
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.layout_menu_fragment.view.*

class MenuFragmentView(context: Context?, menuItemList: ArrayList<MenuItem>, callback: MenuFragmentViewCallbacks) :
    RelativeLayout(context), MenuFragmentGridRecyclerView.GridViewCallback {

    interface MenuFragmentViewCallbacks {
        fun onMenuItemSelected(title: String)
    }


    val mMenuItemList = menuItemList
    val mCallback = callback


    init {
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        View.inflate(context, R.layout.layout_menu_fragment, this)

//        initCircleImage()

        initGridView()
    }


    private fun initCircleImage() {
        val circleImageView = CircleImageView(context)
        val circleImageViewDimen = resources.getDimension(R.dimen.circle_portrait_view_dimen).toInt()
        circleImageView.layoutParams = LayoutParams(circleImageViewDimen, circleImageViewDimen)
        (circleImageView.layoutParams as LayoutParams).addRule(CENTER_HORIZONTAL, TRUE)
        circleImageView.y = resources.displayMetrics.heightPixels / 10f
        circleImageView.setImageResource(R.drawable.me)
        circleImageView.borderColor = Color.parseColor("#72A2EF")
        circleImageView.borderWidth =
            resources.getDimension(R.dimen.circle_portrait_border_width).toInt()

        addView(circleImageView)
    }

    private fun initGridView() {
        FrameLayout_menu_fragment_grid_container.addView(
            MenuFragmentGridRecyclerView(
                context,
                mMenuItemList,
                this
            )
        )
    }


    override fun onMenuItemSelected(title: String) {
        mCallback.onMenuItemSelected(title)
    }


    data class MenuItem(val title: String, val imageRes: Int)


}