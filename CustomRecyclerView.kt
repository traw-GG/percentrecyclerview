package com.russia.common.custom_view

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.russia.online.R

class CustomRecyclerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    private var mEnableScrolling: Boolean = true
    private var itemHeightMultiplier: Float = 0.0f
    private var itemWidthPercent: Float = 0f
    private var itemHeightPercent: Float = 0f
    private var marginTopPercent: Float = 0f
    private var marginBottomPercent: Float = 0f
    private var marginLeftPercent: Float = 0f
    private var marginRightPercent: Float = 0f

    init {
        context.obtainStyledAttributes(attrs, R.styleable.CustomRecyclerView).apply {
            itemHeightMultiplier = getFloat(R.styleable.CustomRecyclerView_itemHeightMultiplier, 0f)
            itemWidthPercent = getFloat(R.styleable.CustomRecyclerView_itemWidthPercent, 0f)
            itemHeightPercent = getFloat(R.styleable.CustomRecyclerView_itemHeightPercent, 0f)
            marginTopPercent = getFloat(R.styleable.CustomRecyclerView_marginTopPercent, 0f)
            marginBottomPercent = getFloat(R.styleable.CustomRecyclerView_marginBottomPercent, 0f)
            marginLeftPercent = getFloat(R.styleable.CustomRecyclerView_marginLeftPercent, 0f)
            marginRightPercent = getFloat(R.styleable.CustomRecyclerView_marginRightPercent, 0f)
            recycle()
        }

        addItemDecoration(
                PercentItemDecoration(
                    itemHeightMultiplier,
                    itemWidthPercent,
                    itemHeightPercent,
                    marginTopPercent,
                    marginBottomPercent,
                    marginLeftPercent,
                    marginRightPercent
                )
        )
    }

    class PercentItemDecoration(
            private val itemHeightMultiplier: Float,
            private val itemWidthPercent: Float,
            private val itemHeightPercent: Float,
            private val marginTopPercent: Float,
            private val marginBottomPercent: Float,
            private val marginLeftPercent: Float,
            private val marginRightPercent: Float
    ) : RecyclerView.ItemDecoration() {

        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
            val totalWidth = parent.width
            val totalHeight = parent.height

            val itemMultiplier = itemHeightMultiplier
            val itemWidth = (totalWidth * itemWidthPercent).toInt()
            val itemHeight = if (itemMultiplier == 0.0f) (totalHeight * itemHeightPercent).toInt() else (itemWidth * itemMultiplier).toInt()
            val marginTop = (totalHeight * marginTopPercent).toInt()
            val marginBottom = (totalHeight * marginBottomPercent).toInt()
            val marginLeft = (totalWidth * marginLeftPercent).toInt()
            val marginRight = (totalWidth * marginRightPercent).toInt()

            view.layoutParams.width = itemWidth
            view.layoutParams.height = itemHeight

            outRect.set(marginLeft, marginTop, marginRight, marginBottom)
        }
    }

    override fun onInterceptTouchEvent(e: MotionEvent?): Boolean {
        if (isEnableScrolling()) {
            return super.onInterceptTouchEvent(e)
        }
        return false
    }

    override fun onTouchEvent(e: MotionEvent?): Boolean {
        if (isEnableScrolling()) {
            return super.onTouchEvent(e)
        }
        return false
    }

    fun getScrollForRecycler(): Int {
        return computeVerticalScrollOffset()
    }

    fun isEnableScrolling(): Boolean {
        return mEnableScrolling
    }

    fun setEnableScrolling(z: Boolean) {
        mEnableScrolling = z
    }
}