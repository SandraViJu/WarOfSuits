package com.sandra.warofsuits.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.sandra.domain.model.CardsModel
import com.sandra.warofsuits.R
import com.sandra.warofsuits.utils.PLAYER_A_WINS
import com.sandra.warofsuits.utils.PLAYER_B_WINS
import com.sandra.warofsuits.utils.observe
import com.sandra.warofsuits.view.summary.SummaryActivity
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private var scorePlayerA: Int = 0
    private var scorePalyerB: Int = 0
    private var cardA: CardsModel? = null
    private var cardB: CardsModel? = null

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_main)
        viewModel.getCardList()
        observeViewModel()
        setUpListeners()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.menu_restart -> {
            restart()
            true
        }
        R.id.menu_summary -> {
            SummaryActivity.startActivity(this)
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    private fun observeViewModel() {
        viewModel.cardA.observe(this) { card ->
            cardA = card
            card?.let {
                player_A.showPreparedCard(it)
            }
        }
        viewModel.cardB.observe(this) { card ->
            cardB = card
            card?.let {
                player_B.showPreparedCard(it)
            }
        }
        viewModel.winner.observe(this) { winner ->
            winner?.let {
                when (it) {
                    PLAYER_A_WINS -> scorePlayerA++
                    PLAYER_B_WINS -> scorePalyerB++
                }
                player_A.updateScore(scorePlayerA.toString())
                player_B.updateScore(scorePalyerB.toString())
            }
        }
        viewModel.lastCardA.observe(this) {
            player_A.lastCard()
        }
        viewModel.lastCardB.observe(this) {
            player_B.lastCard()
        }
        viewModel.finishGame.observe(this) {
            when {
                scorePlayerA > scorePalyerB -> showDialog("${getString(R.string.win)} ${getString(R.string.player_a_text)}")
                scorePlayerA < scorePalyerB -> showDialog("${getString(R.string.win)} ${getString(R.string.player_B_text)}")
                else -> showDialog(getString(R.string.tie))
            }
        }
    }

    private fun showDialog(message: String) {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.end_game))
            .setMessage(message)
            .setCancelable(false)
            .setPositiveButton(getString(R.string.restart_game)) { _, _ ->
                restart()
            }.setNegativeButton(getString(R.string.see_summary)){_,_ ->
                SummaryActivity.startActivity(this)
            }.show()
    }

    private fun setUpListeners() {
        player_A.onCardPrepared = {
            managePlayersArePrepared()
        }
        player_B.onCardPrepared = {
            managePlayersArePrepared()
        }

        player_A.onDiscardCard = {
            manageDiscardCard()
        }
        player_B.onDiscardCard = {
            manageDiscardCard()
        }
    }

    private fun managePlayersArePrepared() {
        if (player_A.isPrepared() && player_B.isPrepared()) {
            viewModel.manageCards(viewModel._playerACards, viewModel._playerBCards)
        }
    }

    private fun manageDiscardCard() {
        if (player_A.isPrepared() && player_B.isPrepared()) {
            cardA?.let { player_A.discardPile(it) }
            cardB?.let { player_B.discardPile(it) }
        }
    }

    private fun restart() {
        scorePlayerA = 0
        scorePalyerB = 0
        viewModel.getCardList()
        player_A.restartGame()
        player_B.restartGame()
    }

}
