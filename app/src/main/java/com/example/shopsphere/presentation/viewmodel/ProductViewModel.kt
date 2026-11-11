
package com.example.shopsphere.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopsphere.data.repository.ProductRepository
import com.example.shopsphere.presentation.uistate.CategoryDetailUiState
import com.example.shopsphere.presentation.uistate.ProductDetailUiState
import com.example.shopsphere.presentation.uistate.ProductListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val repository: ProductRepository
) : ViewModel() {

    // Home Screen State
    private val _homeProductListUiState =
        MutableStateFlow<ProductListUiState>(ProductListUiState.Loading)

    val homeProductListUiState = _homeProductListUiState.asStateFlow()


    // Product Detail Screen State
    private val _productDetailUiState =
        MutableStateFlow<ProductDetailUiState>(ProductDetailUiState.Loading)

    val productDetailUiState = _productDetailUiState.asStateFlow()

    // CategoryDetailScreen state

    private val _categoryDetailUiState =
        MutableStateFlow<CategoryDetailUiState>(CategoryDetailUiState.Loading)


    val categoryDetailUiState = _categoryDetailUiState.asStateFlow()



    



    // Fetch All Products
    fun fetchProducts() {
        viewModelScope.launch {
            _homeProductListUiState.value = ProductListUiState.Loading

            try {
                val response = repository.getProduct()

                if (response.products.isEmpty()) {
                    _homeProductListUiState.value = ProductListUiState.Empty
                } else {
                    _homeProductListUiState.value =
                        ProductListUiState.Success(response.products)
                }

            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                _homeProductListUiState.value = ProductListUiState.Error(
                    e.localizedMessage ?: "Something went wrong"
                )
            }
        }
    }


    // Fetch Single Product by id
    fun getProductById(id: Int) {
        viewModelScope.launch {
            _productDetailUiState.value = ProductDetailUiState.Loading

            try {
                val product = repository.getProductById(id)

                _productDetailUiState.value =
                    ProductDetailUiState.Success(product)

            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                _productDetailUiState.value = ProductDetailUiState.Error(
                    e.localizedMessage ?: "Something went wrong"
                )
            }
        }
    }

    // Fetch list of product by category

    fun getCategory(category: String){

        viewModelScope.launch {

            _categoryDetailUiState.value = CategoryDetailUiState.Loading


            try {

               val categoryProduct = repository.getProductByCategory(category)

               _categoryDetailUiState.value = CategoryDetailUiState.Success(categoryProduct.products)

            }catch (e: CancellationException){

                throw e
            }catch (e: Exception){

                _categoryDetailUiState.value = CategoryDetailUiState.Error(e.localizedMessage ?: "Somthing went wrong")
            }


        }
    }
}
