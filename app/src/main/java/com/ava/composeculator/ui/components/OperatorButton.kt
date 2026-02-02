package com.ava.composeculator.ui.components

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OperatorButton(
    modifier: Modifier = Modifier,
    buttonOperator: String,
    onClick: (numberClicked: String) -> Unit
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
            onClick(buttonOperator)
        },
        colors = ButtonColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            disabledContentColor = Color.Gray,
            disabledContainerColor = Color.LightGray
        ),
        interactionSource = interactionSource
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