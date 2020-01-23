package com.example.myapplication.util

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.myapplication.R


class FragmentUtil {


    companion object {
        val MAIN_ACTIVITY_FRAGMENT_CONTAINER_ID = R.id.MainActivity_Fragment_Container

         fun changeFragment(fragmentManager: FragmentManager?, newfragment: Fragment){
            val fm = fragmentManager?.beginTransaction()
            fm?.replace(MAIN_ACTIVITY_FRAGMENT_CONTAINER_ID, newfragment!!)
            fm?.addToBackStack(null)
            fm?.commit()
        }

    }


}


