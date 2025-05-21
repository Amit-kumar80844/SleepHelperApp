package com.example.sleephelperapp.presentation.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.* // Import remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview // Import Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomTextField(
    color: Color = Color.Transparent,
    modifier: Modifier = Modifier,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    paddingLeadingIconEnd: Dp = 8.dp,
    paddingTrailingIconStart: Dp = 8.dp,
    text: String ="",
    onTextChange: (String) -> Unit,
    borderColor: Color = Color.Magenta,
    borderWidth: Int = 2,
    fontSize: Int = 16,
    textColor: Color = Color.White,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    placeholder: String ="",
) {
    Box(
        modifier = modifier
            .border(BorderStroke(borderWidth.dp, borderColor), shape = RoundedCornerShape(30.dp))
            .background(
                color = color,
                shape = RoundedCornerShape(30.dp)
            )
            .padding(horizontal = 12.dp, vertical = 10.dp)

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            leadingIcon?.let {
                Box(modifier = Modifier.padding(end = paddingLeadingIconEnd)) {
                    it()
                }
            }
            TextField(
                keyboardOptions=keyboardOptions,
                placeholder = { Text(text = placeholder, color = Color.Black, fontSize = fontSize.sp) },
                value = text,
                onValueChange = onTextChange,
                modifier = Modifier.weight(1f),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    errorContainerColor = Color.Transparent,

                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent,

                    focusedTextColor = textColor,
                    unfocusedTextColor = textColor,
                    disabledTextColor = textColor,
                    errorTextColor = textColor,
                    cursorColor = textColor,
                ),
                textStyle = TextStyle(
                    fontSize = fontSize.sp
                ),
                singleLine = true,
            )
            trailingIcon?.let {
                Box(modifier = Modifier.padding(start = paddingTrailingIconStart)) {
                    it()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CustomTextFieldPreview() {
    // To preview the TextField, you need to maintain its state (the text value)
    var textState by remember { mutableStateOf("") }

    CustomTextField(
        text = textState,
        onTextChange = { textState = it },
        leadingIcon = {

        },
        trailingIcon = {
        },
        borderColor = Color.Blue,
        textColor = Color.Black,
        placeholder = "amit"
    )
}