package com.example.shopsphere.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.shopsphere.presentation.component.ProductCard
import com.example.shopsphere.presentation.uistate.CategoryDetailUiState
import com.example.shopsphere.presentation.viewmodel.ProductViewModel
import dev.seyfarth.tablericons.TablerIcons
import dev.seyfarth.tablericons.outlined.ArrowBack
import dev.seyfarth.tablericons.outlined.ShoppingBag


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryDetailScreen(

    controller: NavController,
    category: String
) {

    var selectedSize by remember { mutableStateOf("M") }

    val viewModel: ProductViewModel = hiltViewModel()

    LaunchedEffect(Unit) {

        viewModel.getCategory(category)
    }

    val categoryDetailUiState by viewModel.categoryDetailUiState.collectAsStateWithLifecycle()







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
                    IconButton(
                        onClick = {
                            controller.popBackStack()
                        }
                    ) {
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


        ) { innerPadding ->


        when (val state = categoryDetailUiState) {

            is CategoryDetailUiState.Loading -> {

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    contentAlignment = Alignment.Center
                ) {


                    CircularProgressIndicator()
                }


            }

            is CategoryDetailUiState.Success -> {

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                ) {

                    items(state.product) {item ->

                        ProductCard(item, {})
                    }


                }
            }


            is CategoryDetailUiState.Error -> {

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

        }


    }

}