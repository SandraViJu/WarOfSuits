package com.sandra.warofsuits.widget

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.sandra.domain.model.CardsModel
import com.sandra.warofsuits.R
import com.sandra.warofsuits.utils.gone
import com.sandra.warofsuits.utils.inflate
import com.sandra.warofsuits.utils.visible
import kotlinx.android.synthetic.main.player_view.view.*

class PlayerView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyle: Int = 0
) : ConstraintLayout(context, attributeSet, defStyle) {

    private var cardPrerpare: Boolean = false
    private var lastCard: Boolean = false
    var onCardPrepared: () -> Unit = {}
    var onDiscardCard: () -> Unit = {}

    init {
        inflate(R.layout.player_view, true)
        attributeSet?.let { setAttr(it) }
        user_cards.setOnClickListener {
            prepareCard()
        }
        next_card.setOnClickListener {
            onDiscardCard()
        }
    }

    private fun setAttr(attr: AttributeSet) {
        val typeArray = context.obtainStyledAttributes(attr, R.styleable.player_view_attr)
        player_txt.text = resources.getText(
            typeArray.getResourceId(
                R.styleable.player_view_attr_player_text,
                R.string.player_a_text
            )
        )
    }

    fun isPrepared(): Boolean = cardPrerpare

    fun prepareCard() {
        if (!cardPrerpare) {
            if(lastCard) user_cards.gone()
            next_card.visible()
            cardPrerpare = true
            onCardPrepared()
        }
    }

    fun showPreparedCard(card: CardsModel) {
        next_card.setValues(card)
        next_card.showCard()
    }

    fun discardPile(card: CardsModel) {
        next_card.gone()
        next_card.hideCard()
        discards_cards.visible()
        discards_cards.setValues(card)
        cardPrerpare = false
    }

    fun updateScore(score: String) {
        player_score.text = score
    }

    fun lastCard()  {
        lastCard = true
    }

    fun restartGame() {
        player_score.text = "0"
        user_cards.visible()
        discards_cards.gone()
        next_card.gone()
        next_card.hideCard()
        cardPrerpare = false
        lastCard = false
    }

}
