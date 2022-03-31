package com.example.compose.rally

import android.os.Parcelable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.mapSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.rally.ui.theme.RallyTheme
import kotlinx.parcelize.Parcelize


/**
 * author：yangweichao@reworldgame.com
 * data: 2022/3/22 17:09
 * 在 Compose 中恢复状态
 */
@Parcelize
data class City(val name: String, val country: String) : Parcelable


val citySaver = run {
    val nameKey = "Name"
    val countryKey = "Country"
    mapSaver(
        save = { mapOf(nameKey to it.name, countryKey to it.country) },
        restore = { City(it[nameKey] as String, it[countryKey] as String) }
    )
}

val listSaver = listSaver<City, Any>(
    save = { listOf(it.name, it.country) },
    restore = { City(it[0] as String, it[1] as String) }
)


@Preview
@Composable
fun CityScreenPre2() {
    RallyTheme {
        CityScreen()
    }

}

@Composable
fun CityScreen() {
    var selectedCity = rememberSaveable {
        mutableStateOf(City("故宫", "北京"))
    }
    CityContent(
        selectedCity.component1().name,
        selectedCity.component1().country
    ) { selectedCity.value = it }


}

@Composable
fun CityScreen2() {

//    var selectedCity = rememberSaveable(stateSaver = citySaver) {
//        mutableStateOf(City("博物馆", "郑州"))
//    }
//
//    var listCity = rememberSaveable(stateSaver = listSaver) {
//        mutableStateOf(City("黄河", "郑州"))
//    }


//    CityContent(
//        selectedCity.component1().name,
//        selectedCity.component1().country
//    ) { selectedCity.value = it }


}

@Composable
fun CityContent(name: String, country: String, valueChange: (City) -> Unit) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "$country",
            modifier = Modifier.padding(bottom = 8.dp),
            style = MaterialTheme.typography.h5
        )
        OutlinedTextField(
            value = "$name _ $country",
            onValueChange = {
                valueChange(
                    City(
                        name = it.split("_")[0],
                        country = it.split("_")[1]
                    )
                )
            },
            label = { Text("Name") }
        )
    }
}
