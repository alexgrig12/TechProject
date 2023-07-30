package com.example.techproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.techproject.databinding.FragmentCounterBinding

class CounterFragment : Fragment() {

    private var _binding: FragmentCounterBinding? = null
    private val binding get() = _binding!!

    private var totalCount = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCounterBinding.inflate(inflater, container, false)

        (activity as MainActivity).setVisibilityActivityContainer(View.INVISIBLE)

        savedInstanceState?.let {
            totalCount = it.getInt(TOTAL_COUNT, 0)
        }

        binding.toolbar.title = "Fragment"
        (activity as MainActivity).setSupportActionBar(binding.toolbar)
        val actionBar = (activity as MainActivity).supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            textviewClickCounter.text = totalCount.toString()

            toolbar.setNavigationOnClickListener {
                parentFragmentManager.beginTransaction()
                    .remove(this@CounterFragment)
                    .commit()
                (activity as MainActivity).setVisibilityActivityContainer(View.VISIBLE)
                (activity as MainActivity).updateTextViewCounter(totalCount)
            }

            buttonClickMe.setOnClickListener {
                totalCount += 1
                binding.textviewClickCounter.text = totalCount.toString()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(TOTAL_COUNT, totalCount)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}