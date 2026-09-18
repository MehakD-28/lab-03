package com.example.listycity3

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.listycity3.ui.theme.ListyCity3Theme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.FloatingActionButton
import androidx.compose.foundation.layout.fillMaxSize


//this functions helps to display, select, and update the cities.
@Composable
fun CityListScreen(
    cities: List<City>,
    onAddCity: (City) -> Unit,
    onUpdateCity: (City, City) -> Unit,
    modifier: Modifier = Modifier
) {
    //creted the varialbes to store, and enter the fields
    var newCityName by remember { mutableStateOf("") }
    var newProvinceName by remember { mutableStateOf("") }
    // to control the visibility of add options.
    var showAddCityFields by remember { mutableStateOf(false) }
    // variables to hold the changes , can hold city object or null.
    var selectedCity by remember {
        mutableStateOf<City?>(null)
    }


    Column(modifier = modifier.fillMaxSize()) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {

            FloatingActionButton(
                modifier = Modifier.padding(16.dp),
                onClick = {
                    showAddCityFields = !showAddCityFields

                }
            ) {Text("+")
            }

        }
        // show inputs to add the city.
        if (showAddCityFields){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = newCityName,
                onValueChange = { newCityName = it },
                label = { Text("City") },
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(8.dp))
            OutlinedTextField(
                value = newProvinceName,
                onValueChange = { newProvinceName = it },
                label = { Text("Province") },
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(8.dp))
            Button(
                modifier = Modifier.padding(vertical = 12.dp),
                onClick = {
                    if (newCityName.isNotBlank() && newProvinceName.isNotBlank()) {
                        onAddCity(
                            City(
                                name = newCityName,
                                province = newProvinceName
                            )
                        )
                        newCityName = ""
                        newProvinceName = ""
                        showAddCityFields = false
                    }
                }
            ) {
                Text("Add City")
            }
        }}
    LazyColumn(modifier = Modifier.weight(1f)) {
        itemsIndexed(cities) { index, city ->
            CityRow(city = city,
                modifier= Modifier.clickable{
                    //  made it clickable to Save the selected city and fill the fields  with its current information.
                    selectedCity = city
                    newCityName = city.name
                    newProvinceName = city.province
                })
            if (index < cities.lastIndex) {
                HorizontalDivider()
            }
    }}
        // Display the update fields only when selected.
    if (selectedCity != null){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = newCityName,
                onValueChange = { newCityName = it },
                label = { Text("City") },
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(8.dp))

            OutlinedTextField(
                value = newProvinceName,
                onValueChange = { },
                readOnly = true,
                label = { Text("Province") },
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(8.dp))
            Button(
                modifier = Modifier.padding(vertical = 12.dp),
                onClick = {
                    if ( // checking the requirements before adding into the city repo.
                        selectedCity != null &&
                        newCityName.isNotBlank() &&
                        newProvinceName.isNotBlank()
                    ) {

                        onUpdateCity(
                            selectedCity!!,
                            City(
                                name = newCityName,
                                province = newProvinceName
                            )
                        )

                        newCityName = ""
                        newProvinceName = ""
                        selectedCity = null
                    }
                }
            ) {
                Text("Update City")
            }
        }}
}}


//Displays the name and province of a single city in the city list.
@Composable
fun CityRow(city: City,
            modifier: Modifier){
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 16.dp)

    ) {

        Text(
            text = city.name,
            fontSize = 30.sp,
            modifier = Modifier.weight(1f)
        )

        Text(
            text = city.province,
            fontSize = 30.sp,
            modifier = Modifier.weight(1f)
        )
    }
}



