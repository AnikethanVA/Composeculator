package com.ava.composeculator.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NumberButton(
    modifier: Modifier = Modifier,
    buttonNumber: Int,
    onClick: (numberClicked: Int) -> Unit) {
    Button(
        modifier = Modifier.size(80.dp).then(modifier),
        shape = RoundedCornerShape(percent = 100),
        onClick = { onClick(buttonNumber) },
        colors = ButtonColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer,
            contentColor = MaterialTheme.colorScheme.onSurface,
            disabledContainerColor = Color.Gray,
            disabledContentColor = Color.LightGray
        )
    ) {
        Text(
            textAlign = TextAlign.Center,
            text = buttonNumber.toString(),
            style = TextStyle(
                fontSize = 30.sp
            )
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun NumberButtonPreview() {
    NumberButton(buttonNumber = 1, onClick = {})
}