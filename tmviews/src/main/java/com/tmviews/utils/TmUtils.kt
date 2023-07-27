package com.truemeds.utils

import android.os.Build
import android.view.HapticFeedbackConstants
import android.view.MotionEvent
import com.tmviews.views.buttons.TmButton

object  TmUtils {
    var forceNSB: Boolean = false

    // will use snapshot in isCompatDevice(). so value doesn't change in an app session.
    private val forceNsbSnapshot by lazy { forceNSB }

    fun isCompatDevice(): Boolean = !isDeviceSupported() || forceNsbSnapshot

    private fun isDeviceSupported() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.P

    internal const val TYPE_ELEVATED_SOFT = 2
    internal const val TYPE_ELEVATED_FLAT = 3

    private const val PLATFORM_GAP = 5f
    val platformGap = PLATFORM_GAP.dp

    const val defaultBaseColor = 0xFF426bb6.toInt()
    const val blueColor = 0xFF2671E3.toInt()
    const val greenColor=0xFF4CAF50.toInt()
}


//internal fun TmButton.onTouchHapticEvent(enable: Boolean, event: MotionEvent?) {
//    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P && this.isEnabled) {
//        val flag = if (enable) HapticFeedbackConstants.FLAG_IGNORE_GLOBAL_SETTING else 0
//        when (event?.action) {
//            MotionEvent.ACTION_DOWN -> this.performHapticFeedback(HapticFeedbackConstants.KEYBOARD_PRESS, flag)
//            MotionEvent.ACTION_UP -> this.performHapticFeedback(HapticFeedbackConstants.KEYBOARD_RELEASE, flag)
//        }
//    }
//
//}

internal fun TmButton.onPressed(enable: Boolean, event: MotionEvent?, xButtonPressChangeColor :Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P && this.isEnabled) {
        val flag = if (enable) HapticFeedbackConstants.FLAG_IGNORE_GLOBAL_SETTING else 0
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> this.setBackgroundColor(xButtonPressChangeColor)

        }
    }
}