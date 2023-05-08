package com.books.app.views

import android.content.Context
import android.os.Handler
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.books.app.R

fun ViewPager2.autoScroll(interval: Long, sizeOfList: Int) {
    val handler = Handler()

    val runnable = object : Runnable {
        override fun run() {
            handler.postDelayed(this, interval)
            if (this@autoScroll.currentItem < sizeOfList - 1) this@autoScroll.currentItem += 1
            else this@autoScroll.currentItem = 0
        }
    }

    handler.post(runnable)
}

fun ViewPager2.adjustViewPager(context: Context) {
    this.offscreenPageLimit = 1
    val nextItemVisiblePx = context.resources.getDimension(R.dimen.viewpager_next_item_visible)
    val currentItemHorizontalMarginPx = context.resources.getDimension(R.dimen.viewpager_current_item_horizontal_margin)
    val pageTranslationX = nextItemVisiblePx + currentItemHorizontalMarginPx
    val pageTransformer = ViewPager2.PageTransformer { page: View, position: Float ->
        page.translationX = -pageTranslationX * position
        page.scaleY = 1 - (0.25f * kotlin.math.abs(position))
    }
    this.setPageTransformer(pageTransformer)
}
