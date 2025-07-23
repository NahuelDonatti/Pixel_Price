package com.app.pixelprice.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.google.accompanist.pager.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeCarouselSection(modifier: Modifier = Modifier) {
    val images = listOf(
        "https://shared.akamai.steamstatic.com/store_item_assets/steam/apps/1903340/eccd7fa054e32bed3213fb92f6089bebd9978215/capsule_616x353.jpg?t=1751536664",
        "https://shared.akamai.steamstatic.com/store_item_assets/steam/apps/1771300/bcad55593058f54e54aee8b6714220e216cf9b0c/capsule_616x353.jpg?t=1749563653",
        "https://shared.fastly.steamstatic.com/store_item_assets/steam/apps/2138720/header.jpg?t=1750327757",
        "https://shared.akamai.steamstatic.com/store_item_assets/steam/apps/1245620/capsule_616x353.jpg?t=1748630546"
    )

    val pagerState = rememberPagerState(initialPage = 0)

    LaunchedEffect(Unit) {
        while (true) {
            yield()
            delay(3000)
            pagerState.animateScrollToPage(
                page = (pagerState.currentPage + 1) % pagerState.pageCount
            )
        }
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp)
    ) {
        HorizontalPager(
            count = images.size,
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .clip(MaterialTheme.shapes.medium)
        ) { page ->
            Image(
                painter = rememberImagePainter(images[page]),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 8.dp),
            activeColor = MaterialTheme.colorScheme.primary,
            inactiveColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f),
            indicatorShape = CircleShape,
            indicatorWidth = 8.dp,
            indicatorHeight = 8.dp,
            spacing = 4.dp
        )
    }
}