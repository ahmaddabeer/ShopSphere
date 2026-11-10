package com.example.shopsphere.presentation.uistate

import com.example.shopsphere.data.model.Product

interface ProductListUiState {

    data object Loading : ProductListUiState

    data class Success(
        val products: List<Product>

    ) : ProductListUiState

    data object Empty : ProductListUiState

    data class Error(
        val message: String
    ) : ProductListUiState
}