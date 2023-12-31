/*
 * Copyright 2020 Dreamplug Technologies Private Limited
 * Licensed under the Apache License, Version 2.0 (the “License”);
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an “AS IS” BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 */

package com.tmviews.appearances

import android.content.Context
import android.content.res.TypedArray
import com.truemeds.utils.R
import com.truemeds.utils.darken
import com.truemeds.utils.lighten
import com.truemeds.utils.withAttrs

data class TmButtonAppearance(
    val fillStartColor: Int,
    val fillEndColor: Int,
    val border1StartColor: Int,
    val border1EndColor: Int,
    val border2StartColor: Int,
    val border2EndColor: Int,
    val pressedDarkShadowColor: Int
) {

    constructor(flatButtonColor: Int) : this(
        flatButtonColor.lighten(FILL_COLOR_FRACTION),
        flatButtonColor,
        flatButtonColor.lighten(BORDER1_START_COLOR_FRACTION),
        flatButtonColor.darken(1.0),
        flatButtonColor.darken(1.0),
        flatButtonColor,
        flatButtonColor.darken(PRESSED_SHADOW_FRACTION)
    )

    companion object {

        private const val FILL_COLOR_FRACTION = .28
        private const val BORDER1_START_COLOR_FRACTION = .7
        private const val PRESSED_SHADOW_FRACTION = .5

        fun TypedArray.getTmButtonAppearance(context: Context, flatButtonColor: Int): TmButtonAppearance {
            val appearance = getResourceId(R.styleable.XButton_xPlatformAppearance, -1)
            if (appearance != -1) {
                context.withAttrs(appearance, R.styleable.TmButtonAppearance) {
                    return TmButtonAppearance(
                        fillStartColor = getColor(
                            R.styleable.TmButtonAppearance_tmStartColor,
                            flatButtonColor.lighten(FILL_COLOR_FRACTION)
                        ),
                        fillEndColor = getColor(
                            R.styleable.TmButtonAppearance_tmButtonEndColor,
                            flatButtonColor
                        ),
                        border1StartColor = getColor(
                            R.styleable.TmButtonAppearance_tmButtonBorder1StartColor,
                            flatButtonColor.lighten(BORDER1_START_COLOR_FRACTION)
                        ),
                        border1EndColor = getColor(
                            R.styleable.TmButtonAppearance_tmButtonBorder1EndColor,
                            flatButtonColor.darken(1.0)
                        ),
                        border2StartColor = getColor(
                            R.styleable.TmButtonAppearance_tmButtonBorder2StartColor,
                            flatButtonColor.darken(1.0)
                        ),
                        border2EndColor = getColor(
                            R.styleable.TmButtonAppearance_tmButtonBorder2EndColor,
                            flatButtonColor
                        ),
                        pressedDarkShadowColor = getColor(
                            R.styleable.TmButtonAppearance_tmButtonPressedDarkShadowColor,
                            flatButtonColor.darken(PRESSED_SHADOW_FRACTION)
                        )
                    )
                }
            }

            return TmButtonAppearance(flatButtonColor)
        }
    }


}
