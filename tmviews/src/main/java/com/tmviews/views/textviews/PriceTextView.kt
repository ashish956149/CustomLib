package com.tmviews.views.textviews

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.annotation.ColorInt
import com.truemeds.utils.R
import com.truemeds.utils.databinding.MergeNumDecimalBinding
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

class PriceTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {
    private val currentFractionDigit = 2

    private var binding : MergeNumDecimalBinding
    init {
        binding=MergeNumDecimalBinding.inflate(LayoutInflater.from(context), this)

        attrs?.let {
            val styledAttributes =
                context.obtainStyledAttributes(it, R.styleable.PriceTextView, 0, 0)


            val textValue = styledAttributes.getString(R.styleable.PriceTextView_text)

            val suffixSymbol =
                styledAttributes.getString(R.styleable.PriceTextView_suffixSymbol)
            val prefixSymbol=styledAttributes.getString(R.styleable.PriceTextView_prefixSymbol)

            val textSize = styledAttributes.getDimensionPixelSize(
                R.styleable.PriceTextView_textSize,
                24
            )
            val decimalPercentage =
                styledAttributes.getInteger(R.styleable.PriceTextView_textPercentage, 100)
            val textStyle = styledAttributes.getInt(R.styleable.PriceTextView_textStyle, 0)

            val textColor = styledAttributes.getColor(
                R.styleable.PriceTextView_textColor,
                0xFF808080.toInt()
            )
            val secondaryTextColor = styledAttributes.getColor(
                R.styleable.PriceTextView_secondaryTextColor,
                textColor
            )



            setTextStyle(textStyle)
            setTextSizeInteger(textSize)
            setTextSizeDecimal((textSize * decimalPercentage) / 100)
            setTextColor(textColor, secondaryTextColor)
            if (!prefixSymbol.isNullOrEmpty()) {
                setText(textValue, prefixSymbol)
            } else {
                setText(textValue)
            }
//            if (!prefixSymbol.isNullOrEmpty()) {
//                setTextWithRupee(prefixSymbol,textValue)
//            } else {
//                setText(textValue)
//            }

            styledAttributes.recycle()
        }
    }


    private fun setTextSizeInteger(textSize: Int) {
        binding.textInteger.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize.toFloat())
    }

    private fun setTextSizeDecimal(textSize: Int) {
        binding.textDecimal.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize.toFloat())
    }


    private fun setDecimalColor(colorID: Int) {
        binding.textDecimal.setTextColor(colorID)

    }

    //region Public Functions
    fun setText(value: String?, symbols: String? = "") {
        if (value?.toFloatOrNull() != null) {
            val dfFormat = DecimalFormat(("#" + getDecimalSeparator() + "##"))
            dfFormat.maximumFractionDigits = currentFractionDigit
            dfFormat.minimumFractionDigits = currentFractionDigit
            val tempValue = dfFormat.format(value.toFloat())
            binding.textInteger.text = tempValue.split(getDecimalSeparator())[0]
            if (symbols.isNullOrEmpty()) {

                binding.textDecimal.text = String.format(
                    "%s%s",
                    getDecimalSeparator(), tempValue.split(getDecimalSeparator())[1]
                )
            } else {
                binding.textDecimal.text = String.format(
                    "%s%s",
                    getDecimalSeparator(), tempValue.split(getDecimalSeparator())[1]
                )
            }

        } else {
            setText("00" + getDecimalSeparator() + "00")
        }
    }
    fun setTextWithRupee(symbols: String? = "",value: String?) {
        if (value?.toFloatOrNull() != null) {
            val dfFormat = DecimalFormat(("#" + getDecimalSeparator() + "##"))
            dfFormat.maximumFractionDigits = currentFractionDigit
            dfFormat.minimumFractionDigits = currentFractionDigit
            val tempValue = dfFormat.format(value.toFloat())

//            binding.textInteger.text = tempValue.split(getDecimalSeparator())[0]
//            binding.textDecimal.text = String.format(
//                "%s %s%s",
//                symbols,getDecimalSeparator(), tempValue.split(getDecimalSeparator())[0]
//            )

            binding.textDecimal.text = String.format(
                "%s %s%s",
                symbols,getDecimalSeparator(), tempValue.split(getDecimalSeparator())[0]
            )
            if (symbols.isNullOrEmpty()) {

                binding.textDecimal.text = String.format(
                    "%s%s",
                    getDecimalSeparator(), tempValue.split(getDecimalSeparator())[1]
                )
            } else {
                binding.textDecimal.text = String.format(
                    "%s%s %s",
                    getDecimalSeparator(), tempValue.split(getDecimalSeparator())[1],symbols
                )
            }

        } else {
            setText("00" + getDecimalSeparator() + "00")
        }
    }

    fun setTextSize(textSize: Int, decimalPercentage: Int = 100) {
        setTextSizeInteger(textSize)
        setTextSizeDecimal((decimalPercentage * textSize) / 100)
    }

    fun setTextColor(
        @ColorInt primaryColorID: Int,
        @ColorInt secondaryColorID: Int = primaryColorID
    ) {
        binding.textInteger.setTextColor(primaryColorID)
        setDecimalColor(secondaryColorID)
    }
    //endregion


    private fun getDecimalSeparator(locale: Locale): Char {
        val formatSymbols = DecimalFormatSymbols.getInstance(locale)
        return formatSymbols.decimalSeparator
    }

    private fun getDecimalSeparator(): Char {
        return getDecimalSeparator(Locale.getDefault())
    }

    private fun setTextStyle(textStyle: Int) {
        binding.textInteger.typeface = Typeface.defaultFromStyle(textStyle)
        binding.textDecimal.typeface = Typeface.defaultFromStyle(textStyle)
    }

}

