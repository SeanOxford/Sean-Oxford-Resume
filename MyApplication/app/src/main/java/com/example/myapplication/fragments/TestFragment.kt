package com.example.myapplication.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import com.example.myapplication.views.FibView
import com.example.myapplication.R
import kotlinx.android.synthetic.main.layout_test_fragment.*

class TestFragment : Fragment() {

    private var mFibView: FibView? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.layout_test_fragment, null)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFibView()
        initListeners()
        initSliders()
    }


    private fun initSliders(){
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
                    String.format(getString(R.string.TextView_fib_fragment_squares_number), progress)
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
                    String.format(getString(R.string.TextView_fib_fragment_scale), scale / 100f)
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