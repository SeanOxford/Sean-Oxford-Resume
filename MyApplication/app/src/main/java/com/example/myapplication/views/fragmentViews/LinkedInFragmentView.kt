package com.example.myapplication.views.fragmentViews

import android.content.Context
import android.util.Log
import android.webkit.WebViewClient
import com.example.myapplication.R
import com.example.myapplication.views.AbsInfoFragmentView
import kotlinx.android.synthetic.main.layout_linked_in_fragment.view.*

public class LinkedInFragmentView(
    context: Context?
) : BaseFragmentView(context) {


    override fun getLayout(): Int {
        return R.layout.layout_linked_in_fragment
    }


    init {
        WebView_linked_in.webViewClient = WebViewClient()
        WebView_linked_in.loadUrl("https://www.linkedin.com/in/sean-oxford-943427103/")
        WebView_linked_in.settings.javaScriptEnabled = true
    }


}