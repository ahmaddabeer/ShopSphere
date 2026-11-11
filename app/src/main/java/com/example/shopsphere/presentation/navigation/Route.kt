package com.example.shopsphere.presentation.navigation

import kotlinx.serialization.Serializable


@Serializable
sealed class Route {


    @Serializable
    object HomeScreen : Route()


    @Serializable
    data class ProductDetailScreen(
        val id: Int

    )


    @Serializable
    data class CategoryDetailScreen(
        val category: String
    )

}