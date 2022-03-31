package com.example.compose.rally.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.rally.ui.theme.RallyDialogThemeOverlay

@Preview
@Composable
fun DialogPre() {
    RallyAlertDialog(bodyText = "12212", buttonText = "121212",{})
}
@Composable
fun RallyAlertDialog(bodyText: String, buttonText: String, onDismiss: () -> Unit) {
    RallyDialogThemeOverlay {
        AlertDialog(onDismissRequest = onDismiss,
            title = { Text(text = bodyText) },
            buttons = {
                Column {
                    Divider(
                        Modifier.padding(horizontal = 12.dp),
                        color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
                    )
                    TextButton(
                        onClick = onDismiss,
                        shape = RectangleShape,
                        contentPadding = PaddingValues(16.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = buttonText)
                    }
                }
            })
    }
}