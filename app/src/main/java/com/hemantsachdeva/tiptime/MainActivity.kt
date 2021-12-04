package com.hemantsachdeva.tiptime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hemantsachdeva.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener{
            calculateTip()
        }
    }

    private fun calculateTip() {
        val stringInTextField = binding.costOfService.text.toString()
        val cost = stringInTextField.toDoubleOrNull()

        // If the cost is null or 0, then display 0 tip and exit this function early.
        if (cost == null || cost == 0.0) {
            binding.tipResult.text = ""
            return
        }
        val tipPercentage = when (binding.tipOptions.checkedRadioButtonId) {
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }

        var tip = tipPercentage * cost
        val roundUp = binding.roundUpSwitch.isChecked
        if (roundUp) {
            tip = kotlin.math.ceil(tip)
        }

        // Display the formatted tip value on screen
        displayTip(tip)
    }

    private fun displayTip(tip : Double) {
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }
}