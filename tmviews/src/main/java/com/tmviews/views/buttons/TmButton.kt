package com.tmviews.views.buttons

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import com.tmviews.appearances.TmButtonAppearance
import com.truemeds.utils.*
import com.truemeds.utils.dynamicAttr

@SuppressLint("CustomViewStyleable")
class TmButton @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatButton(context, attrs, defStyleAttr) {
    var xButtonTextColor: Int = 0xFFFFFFFF.toInt()
    private var xButtonEnableHaptic: Boolean = false
    var xButtonPressChangeColor: Int by dynamicAttr(TmUtils.defaultBaseColor)
    var tmButtonAppearance: TmButtonAppearance? by dynamicAttr(null)
    var flatButtonColor: Int by dynamicAttr(TmUtils.blueColor) {
        tmButtonAppearance = TmButtonAppearance(it)

    }
    var cornerRadius: Float by dynamicAttr(16f.dp)

    init {
//todo        navigate to appearance
//        delegate.handleAttrs(attrs)
//        tmButtonAppearance = getTmButtonAppearance(context, flatButtonColor)

        val typedArray: TypedArray =
            context.obtainStyledAttributes(attrs, R.styleable.XButton, defStyleAttr, 0)
        try {
            flatButtonColor =
                typedArray.getColor(R.styleable.XButton_flatButtonColor, flatButtonColor)
            xButtonTextColor =
                typedArray.getColor(R.styleable.XButton_xButtonTextColor, xButtonTextColor)
            xButtonEnableHaptic =
                typedArray.getBoolean(R.styleable.XButton_xButtonEnableHaptic, xButtonEnableHaptic)
            setTextColor(xButtonTextColor)
//            setBackgroundColor(flatButtonColor)
//            setBackgroundResource(R.drawable.btn_press)
            cornerRadius = typedArray.getDimension(R.styleable.XButton_tmButtonRadius, cornerRadius)
            background = ContextCompat.getDrawable(context, R.drawable.btn_press_normal)

        } catch (e: Exception) {
        } finally {
            typedArray.recycle()
        }
    }

//    override fun onTouchEvent(event: MotionEvent?): Boolean {
//        if (xButtonEnableHaptic) {
//            this.onTouchHapticEvent(xButtonEnableHaptic, event)
//        }
//        return super.onTouchEvent(event)
//    }


}