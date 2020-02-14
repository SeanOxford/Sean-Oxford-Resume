package com.oxford.resume.views

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.oxford.resume.ResumeApp
import com.oxford.resume.R
import com.oxford.resume.views.fragmentViews.MenuFragmentView
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
    var mAdapter: MenuFragmentGridAdapter? = null


    init {
        (context?.applicationContext as ResumeApp).myComponent.inject(this)
        layoutParams = FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)


        viewTreeObserver.addOnGlobalLayoutListener {
            if (mAdapter == null) {
                mAdapter = MenuFragmentGridAdapter(dataList)
                adapter = mAdapter
            }
        }

        setNestedScrollingEnabled(false)
        recycledViewPool.setMaxRecycledViews(0, 16)
        setRecycledViewPool(recycledViewPool)

    }


    public fun switchMenu(menuData: List<MenuFragmentView.MenuItem>) {
        fadeOutMenu(menuData)
    }

    private fun fadeOutMenu(menuData: List<MenuFragmentView.MenuItem>) {
        menuButtonTransition(true, menuData)
    }

    private fun fadeInMenu() {
        menuButtonTransition(false, ArrayList<MenuFragmentView.MenuItem>())
    }


    public fun menuButtonTransition(fadeOut: Boolean, menuData: List<MenuFragmentView.MenuItem>) {
        val animSet = AnimatorSet()
        val animList = ArrayList<Animator>()
        var alpha = if (fadeOut) 0f else 1f

        for (i in 0 until mAdapter!!.itemCount) {
            val viewButton = findViewHolderForLayoutPosition(i) as MenuViewHolder

            val fadeOutImageAnim =
                ObjectAnimator.ofFloat(viewButton.itemView.ImageView_menu_grid_view_image, View.ALPHA, alpha)
            val fadeOutTextAnim =
                ObjectAnimator.ofFloat(viewButton.itemView.TextView_menu_grid_view_text, View.ALPHA, alpha)

            animList.add(fadeOutImageAnim)
            animList.add(fadeOutTextAnim)
        }

        animSet.playTogether(animList)
        animSet.duration = 500
        if (fadeOut) {
            animSet.addListener(object : Animator.AnimatorListener {
                override fun onAnimationRepeat(animation: Animator?) {
                }

                override fun onAnimationCancel(animation: Animator?) {
                }

                override fun onAnimationStart(animation: Animator?) {
                }

                override fun onAnimationEnd(animation: Animator?) {
                    mAdapter?.setDataList(menuData)
                    mAdapter?.notifyDataSetChanged()
                    fadeInMenu()
                }
            })

        }

        animSet.start()
    }


    inner class MenuFragmentGridAdapter(var mList: List<MenuFragmentView.MenuItem>) :
        RecyclerView.Adapter<MenuViewHolder>() {

        public fun setDataList(list: List<MenuFragmentView.MenuItem>) {
            mList = list


        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
            val inflatedView = inflate(context, R.layout.menu_grid_view, null)
            inflatedView.layoutParams = LayoutParams(0, 0)
            inflatedView.layoutParams.width = resources.displayMetrics.widthPixels / 2
            inflatedView.layoutParams.height = height / 3

            return MenuViewHolder(inflatedView)
        }

        override fun getItemCount(): Int {
            return mList.size
        }

        override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
            val menuItem = mList[position]

            holder.itemView.ImageView_menu_grid_view_image.setImageResource(menuItem.imageRes)
            holder.itemView.ImageView_menu_grid_view_image.setColorFilter(menuItem.color)
            holder.itemView.TextView_menu_grid_view_text.text = menuItem.title
            holder.itemView.TextView_menu_grid_view_text.setTextColor(menuItem.color)

            holder.itemView.setOnClickListener {
                mCallBack.onMenuItemSelected(menuItem.title)
            }
        }

    }


    class MenuViewHolder(view: View) : ViewHolder(view)


}



