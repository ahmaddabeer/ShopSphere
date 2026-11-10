package com.example.shopsphere.presentation.uistate

import com.example.shopsphere.data.model.Product

interface ProductDetailUiState {


    data object Loading: ProductDetailUiState

    data class Success(

        val product: Product

    ): ProductDetailUiState


    data class Error(

        val message:String

    ): ProductDetailUiState
}