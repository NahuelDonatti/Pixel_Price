package com.app.pixelprice.ui.screens.commons

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.geometry.Size

@Composable
fun rememberColorPainter(color: Color): Painter {
    return remember(color) {
        object : Painter() {
            override val intrinsicSize: Size
                get() = Size.Unspecified

            override fun DrawScope.onDraw() {
                drawRect(color = color) // DIBUJA EL RECTANGULO, UTILIZADO PARA LAS IMAGENES DE LA TIENDA
            }
        }
    }
}