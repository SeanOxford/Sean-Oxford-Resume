package com.example.myapplication.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import kotlinx.android.synthetic.main.layout_menu_fragment.*

class MenuFragment : Fragment() {

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


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Button_menu_fragment_one.setOnClickListener {

        }

    }


    private fun initViews(view: View) {


    }


}