package com.example.sleephelperapp.presentation.common

import android.widget.Toast
import androidx.annotation.Nullable
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
@Composable
fun CustomButton(
    text: String = "Click Me",
    fontSize: Int = 20,
    textColor: Color = Color.White,
    roundedCorner: Int = 50,
    buttonColor: Color = Color.Blue,
    borderColor: Color = Color.White, // Border color
    borderWidth: Dp = 3.dp,
    modifier: Modifier = Modifier.fillMaxWidth()
        .border(borderWidth, borderColor, RoundedCornerShape(roundedCorner)),
    transparency: Float = 0.5f,
    onClick: () -> Unit = {}
) {
    val context = LocalContext.current
    Button(
        onClick =onClick,
        shape = RoundedCornerShape(roundedCorner),
        colors = ButtonDefaults.buttonColors(containerColor = buttonColor.copy(alpha = 1f-transparency)), // Apply transparency
        modifier = modifier
    ) {
        Text(
            text = text,
            fontSize = fontSize.sp,
            fontWeight = FontWeight.SemiBold,
            color = textColor,
            softWrap = true
        )
    }
}
@Preview(showBackground = false)
@Composable
fun DefaultPreview() {
    CustomButton()
}