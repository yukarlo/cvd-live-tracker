package com.yukarlo.ui.countries.adapter

import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.Transformation

class Animations {
    companion object {
        fun expand(view: View) {
            view.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            val actualHeight: Int = view.measuredHeight
            view.layoutParams.height = 0
            view.visibility = View.VISIBLE
            val animation: Animation = object : Animation() {
                override fun applyTransformation(
                    interpolatedTime: Float,
                    t: Transformation?
                ) {
                    view.layoutParams.height =
                        if (interpolatedTime == 1f) ViewGroup.LayoutParams.WRAP_CONTENT else (actualHeight * interpolatedTime).toInt()
                    view.requestLayout()
                }
            }
            animation.duration =
                (actualHeight / view.context.resources.displayMetrics.density).toLong()
            view.startAnimation(animation)
        }

        fun collapse(view: View) {
            val actualHeight: Int = view.measuredHeight
            val animation: Animation = object : Animation() {
                override fun applyTransformation(
                    interpolatedTime: Float,
                    t: Transformation?
                ) {
                    if (interpolatedTime == 1f) {
                        view.visibility = View.GONE
                    } else {
                        view.layoutParams.height =
                            actualHeight - (actualHeight * interpolatedTime).toInt()
                        view.requestLayout()
                    }
                }
            }
            animation.duration =
                (actualHeight / view.context.resources.displayMetrics.density).toLong()
            view.startAnimation(animation)
        }
    }
}