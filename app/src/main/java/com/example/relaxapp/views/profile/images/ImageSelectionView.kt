package com.example.relaxapp.views.profile.images


import ImageViewModel
import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.relaxapp.views.RetrofitClientInstance
import com.example.relaxapp.views.login.UserRepository
import com.example.relaxapp.views.profile.images.ImagesData

@Composable
fun ImageSelectionView(
    navController: NavController,
    onImageSelected: (String) -> Unit,
    viewModel: ImageViewModel
) {
    val images by remember { viewModel.imagesState }
    val isLoading by remember { viewModel.isLoading }
    val errorMessage by remember { viewModel.errorMessage }

    // Lanzar la carga de imágenes solo una vez cuando se monta la vista
    LaunchedEffect(Unit) {
        viewModel.fetchImages()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Seleccione una imagen",
            modifier = Modifier.padding(bottom = 8.dp),
            style = MaterialTheme.typography.headlineMedium
        )

        if (isLoading) {
            Text(text = "Cargando imágenes...")
        } else if (errorMessage != null) {
            Text(text = errorMessage ?: "Error desconocido")
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(images) { image ->
                    ImageCard(image = image) {
                        viewModel.updateUserProfile(image.url)
                    }
                }
            }
        }
    }
}


@Composable
fun ImageCard(image: ImagesData, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(8.dp),
        shape = CircleShape,
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = image.url,  // URL de la imagen
                contentDescription = "Image",
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
        }
    }
}