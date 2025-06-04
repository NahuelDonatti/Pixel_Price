package com.app.pixelprice.ui.screens.commons

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.app.pixelprice.data.GameDetailsResponse
import com.app.pixelprice.data.Stores
import com.app.pixelprice.data.formatReleaseDate


@Composable
fun GameDetailsContent(
    gameDetails: GameDetailsResponse,
    stores: Stores?,
    modifier: Modifier = Modifier
) {
    val baseImageURL = "https://www.cheapshark.com/"
    val gameInfo = gameDetails.gameInfo
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        AsyncImage(
            model = gameInfo.thumb,
            contentDescription = "Portada de ${gameInfo.name}",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .clip(MaterialTheme.shapes.medium)
        )
        Spacer(modifier = Modifier.height(16.dp))

        // TITULO
        Text(
            text = gameInfo.name,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(24.dp))

        // DETALLES
        Column(modifier = Modifier.fillMaxWidth()) {

            // TIENDA
            stores?.let {
                DetailRow(label = "Disponible en:") {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        AsyncImage(
                            model = baseImageURL + it.images.icon, // O .logo
                            contentDescription = "Logo de ${it.storeName}",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(Modifier.width(8.dp))
                        Text(
                            text = it.storeName,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))
            }


            // PRECIOS
            DetailRow(label = "Precio:") {
                Row(verticalAlignment = Alignment.Bottom) {
                    Text(
                        text = "$${gameInfo.salePrice}",
                        style = MaterialTheme.typography.headlineSmall, // PRECIO ACTUAL
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    //PRECIO ANTERIOR TACHADO
                    if (gameInfo.retailPrice != gameInfo.salePrice && (gameInfo.retailPrice.toDoubleOrNull() ?: 0.0) > 0.0) {
                        Spacer(Modifier.width(8.dp))
                        Text(
                            text = "$${gameInfo.retailPrice}",
                            style = MaterialTheme.typography.bodyLarge,
                            textDecoration = TextDecoration.LineThrough,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(12.dp))

            // RELEASE DATE
            formatReleaseDate(gameInfo.releaseDate)?.let { formattedDate ->
                DetailRow(label = "Fecha de Lanzamiento:", value = formattedDate)
                Spacer(modifier = Modifier.height(12.dp))
            }

            // INFORMACION STEAM SI NO ES NULA
            if (!gameInfo.steamAppID.isNullOrBlank() || !gameInfo.steamRatingText.isNullOrBlank()) {
                Text(
                    text = "Información de Steam",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(top = 8.dp, bottom = 4.dp)
                )
                Divider()
                Spacer(modifier = Modifier.height(8.dp))

                gameInfo.steamAppID?.takeIf { it.isNotBlank() }?.let { steamId ->
                    DetailRow(label = "Steam App ID:", value = steamId)
                    Spacer(modifier = Modifier.height(8.dp))
                }
                gameInfo.steamRatingText?.takeIf { it.isNotBlank() }?.let { rating ->
                    DetailRow(label = "Valoración en Steam:", value = rating)
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
        }
        //ToDo
    }
}

@Composable
fun DetailRow(label: String, value: String? = null, content: (@Composable () -> Unit)? = null) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = if (content != null) Alignment.Top else Alignment.CenterVertically
    ) {
        Text(
            text = "$label ",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        if (value != null) {
            Text(
                text = value,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
        content?.invoke()
    }
}