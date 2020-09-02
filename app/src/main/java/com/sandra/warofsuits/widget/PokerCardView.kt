package com.sandra.warofsuits.widget

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.sandra.domain.model.CardsModel
import com.sandra.warofsuits.R
import com.sandra.warofsuits.utils.getSuitImage
import com.sandra.warofsuits.utils.gone
import com.sandra.warofsuits.utils.inflate
import com.sandra.warofsuits.utils.visible
import kotlinx.android.synthetic.main.card_view.view.*

class PokerCardView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyle: Int = 0
) : ConstraintLayout(context, attributeSet, defStyle) {

    init {
        inflate(R.layout.card_view, true)
        attributeSet?.let { setAttr(it) }
    }

    fun setValues(cardModel: CardsModel) {
        cardModel.apply {
            card_num.text = cardNumber
            card_icon.setImageDrawable(cardType.getSuitImage(context = context))
          }
    }

    fun hideCard() {
        poker_card.background = ContextCompat.getDrawable(context, R.drawable.background_back_card)
        front_card_view.gone()
    }

    fun showCard() {
        poker_card.background = ContextCompat.getDrawable(context, android.R.color.white)
        front_card_view.visible()
    }

    private fun setAttr(attr: AttributeSet) {
        val typeArray = context.obtainStyledAttributes(attr, R.styleable.poker_card_view_attr)
        val isShowCard = typeArray.getBoolean(R.styleable.poker_card_view_attr_card_show, false)
        if (isShowCard) showCard()
        else hideCard()
    }



}