package com.example.listycity3

import androidx.compose.runtime.mutableStateListOf
class CityRepository {
    private val _cities = mutableStateListOf(
        City("Edmonton", "AB"),
        City("Vancouver", "BC"),
        City("Toronto", "ON")
    )

    val cities: List<City>
        get() = _cities

    fun addCity(city: City){
        _cities.add(city)
    }

    //Updates an existing city in the city list.

    fun updateCity(oldCity: City, newCity: City) {
        val index = _cities.indexOf(oldCity)
        // Replace the city only if it exists in the list.
        if (index != -1) {
            _cities[index] = newCity
        }
    }

}