package com.seanoxford.resume.views

import android.content.Context
import android.content.res.Resources
import android.graphics.*
import android.view.View
import android.widget.RelativeLayout
import com.seanoxford.resume.R
import kotlin.collections.ArrayList

class FibView(context: Context?) : View(context) {

    private val DIRECTION_BOTTOM = 0
    private val DIRECTION_RIGHT = 1
    private val DIRECTION_TOP = 2
    private val DIRECTION_LEFT = 3

    private val mLinePaint = Paint()
    private val mFillPaint = Paint()
    private val mCurvePaint = Paint(Paint.ANTI_ALIAS_FLAG)

    private var mRectFs = ArrayList<FibRectF>()
    private var mCurvedRectFs = ArrayList<FibRectF>()
    private var mDirectionIterator = DIRECTION_BOTTOM
    private var mFibSquareCount = 1
    private var mFibScale = 10f

    private val SCREEN_WIDTH = Resources.getSystem().displayMetrics.widthPixels
    private val MAX_SQUARES = 100

    private val mColorShade = ColorShade()


    init {
        val relativeLayoutParams = RelativeLayout.LayoutParams(SCREEN_WIDTH, SCREEN_WIDTH)
        layoutParams = relativeLayoutParams
        setBackgroundResource(R.drawable.google_bg)

        initPaint()
        initSquareDimens()
    }

    public fun setNumberOfSquares(n: Int) {
        mFibSquareCount = n
        resetView()
    }

    public fun setScale(n: Float) {
        mFibScale = n
        resetView()
    }

    private fun resetView() {
        mRectFs = ArrayList<FibRectF>()
        mCurvedRectFs = ArrayList<FibRectF>()
        mDirectionIterator = DIRECTION_BOTTOM
        initSquareDimens()
        invalidate()
    }

    private fun initPaint() {
        mLinePaint.setColor(Color.BLACK)
        mLinePaint.strokeWidth = 3f
        mLinePaint.style = Paint.Style.STROKE

        mFillPaint.style = Paint.Style.FILL

        mCurvePaint.setColor(Color.BLACK)
        mCurvePaint.strokeWidth = 4f
        mCurvePaint.style = Paint.Style.STROKE
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        drawSquaresAndCurve(canvas)
    }


    private fun calculateBottomSquare(currentRect: FibRectF, prevRect: FibRectF, curveRect: FibRectF) {
        currentRect.left = prevRect.left
        currentRect.top = prevRect.bottom
        currentRect.right = prevRect.left + currentRect.dimen
        currentRect.bottom = prevRect.bottom + currentRect.dimen


        curveRect.left = currentRect.left
        curveRect.top = currentRect.bottom - currentRect.dimen * 2
        curveRect.right = currentRect.left + currentRect.dimen * 2
        curveRect.bottom = currentRect.bottom
        curveRect.startAngle = 90
    }

    private fun calculateRightSquare(currentRect: FibRectF, prevRect: FibRectF, curveRect: FibRectF) {
        currentRect.left = prevRect.right
        currentRect.top = prevRect.bottom - currentRect.dimen
        currentRect.right = prevRect.right + currentRect.dimen
        currentRect.bottom = prevRect.bottom


        curveRect.left = currentRect.right - currentRect.dimen * 2
        curveRect.top = currentRect.bottom - currentRect.dimen * 2
        curveRect.right = currentRect.right
        curveRect.bottom = currentRect.bottom
        curveRect.startAngle = 0
    }

    private fun calculateTopSquare(currentRect: FibRectF, prevRect: FibRectF, curveRect: FibRectF) {
        currentRect.left = prevRect.right - currentRect.dimen
        currentRect.top = prevRect.top - currentRect.dimen
        currentRect.right = prevRect.right
        currentRect.bottom = prevRect.top


        curveRect.left = currentRect.right - currentRect.dimen * 2
        curveRect.top = currentRect.top
        curveRect.right = currentRect.right
        curveRect.bottom = currentRect.top + currentRect.dimen * 2
        curveRect.startAngle = 270

    }

    private fun calculateLeftSquare(currentRect: FibRectF, prevRect: FibRectF, curveRect: FibRectF) {
        currentRect.left = prevRect.left - currentRect.dimen
        currentRect.top = prevRect.top
        currentRect.right = prevRect.left
        currentRect.bottom = prevRect.top + currentRect.dimen

        curveRect.left = currentRect.left
        curveRect.top = currentRect.top
        curveRect.right = currentRect.left + currentRect.dimen * 2
        curveRect.bottom = currentRect.top + currentRect.dimen * 2
        curveRect.startAngle = 180

    }

    private fun drawSquaresAndCurve(canvas: Canvas?) {
        for (i in 0..mRectFs.size - 1) {
            val rectF = mRectFs[i]
            initColor(i)
            canvas?.drawRect(rectF, mFillPaint)
            canvas?.drawRect(rectF, mLinePaint)
        }
        for (rectF in mCurvedRectFs) {
            canvas?.drawArc(rectF, rectF.startAngle.toFloat(), 90f, false, mCurvePaint)
        }
    }

    private fun initColor(i: Int) {
        var fibColor = mColorShade.handleColor(i)
        mFillPaint.setARGB(255, fibColor.R.toInt(), fibColor.G.toInt(), fibColor.B.toInt())
    }

    private fun initZeroPoint() {
        val rect = FibRectF(0f)
        rect.left = (SCREEN_WIDTH.toFloat() / 2)
        rect.top = (SCREEN_WIDTH.toFloat() / 2)
        rect.right = (SCREEN_WIDTH.toFloat() / 2)
        rect.bottom = (SCREEN_WIDTH.toFloat() / 2)
        mRectFs.add(rect)
    }

    private fun initFirstSquare() {
        val prevRect = mRectFs[0]
        val rect = FibRectF(mFibScale + prevRect.dimen)
        rect.left = prevRect.left
        rect.top = prevRect.bottom
        rect.right = prevRect.left + rect.dimen
        rect.bottom = prevRect.bottom + rect.dimen
        mRectFs.add(rect)


        val curveRect = FibRectF(rect.dimen)

        curveRect.left = rect.left
        curveRect.top = rect.top
        curveRect.right = rect.left + rect.dimen * 2
        curveRect.bottom = rect.top + rect.dimen * 2
        curveRect.startAngle = 180

        mCurvedRectFs.add(curveRect)


    }

    private fun initSquare(i: Int) {
        val prevRect = mRectFs[i - 1]
        val prevPrevRect = mRectFs[i - 2]
        val newRectDimen = prevRect.dimen + prevPrevRect.dimen
        val rect = FibRectF(newRectDimen)
        val curveRect = FibRectF(newRectDimen)
        when (mDirectionIterator) {
            DIRECTION_BOTTOM -> {
                calculateBottomSquare(rect, prevRect, curveRect)
            }
            DIRECTION_RIGHT -> {
                calculateRightSquare(rect, prevRect, curveRect)
            }
            DIRECTION_TOP -> {
                calculateTopSquare(rect, prevRect, curveRect)
            }
            DIRECTION_LEFT -> {
                calculateLeftSquare(rect, prevRect, curveRect)
            }
        }

        mRectFs.add(rect)
        mCurvedRectFs.add(curveRect)

        //Rotate next placement direction
        if (mDirectionIterator < DIRECTION_LEFT) {
            mDirectionIterator += 1
        } else {
            mDirectionIterator = DIRECTION_BOTTOM
        }

    }




    private fun initSquareDimens() {
        for (i in 0..mFibSquareCount) {
            when (i) {
                0 -> initZeroPoint()
                1 -> initFirstSquare()
                else -> initSquare(i)
            }
        }
    }


    private class FibRectF(dimen: Float) : RectF() {
        val dimen = dimen
        var startAngle = 0
    }

    inner class ColorShade {
        val BLUE = FibColor(66f, 133f, 244f)
        val RED = FibColor(219f, 68f, 55f)
        val YELLOW = FibColor(244f, 160f, 0f)
        val GREEN = FibColor(15f, 157f, 88f)
        val TOTAL_TRANSITIONS = 3
        val TRANSITION_INCREMENT = MAX_SQUARES / TOTAL_TRANSITIONS

        public fun handleColor(i: Int): FibColor {
            var resultColor = FibColor(0f, 0f, 0f)
            when (i) {
                in TRANSITION_INCREMENT * 0..TRANSITION_INCREMENT * 1 - 1 -> resultColor =
                    blueToRed(i)
                in TRANSITION_INCREMENT * 1..TRANSITION_INCREMENT * 2 - 1 -> resultColor =
                    redToYellow(i)
                in TRANSITION_INCREMENT * 2..TRANSITION_INCREMENT * 3 -> resultColor =
                    yellowToGreen(i)
            }

            return resultColor
        }

        private fun blueToRed(i: Int): FibColor {
            val progress = i.toFloat() / TRANSITION_INCREMENT

            val R = RED.R * progress + BLUE.R * (1 - progress)
            val G = RED.G * progress + BLUE.G * (1 - progress)
            val B = RED.B * progress + BLUE.B * (1 - progress)
            return FibColor(R, G, B)
        }

        private fun redToYellow(i: Int): FibColor {
            val progress = (i.toFloat() - TRANSITION_INCREMENT) / TRANSITION_INCREMENT

            val R = YELLOW.R * progress + RED.R * (1 - progress)
            val G = YELLOW.G * progress + RED.G * (1 - progress)
            val B = YELLOW.B * progress + RED.B * (1 - progress)
            return FibColor(R, G, B)
        }

        private fun yellowToGreen(i: Int): FibColor {
            val progress = (i.toFloat() - TRANSITION_INCREMENT * 2) / TRANSITION_INCREMENT

            val R = GREEN.R * progress + YELLOW.R * (1 - progress)
            val G = GREEN.G * progress + YELLOW.G * (1 - progress)
            val B = GREEN.B * progress + YELLOW.B * (1 - progress)
            return FibColor(R, G, B)
        }
    }

    data class FibColor(val R: Float, val G: Float, val B: Float)


}