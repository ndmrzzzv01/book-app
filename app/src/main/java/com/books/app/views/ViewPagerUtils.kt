package com.books.app.views

import android.os.Handler
import androidx.viewpager2.widget.ViewPager2

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