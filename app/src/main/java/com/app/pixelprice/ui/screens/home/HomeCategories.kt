package com.app.pixelprice.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeCategoriesSection(
    onCatalogClick: () -> Unit,
    onWishlistClick: () -> Unit,
    onOffersClick: () -> Unit,
    onSettingsClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            CategoryButton(text = "CATALOGO", onClick = onCatalogClick, modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.width(16.dp))
            CategoryButton(text = "LISTA DE DESEADOS", onClick = onWishlistClick, modifier = Modifier.weight(1f))
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            CategoryButton(text = "OFERTAS", onClick = onOffersClick, modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.width(16.dp))
            CategoryButton(text = "AJUSTES", onClick = onSettingsClick, modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun CategoryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .aspectRatio(1f) // Hace que el botón sea cuadrado
            .clip(RoundedCornerShape(16.dp)) // Esquinas redondeadas
            .background(MaterialTheme.colorScheme.surfaceVariant) // Color de fondo del botón
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            style = MaterialTheme.typography.bodyLarge.copy(fontSize = 14.sp),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(8.dp)
        )
    }
}
