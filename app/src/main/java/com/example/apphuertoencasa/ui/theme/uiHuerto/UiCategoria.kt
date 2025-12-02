package com.example.apphuertoencasa.ui.theme.uiHuerto

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.apphuertoencasa.R
import com.example.apphuertoencasa.ui.theme.model.Category
import com.example.apphuertoencasa.ui.theme.viewModel.CategoryUiState
import com.example.apphuertoencasa.ui.theme.viewModel.CategoryViewModel

@Composable
fun Categoria(
    navCtrl: NavHostController,
    viewModel: CategoryViewModel
) {
    val uiState by viewModel.uiState.collectAsState()

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            "Productos",
            modifier = Modifier.padding(start = 10.dp, top = 70.dp),
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(20.dp))
        when (val state = uiState) {

            is CategoryUiState.Loading -> {
                CircularProgressIndicator()
            }

            is CategoryUiState.Error -> {
                Text(
                    text = state.message,
                    color = Color.Red,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(16.dp)
                )
            }

            is CategoryUiState.Success -> {
                CategoriaLista(
                    navCtrl = navCtrl,
                    categories = state.response.categories
                )
            }
        }
    }
}


@Composable
fun CategoriaLista(
    navCtrl: NavHostController,
    categories: List<Category>
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .padding(top = 20.dp, start = 30.dp, end = 30.dp)
                .background(
                    color = Color(0xFFF19737),
                    shape = RoundedCornerShape(6.dp),
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text("CATEGORÍAS", fontWeight = FontWeight.Bold)
            Text(
                "BLOG",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable {
                    navCtrl.navigate("Consejos saludables")
                }
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        categories.forEach { category ->
            val route = when (category.name) {
                "Frutas" -> "Frutas frescas"
                "Verduras" -> "Verduras organicas"
                else -> "Categoria"
            }
            InfoRowCategoria(
                title = category.name,
                description = category.description,
                imageRes = category.imageUrl,
                iconRes = R.drawable.flechaderecha,
                onIconClick = { navCtrl.navigate(route) }
            )
        }
    }
}

@Composable
fun InfoRowCategoria(
    title: String,
    description: String,
    imageRes: String,
    iconRes: Int,
    onIconClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 10.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFF19737)
                        )
                    ) { append("$title: ") }
                    append(description)
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 10.dp)
            )

            AsyncImage(
                model = imageRes,
                contentDescription = null,
                modifier = Modifier
                    .size(150.dp)
                    .clip(RoundedCornerShape(40.dp)),
                contentScale = ContentScale.Crop
            )

            Image(
                painter = painterResource(iconRes),
                contentDescription = "Icono clickeable",
                modifier = Modifier
                    .size(30.dp)
                    .clickable { onIconClick() }
            )
        }
    }
}


