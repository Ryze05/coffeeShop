package com.example.coffeeshops

import Comments
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.coffeeshops.ui.theme.CoffeeShopsTheme
import kotlin.math.floor

val courgetteFontFamily = FontFamily(
    Font(R.font.aliviaregular, FontWeight.Normal)
)

data class CoffeeShop(val nombre: String, val direccion: String, @DrawableRes var photo: Int)

val coffeeShops = listOf(
    CoffeeShop("Antico Caffè Greco", "St. Italy, Rome", R.drawable.images),
    CoffeeShop("Coffee Room", "St. Germany, Berlin", R.drawable.images1),
    CoffeeShop("Coffee Ibiza", "St. Colon, Madrid", R.drawable.images2),
    CoffeeShop("Pudding Coffee Shop", "St. Diagonal, Barcelona", R.drawable.images3),
    CoffeeShop("L'Express", "St. Picadilly Circus, London", R.drawable.images4),
    CoffeeShop("Coffee Corner", "St. Àngel Guimerà, Valencia", R.drawable.images5),
    CoffeeShop("Sweet Cup", "St. Kinkerstraat, Amsterdam", R.drawable.images6)
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CoffeeShopsTheme {
                Scaffold(modifier = Modifier.fillMaxSize(), topBar = { TopBar() }) { innerPadding ->
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = "Main",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("Main") { CoffeeMain(navController) }
                        //composable("Comments") { Comments() }
                        composable("Comments/{nombre}") { backStackEntry ->
                            val nombre = backStackEntry.arguments?.getString("nombre")?:""
                            Comments(nombre = nombre)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CoffeeMain(navController: NavController) {
    var starsSelected by remember { mutableStateOf(0.0) }

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(14.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(coffeeShops) { shop ->
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFfbe3e3),
                ),
                modifier = Modifier
                    .clickable{
                        navController.navigate("Comments/${shop.nombre}")
                    }
            ) {
                Column {
                    Image(
                        painter = painterResource(id = shop.photo),
                        contentDescription = "Camera image",
                        modifier = Modifier.fillMaxWidth(),
                        contentScale = ContentScale.Crop
                    )

                    Spacer(modifier = Modifier.size(10.dp))

                    Text(
                        text = shop.nombre,
                        fontFamily = courgetteFontFamily,
                        modifier = Modifier.padding(horizontal = 14.dp)
                    )

                    Spacer(modifier = Modifier.size(10.dp))

                    RatingBar(
                        rating = starsSelected,
                        onItemSelected = { newValue -> starsSelected = newValue })

                    Spacer(modifier = Modifier.size(10.dp))

                    Text(
                        text = shop.direccion,
                        modifier = Modifier.padding(horizontal = 14.dp)
                    )

                    Spacer(modifier = Modifier.size(7.dp))

                    HorizontalDivider(
                        color = Color.Gray,
                        thickness = 1.dp,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.size(7.dp))

                    TextButton(onClick = {}) {
                        Text(
                            text = "RESERVE",
                            color = Color(0xFFe57d90)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun RatingBar(
    modifier: Modifier = Modifier.padding(horizontal = 14.dp),
    rating: Double = 0.0,
    stars: Int = 5,
    starsColor: Color = Color(0xFF888888),
    onItemSelected: (Double) -> Unit
) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 14.dp)) {
        for (i in 1..stars) {
            val icon = if (i <= floor(rating).toInt()) {
                Icons.Filled.Star
            } else {
                Icons.Outlined.Star
            }
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = if (i <= rating) Color(0xFF379665) else starsColor,
                modifier = Modifier
                    .clickable { onItemSelected(i.toDouble()) }
                    .padding(2.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    var expanded by remember { mutableStateOf(false) }
    var option by remember { mutableStateOf("") }

    //val options = listOf<String>("Compartir", "Album")

    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFFf99aaa),
            titleContentColor = Color.White,
        ),
        title = {
            Text("CoffeeShops")
        },
        navigationIcon = {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Menu",
                    tint = Color.White
                )
            }
        },
        actions = {
            IconButton(onClick = { expanded = !expanded }) {
                Icon(
                    imageVector = Icons.Filled.MoreVert,
                    contentDescription = "More options",
                    tint = Color.White
                )
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .background(Color(0xFFfbe3e3))
            ) {
                    DropdownMenuItem(
                        onClick = {
                            option = "Compartir"
                            expanded = false
                        },
                        leadingIcon = { Icon(Icons.Filled.Share, contentDescription = null) },
                        text = { Text("Compartir") }
                    )
                DropdownMenuItem(
                    onClick = {
                        option = "Album"
                        expanded = false
                    },
                    leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = null) },
                    text = { Text("Album") }
                )
            }
        }
    )
}


/*@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CoffeeShopsTheme {
        CoffeeMain()
    }
}*/