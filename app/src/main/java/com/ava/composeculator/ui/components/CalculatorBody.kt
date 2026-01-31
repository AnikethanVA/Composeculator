package com.ava.composeculator.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ava.composeculator.viewmodels.CalculatorViewModel

@Composable
fun CalculatorBody(
    modifier: Modifier = Modifier,
    calculatorViewModel: CalculatorViewModel = viewModel(),
) {

    val displayText by calculatorViewModel.displayText.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                MaterialTheme.colorScheme.surfaceContainer
            )
            .then(modifier),
        verticalArrangement = Arrangement.Top,
    ) {

        Text(
            modifier = Modifier
                .fillMaxHeight(0.4f)
                .fillMaxWidth()
                .wrapContentHeight(Alignment.Bottom)
            ,
            text = displayText,
            textAlign = TextAlign.End,
            maxLines = 1,
            style = TextStyle(
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 50.sp,
            )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(20.dp, 20.dp, 0.dp, 0.dp))
                .background(MaterialTheme.colorScheme.surfaceContainerLowest),
            verticalArrangement = Arrangement.SpaceEvenly,
        ) {


            Row (
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OperatorButton (buttonOperator = "AC", onClick = calculatorViewModel::onOperatorClicked)
                OperatorButton (buttonOperator = "00", onClick = calculatorViewModel::onOperatorClicked)
                OperatorButton (buttonOperator = "%", onClick = calculatorViewModel::onOperatorClicked)
                OperatorButton (buttonOperator = "÷", onClick = calculatorViewModel::onOperatorClicked)
            }

            Row (
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                NumberButton(buttonNumber = 7, onClick = calculatorViewModel::onNumberClicked)
                NumberButton (buttonNumber = 8, onClick = calculatorViewModel::onNumberClicked)
                NumberButton (buttonNumber = 9, onClick = calculatorViewModel::onNumberClicked)
                OperatorButton (buttonOperator = "×", onClick = calculatorViewModel::onOperatorClicked)
            }
            Row (
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                NumberButton(buttonNumber = 4, onClick = calculatorViewModel::onNumberClicked)
                NumberButton (buttonNumber = 5, onClick = calculatorViewModel::onNumberClicked)
                NumberButton (buttonNumber = 6, onClick = calculatorViewModel::onNumberClicked)
                OperatorButton (buttonOperator = "-", onClick = calculatorViewModel::onOperatorClicked)
            }
            Row (
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                NumberButton(buttonNumber = 1, onClick = calculatorViewModel::onNumberClicked)
                NumberButton (buttonNumber = 2, onClick = calculatorViewModel::onNumberClicked)
                NumberButton (buttonNumber = 3, onClick = calculatorViewModel::onNumberClicked)
                OperatorButton (buttonOperator = "+", onClick = calculatorViewModel::onOperatorClicked)
            }

            Row (
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OperatorButton (buttonOperator = ".", onClick = calculatorViewModel::onOperatorClicked)
                NumberButton(buttonNumber = 0, onClick = calculatorViewModel::onNumberClicked)
                OperatorButton (buttonOperator = "⌫", onClick = calculatorViewModel::onOperatorClicked)
                OperatorButton (buttonOperator = "=", onClick = calculatorViewModel::onOperatorClicked)
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun CalculatorBodyPreview() {
    CalculatorBody()
}