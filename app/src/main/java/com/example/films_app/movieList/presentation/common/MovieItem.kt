package com.example.films_app.movieList.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ImageNotSupported
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.films_app.movieList.domain.Movie
import com.example.films_app.movieList.presentation.navigation.Screen
import com.example.films_app.movieList.util.Constants

@Composable
fun MovieItem(
    movie: Movie ,
    navHostController: NavHostController
){
    val imageState = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(Constants.IMAGE_BASE_URL + movie.backdrop_path)
            .size(Size.ORIGINAL)
            .build()
    ).state


    val defaultColor = MaterialTheme.colorScheme.secondaryContainer
    val dominantColor by remember {
        mutableStateOf(defaultColor)
    }

    Column(
        modifier = Modifier
            .wrapContentHeight()
            .width(200.dp)
            .padding(8.dp)
            .clip(RoundedCornerShape(28.dp))
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.secondaryContainer,
                        dominantColor
                    )
                )
            )
            .clickable {
                navHostController.navigate(Screen.Details.route + "/${movie.id}")
            }
    ) {
        if (imageState is AsyncImagePainter.State.Error){
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(6.dp)
                    .height(250.dp)
                    .clip(RoundedCornerShape(22.dp))
                    .background(MaterialTheme.colorScheme.primaryContainer) ,
                contentAlignment = Alignment.Center
            ){
                Icon(
                    imageVector = Icons.Rounded.ImageNotSupported,
                    contentDescription = movie.title
                )

            }

        }

        if (imageState is AsyncImagePainter.State.Success){
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(6.dp)
                    .height(250.dp)
                    .clip(RoundedCornerShape(22.dp)) ,
                painter = imageState.painter ,
                contentDescription = movie.title,
                contentScale = ContentScale.Crop
            )
        }
        
        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = movie.title,
            modifier = Modifier.padding(start = 26.dp, end = 8.dp) ,
            color = Color.White ,
            fontSize = 15.sp ,
            maxLines = 1
            )

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, bottom = 12.dp, top = 4.dp)
        ){
            RatingBar(
                modifier = Modifier.size(18.dp) ,
                rating = movie.vote_average /2
            )
            Text(
                text = movie.vote_average.toString().take(3),
                modifier = Modifier.padding(start = 4.dp) ,
                color = Color.LightGray ,
                fontSize = 14.sp ,
                maxLines = 1
            )
        }

    }



}
