package com.oxford.resume.views.fragmentViews

import android.animation.ObjectAnimator
import android.content.Context
import android.widget.SeekBar
import com.oxford.resume.R
import com.oxford.resume.views.FibView
import kotlinx.android.synthetic.main.layout_test_fragment.view.*
import kotlinx.android.synthetic.main.layout_test_fragment.view.FrameLayout_info_fragment_bg

class FibonacciFragmentView(context: Context?, callback: InfoFragmentViewCallback) :
    AbsInfoFragmentView(context, callback) {


    private var mFibView: FibView? = null


    init {
        initFibView()
        initListeners()
        initSliders()
    }

    override fun getLayout(): Int {
        return R.layout.layout_test_fragment
    }


    protected override fun animateIn(){
        val fadeSpeed = 500L
        val bgFadeIn = ObjectAnimator.ofFloat(FrameLayout_info_fragment_bg, "alpha", 0f, 1f)
        bgFadeIn.duration = fadeSpeed
        bgFadeIn.start()
    }

    private fun initSliders() {
        Seekbar_test_fragment_squares.progress = 1
        Seekbar_test_fragment_scale.progress = 100
    }

    private fun initFibView() {
        mFibView = FibView(context)
        FrameLayout_test_fragment_fibview_container.addView(mFibView)
        mFibView?.setScale(calculateLogScale(100))
    }

    private fun initListeners() {
        Seekbar_test_fragment_squares.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                mFibView?.setNumberOfSquares(progress)
                TextView_test_fragment_squares.text =
                    String.format(context.getString(R.string.TextView_fib_fragment_squares_number), progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })

        Seekbar_test_fragment_scale.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val scale = calculateLogScale(progress)
                mFibView?.setScale(scale)
                TextView_test_fragment_scale.text =
                    String.format(context.getString(R.string.TextView_fib_fragment_scale), scale / 100f)
            }
        })
    }

    //Taken from https://stackoverflow.com/questions/846221/logarithmic-slider
    private fun calculateLogScale(progress: Int): Float {
        var minp = 0
        var maxp = 100

        var minv = Math.log(0.00000000000000001)
        var maxv = Math.log(100.0)

        var scale = (maxv - minv) / (maxp - minp)

        return Math.exp(minv + scale * (progress - minp)).toFloat()
    }


}