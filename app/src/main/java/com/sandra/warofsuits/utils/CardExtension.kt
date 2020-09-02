package com.sandra.warofsuits.utils

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.sandra.domain.model.SuitsModel
import com.sandra.warofsuits.R

fun SuitsModel.getSuitImage(context: Context): Drawable? =
    when (this) {
        SuitsModel.CLUBS -> ContextCompat.getDrawable(context, R.drawable.ace)
        SuitsModel.DIAMONDS -> ContextCompat.getDrawable(context, R.drawable.diamonds)
        SuitsModel.HEARTS -> ContextCompat.getDrawable(context, R.drawable.heart)
        SuitsModel.SPADES -> ContextCompat.getDrawable(context, R.drawable.clubes)
    }
