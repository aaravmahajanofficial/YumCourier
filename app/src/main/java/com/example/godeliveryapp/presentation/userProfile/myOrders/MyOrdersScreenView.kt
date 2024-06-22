package com.example.godeliveryapp.presentation.userProfile.myOrders

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.godeliveryapp.R
import com.example.godeliveryapp.presentation.Dimens.ExtraSmallPadding1
import com.example.godeliveryapp.presentation.Dimens.ExtraSmallPadding3
import com.example.godeliveryapp.presentation.Dimens.NormalPadding
import com.example.godeliveryapp.presentation.common.CustomLineBreak
import com.example.godeliveryapp.presentation.navigation.Route
import com.example.godeliveryapp.presentation.userProfile.ProfileScreenViewModel

@Composable
fun MyOrdersScreenView(
    modifier: Modifier = Modifier,
    navController: NavController,
    profileScreenViewModel: ProfileScreenViewModel = hiltViewModel()
) {

    val orders = profileScreenViewModel.orders.collectAsState(initial = listOf()).value
    val isLoading = profileScreenViewModel.isLoading.collectAsState(initial = false).value
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    LaunchedEffect(Unit) {
        profileScreenViewModel.getOrders()
    }

    if (!isLoading) {
        if (orders != null) {
            if (orders.isEmpty()) {

                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(NormalPadding),
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {

                        Box(
                            modifier = Modifier
                                .clickable { navController.navigateUp() }
                                .size(42.dp),
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                                contentDescription = null,
                                tint = colorResource(id = R.color.black),
                                modifier = Modifier.scale(1.2f)
                            )
                        }

                        Spacer(modifier = Modifier.height(screenHeight / 14))

                        Box(
                            modifier = Modifier
                                .height(screenHeight / 3)
                                .fillMaxWidth(),
                            contentAlignment = Alignment.Center,
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.no_orders_found),
                                contentDescription = null,
                                contentScale = ContentScale.Fit,
                                modifier = Modifier.fillMaxSize()
                            )
                        }

                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Text(
                                text = "No orders yet!",
                                color = colorResource(id = R.color.black),
                                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.SemiBold),
                                textAlign = TextAlign.Justify
                            )

                            Spacer(modifier = Modifier.height(ExtraSmallPadding3))

                            Box(modifier = Modifier.width(screenWidth / 2)) {
                                Text(
                                    text = "Explore our menu and get started with your first order.",
                                    color = colorResource(id = R.color.black),
                                    style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Normal),
                                    textAlign = TextAlign.Center
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(screenHeight / 8))

                        TextButton(
                            onClick = { navController.navigate(Route.HomeScreen.route) },
                            border = BorderStroke(0.5.dp, colorResource(id = R.color.primaryColor)),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(screenHeight / 14),
                            shape = RoundedCornerShape(5.dp),
                            colors = ButtonDefaults.textButtonColors(
                                containerColor = colorResource(id = R.color.primaryColor),
                            )
                        ) {

                            Text(
                                text = "Explore menu",
                                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
                                color = colorResource(id = R.color.black)
                            )

                        }

                    }
                }

            } else {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(NormalPadding),
                        horizontalAlignment = Alignment.Start
                    ) {

                        item {

                            Box(
                                modifier = Modifier
                                    .clickable { navController.navigateUp() }
                                    .size(42.dp),
                            ) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                                    contentDescription = null,
                                    tint = colorResource(id = R.color.black),
                                    modifier = Modifier.scale(1.2f)
                                )
                            }

                            Spacer(modifier = Modifier.height(ExtraSmallPadding1))

                            Text(
                                text = "My Orders",
                                color = colorResource(id = R.color.black),
                                style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.ExtraBold)
                            )

                            CustomLineBreak()


                        }


                        items(orders.size) {
                            order ->
                            ItemView(
                                restaurantName = orders[order].restaurantName,
                                restaurantAddress = orders[order].restaurantAddress,
                                restaurantImage = orders[order].restaurantImage,
                                createdAt = orders[order].createdAt,
                                orderStatus = orders[order].orderStatus,
                                totalAmount = orders[order].orderTotal,
                                totalItems = orders[order].totalItems.toString(),
                                screenHeight = screenHeight,
                                screenWidth = screenWidth,
                            )

                        }


                    }


                }
            }
        }
    } else {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = colorResource(id = R.color.primaryColor))
        }
    }

}

@Composable
private fun ItemView(
    restaurantName : String,
    restaurantAddress : String,
    restaurantImage : Int,
    createdAt : String,
    orderStatus : String,
    totalAmount : String,
    totalItems : String,
    screenHeight: Dp,
    screenWidth: Dp
) {
    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .height(screenHeight.div(8))
                        .width(screenWidth.div(4))
                        .background(
                            Color.Transparent,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .clip(
                            RoundedCornerShape(12.dp)
                        ), contentAlignment = Alignment.Center
                ) {

                    Image(
                        painter = painterResource(id = restaurantImage),
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )

                }

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = ExtraSmallPadding3)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.Start,
                ) {

                    Row(
                        modifier = Modifier.fillMaxSize(),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = restaurantName,
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.SemiBold
                            ),
                            color = colorResource(
                                id = R.color.black
                            ),
                            maxLines = 2,
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End,
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Icon(
                                modifier = Modifier.size(screenHeight.div(38)),
                                imageVector = Icons.Rounded.Check,
                                contentDescription = null,
                                tint = colorResource(id = R.color.secondaryColor)
                            )
                            Text(
                                text = orderStatus,
                                style = MaterialTheme.typography.bodySmall.copy(
                                    fontWeight = FontWeight.Medium
                                ),
                                color = colorResource(
                                    id = R.color.secondaryColor
                                ),
                                maxLines = 2,
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(ExtraSmallPadding3))

                    Text(
                        text = restaurantAddress,
                        style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Normal),
                        color = colorResource(
                            id = R.color.black
                        ),
                        maxLines = 1,
                    )

                    Spacer(modifier = Modifier.height(ExtraSmallPadding3))


                    Text(
                        text = createdAt,
                        style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Normal),
                        color = Color.Gray
                    )

                }


            }

            Spacer(modifier = Modifier.height(NormalPadding))

            Row(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "₹ $totalAmount  |  $totalItems items",
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Normal),
                    color = colorResource(id = R.color.black)
                )

                Row(
                    modifier = Modifier,
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "View Details",
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Normal),
                        color = colorResource(id = R.color.black)
                    )

                    Icon(
                        modifier = Modifier
                            .size(screenHeight.div(38))
                            .scale(1.2f),
                        imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowRight,
                        contentDescription = null,
                        tint = colorResource(id = R.color.black)
                    )
                }

            }

            CustomLineBreak()
        }

    }
}