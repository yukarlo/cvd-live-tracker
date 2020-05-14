package com.yukarlo.common.android.view

import android.animation.ObjectAnimator
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.Transformation

fun View.expand(expandedHeight: Int) {
    layoutParams.height = 0
    visibility = View.VISIBLE
    val animation: Animation = object : Animation() {
        override fun applyTransformation(
            interpolatedTime: Float,
            t: Transformation?
        ) {
            layoutParams.height =
                if (interpolatedTime == 1f) {
                    ViewGroup.LayoutParams.WRAP_CONTENT
                } else {
                    (expandedHeight * interpolatedTime).toInt()
                }
            requestLayout()
        }
    }
    animation.duration = expandedHeight.div(context.resources.displayMetrics.density).toLong()
    startAnimation(animation)
}

fun View.collapse(expandedHeight: Int) {
    val animation: Animation = object : Animation() {
        override fun applyTransformation(
            interpolatedTime: Float,
            t: Transformation?
        ) {
            if (interpolatedTime == 1f) {
                visibility = View.GONE
            } else {
                layoutParams.height =
                    expandedHeight - (expandedHeight * interpolatedTime).toInt()
                requestLayout()
            }
        }
    }
    animation.duration = expandedHeight.div(context.resources.displayMetrics.density).toLong()
    startAnimation(animation)
}

fun View.rotate(from: Float, to: Float) {
    ObjectAnimator.ofFloat(this, View.ROTATION, from, to)
        .setDuration(300).start()
}
