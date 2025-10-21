import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.coffeeshops.R
import com.example.coffeeshops.courgetteFontFamily
import com.example.coffeeshops.ui.theme.CoffeeShopsTheme

val courgetteFontFamily = FontFamily(
    Font(R.font.aliviaregular, FontWeight.Normal)
)

@Composable
fun Comments() {
    val comments = listOf(
        "Excelente café, me encantó el aroma y sabor.",
        "Muy buen servicio y ambiente agradable.",
        "La mejor cafetería de la ciudad.",
        "Precios justos y calidad excelente.",
        "Perfecto para trabajar o estudiar.",
        "El espresso está espectacular.",
        "Me encanta su variedad de pasteles.",
        "Ambiente acogedor y música agradable.",
        "Ideal para reuniones informales.",
        "Recomiendo probar su latte de vainilla.",
        "El personal es muy amable y atento.",
        "Buena ubicación y fácil acceso.",
        "Los precios podrían ser un poco más bajos.",
        "Tienen opciones veganas, ¡genial!",
        "Perfecto para una pausa rápida.",
        "El café frío es delicioso en verano.",
        "No me gustó mucho la atención.",
        "Me gusta su decoración vintage.",
        "Los postres son caseros y frescos.",
        "Volveré sin duda alguna.",
        "Excelente café, me encantó el aroma y sabor.",
        "Muy buen servicio y ambiente agradable.",
        "La mejor cafetería de la ciudad.",
        "Precios justos y calidad excelente.",
        "Perfecto para trabajar o estudiar.",
        "El espresso está espectacular.",
        "Me encanta su variedad de pasteles.",
        "Ambiente acogedor y música agradable.",
        "Ideal para reuniones informales.",
        "Recomiendo probar su latte de vainilla.",
        "El personal es muy amable y atento.",
        "Buena ubicación y fácil acceso.",
        "Los precios podrían ser un poco más bajos.",
        "Tienen opciones veganas, ¡genial!",
        "Perfecto para una pausa rápida.",
        "El café frío es delicioso en verano.",
        "No me gustó mucho la atención.",
        "Me gusta su decoración vintage.",
        "Los postres son caseros y frescos.",
        "Volveré sin duda alguna."
    )

    Column() {
        Text(
            text = "Nombre cafe",
            modifier = Modifier.fillMaxWidth().padding(vertical = 15.dp),
            textAlign = TextAlign.Center,
            fontFamily = courgetteFontFamily,
            fontSize = 30.sp

        )

        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            modifier = Modifier.padding(8.dp),
            contentPadding = PaddingValues(8.dp),
            verticalItemSpacing = 8.dp,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(comments.size) { index ->
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFfbe3e3),
                    )
                ) {
                    Text(
                        text = comments[index],
                        modifier = Modifier.padding(12.dp),
                        textAlign = TextAlign.Start
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CoffeeShopsTheme {
        Comments()
    }
}