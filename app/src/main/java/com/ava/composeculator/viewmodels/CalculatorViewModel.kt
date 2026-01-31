package com.ava.composeculator.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CalculatorViewModel: ViewModel() {

    private var number1: Double = 0.0
    private var number2: Double = 0.0
    private var operator: String = ""
    private var isNumber1BeingAdded: Boolean = true
    private val _displayText = MutableStateFlow("")
    val displayText: StateFlow<String> = _displayText

    fun updateDisplay(text: String) {
        _displayText.value = text
    }

    fun onNumberClicked(number: Int) {
        if(doesInputContainAnOperator() || _displayText.value == "0") {
            updateDisplay(number.toString())
        } else {
            _displayText.value += number.toString()
        }
    }

    fun onOperatorClicked(operator: String) {
        when(operator) {
            "+",
            "-",
            "÷",
            "×",
            "%", -> {
                if(isNumber1BeingAdded) {
                    number1 = _displayText.value.toDouble()
                    isNumber1BeingAdded = false
                }
                this.operator = operator
                updateDisplay(operator)
            }
            "AC" -> resetState()
            "00" -> {
                if(!doesInputContainAnOperator() && _displayText.value != "0") {
                    _displayText.value += "00"
                }
            }
            "." -> {
                if(!doesInputContainAnOperator() && !doesInputContainDecimal()) {
                    _displayText.value += "."
                }
            }
            "⌫" -> {
                if(doesInputContainAnOperator()) {
                    this.operator = ""
                    _displayText.value = formatNumber(number1)
                    isNumber1BeingAdded = true
                    return
                }
                updateDisplay(_displayText.value.dropLast(1))
            }
            "=" -> {
                number2 = _displayText.value.toDouble()
                calculateResult()
            }
        }
    }

    private fun calculateResult() {
        val result: Double = when(operator) {
            "+" -> number1 + number2
            "-" -> number1 - number2
            "×" -> number1 * number2
            "÷" -> number1 / number2
            "%" -> number1 % number2
            else -> {
                0.0
            }
        }
        _displayText.value = formatNumber(result)
        number1 = result
    }

    private fun formatNumber(number: Double): String {
        return if(number % 1.0 == 0.0) number.toInt().toString() else number.toString()
    }

    private fun doesInputContainAnOperator(): Boolean {
        return _displayText.value.contains(Regex("[+\\-÷×%]"))
    }

    private fun doesInputContainDecimal(): Boolean {
        return _displayText.value.contains(".")
    }

    private fun resetState() {
        number1 = 0.0
        number2 = 0.0
        operator = ""
        isNumber1BeingAdded = true
        _displayText.value = ""
    }
}