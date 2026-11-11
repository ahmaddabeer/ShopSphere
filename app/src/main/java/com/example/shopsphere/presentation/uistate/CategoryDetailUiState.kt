package com.example.shopsphere.presentation.uistate

import com.example.shopsphere.data.model.Product

sealed interface CategoryDetailUiState {


    data object Loading: CategoryDetailUiState


    data class Success(
        val product:List<Product>
    ): CategoryDetailUiState

    data class Error(

        val message:String

    ): CategoryDetailUiState
}