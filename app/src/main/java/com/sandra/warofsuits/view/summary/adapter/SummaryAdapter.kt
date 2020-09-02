package com.sandra.warofsuits.view.summary.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sandra.domain.model.RoundInfoModel
import com.sandra.warofsuits.R
import com.sandra.warofsuits.utils.PLAYER_A_WINS
import com.sandra.warofsuits.utils.PLAYER_B_WINS
import com.sandra.warofsuits.utils.inflate
import kotlinx.android.synthetic.main.row_round_item.view.*

class SummaryAdapter(var list: List<RoundInfoModel>, var context: Context) :
    RecyclerView.Adapter<SummaryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(parent.inflate(R.layout.row_round_item))

    override fun getItemCount(): Int =
        list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(list[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItem(item: RoundInfoModel) {
            itemView.card_player_a.setValues(item.cardA)
            itemView.card_player_b.setValues(item.cardB)
            itemView.winner.text = when(item.winner) {
                PLAYER_A_WINS -> "${context.getString(R.string.win)} ${context.getString(R.string.player_a_text)}"
                PLAYER_B_WINS -> "${context.getString(R.string.win)} ${context.getString(R.string.player_B_text)}"
                else -> ""
            }
        }
    }
}