package com.example.healthcare.ui.general.fragment.statistik

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.anychart.AnyChart
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.anychart.charts.Cartesian
import com.anychart.core.cartesian.series.Column
import com.anychart.enums.Anchor
import com.anychart.enums.HoverMode
import com.anychart.enums.Position
import com.anychart.enums.TooltipPositionMode
import com.example.healthcare.databinding.FragmentStatistikWilayahBinding

class StatistikWilayahFragment : Fragment() {

    private var _binding: FragmentStatistikWilayahBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentStatistikWilayahBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val anyChartView = binding.anyChartView
        val cartesian: Cartesian = AnyChart.column()

        val data: MutableList<DataEntry> = mutableListOf()
        data.add(ValueDataEntry("Resiko Tinggi", 200))
        data.add(ValueDataEntry("Resiko Menengah", 150))
        data.add(ValueDataEntry("Resiko Rendah", 500))

        val column: Column = cartesian.column(data)

        column.tooltip()
            .titleFormat("{%X}")
            .position(Position.CENTER_BOTTOM)
            .anchor(Anchor.CENTER_BOTTOM)
            .offsetX(0.0)
            .offsetY(5.0)
            .format("${"%"}{Value}{groupsSeparator: }")

        cartesian.animation(true)
        cartesian.title("Tingkat Resiko Per Wilayah")

        cartesian.yScale().minimum(0.0)

        cartesian.yAxis(0).labels().format("${"%"}{Value}{groupsSeparator: }")

        cartesian.tooltip().positionMode(TooltipPositionMode.POINT)
        cartesian.interactivity().hoverMode(HoverMode.BY_X)

        cartesian.xAxis(0).title("Tingkat Resiko")
        cartesian.yAxis(0).title("Jumlah")

        anyChartView.setChart(cartesian)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}