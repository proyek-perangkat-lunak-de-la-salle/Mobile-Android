package com.example.healthcare.ui.general.fragment

import android.os.Build
import android.os.Bundle
import android.widget.TableRow
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.healthcare.R
import com.example.healthcare.data.remote.model.Questioner
import com.example.healthcare.data.remote.model.QuestionerResponse
import com.example.healthcare.databinding.ActivityHasilDeteksiGeneralBinding

class HasilDeteksiGeneralActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHasilDeteksiGeneralBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityHasilDeteksiGeneralBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val responseQuestioner = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(EXTRA_DATA, QuestionerResponse::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<QuestionerResponse>(EXTRA_DATA)
        }

        val answers = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(EXTRA_ANSWER, Questioner::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Questioner>(EXTRA_ANSWER)
        }

        if (responseQuestioner != null && answers != null) {
            binding.let {
                val stringTemplateResult = resources.getString(R.string.cluster_result)
                it.tvHasilCluster.text = stringTemplateResult.format(responseQuestioner.predictedCluster)
                it.tvInformasiPjk.text = responseQuestioner.informasi

                val tableLayout = it.tableHasilDeteksi
                val firstRow = tableLayout.getChildAt(0) as TableRow
            }
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()

    }

    companion object {
        const val EXTRA_DATA = "extra_data"
        const val EXTRA_ANSWER = "extra_answer"
    }
}