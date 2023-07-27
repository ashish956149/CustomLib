package com.truemeds.views.buttons

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import com.tmviews.appearances.TmButtonAppearance
import com.truemeds.utils.*


@SuppressLint("CustomViewStyleable")
class TmRoundCornerButton @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatButton(context, attrs, defStyleAttr) {
    var xButtonTextColor: Int = 0xFFFFFFFF.toInt()
    private var xButtonEnableHaptic: Boolean = false
    var xButtonPressChangeColor: Int by dynamicAttr(TmUtils.defaultBaseColor)
    var tmButtonAppearance: TmButtonAppearance? by dynamicAttr(null)
    var flatButtonColor: Int by dynamicAttr(TmUtils.defaultBaseColor) {
        tmButtonAppearance = TmButtonAppearance(it)
    }

    init {
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
            setBackgroundColor(flatButtonColor)
            background = ContextCompat.getDrawable(context, R.drawable.btn_press_round)

            val typeface = Typeface.createFromAsset(context.assets, "inter_semibold.ttf")
            text = "Proceed to Checkout"
            setTypeface(typeface)


        } catch (e: Exception) {
        } finally {
            typedArray.recycle()
        }
    }


}