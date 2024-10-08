package com.example.films_app.movieList.presentation.movies.details


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ImageNotSupported
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.films_app.R
import com.example.films_app.movieList.presentation.common.BackIcon
import com.example.films_app.movieList.presentation.common.RatingBar
import com.example.films_app.movieList.util.Constants


@Composable
fun DetailsScreen(
    navController: NavHostController,
    navBackStackEntry: NavBackStackEntry
){

    val detailsViewModel= hiltViewModel<DetailsScreenViewModel>()
    val detailsState =detailsViewModel.detailsState.collectAsState().value

    val movieId = navBackStackEntry.arguments?.getInt("movieId") ?: -1
    LaunchedEffect(movieId) {
        detailsViewModel.getMovie(movieId)
    }

    val posterImageState = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(Constants.IMAGE_BASE_URL + detailsState.movie?.poster_path)
            .size(Size.ORIGINAL)
            .build()
    ).state

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        Spacer(modifier = Modifier.height(30.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 12.dp,
                    end = 12.dp
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BackIcon{ navController.popBackStack() }
        }

       Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ){
            Box(
                modifier = Modifier
                    .width(160.dp)
                    .height(240.dp)
            ) {

                if (posterImageState is AsyncImagePainter.State.Error){
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(12.dp))
                            .background(MaterialTheme.colorScheme.onSecondary) ,
                        contentAlignment = Alignment.Center
                    ){
                        Icon(
                            imageVector = Icons.Rounded.ImageNotSupported,
                            contentDescription = detailsState.movie?.title ,
                            modifier = Modifier.size(70.dp)
                        )
                    }
                }

                if (posterImageState is AsyncImagePainter.State.Success){
                    Image(
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(12.dp)) ,
                        painter = posterImageState.painter ,
                        contentDescription = detailsState.movie?.title,
                        contentScale = ContentScale.Crop
                    )
                }
            }

                detailsState.movie?.let { movie->
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            modifier = Modifier.padding(start = 16.dp),
                            text = movie.title ,
                            fontSize = 19.sp ,
                            fontWeight = FontWeight.SemiBold
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Row (
                            modifier = Modifier
                                .padding(start = 16.dp)
                        ){
                            RatingBar(
                                starsModifier = Modifier.size(18.dp) ,
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

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            modifier = Modifier.padding(start = 16.dp),
                            text = stringResource(R.string.language) + " " + movie.original_language ,
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            modifier = Modifier.padding(start = 16.dp),
                            text = stringResource(R.string.releaseDate) + " " + movie.release_date ,
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            modifier = Modifier.padding(start = 16.dp),
                            text = movie.vote_count.toString() + " " + stringResource(R.string.votes)  ,
                        )

                }


            }

        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            modifier = Modifier.padding(start = 16.dp),
            text = stringResource(R.string.overview) ,
            fontSize = 19.sp ,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(8.dp))

        detailsState.movie?.let { movie->
            Text(
                modifier = Modifier.padding(start = 16.dp),
                text = movie.overview ,
                fontSize = 19.sp
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

    }


}
