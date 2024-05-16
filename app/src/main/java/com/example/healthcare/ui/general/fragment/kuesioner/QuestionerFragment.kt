package com.example.healthcare.ui.general.fragment.kuesioner

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.healthcare.R
import com.example.healthcare.data.remote.model.Questioner
import com.example.healthcare.data.remote.model.QuestionerResponse
import com.example.healthcare.databinding.FragmentQuestionerBinding
import com.example.healthcare.helper.Result
import com.example.healthcare.helper.ViewModelFactory
import com.example.healthcare.ui.general.fragment.HasilDeteksiGeneralActivity

class QuestionerFragment : Fragment() {

    private var _binding: FragmentQuestionerBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentQuestionerBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = ViewModelFactory.getInstance(requireActivity())
        val viewModel: QuestionerViewModel by viewModels {
            factory
        }

        setLoading(false)

        binding.submitFormBtn.setOnClickListener {
            val arrayGender = resources.getStringArray(R.array.jenis_kelamin)
            val genderAdapter = ArrayAdapter(
                requireActivity(), android.R.layout.simple_dropdown_item_1line,
                arrayGender
            )

            binding.pilihJenisKelamin.setAdapter(genderAdapter)

            val jenisKelamin = binding.pilihJenisKelamin.text.toString().trim()
            val tinggiBadan = binding.edtTinggi.text.toString().trim()
            val beratBadan = binding.edtBeratBadan.text.toString().trim()

            val arrayPahamPJK = resources.getStringArray(R.array.yes_or_no)
            val pahamPJKAdapter = ArrayAdapter(
                requireActivity(), android.R.layout.simple_dropdown_item_1line,
                arrayPahamPJK
            )

            binding.pilihPaham.setAdapter(pahamPJKAdapter)
            val pahamPJK = binding.pilihPaham.text.toString().trim()

            // Sberapa sering anda melakukan checkup
            val checkupAdapter = ArrayAdapter(
                requireActivity(),
                android.R.layout.simple_dropdown_item_1line,
                arrayPahamPJK
            )

            binding.pilihCheckupJantung.setAdapter(checkupAdapter)
            val checkUpJantung = binding.pilihCheckupJantung.text.toString().trim()

            // nyeri dada
            val nyeriDadaArray = resources.getStringArray(R.array.nyeri)
            val nyeriDadaAdapter = ArrayAdapter(
                requireActivity(), android.R.layout.simple_dropdown_item_1line,
                nyeriDadaArray
            )

            binding.pilihNyeriDada.setAdapter(nyeriDadaAdapter)
            val nyeriDada = binding.pilihNyeriDada.text.toString().trim()

            // Mual
            val mualAdapter = ArrayAdapter(
                requireActivity(), android.R.layout.simple_dropdown_item_1line,
                nyeriDadaArray
            )

            binding.pilihMual.setAdapter(mualAdapter)
            val mual = binding.pilihMual.text.toString().trim()

            // Sesak napas
            val sesakNapasAdapter = ArrayAdapter(
                requireActivity(), android.R.layout.simple_dropdown_item_1line,
                nyeriDadaArray
            )

            binding.pilihSesak.setAdapter(sesakNapasAdapter)
            val sesakNapas = binding.pilihSesak.text.toString().trim()

            // Nyeri ulu hati
            val nyeriUluHatiAdapter = ArrayAdapter(
                requireActivity(), android.R.layout.simple_dropdown_item_1line,
                nyeriDadaArray
            )

            binding.pilihNyeriUluHati.setAdapter(nyeriUluHatiAdapter)
            val nyeriUluHati = binding.pilihNyeriUluHati.text.toString().trim()

            // hipertensi
            val hipertensiAdapter = ArrayAdapter(
                requireActivity(), android.R.layout.simple_dropdown_item_1line,
                arrayPahamPJK
            )

            binding.pilihRiwayatHipertensi.setAdapter(hipertensiAdapter)
            val hipertensi = binding.pilihRiwayatHipertensi.text.toString().trim()

            val obesitasAdapter = ArrayAdapter(
                requireActivity(), android.R.layout.simple_dropdown_item_1line,
                arrayPahamPJK
            )

            binding.pilihObesitas.setAdapter(obesitasAdapter)
            val obesitas = binding.pilihObesitas.text.toString().trim()

            val diabetesAdapter = ArrayAdapter(
                requireActivity(), android.R.layout.simple_dropdown_item_1line,
                arrayPahamPJK
            )

            binding.pilihDiabetes.setAdapter(diabetesAdapter)
            val diabetes = binding.pilihDiabetes.text.toString().trim()

            val genetikaAdapter = ArrayAdapter(
                requireActivity(), android.R.layout.simple_dropdown_item_1line,
                arrayPahamPJK
            )

            binding.pilihRiwayatPjk.setAdapter(genetikaAdapter)
            val genetika = binding.pilihRiwayatPjk.text.toString().trim()

            if (jenisKelamin.isEmpty() || jenisKelamin.isBlank()) {
                binding.pilihJenisKelamin.error = resources.getString(R.string.hint_field)
            }
            if (tinggiBadan.isEmpty() || tinggiBadan.isBlank()) {
                binding.edtTinggi.error = resources.getString(R.string.hint_field)
            }
            if (beratBadan.isEmpty() || beratBadan.isBlank()) {
                binding.edtBeratBadan.error = resources.getString(R.string.hint_field)
            }
            if (pahamPJK.isEmpty() || pahamPJK.isBlank()) {
                binding.pilihPaham.error = resources.getString(R.string.hint_field)
            }
            if (checkUpJantung.isEmpty() || checkUpJantung.isBlank()) {
                binding.pilihCheckupJantung.error = resources.getString(R.string.hint_field)
            }
            if (nyeriDada.isEmpty() || nyeriDada.isBlank()) {
                binding.pilihNyeriDada.error = resources.getString(R.string.hint_field)
            }
            if (mual.isEmpty() || mual.isBlank()) {
                binding.pilihMual.error = resources.getString(R.string.hint_field)
            }
            if (sesakNapas.isEmpty() || sesakNapas.isBlank()) {
                binding.pilihSesak.error = resources.getString(R.string.hint_field)
            }
            if (nyeriUluHati.isEmpty() || nyeriUluHati.isBlank()) {
                binding.pilihNyeriUluHati.error = resources.getString(R.string.hint_field)
            }
            if (hipertensi.isBlank() || hipertensi.isEmpty()) {
                binding.pilihRiwayatHipertensi.error = resources.getString(R.string.hint_field)
            }
            if (obesitas.isBlank() || obesitas.isEmpty()) {
                binding.pilihObesitas.error = resources.getString(R.string.hint_field)
            }
            if (diabetes.isBlank() || diabetes.isEmpty()) {
                binding.pilihDiabetes.error = resources.getString(R.string.hint_field)
            }
            if (genetika.isBlank() || genetika.isEmpty()) {
                binding.pilihRiwayatPjk.error = resources.getString(R.string.hint_field)
            }

            viewModel.submitForm(
                jenisKelamin,
                tinggiBadan.toInt(),
                beratBadan.toInt(),
                pahamPJK,
                checkUpJantung,
                nyeriDada,
                mual,
                sesakNapas,
                nyeriUluHati,
                hipertensi,
                obesitas,
                diabetes,
                genetika
            ).observe(requireActivity()) {
                when (it) {
                    is Result.Error -> {
                        setLoading(false)
                        showToast(it.error)
                    }

                    Result.Loading -> setLoading(true)
                    is Result.Success -> {
                        setLoading(false)
                        val intent =
                            Intent(requireActivity(), HasilDeteksiGeneralActivity::class.java)
                        val data = QuestionerResponse(
                            it.data.predictedCluster,
                            it.data.message,
                            it.data.informasi
                        )
                        intent.putExtra(HasilDeteksiGeneralActivity.EXTRA_DATA, data)
                        val questionerAnswer = Questioner(
                            1, jenisKelamin,
                            tinggiBadan.toInt(),
                            beratBadan.toInt(),
                            pahamPJK,
                            checkUpJantung,
                            nyeriDada,
                            mual,
                            sesakNapas,
                            nyeriUluHati,
                            hipertensi,
                            obesitas,
                            diabetes,
                            genetika
                        )
                        intent.putExtra(HasilDeteksiGeneralActivity.EXTRA_ANSWER, questionerAnswer)
                        startActivity(intent)
                    }
                }
            }
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setLoading(isLoading: Boolean) {
        if (isLoading) binding.progressBarFormGeneral.visibility = View.VISIBLE
        else binding.progressBarFormGeneral.visibility = View.INVISIBLE
    }

    private fun showToast(message: String) {
        Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show()
    }

}