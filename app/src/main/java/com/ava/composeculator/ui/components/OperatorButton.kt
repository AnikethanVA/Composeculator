package com.ava.composeculator.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OperatorButton(
    modifier: Modifier = Modifier,
    buttonOperator: String,
    onClick: (numberClicked: String) -> Unit) {
        Button(
            modifier = Modifier.size(80.dp).then(modifier),
            shape = RoundedCornerShape(percent = 100),
            onClick = { onClick(buttonOperator) },
            colors = ButtonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                disabledContentColor = Color.Gray,
                disabledContainerColor = Color.LightGray
            )
        ) {
            BasicText(
                text = buttonOperator,
                maxLines = 1,
                style = TextStyle(
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                ),
                autoSize = TextAutoSize.StepBased(
                    maxFontSize = 30.sp
                ),

            )
        }
}

@Preview(showSystemUi = true)
@Composable
private fun OperatorButtonPreview() {
    OperatorButton(buttonOperator = "⌫", onClick = {})
}