package com.example.films_app.movieList.util

import android.graphics.Bitmap
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.toArgb
import androidx.core.graphics.ColorUtils

@Composable
fun averageColor(image :ImageBitmap) : Color{

    var averageColor  by remember { mutableStateOf(Color.Transparent) }

    LaunchedEffect (Unit){

        // Convert ImageBitmap to Android Bitmap
        val compatibleBitmap =image.asAndroidBitmap()
            .copy(Bitmap.Config.ARGB_8888,false)

        // Ensure the bitmap is not empty
        if (compatibleBitmap.width == 0 || compatibleBitmap.height == 0) {
            return@LaunchedEffect
        }

        // Retrieve the pixels from the compatible Bitmap
        val pixels = IntArray(compatibleBitmap.width * compatibleBitmap.height)

        compatibleBitmap.getPixels(
            pixels , 0 , compatibleBitmap.width ,0 ,0,
            compatibleBitmap.width ,compatibleBitmap.height )

        var redSum = 0
        var greenSum = 0
        var blueSum = 0

        // Calculate the average color
        for (pixel in pixels){
            redSum += android.graphics.Color.red(pixel)
            greenSum += android.graphics.Color.green(pixel)
            blueSum += android.graphics.Color.blue(pixel)
        }

        val pixelCount = pixels.size
        val averageRed = redSum / pixelCount
        val averageGreen = greenSum / pixelCount
        val averageBlue = blueSum / pixelCount

        //set the average color
        averageColor =Color(averageRed , averageGreen , averageBlue)

    }

    // Convert the average color to HSL
    val hsl =FloatArray(3)
    ColorUtils.colorToHSL(averageColor.toArgb() , hsl)

    // Darken the color
     val darkerLightness =hsl[2] - 0.2f

    // create new color with darker lightness
    val darkerColor = ColorUtils.HSLToColor(
        floatArrayOf(
            hsl[0] ,
            hsl[1] ,
            darkerLightness
        )
    )
    return Color(darkerColor)


}