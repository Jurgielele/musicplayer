package com.musicplayer.designsystem

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

object DsTextField {

    @Composable
    fun Default(
        modifier: Modifier = Modifier,
        isError: Boolean = false,
        singleLine: Boolean = true,
        shape: Shape = RoundedCornerShape(16.dp),
        label: String? = null,
        value: String = "",
        visualTransformation: VisualTransformation = VisualTransformation.None,
        keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
        leadingIcon: @Composable (() -> Unit)? = null,
        trailingIcon: @Composable (() -> Unit)? = null,
        onValueChange: (String) -> Unit = {}
    ) {
        TextField(
            value = value,
            onValueChange = onValueChange,
            label = if (label != null) {
                { Text(label) }
            } else null,
            modifier = modifier
                .fillMaxWidth()
                .shadow(4.dp, shape = shape)
                .border(isError, shape),
            shape = shape,
            singleLine = singleLine,
            isError = isError,
            visualTransformation = visualTransformation,
            keyboardOptions = keyboardOptions,
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.White,
                disabledContainerColor = Color.Gray,
                focusedContainerColor = Color.White,
                errorContainerColor = Color.White,
                errorIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon
        )
    }

    // Email field
    @Composable
    fun Email(
        modifier: Modifier = Modifier,
        value: String = "",
        label: String? = null,
        isError: Boolean = false,
        onValueChange: (String) -> Unit = {}
    ) {
        Default(
            modifier = modifier,
            value = value,
            label = label,
            isError = isError,
            onValueChange = onValueChange,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        )
    }


    @Composable
    fun Password(
        modifier: Modifier = Modifier,
        value: String = "",
        label: String? = null,
        isError: Boolean = false,
        onValueChange: (String) -> Unit = {}
    ) {
        var isPasswordHidden by remember { mutableStateOf(false) }
        val drawableId =
            if (isPasswordHidden) R.drawable.ic_visibility_off else R.drawable.ic_visibility
        Default(
            modifier = modifier,
            value = value,
            label = label,
            isError = isError,
            onValueChange = onValueChange,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = if (isPasswordHidden) PasswordVisualTransformation() else VisualTransformation.None,
            trailingIcon = {
                Icon(
                    modifier = Modifier.clickable {
                        isPasswordHidden = !isPasswordHidden
                    },
                    imageVector = ImageVector.vectorResource(id = drawableId),
                    contentDescription = "Password",
                    tint = Color.Black
                )
            }
        )
    }


    private fun Modifier.border(isError: Boolean, shape: Shape) = composed {
        when (isError) {
            true -> border(width = 1.dp, color = Color.Red, shape = shape)
            false -> this
        }
    }
}

@Preview
@Composable
private fun DsTextFieldPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        with(DsTextField) {
            Password(label = "Password", value = "Password")
            Password(label = "Password", value = "Password", isError = true)
            Email(label = "Email", value = "Value")
            Default(value = "Value")
            Default(label = "Email", value = "Value", isError = true)
        }
    }
}