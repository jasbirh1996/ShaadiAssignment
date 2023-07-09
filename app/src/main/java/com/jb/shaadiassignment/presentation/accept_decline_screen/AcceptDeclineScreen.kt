package com.jb.shaadiassignment.presentation.accept_decline_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.DefaultShadowColor
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.jb.shaadiassignment.util.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue


@Composable
fun AcceptDeclineScreen(nav: NavController, viewModel: UserViewModel = hiltViewModel()) {
    val uiState = viewModel.state

    Box(
        modifier = Modifier.fillMaxSize(),

        ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            item {
                Spacer(Modifier.height(20.dp))
                Text(
                    text = "Accept/Decline Screen", style =
                    TextStyle(
                        color = Color.Black,
                    ), fontFamily = FontFamily.Monospace, fontSize = 20.sp
                )
            }
            items(uiState.value.userDetailList) {

                Spacer(Modifier.height(8.dp))
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .padding(20.dp)
                        .height(220.dp),
                    shape = RoundedCornerShape(12.dp),
                    elevation = CardDefaults.cardElevation(12.dp),
                )
                {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.White)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.White),
                            horizontalArrangement = Arrangement.Start
                        ) {
                            AsyncImage(
                                model = it.image, "profile", modifier =
                                Modifier
                                    .padding(top = 10.dp, start = 10.dp)
                                    .size(80.dp)
                                    .clip(CircleShape)

                            )
                            Spacer(Modifier.width(20.dp))
                            Column(modifier = Modifier.padding(10.dp)) {
                                Spacer(Modifier.height(10.dp))
                                Text(text = it.firstName)
                                Text(text = it.age + "yrs")
                                Text(text = it.streetName + "," + it.state)
                                Text(text = it.city)


                            }


                        }
                        Spacer(Modifier.height(12.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {


                            when (it.acceptDeclinedStatus) {
                                true -> {
                                    // show accept button
                                    Button(
                                        modifier = Modifier
                                            .weight(1f)
                                            .padding(start = 10.dp, end = 10.dp),
                                        onClick = {

                                        },
                                        colors = ButtonDefaults.buttonColors(Constants.BUTTON_ACCEPT_COLOR)
                                    ) {
                                        Text(text = "Accept", color = Color.White)


                                    }

                                }

                                false -> {
                                    //show declined button
                                    Button(
                                        modifier = Modifier
                                            .weight(1f)
                                            .padding(start = 10.dp, end = 10.dp),
                                        onClick = {

                                        },
                                        colors = ButtonDefaults.buttonColors(Color.Gray),
                                    ) {
                                        Text(text = "Decline", color = Color.White)
                                    }
                                }

                                null -> {
                                    // show both button
                                    Button(
                                        modifier = Modifier
                                            .weight(1f)
                                            .padding(start = 10.dp, end = 10.dp),
                                        onClick = {
                                            viewModel.updateStatus(false, it.id)

                                        },
                                        colors = ButtonDefaults.buttonColors(Color.Gray),
                                    ) {
                                        Text(text = "Decline", color = Color.White)
                                    }
                                    Button(
                                        modifier = Modifier
                                            .weight(1f)
                                            .padding(start = 10.dp, end = 10.dp),
                                        onClick = {
                                            viewModel.updateStatus(true, it.id)

                                        },
                                        colors = ButtonDefaults.buttonColors(Constants.BUTTON_ACCEPT_COLOR)
                                    ) {
                                        Text(text = "Accept", color = Color.White)


                                    }
                                }
                            }


                        }
                        Spacer(Modifier.height(12.dp))


                    }

                }
                Spacer(Modifier.height(8.dp))

            }


        }
        if (uiState.value.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}