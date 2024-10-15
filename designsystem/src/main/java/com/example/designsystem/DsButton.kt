package com.example.designsystem

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.designsystem.DsButton.ButtonSize

object DsButton {

    @Composable
    fun Primary(
        modifier: Modifier = Modifier,
        buttonSize: ButtonSize,
        onClick: () -> Unit,
        content: @Composable () -> Unit,

        ) {
        BaseButton(
            modifier = modifier,
            buttonSize = buttonSize,
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                disabledContentColor = MaterialTheme.colorScheme.onPrimary.disabled(),
                disabledContainerColor = MaterialTheme.colorScheme.primary.disabled(),
            ),
            content = content
        )
    }

    @Composable
    fun Primary(
        modifier: Modifier = Modifier,
        text: String,
        buttonSize: ButtonSize,
        onClick: () -> Unit
    ) {
        Primary(
            modifier = modifier,
            buttonSize = buttonSize,
            onClick = onClick
        ) {
            Text(text = text)
        }
    }

    @Composable
    fun Secondary(
        modifier: Modifier = Modifier,
        buttonSize: ButtonSize,
        onClick: () -> Unit,
        content: @Composable () -> Unit,
        ) {
        BaseButton(
            modifier = modifier,
            buttonSize = buttonSize,
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.onSecondary,
                disabledContentColor = MaterialTheme.colorScheme.onSecondary.disabled(),
                disabledContainerColor = MaterialTheme.colorScheme.secondary.disabled(),
            ),
            content = content
        )
    }

    @Composable
    fun Secondary(
        modifier: Modifier = Modifier,
        text: String,
        buttonSize: ButtonSize,
        onClick: () -> Unit
    ) {
        Secondary(
            modifier = modifier,
            buttonSize = buttonSize,
            onClick = onClick
        ) {
            Text(text = text)
        }
    }

    @Composable
    private fun BaseButton(
        modifier: Modifier,
        colors: ButtonColors,
        buttonSize: ButtonSize,
        onClick: () -> Unit,
        content: @Composable () -> Unit
    ) {
        val verticalPadding = when (buttonSize) {
            ButtonSize.Small -> 8.dp
            ButtonSize.Medium -> 10.dp
            ButtonSize.Large -> 12.dp
        }
        val horizontalPadding = 16.dp
        Button(
            modifier = modifier.defaultMinSize(minWidth = 1.dp, minHeight = 1.dp),
            onClick = { onClick() },
            colors = colors,
            contentPadding = PaddingValues(
                vertical = verticalPadding,
                horizontal = horizontalPadding
            )
        ) {
            content()
        }
    }


    enum class ButtonSize {
        Small, Medium, Large
    }

}

@Preview
@Composable
fun DsButtonPrimaryPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        with(DsButton) {
            Primary(
                text = "Primary Small",
                buttonSize = ButtonSize.Small,
                onClick = {}
            )
            Primary(
                text = "Primary Medium",
                buttonSize = ButtonSize.Medium,
                onClick = {}
            )
            Primary(
                text = "Primary Large",
                buttonSize = ButtonSize.Large,
                onClick = {}
            )

            Primary(
                modifier = Modifier.fillMaxWidth(),
                text = "Primary Small",
                buttonSize = ButtonSize.Small,
                onClick = {}
            )
            Primary(
                modifier = Modifier.fillMaxWidth(),
                text = "Primary Medium",
                buttonSize = ButtonSize.Medium,
                onClick = {}
            )
            Primary(
                modifier = Modifier.fillMaxWidth(),
                text = "Primary Large",
                buttonSize = ButtonSize.Large,
                onClick = {}
            )
            Primary(
                modifier = Modifier.fillMaxWidth(),
                buttonSize = ButtonSize.Small,
                onClick = {}
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add"
                    )
                    Text(text = "Primary small with icon")
                }
            }
        }

    }
}
@Preview
@Composable
fun DsButtonSecondaryPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        with(DsButton) {
            Secondary(
                text = "Primary Small",
                buttonSize = ButtonSize.Small,
                onClick = {}
            )
            Secondary(
                text = "Primary Medium",
                buttonSize = ButtonSize.Medium,
                onClick = {}
            )
            Secondary(
                text = "Primary Large",
                buttonSize = ButtonSize.Large,
                onClick = {}
            )

            Secondary(
                modifier = Modifier.fillMaxWidth(),
                text = "Primary Small",
                buttonSize = ButtonSize.Small,
                onClick = {}
            )
            Secondary(
                modifier = Modifier.fillMaxWidth(),
                text = "Primary Medium",
                buttonSize = ButtonSize.Medium,
                onClick = {}
            )
            Secondary(
                modifier = Modifier.fillMaxWidth(),
                text = "Primary Large",
                buttonSize = ButtonSize.Large,
                onClick = {}
            )
            Secondary(
                modifier = Modifier.fillMaxWidth(),
                buttonSize = ButtonSize.Small,
                onClick = {}
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add"
                    )
                    Text(text = "Primary small with icon")
                }
            }
        }

    }
}

