import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.coffeeshops.R
import com.example.coffeeshops.courgetteFontFamily
import com.example.coffeeshops.ui.theme.CoffeeShopsTheme
import kotlinx.coroutines.launch

val courgetteFontFamily = FontFamily(
    Font(R.font.aliviaregular, FontWeight.Normal)
)

@Composable
fun Comments(nombre: String) {
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

    val grisState = rememberLazyStaggeredGridState()
    val scope = rememberCoroutineScope()

    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(grisState.firstVisibleItemIndex, grisState.firstVisibleItemScrollOffset) {
        val firstVisibleItemIndex = grisState.firstVisibleItemIndex
        val firstVisibleItemScrollOffset = grisState.firstVisibleItemScrollOffset

        if (firstVisibleItemIndex > 0 || firstVisibleItemScrollOffset > 0) {
            visible = true
        } else {
            visible = false
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = nombre,
                modifier = Modifier.fillMaxWidth().padding(vertical = 15.dp),
                textAlign = TextAlign.Center,
                fontFamily = courgetteFontFamily,
                fontSize = 30.sp

            )

            LazyVerticalStaggeredGrid(
                state = grisState,
                columns = StaggeredGridCells.Fixed(2),
                modifier = Modifier.padding(8.dp).weight(1f),
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

        if (visible) {
            Button(
                onClick = {
                    scope.launch {
                        grisState.scrollToItem(0)
                    }
                },
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.BottomCenter),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFf99aaa),
                    contentColor = Color.White
                )
            ) {
                Text("Add new comment")
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CoffeeShopsTheme {
        Comments(nombre = "dd")
    }
}