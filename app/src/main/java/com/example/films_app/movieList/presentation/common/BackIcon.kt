package com.example.films_app.movieList.presentation.common


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.films_app.ui.theme.brush


@Composable
fun BackIcon(onBackIconClick:()->Unit) {
    CircleIconBackground(
        imageVector = Icons.AutoMirrored.Default.ArrowBack,
        modifier = Modifier.background( brush, shape = CircleShape),
        contentDescription = "Arrow back"
    ){
        onBackIconClick()
    }
}


@Composable
fun CircleIconBackground(
    imageVector: ImageVector,
    modifier: Modifier = Modifier,
    contentDescription:String = "Icon",
    iconColor: Color = MaterialTheme.colorScheme.primary,
    iconSize:Int = 50,
    onBackIconClick:()->Unit = {}
) {
    Icon(
        imageVector = imageVector,
        contentDescription = contentDescription,
        modifier
            .size(iconSize.dp)
            .padding(5.dp)
            .clickable {
                onBackIconClick()
            },
        tint = iconColor
    )
}