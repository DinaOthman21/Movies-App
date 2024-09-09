package com.example.films_app.movieList.presentation.common


import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.StarHalf
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material.icons.rounded.StarOutline
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
@Composable
fun RatingBar(
    modifier: Modifier = Modifier,
    starsModifier: Modifier = Modifier,
    rating: Double = 0.0,
    stars: Int = 5,
    starsColor: Color = Color.Yellow
) {
    val filledStars = rating.toInt()  // Whole number part of the rating
    val halfStar = rating - filledStars >= 0.5  // Check if there's a half star
    val unfilledStars = stars - filledStars - if (halfStar) 1 else 0  // Adjust for half star

    Row(
        modifier = modifier
    ) {
        // Filled stars
        repeat(filledStars) {
            Icon(
                modifier = starsModifier,
                imageVector = Icons.Rounded.Star,
                contentDescription = "Filled Star",
                tint = starsColor
            )
        }

        // Half star (if any)
        if (halfStar) {
            Icon(
                modifier = starsModifier,
                imageVector = Icons.AutoMirrored.Rounded.StarHalf,
                contentDescription = "Half Star",
                tint = starsColor
            )
        }

        // Unfilled stars
        repeat(unfilledStars) {
            Icon(
                modifier = starsModifier,
                imageVector = Icons.Rounded.StarOutline,
                contentDescription = "Unfilled Star",
                tint = starsColor
            )
        }
    }
}


@Preview(showBackground = true , showSystemUi = true)
@Composable
fun RatingBarPreview(){
    RatingBar()
}