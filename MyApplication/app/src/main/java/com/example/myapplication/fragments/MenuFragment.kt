package com.example.myapplication.fragments

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.R
import com.example.myapplication.util.OttoBusClasses
import kotlinx.android.synthetic.main.layout_menu_fragment.view.*

class MenuFragment : BaseFragment() {


    companion object {
        fun create(): MenuFragment = MenuFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.layout_menu_fragment, null)
        initViews(view)



        return view

    }




    private fun initViews(view: View) {
//        mPortraitView = CirclePortraitView(context)
        view.CirclePortraitView_menu_fragment.setImageResource(R.drawable.me2)
        view.CirclePortraitView_menu_fragment.borderColor = Color.parseColor("#72A2EF")
        view.CirclePortraitView_menu_fragment.borderWidth =
            resources.getDimension(R.dimen.circle_portrait_border_width).toInt()
//
//        view.RelativeLayout_menu_Fragment.addView(mPortraitView)


        view.button.setOnClickListener {
            Log.d("nnn", String.format("clickd"))
            mBus.post(OttoBusClasses.ButtonEvent())
        }

    }


}