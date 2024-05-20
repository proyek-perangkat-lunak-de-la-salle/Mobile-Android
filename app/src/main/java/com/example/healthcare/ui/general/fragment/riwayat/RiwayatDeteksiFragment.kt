package com.example.healthcare.ui.general.fragment.riwayat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.healthcare.data.adapter.HistoryAdapter
import com.example.healthcare.data.remote.model.HistoryResponseItem
import com.example.healthcare.databinding.FragmentRiwayatDeteksiBinding
import com.example.healthcare.helper.Result
import com.example.healthcare.helper.ViewModelFactory

class RiwayatDeteksiFragment : Fragment() {

    private var _binding: FragmentRiwayatDeteksiBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentRiwayatDeteksiBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = ViewModelFactory.getInstance(requireActivity())
        val viewModel: RiwayatDeteksiViewModel by viewModels {
            factory
        }

        setLoading(false)
        binding.rvRiwayat.layoutManager = LinearLayoutManager(requireActivity())
        binding.rvRiwayat.adapter = HistoryAdapter()

        viewModel.getUserHistory().observe(requireActivity()) {
            when(it) {
                is Result.Error -> {
                    setLoading(false)
                    showToast(it.error)
                }
                Result.Loading -> setLoading(true)
                is Result.Success -> {
                    setLoading(false)
                   setUpData(it.data)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setUpData(items: List<HistoryResponseItem>) {
        val adapter = HistoryAdapter()
        adapter.items = items
        binding.rvRiwayat.adapter = adapter
    }

    private fun setLoading(isLoading: Boolean) {
        if (isLoading) binding.progressBarHistory.visibility = View.VISIBLE
        else binding.progressBarHistory.visibility = View.INVISIBLE
    }

    private fun showToast(message: String) {
        Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show()
    }
}