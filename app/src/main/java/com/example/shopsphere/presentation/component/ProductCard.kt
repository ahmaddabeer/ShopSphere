package com.example.shopsphere.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.shopsphere.data.model.Product


@Composable
fun ProductCard(
    product: Product,
    onClick:()-> Unit
) {



    Box(
        modifier = Modifier
            .width(180.dp)
            .background(
                color = Color.White,
                shape = RoundedCornerShape(16.dp)
            )
            .clickable(onClick = onClick)
    ) {

        Column {

            // Product Image
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .background(
                        color = Color(0xFFF5F5F5),
                        shape = RoundedCornerShape(16.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {

                AsyncImage(
                    model = product.thumbnail,
                    contentDescription = product.title,
                    contentScale = ContentScale.Fit

                )

                // Discount and Timer
               if(product.price.toInt() % 2 == 0){

                   Row(
                       modifier = Modifier
                           .align(Alignment.TopStart)
                           .padding(8.dp)
                   ) {
                       Text(
                           text = "10% OFF",
                           modifier = Modifier
                               .background(
                                   Color(0xFFE85D5D),
                                   RoundedCornerShape(6.dp)
                               )
                               .padding(
                                   horizontal = 8.dp,
                                   vertical = 4.dp
                               ),
                           color = Color.White
                       )
                   }
               }
            }

            // Product Details
            Column(
                modifier = Modifier.padding(10.dp)
            ) {

                Text(
                    text = product.title,
                    color = Color.DarkGray,
                    style = MaterialTheme.typography.titleSmall
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text ="$${product.price}",
                    color = Color.Black,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}
