package com.app.pixelprice.ui.screens.commons

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.app.pixelprice.data.Game
import com.app.pixelprice.data.Stores


@Composable
fun GameUIItem(
    game: Game,
    storeDetails: Stores?,
    modifier: Modifier = Modifier,
    onItemClick: (String) -> Unit
) {
    val baseImageURL = "https://www.cheapshark.com/"
    val placeholderColor = Color(0xFFE0E0E0)
    val errorColor = Color(0xFFD32F2F)

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .clickable { onItemClick(game.dealID) },
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // JUEGO Y TITULO
            Row(
                modifier = Modifier.weight(0.55f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    model = game.thumb,
                    contentDescription = "Portada de ${game.gameName}",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(72.dp)
                        .clip(RoundedCornerShape(6.dp)),
                    placeholder = rememberColorPainter(color = placeholderColor),
                    error = rememberColorPainter(color = errorColor)
                )

                Spacer(Modifier.width(12.dp))

                Text(
                    text = game.gameName,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
            }

            // TIENDA
            Row(
                modifier = Modifier.weight(0.20f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                AsyncImage(
                    model = if (storeDetails?.images?.logo?.isNotBlank() == true) baseImageURL + storeDetails.images.logo else null,
                    contentDescription = storeDetails?.storeName,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.size(30.dp),
                    placeholder = rememberColorPainter(color = placeholderColor),
                    error = rememberColorPainter(color = errorColor)
                )

                Spacer(Modifier.width(6.dp))
            }

            // PRECIO
            Column(
                modifier = Modifier.weight(0.25f),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "$${game.salePrice}",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                if (game.normalPrice != game.salePrice && (game.normalPrice.toFloatOrNull()
                        ?: 0f) > 0f
                ) {
                    Text(
                        text = "$${game.normalPrice}",
                        style = MaterialTheme.typography.bodySmall,
                        textDecoration = TextDecoration.LineThrough,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(top = 2.dp)
                    )
                }
            }
        }
    }
}