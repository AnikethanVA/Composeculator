package com.ava.composeculator.ui.components

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NumberButton(
    modifier: Modifier = Modifier,
    buttonNumber: Int,
    onClick: (numberClicked: Int) -> Unit
) {

    val interactionSource = remember { MutableInteractionSource() }
    val hapticFeedback = LocalHapticFeedback.current
    val isPressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(
        if (isPressed) 0.9f else 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioHighBouncy,
        )
    )

    val roundedCornerPercentage by animateIntAsState(
        targetValue = if (isPressed) 30 else 100,
    )

    Button(
        modifier = Modifier
            .size(80.dp)
            .scale(scale)
            .then(modifier),
        shape = RoundedCornerShape(percent = roundedCornerPercentage),
        onClick = {
            hapticFeedback.performHapticFeedback(hapticFeedbackType = HapticFeedbackType.VirtualKey)
            onClick(buttonNumber)
        },
        colors = ButtonColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer,
            contentColor = MaterialTheme.colorScheme.onSurface,
            disabledContainerColor = Color.Gray,
            disabledContentColor = Color.LightGray
        ),
        interactionSource = interactionSource,
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