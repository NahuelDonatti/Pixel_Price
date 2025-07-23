package com.app.pixelprice.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.carousel.CarouselState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.currentComposer
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.app.pixelprice.ui.screens.Screens
import com.google.accompanist.pager.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield


data class CarouselItem(
    val imageUrl: String,
    val dealId: String
)

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeCarouselSection(
    navController: NavController,
    modifier: Modifier = Modifier) {
    val carouselItems = listOf(
        CarouselItem("https://shared.akamai.steamstatic.com/store_item_assets/steam/apps/1903340/eccd7fa054e32bed3213fb92f6089bebd9978215/capsule_616x353.jpg?t=1751536664", "F%2F38xmumeYMs9bo2%2B2SXb0zzP1QXPbASvOMKoSXhCrE%3D"),
        CarouselItem("https://shared.akamai.steamstatic.com/store_item_assets/steam/apps/1771300/bcad55593058f54e54aee8b6714220e216cf9b0c/capsule_616x353.jpg?t=1749563653", "GilH2Q7ji6qDQ7896p3k746JHOr2cRkbNyxY3fWLI%2FQ%3D"),
        CarouselItem("https://shared.fastly.steamstatic.com/store_item_assets/steam/apps/2138720/header.jpg?t=1750327757", "BvVJbZyaNaPgEl6nce5CRt21r0K9yCp2YPu2lt6e7aI%3D"),
        CarouselItem("https://shared.akamai.steamstatic.com/store_item_assets/steam/apps/1245620/capsule_616x353.jpg?t=1748630546", "NUAl9SOFl4KOLt0Y1XBLssw04hFw93tIbj19hMZfV94%3D")
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
            count = carouselItems.size,
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .clip(MaterialTheme.shapes.medium)
        ) { page ->
            val currentItem = carouselItems[page]
            Image(
                painter = rememberImagePainter(currentItem.imageUrl),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize().clickable{
                    navController.navigate(Screens.GameDetail.route+"/${currentItem.dealId}")
                }
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