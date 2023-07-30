package com.example.techproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.techproject.databinding.ActivityMainBinding

const val TOTAL_COUNT = "saved_count"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            textviewClickCounter.text = getString(R.string.total_count, 0)
            buttonGoToFragment.setOnClickListener {
                val fragment = CounterFragment()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit()
            }
        }
    }

    fun setVisibilityActivityContainer(visibility: Int) {
        binding.activityContainer.visibility = visibility
    }

    fun updateTextViewCounter(totalCount: Int) {
        binding.textviewClickCounter.text = getString(R.string.total_count, totalCount)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        binding.textviewClickCounter.text = savedInstanceState.getString(TOTAL_COUNT) ?: "0"
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(TOTAL_COUNT, binding.textviewClickCounter.text.toString())
    }
}