package com.example.shopsphere.data.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

data class Product(

 val id:Int,
 val title:String,
 val description:String,
 val price:Double,
 val rating:Double,
 val category:String,
 val thumbnail: String
)