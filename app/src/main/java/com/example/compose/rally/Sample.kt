package com.example.compose.rally

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.rally.ui.theme.RallyTheme


@Preview
@Composable
fun HelloScreenPre() {
    RallyTheme {
        HelloScreen()
    }

}


@Composable
fun HelloScreen() {
    //③ 状态提升到组合函数之外，提高组合函数的复用率
    var name by remember { mutableStateOf("") }
    HelloContent(name = name, valueChange = { name = it })
}

@Composable
fun HelloContent(name: String, valueChange: (String) -> Unit) {
    Column(modifier = Modifier.padding(16.dp)) {
// ①   var name by remember { mutableStateOf("") }
        Text(
            text = "Hello",
            modifier = Modifier.padding(bottom = 8.dp),
            style = MaterialTheme.typography.h5
        )
        OutlinedTextField(
            value = name,
       // ② 监听文字变化,将变化文字回调到组合函数之外
            onValueChange = valueChange,
            label = { Text("Name") }
        )
    }
}
