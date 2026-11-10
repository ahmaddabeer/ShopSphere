package com.example.shopsphere.presentation.screen.detail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.shopsphere.R
import dev.seyfarth.tablericons.TablerIcons
import dev.seyfarth.tablericons.outlined.ArrowBack
import dev.seyfarth.tablericons.outlined.ShoppingBag

private val Green = Color(0xFF16855A)
private val LightBackground = Color(0xFFF5F7F5)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailScreen(controller: NavController,id:Int) {

    var selectedSize by remember { mutableStateOf("M") }



    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Product Detail",
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = TablerIcons.Outlined.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = TablerIcons.Outlined.ShoppingBag,
                            contentDescription = "Cart"
                        )
                    }
                }
            )
        },
        bottomBar = {
            Button(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp)
                    .height(52.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Green
                )
            ) {
                Text(
                    text = "Add to Cart",
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
                .padding(top = 8.dp)
        ) {

            // Product Image
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(230.dp)
                    .background(
                        LightBackground,
                        RoundedCornerShape(16.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(
                        R.drawable.ic_launcher_background
                    ),
                    contentDescription = "Product Image",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Fit
                )

                Text(
                    text = "20% OFF",
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(12.dp)
                        .background(
                            Color(0xFFE85D5D),
                            RoundedCornerShape(6.dp)
                        )
                        .padding(
                            horizontal = 10.dp,
                            vertical = 5.dp
                        ),
                    color = Color.White,
                    style = MaterialTheme.typography.labelMedium
                )
            }

            // Image Indicators
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(3) { index ->
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 4.dp)
                            .size(if (index == 0) 9.dp else 7.dp)
                            .background(
                                if (index == 0) Green else Color.LightGray,
                                RoundedCornerShape(50)
                            )
                    )
                }
            }

            // Product Name
            Text(
                text = "Yellow Sweatshirt",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Price
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = "$39.99",
                    style = MaterialTheme.typography.titleLarge,
                    color = Green,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "$49.99",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray,
                    textDecoration = TextDecoration.LineThrough
                )

                Text(
                    text = "20% OFF",
                    color = Green,
                    style = MaterialTheme.typography.labelMedium
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Rating
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "★ 4.5",
                    color = Color(0xFFE6A12B),
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = "(120 Reviews)",
                    color = Color.Gray,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            HorizontalDivider()

            Spacer(modifier = Modifier.height(12.dp))

            // Description
            Text(
                text = "Description",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Comfortable and stylish yellow sweatshirt made with premium quality fabric for everyday wear.",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Size Selection
            Text(
                text = "Select Size",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                listOf("S", "M", "L", "XL").forEach { size ->

                    val isSelected = selectedSize == size

                    OutlinedButton(
                        onClick = {
                            selectedSize = size
                        },
                        modifier = Modifier.size(48.dp),
                        shape = RoundedCornerShape(10.dp),
                        contentPadding = PaddingValues(0.dp),
                        border = BorderStroke(
                            1.dp,
                            if (isSelected) Green else Color.LightGray
                        ),
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = if (isSelected)
                                Green.copy(alpha = 0.1f)
                            else Color.Transparent
                        )
                    ) {
                        Text(
                            text = size,
                            color = if (isSelected) Green else Color.DarkGray,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Color Selection
            Text(
                text = "Select Color",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                listOf(
                    Color(0xFFFFD54F),
                    Color.DarkGray,
                    Color(0xFFEF9A9A),
                    Color(0xFF90CAF9)
                ).forEach { color ->

                    Box(
                        modifier = Modifier
                            .size(30.dp)
                            .background(color, RoundedCornerShape(50))
                    )
                }
            }
        }
    }
}
