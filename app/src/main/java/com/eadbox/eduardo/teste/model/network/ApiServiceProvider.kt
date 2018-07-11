package com.eadbox.eduardo.teste.model.network

/**
 * Class to provide a singleton ApiService instance
 */
object ApiServiceProvider {

    val apiService = ApiService.create()
}
