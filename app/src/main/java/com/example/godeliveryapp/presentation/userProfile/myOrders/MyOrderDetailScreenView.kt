package com.example.godeliveryapp.presentation.userProfile.myOrders

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.godeliveryapp.R
import com.example.godeliveryapp.presentation.Dimens.ExtraSmallPadding1
import com.example.godeliveryapp.presentation.Dimens.ExtraSmallPadding2
import com.example.godeliveryapp.presentation.Dimens.ExtraSmallPadding3
import com.example.godeliveryapp.presentation.Dimens.MediumPadding1
import com.example.godeliveryapp.presentation.Dimens.MediumPadding2
import com.example.godeliveryapp.presentation.Dimens.NormalPadding
import com.example.godeliveryapp.presentation.common.CustomBackArrowButton
import com.example.godeliveryapp.presentation.common.CustomLineBreak
import com.example.godeliveryapp.presentation.common.PaymentDetailsCard

@Composable
fun MyOrderDetailScreenView(modifier: Modifier = Modifier, navController: NavController) {

    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = NormalPadding, top = NormalPadding, end = NormalPadding),
            horizontalAlignment = Alignment.Start,
        ) {

            item {
                CustomBackArrowButton(navController = navController)

                Spacer(modifier = Modifier.height(ExtraSmallPadding1))

                Text(
                    text = "Order Details",
                    color = colorResource(id = R.color.black),
                    style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.ExtraBold)
                )

                Spacer(modifier = Modifier.height(MediumPadding2))

                DetailCard(screenHeight = screenHeight, screenWidth = screenWidth)
            }

            item {
                Text(
                    text = "Items",
                    color = colorResource(id = R.color.black),
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )

                Spacer(modifier = Modifier.height(MediumPadding1))
            }

            items(3) {

                ItemCard(screenHeight = screenHeight, screenWidth = screenWidth)

            }

            item {
                CustomLineBreak()
                Text(
                    text = "Bill Details",
                    color = colorResource(id = R.color.black),
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )
                Spacer(modifier = Modifier.height(MediumPadding1))
                PaymentDetailsCard(cartSubTotal = "₹ 599")
            }

            item {

                Spacer(modifier = Modifier.height(NormalPadding))

                Box(
                    modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {

                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Total",
                                color = colorResource(id = R.color.black),
                                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                            )
                            Text(
                                text = "₹ 1060",
                                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                                color = colorResource(id = R.color.black)
                            )
                        }

                        Spacer(modifier = Modifier.height(screenHeight / 18))
                        OutlinedButton(
                            onClick = {
                            },
                            shape = RoundedCornerShape(5.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = colorResource(
                                    id = R.color.black
                                ),
                            ),
                            border = BorderStroke(0.dp, Color.Transparent),
                            modifier = Modifier
                                .height(screenHeight / 14)
                                .fillMaxWidth()
                        ) {

                            Text(
                                "Reorder",
                                color = Color.White,
                                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium)
                            )

                        }

                        Spacer(modifier = Modifier.height(NormalPadding))
                    }
                }
            }
        }


    }


}

@Composable
private fun DetailCard(
    modifier: Modifier = Modifier,
    screenHeight: Dp,
    screenWidth: Dp
) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
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
                        painter = painterResource(id = R.drawable.restaurant2),
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )

                }

                Column(
                    modifier = Modifier
                        .padding(start = ExtraSmallPadding3)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.Start,
                ) {

                    Text(
                        text = "Call me Chow",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.SemiBold
                        ),
                        color = colorResource(
                            id = R.color.black
                        ),
                        maxLines = 2,
                    )

                    Spacer(modifier = Modifier.height(ExtraSmallPadding2))

                    Text(
                        text = "restaurantAddress",
                        style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Normal),
                        color = colorResource(
                            id = R.color.black
                        ),
                        maxLines = 1,
                    )

                }


            }

            CustomLineBreak()

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceBetween
            ) {

                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Order ID",
                        color = colorResource(id = R.color.black),
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold)
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
                            text = "Delivered",
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

                Spacer(modifier = Modifier.height(ExtraSmallPadding1))

                Text(
                    text = "#0834239849238",
                    color = Color.Gray,
                    style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Normal)
                )

                Spacer(modifier = Modifier.height(MediumPadding1))

                Text(
                    text = "Date & Time",
                    color = colorResource(id = R.color.black),
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold)
                )

                Spacer(modifier = Modifier.height(ExtraSmallPadding1))

                Text(
                    text = "13/2/2023, 8:45 PM",
                    color = Color.Gray,
                    style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Normal)
                )

                Spacer(modifier = Modifier.height(MediumPadding1))

                Text(
                    text = "Delivered to",
                    color = colorResource(id = R.color.black),
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold)
                )

                Spacer(modifier = Modifier.height(ExtraSmallPadding1))

                Text(
                    text = "4th Cross Street, 2nd Main Road, Chennai",
                    color = Color.Gray,
                    style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Normal)
                )

                Spacer(modifier = Modifier.height(MediumPadding1))

                Text(
                    text = "Payment Method",
                    color = colorResource(id = R.color.black),
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold)
                )

                Spacer(modifier = Modifier.height(ExtraSmallPadding1))

                Text(
                    text = "Cash on Delivery",
                    color = Color.Gray,
                    style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Normal)
                )

                CustomLineBreak()


            }
        }

    }
}

@Composable
fun ItemCard(modifier: Modifier = Modifier, screenHeight: Dp, screenWidth: Dp) {

    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
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
                        painter = painterResource(id = R.drawable.restaurant3),
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )

                }

                Row(
                    modifier = Modifier
                        .padding(start = ExtraSmallPadding3),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {

                    Text(
                        modifier = Modifier.weight(1f),
                        text = "Lazeez Bhuna Murgh Chicken Dum Biryani",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.SemiBold
                        ),
                        color = colorResource(
                            id = R.color.black
                        ),
                        textAlign = TextAlign.Justify,
                        maxLines = 2,
                    )

                    Text(
                        text = "₹ 599",
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Normal),
                        color = Color.Gray,
                        maxLines = 1,
                    )

                }

            }

            Spacer(modifier = Modifier.height(ExtraSmallPadding3))
        }
    }


}