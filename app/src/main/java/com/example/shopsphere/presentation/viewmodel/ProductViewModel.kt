package com.example.shopsphere.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopsphere.data.model.Product
import com.example.shopsphere.data.repository.ProductRepository
import com.example.shopsphere.presentation.uistate.ProductDetailUiState
import com.example.shopsphere.presentation.uistate.ProductListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val repository: ProductRepository

) : ViewModel() {


    private val _uiState =
        MutableStateFlow<ProductListUiState>(ProductListUiState.Loading)


    private val _uiStateDetail =
        MutableStateFlow<ProductDetailUiState>(ProductDetailUiState.Loading)


    val uiStateDetail = _uiStateDetail.asStateFlow()
    val uiState = _uiState.asStateFlow()




    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error


    fun getProductById(id: Int) {

        viewModelScope.launch {

            _uiStateDetail.value = ProductDetailUiState.Loading

            try {

                val product = repository.getProductById(id)

                _uiStateDetail.value = ProductDetailUiState.Success(product)
            }catch (e: CancellationException){

                throw e
            }catch(e: Exception){

                _uiStateDetail.value = ProductDetailUiState.Error(
                    e.localizedMessage ?: "Somthing went wrong"
                )
            }
        }
    }

    fun fetchProducts() {

        viewModelScope.launch {


            _uiState.value = ProductListUiState.Loading

            try {

                val response = repository.getProduct()

                if (response.products.isEmpty()) {

                    _uiState.value = ProductListUiState.Empty
                } else {

                    _uiState.value = ProductListUiState.Success(response.products)
                }
            } catch (e: Exception) {

                _uiState.value = ProductListUiState.Error(
                    message = e.localizedMessage
                        ?: "Somthing went wrong"
                )
            }
        }


    }


}