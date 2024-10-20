package com.musicplayer.designsystem.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

object DsCard {


    @Composable
    fun Elevated(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
        val shape = RoundedCornerShape(16.dp)
        Custom(
            modifier = modifier,
            shape = shape,
            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp),
        ) {
            content()
        }
    }

    @Composable
    fun Custom(
        modifier: Modifier = Modifier,
        shape: Shape = CardDefaults.shape,
        colors: CardColors = CardDefaults.cardColors(
            containerColor = Color.White,
            disabledContainerColor = Color.White,
            disabledContentColor = Color.White
        ),
        elevation: CardElevation = CardDefaults.cardElevation(),
        border: BorderStroke? = null,
        content: @Composable ColumnScope.() -> Unit
    ) {
        Card(
            modifier = modifier,
            shape = shape,
            colors = colors,
            elevation = elevation,
            border = border,
            content = content
        )
    }
}


@Composable
@Preview
private fun DsCardPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        with(DsCard) {
            Text(text = "Custom card")
            Custom {
                Placeholder()
            }
            Text(text = "Elevated card")
            Elevated (modifier = Modifier.padding(16.dp)) {
                Placeholder()
            }
        }
    }
}

@Composable
private fun Placeholder() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("lorem ipsum ")
        Text("lorem ipsum ")
        Text("lorem ipsum ")
        Text("lorem ipsum ")
        Text("lorem ipsum ")
    }
}