package com.example.shopsphere.presentation.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.shopsphere.presentation.component.CategorySection
import com.example.shopsphere.presentation.component.ProductCard
import com.example.shopsphere.presentation.component.SearchBar
import com.example.shopsphere.presentation.navigation.Route
import com.example.shopsphere.presentation.uistate.ProductListUiState
import com.example.shopsphere.presentation.viewmodel.ProductViewModel
import dev.seyfarth.tablericons.TablerIcons
import dev.seyfarth.tablericons.outlined.DeviceLaptop
import dev.seyfarth.tablericons.outlined.Home
import dev.seyfarth.tablericons.outlined.Menu
import dev.seyfarth.tablericons.outlined.Shirt
import dev.seyfarth.tablericons.outlined.Shoe
import dev.seyfarth.tablericons.outlined.ShoppingBag

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(controller: NavController) {


// 1. Light aur standard vibrant colors ki list banayein
    val productColors = listOf(
        Color(0xFFFFEB3B), // Light Yellow
        Color(0xFFFFCDD2), // Light Red / Pinkish
        Color(0xFFC8E6C9), // Light Green
        Color(0xFFBBDEFB), // Light Blue
        Color(0xFFE1BEE7), // Light Purple
        Color(0xFFFFE0B2)  // Light Orange
    )

// 2. .random() use karke list initialize karein
    val CategoryItemList = listOf(
        CategoryItem("Shoes", TablerIcons.Outlined.Shoe, productColors.random()),
        CategoryItem("Laptop", TablerIcons.Outlined.DeviceLaptop, productColors.random()),
        CategoryItem("Clothing", TablerIcons.Outlined.Shirt, productColors.random()),
        CategoryItem("Electronics", TablerIcons.Outlined.DeviceLaptop, productColors.random()),
        CategoryItem("Home", TablerIcons.Outlined.Home, productColors.random()),
    )


    val viewModel: ProductViewModel = hiltViewModel()

    LaunchedEffect(Unit) {
        viewModel.fetchProducts()
    }


    val listUiState by viewModel.uiState.collectAsStateWithLifecycle()











    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp),

        topBar = {

            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "E-Commerce",
                        style = MaterialTheme.typography.titleLarge
                    )
                },

                navigationIcon = {

                    Icon(imageVector = TablerIcons.Outlined.Menu, contentDescription = "Menu")

                },

                actions = {
                    IconButton(
                        onClick = {


                        },

                        ) {

                        Icon(
                            imageVector = TablerIcons.Outlined.ShoppingBag,
                            contentDescription = "Cart",
                        )
                    }
                }

            )
        },


        ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)


        ) {


            SearchBar(modifier = Modifier)
            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Categories",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(start = 16.dp)
            )

            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(vertical = 8.dp)
            ) {


                items(CategoryItemList) { item ->

                    CategorySection(
                        item.title,
                        item.icon,
                        item.iconColor,
                        {}
                    )


                }

            }


            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Featured",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(start = 16.dp)
            )



            Spacer(modifier = Modifier.height(10.dp))


            //

            when (val state = listUiState) {

                is ProductListUiState.Loading -> {

                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {

                        CircularProgressIndicator()
                    }
                }

                is ProductListUiState.Success -> {

                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        modifier = Modifier.fillMaxSize()
                    ) {


                        items(state.products) { item ->

                            ProductCard(item,
                                { controller.navigate(Route.ProductDetailScreen(item.id)) })
                        }
                    }
                }

                is ProductListUiState.Error -> {

                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            Text(state.message)

                            Button(
                                onClick = { viewModel.fetchProducts() }
                            ) {
                                Text("Retry")
                            }
                        }
                    }
                }

                is ProductListUiState.Empty ->{

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("No products found")
                    }


                }


            }
        }


    }
}


data class CategoryItem(

    val title: String,
    val icon: ImageVector,
    val iconColor: Color,

    )