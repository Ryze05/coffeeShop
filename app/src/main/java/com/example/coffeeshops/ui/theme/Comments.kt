package com.example.coffeeshops.ui.theme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.coffeeshops.CoffeeMain

@Composable
fun Comments() {
    Text(
        "Pantalla Comments",
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    )
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CoffeeShopsTheme {
        Comments()
    }
}