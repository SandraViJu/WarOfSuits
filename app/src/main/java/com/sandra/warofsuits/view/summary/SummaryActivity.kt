package com.sandra.warofsuits.view.summary

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sandra.domain.model.RoundInfoModel
import com.sandra.warofsuits.R
import com.sandra.warofsuits.utils.observe
import com.sandra.warofsuits.view.summary.adapter.SummaryAdapter
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_summary.*
import javax.inject.Inject

class SummaryActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(SummaryViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_summary)
        viewModel.getRoundInfo()
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.roundInfo.observe(this) { roundInfo ->
            roundInfo?.let {
                setUpAdapter(it)
            }
        }
    }

    private fun setUpAdapter(list: List<RoundInfoModel>) {
        summary_recycler.layoutManager = LinearLayoutManager(this)
        summary_recycler.adapter = SummaryAdapter(list, this)
    }

    companion object {
        fun startActivity(activity: Activity) {
            activity.startActivity(Intent(activity, SummaryActivity::class.java))
        }
    }

}
