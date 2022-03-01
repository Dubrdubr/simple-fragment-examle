package ru.dubr.fragmentsexamle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.dubr.fragmentsexamle.databinding.FragmentCounterBinding

class CounterFragment : Fragment() {

    private lateinit var binding: FragmentCounterBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCounterBinding.inflate(inflater, container, false)

        binding.counterTextView.text = getString(R.string.screen_label, getCounterValue())
        binding.quoteTextView.text = getQuote()

        binding.launchNextButton.setOnClickListener { onLaunchNextPressed() }
        binding.goBackButton.setOnClickListener { onBackPressed() }

        return binding.root
    }

    private fun onLaunchNextPressed() {
        val fragment = newInstance(
            counterValue = (requireActivity() as MainActivity).getScreensCount() + 1,
            quote = (requireActivity() as MainActivity).createQuote()
        )
        parentFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }

    private fun onBackPressed() {
        requireActivity().onBackPressed()
    }

    private fun getCounterValue() = requireArguments().getInt(ARG_COUNTER_VALUE)
    private fun getQuote() = requireArguments().getString(ARG_QUOTE)

    companion object {

        private const val ARG_COUNTER_VALUE = "ARG_COUNTER_VALUE"
        private const val ARG_QUOTE = "ARG_QUOTE"

        fun newInstance(counterValue: Int, quote: String): CounterFragment {
            val args = Bundle().apply {
                putInt(ARG_COUNTER_VALUE, counterValue)
                putString(ARG_QUOTE, quote)
            }

            val fragment = CounterFragment()
            fragment.arguments = args
            return fragment
        }
    }
}